import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.io.Serializable;

public class Cell extends JLabel implements Serializable
{
	private int price;
	private Player owner;
	private int houses; // 0 nothing // 1-2-3-4 houses // 5 hotel
	private int type;
	private String description;
	private boolean canBeBought;
	private boolean canBeBuilt;
	private boolean isHighlighted;
	private boolean isMortgaged;
	private boolean isSelected;
	private ImageIcon icon;
	private int id;
	private Board parent;
	private Vector<Player> playersOnIt;
	
	public Cell(Board parent,int price, int type, String iconpath, String description,int id)
	{	
		this.houses=0;
		this.parent=parent;
		playersOnIt = new Vector<Player>();
		setHighlighted(false);
		this.type = type;
		this.price = price;
		this.description = description;
		this.id=id;
		if (type >= 1 && type <= 8)
			canBeBuilt = true;
		else
			canBeBuilt = false;
		if ((type >= 1 && type <= 8) || (type >= 24 && type <= 27) || type==12 || type==13) //  is a train or is a utility
			canBeBought = true;
		else
			canBeBought = false;
		icon = new ImageIcon(iconpath);

		this.setIcon(icon);
		this.setSize(getIcon().getIconWidth(), getIcon().getIconHeight());
		this.setOpaque(true);
	}
	public int getHouses()
	{
		return houses;
	}
	public void setHouses(int houses)
	{
		this.houses = houses;
	}
	public int getId()
	{
		return id;
	}
	public int getPrice()
	{
		return price;
	}

	public Player getOwner()
	{
		return owner;
	}

	public void setType(int type)
	{
		this.type = type;
	}
	public void setMortgaged(boolean bool){
		this.isMortgaged=bool;
		
		if (bool){
			//this.getParent().highlighted=null;
		//	this.setHighlighted(false);
		}
		repaint();
	}
	
	public boolean isMortgaged(){
		return isMortgaged;
	}
	
	public void setOwner(Player owner)
	{
		this.owner = owner;
	}

	public boolean canBeBought()
	{
		return owner==null && canBeBought;
	}
	public boolean canBeBuilt(){
		return canBeBuilt;
	}
	
	public int getHousePrice(){
		return 100;
	}
	public int getHotelPrice(){
		return 200;
	}

	public int getRent()
	{
		if (this.getOwner()==null) // it doesn't make sense to getRent for something with no owner...
			return 0;
		if (this.getType()>=1 && this.getType()<=8)
			return (int)(this.getPrice() * 0.20 + getHouses() * getHousePrice() * 0.90);
		else if(this.getType()==12 || this.getType()==13){
			if(this.getOwner().getUtiliesCount()==1){
				return 4*(parent.dice1+parent.dice2);
			}
			else if(this.getOwner().getUtiliesCount()==2){
				return 10*(parent.dice1+parent.dice2);
			}
			else
				return 0;
		}
		else if(this.getType()>=24 || this.getType()<=27){
			if(this.getOwner().getRailRoadCount()==1){
				return 25;
			}
			else if(this.getOwner().getRailRoadCount()==2){
				return 50;
			}
			else if(this.getOwner().getRailRoadCount()==3){
				return 100;
			}
			else if(this.getOwner().getRailRoadCount()==4){
				return 200;
			}
			return 0;
		}
		else return 0;
	}

	public int getMortgage()
	{
		if (this.getHouses()<5)
			return (this.getPrice()+this.getHouses()*this.getHousePrice())/2;
		else if(this.getHouses()==5)
			return (this.getPrice()+4*this.getHousePrice()+this.getHotelPrice())/2;
		else return 0;
	}

	public String getTypeName()
	{
		return whatTypeIs(this.type);
	}
	public Board getParent() {
		return parent;
	}
	//after init of vector
	public Cell getNextCell(){
			for (Cell cell : this.getParent().getCells()){
				if (this.id==9)
					return this.getParent().getCell(11);
				if (this.id==19)
					return this.getParent().getCell(21);
				if (this.id==29)
					return this.getParent().getCell(31);
				if (this.id==39)
					return this.getParent().getCell(1);	
				return this.getParent().getCell(this.id+1);
			}
		return null;
	}
	public Cell getPreviousCell(){
		for (Cell cell : this.getParent().getCells()){
			if (this.id==1)
				return this.getParent().getCell(39);
			if (this.id==11)
				return this.getParent().getCell(9);
			if (this.id==21)
				return this.getParent().getCell(19);
			if (this.id==31)
				return this.getParent().getCell(29);	
			return this.getParent().getCell(this.id-1);
		}
		return null;
	}
	//after init of vecgtor
	public static String whatTypeIs(int type)
	{
		switch (type)
		{
		case 1:
			return "red";
		case 2:
			return "yellow";
		case 3:
			return "green";
		case 4:
			return "blue";
		case 5:
			return "purple";
		case 6:
			return "aquamarine";
		case 7:
			return "violet";
		case 8:
			return "orange";
	
			
			
		case 12:
			return "waterworks";
		case 13:
			return "electriccompany";
		case 14:
			return "start";
		case 15:
			return "gotojail";
		case 16:
			return "getmoney";
		case 17:
			return "jail";
		case 18:
			return "chance1";
		case 19:
			return "chance2";
		case 20:
			return "chance3";
		case 21:
			return "communitychest1";
		case 22:
			return "communitychest2";
		case 23:
			return "communitychest3";	
		case 24:
			return "railroad1";
		case 25:
			return "railroad2";
		case 26:
			return "railroad3";	
		case 27:
			return "railroad4";
		case 28:
			return "luxurytax";
		case 29:
			return "incometax";	
		default:
			return "visit only";
		}
	}

	public void paint(Graphics g) //override
	
	{
		super.paint(g);
		g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
		drawHousesAndOwnerOnCell(g); //owner and houses paint
		drawMortGaged(g);
		drawPlayersOnCell(g); //player paint
		drawHighlighedAndSelectedOnCell(g);	//highlighted
	}	

	private void drawMortGaged(Graphics gold){
		Graphics2D g = (Graphics2D)gold;
		if (isMortgaged){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(0.4f,0.0f,0.0f));
			g.setStroke(new BasicStroke(2));
			if (getType()==1 || getType()==2 || getType()==24 ||  getType()==28)
				g.drawLine(0,10,17,38);
			else if (getType()==3 || getType()==4 || getType()==25)
				g.drawLine(0,0,28,17);
			else if (getType()==5 || getType()==6 ||  getType()==26)
				g.drawLine(0,0,17,28);
			else if (getType()==7 || getType()==8 ||  getType()==27 ||  getType()==29)
				g.drawLine(10,0,38,17);
			g.setStroke(new BasicStroke(1));
		}
	}
	
	private void drawHighlighedAndSelectedOnCell(Graphics g) {
		if (isSelected){
			g.setColor(Color.BLUE);
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);				

		}
		else if (isHighlighted && !isSelected)
		{
				g.setColor(Color.YELLOW);
				g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}
	private void drawHousesAndOwnerOnCell(Graphics g){
	
		if (this.getOwner()!=null){
			g.setColor(this.getOwner().getPlayerColor());
			if (houses>=0){
				if (this.getId()>0 && this.getId()<10){
					g.fillRect(0, 5, 17, 3);
				}
				else if (this.getId()>10 && this.getId()<20){
					g.fillRect(30, 0, 3, 17);
				}
				else if (this.getId()>20 && this.getId()<30){
					g.fillRect(0, 30, 17, 3);
				}
				else if (this.getId()>30 && this.getId()<40){
					g.fillRect(5, 0, 3, 17);
				}
			}
			if (houses==5){
				g.setColor(new Color(0.8f,0.0f,0.0f));
				if (this.getId()>0 && this.getId()<10){
					g.drawLine(6, 0, 10, 0);
					g.drawLine(3, 1, 13, 1);
					g.drawLine(0, 2, 16, 2);
				}
				else if (this.getId()>10 && this.getId()<20){
					g.drawLine(37, 6,37, 10);
					g.drawLine(36, 3,36, 13);
					g.drawLine(35, 0,35, 16);
				}
				else if (this.getId()>20 && this.getId()<30){
					g.drawLine(6, 37, 10, 37);
					g.drawLine(3, 36, 13, 36);
					g.drawLine(0, 35, 16, 35);
				}
				else if (this.getId()>30 && this.getId()<40){
					g.drawLine(0, 6, 0, 10);
					g.drawLine(1, 3, 1, 13);
					g.drawLine(2, 0, 2, 16);
				}
			}
			else {
				
				if (houses>=1){
					g.setColor(Color.GREEN);
					if (this.getId()>0 && this.getId()<10){	
						g.fillRect(1, 0, 3, 3);
					}
					else if (this.getId()>10 && this.getId()<20){
						g.fillRect(35, 1, 3, 3);
					}
					else if (this.getId()>20 && this.getId()<30){
						g.fillRect(1, 35, 3, 3);
					}
					else if (this.getId()>30 && this.getId()<40){
						g.fillRect(0, 1, 3,	3);
					}
				}
				if (houses>=2){
					g.setColor(Color.GREEN);
					if (this.getId()>0 && this.getId()<10){	
						g.fillRect(5, 0, 3, 3);
					}
					else if (this.getId()>10 && this.getId()<20){
						g.fillRect(35, 5, 3, 3);
					}
					else if (this.getId()>20 && this.getId()<30){
						g.fillRect(5, 35, 3, 3);
					}
					else if (this.getId()>30 && this.getId()<40){
						g.fillRect(0, 5, 3,	3);
					}
				}
				if (houses>=3){
					g.setColor(Color.GREEN);
					if (this.getId()>0 && this.getId()<10){	
						g.fillRect(9, 0, 3, 3);
					}
					else if (this.getId()>10 && this.getId()<20){
						g.fillRect(35, 9, 3, 3);
					}
					else if (this.getId()>20 && this.getId()<30){
						g.fillRect(9, 35, 3, 3);
					}
					else if (this.getId()>30 && this.getId()<40){
						g.fillRect(0, 9, 3,	3);
					}
				}
				if (houses>=4){
					g.setColor(Color.GREEN);
					if (this.getId()>0 && this.getId()<10){	
						g.fillRect(13, 0, 3, 3);
					}
					else if (this.getId()>10 && this.getId()<20){
						g.fillRect(35, 13, 3, 3);
					}
					else if (this.getId()>20 && this.getId()<30){
						g.fillRect(13, 35, 3, 3);
					}
					else if (this.getId()>30 && this.getId()<40){
						g.fillRect(0, 13, 3,3);
					}
				}
			}
		}
	}
	private void drawPlayersOnCell(Graphics gold)
	{
		Graphics2D g = (Graphics2D) gold;
		g.setColor(Color.BLACK);
		if (playersOnIt.size()>=1){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(playersOnIt.get(0).getPlayerColor());
			g.fillOval(this.getWidth()/2-6, this.getHeight()/2-6, 12, 12);
			g.setColor(Color.BLACK);
			g.drawOval(this.getWidth()/2-6, this.getHeight()/2-6, 12, 12);
	  }
		g.setColor(Color.BLACK);
	  if (playersOnIt.size()==2){
			int offsetx=0;
			int offsety=0;
			if (id==0){
				offsetx=3;
				offsety=3;
			}
			else if(id>0 && id<10){
				offsety=4;
			}
			else if (id==10){
				offsetx=-3;
				offsety=3;
			}
			else if(id>10 && id<20){
				offsetx=-4;
			}
			else if (id==20){
				offsetx=-3;
				offsety=-3;
			}
			else if(id>20 && id<30){
				offsety=-4;
			}
			else if (id==30){
				offsetx=3;
				offsety=-3;
			}
			else if(id>30 && id<40){
				offsetx=4;
			}
		
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(playersOnIt.get(1).getPlayerColor());
			g.fillOval(this.getWidth()/2-6+offsetx, this.getHeight()/2-6+offsety, 12, 12);
			g.setColor(Color.BLACK);
			g.drawOval(this.getWidth()/2-6+offsetx, this.getHeight()/2-6+offsety, 12, 12);
	  	}
	}
	
	
	public void setHighlighted(boolean bool)
	{
		if (bool)
			getParent().highlighted=this;
		isHighlighted = bool;
		repaint();
		
	}
	public boolean isHighlighted(){
		return isHighlighted;
	}
	public boolean isSelected(){
		return isSelected;
	}
	
	
	public void setSelected(boolean bool)
	{
		getParent().selected=this;
		isSelected = bool;
	}
	
	public void addPlayer(Player pl){
		playersOnIt.add(pl);
		pl.putOnCell(this);
		special(pl,parent.dice1,parent.dice2);
		
	}
	public void removePlayer(Player pl)
	{
		for(int i=0; i<playersOnIt.size(); i++)	{
			if (playersOnIt.get(i).equals(pl)){
					playersOnIt.remove(pl);
				
			}
		}
	}
	public int getType(){
		return type;
	}
	public String getDescription(){
		return description;
	}
	public void special(Player p,int dice1,int dice2) {
		if (p.getOnCell().getId()>=19 && p.getOnCell().getId()!=30) {//get money cell & not in jail
			p.setMoney(p.getMoney()+200);
			((Monopoli)this.getParent().getParent()).addToHistory("Go!!! You earn 200");
		}
		else if (type==28){// luxury tax
			((Monopoli)this.getParent().getParent()).addToHistory("You've just paid the luxorious tax of 100");
			p.setMoney(p.getMoney()-100);
		}
		else if (type==29){ // income tax;
			((Monopoli)this.getParent().getParent()).addToHistory("You've just paid the income tax of 200");
			p.setMoney(p.getMoney()-200);
		}
		else if (type==15){ // go to jail
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.movePlayerTo(((Board)this.getParent()).getCell(30));
			((Monopoli)this.getParent().getParent()).addToHistory("Go to the Jail Player "+p.getName()+"!");
		}
		else if (type>=18 && type<=20) { //chance
			
			Chance chance=((Monopoli)(parent.getParent())).ChanceInit();
			((Monopoli)this.getParent().getParent()).addToHistory(p.getName()+" collected chance card No."+chance.getId()+"!");
			chance.handle(p);
		}
		else if (type>=21 && type<=23) { //chance
			CommunityChest communitychest=((Monopoli)(parent.getParent())).CommunityChestInit();
			((Monopoli)this.getParent().getParent()).addToHistory(p.getName()+" collected community chest card No."+communitychest.getId()+"!");
			communitychest.handle(p);
			
		}
		else if (type==12 || type==13){} //utility 
	}	
}
