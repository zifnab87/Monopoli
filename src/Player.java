import java.awt.Color;
import java.io.Serializable;
import java.util.Vector;

public class Player implements Serializable
{
	int money;
	private int hotels;
	private int houses;
	private int mortgages;
	private Cell onCell; 
	private int id;
	private boolean paidRent;
	private boolean isInJail;
	public static final int INITMONEY = 100000;
	private Vector<Cell> owns;
	private Color playerColor;
	private String name;
	private Monopoli parent;
	public Player(Monopoli parent,int id)
	{
		this.parent=parent;
		this.id=id;
		hotels=0;
		houses=0;
		mortgages=0;
		owns = new Vector<Cell>();
		money = INITMONEY;
	}

	public int getId(){
		return this.id;
	}
	public void setInJail(boolean bool) {
		isInJail=bool;
	}
	public boolean isInJail(){
		return isInJail;
	}
	
	public Vector<Cell> getOwns() {
		Vector<Cell> owns = new Vector<Cell>();
		for (Cell cell: parent.mainPanel.getCells()){
			if(this.equals(cell.getOwner()))
				owns.add(cell);
		}
		return owns;
	}
	
	public int getMoney(){
		return this.money;
	}
	
	public int getMonopoliesCount(){
		return getMonopolies().size();
	}
	public boolean hasMonopoly(int type){
		Vector<Cell> Monopolies = new Vector<Cell>();
		for (Cell cell : owns){
			if (cell.getType()==type)
				Monopolies.add(cell);
		}
		if (Monopolies.size()==2 && (type==4 || type==5 ))
			return true;
		else if (Monopolies.size()==3 && (type==1 || type==2 || type==3 || type==6 || type==7 || type==8 ))
			return true;
		else
			return false;
	}
	
	public boolean hasMonopoly(Cell in){
		int type = in.getType();
		return hasMonopoly(type);
	}
	
	public Vector<Cell> getMonopoly(int type){
		Vector<Cell> Monopolies = new Vector<Cell>();
		for (Cell cell : owns){
			if (cell.getType()==type)
				Monopolies.add(cell);
		}
		if (Monopolies.size()==2 && (type==4 || type==5 ))
			return Monopolies;
		else if (Monopolies.size()==3 && (type==1 || type==2 || type==3 || type==6 || type==7 || type==8 ))
			return Monopolies;
		else
			return null;
	}
	
	public Vector<Cell> getMonopoly(Cell in){
		int type=in.getType();
		return getMonopoly(type);
	}
	
	public Vector<Vector<Cell>> getMonopolies(){
		Vector<Vector<Cell>> vec = new Vector<Vector<Cell>>();
		for (int i=0; i<9; i++){
			if (hasMonopoly(i)){
				vec.add(getMonopoly(i));
			}
		}
		return vec;
	}
	
	public Vector<Vector<Cell>> getUnfinishedMonopolies(){ //monopolies that are not full with hotels
		Vector<Vector<Cell>> vec = new Vector<Vector<Cell>>();
		for (int i=0; i<9; i++){
			if (!hasBuiltUpMonopoly(i)){
				vec.add(getMonopoly(i));
			}
		}
		return vec;
	}
	
	public boolean hasBuiltUpMonopoly(int i){ //if a monopoly contains only hotels
		Vector<Cell> monopoly = getMonopoly(i);
		if (monopoly==null){
			return false;
		}
		for (Cell cell: monopoly){
			if (cell.getHouses()<5){
				return false;
			}
		}
		return true;
	}
	public boolean hasBuiltUpMonopoly(Cell cell){ //if a monopoly contains only hotels
		int type=cell.getType();
		return hasBuiltUpMonopoly(type);
	}
	
	
	public void putOnCell(Cell cell){
		if (this.getOnCell()!=null)
			this.getOnCell().removePlayer(this);
		this.onCell=cell;
	}
	public Cell getOnCell(){
		return this.onCell;
	}

	public String getName() {
		return name;
	}



	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}



	public void setName(String name) {
		this.name = name;
	}

	public void auctionBuy(Cell cell, int price){
		if (this.getMoney()-price<0)
			return;
		if (!cell.canBeBought() || money-price<0){
			return;
		}
		else
		{
			cell.setOwner(this);
			owns.add(cell);
			System.out.println("~~~"+this.getMoney());
			this.setMoney(this.getMoney()-price);
			System.out.println("~~~"+this.getMoney());
			cell.repaint();
			parent.addToHistory(this.getName()+" (Player "+this.getId()+") bought site on "+cell.getDescription()+" for "+price+" after auction with the opponent");
		}
	}	

	public void buyFromBank()
	{	Cell cell=this.getOnCell();
		if (this.getMoney()-cell.getPrice()<0)
			return;
		if (!cell.canBeBought() || money-cell.getPrice()<0){
			return;
		}
		else
		{
			cell.setOwner(this);
			owns.add(cell);
			System.out.println("~~~"+this.getMoney());
			this.setMoney(this.getMoney()-cell.getPrice());
			System.out.println("~~~"+this.getMoney());
			cell.repaint();
			parent.addToHistory(this.getName()+" (Player "+this.getId()+") bought site on "+cell.getDescription()+" for "+cell.getPrice());
		}
	
		// add to monopolies...
	}
	public void buildHouse(Cell cell){ // to cell meta to selection
		if (cell.isMortgaged()) //if it is mortgaged player can't build
			return;
		Vector<Cell> Monopoly=getMonopoly(cell);
		if (Monopoly==null) //den exei monopoly sto xrwma pou epilextike ara den xtizei i den tou anikei kan to cell...
			return;
		else{
		//thelw epilogi tou cell pou 8a kanw build
			Cell cellToBuild=null;
			Cell cellMinHouses=null;
			int minhouses=100;
			for (int i=0; i<Monopoly.size(); i++){
				if(Monopoly.get(i).getHouses()<minhouses){
					minhouses=Monopoly.get(i).getHouses();
					cellMinHouses=Monopoly.get(i);
				}
			}
			cellToBuild=cellMinHouses;
			if (cellToBuild.getHouses()<4 && this.getMoney()-cellToBuild.getHousePrice()<0)
				return;
			if (cellToBuild.getHouses()==4 && this.getMoney()-cellToBuild.getHotelPrice()<0)
				return;
			if(cellToBuild.getHouses()<4){
				this.setMoney(this.getMoney()-cellToBuild.getHousePrice()); //buys a house
				cellToBuild.setHouses(cellToBuild.getHouses()+1);
				houses++;
				parent.addToHistory(this.getName()+" (Player "+this.getId()+") builded house No."+houses+" on "+cell.getDescription());
			}
			else if (cellToBuild.getHouses()==4) {//buys a hotel
				this.setMoney(this.getMoney()-cell.getHotelPrice());
				cellToBuild.setHouses(cellToBuild.getHouses()+1);
				hotels++;
				houses-=4;
				parent.addToHistory(this.getName()+" (Player "+this.getId()+") builded hotel on "+cell.getDescription());
			}
		}	
	}
	public void mortgage(Cell cell){ //to cell meta to selection
	
		System.out.println("mortgage");
		if(cell==null || cell.getOwner()==null || !cell.getOwner().equals(this) || cell.isMortgaged())//if not owner of it or if it is already mortgaged
			return;
		cell.setMortgaged(true);
		mortgages++;
		money+=cell.getMortgage();
		parent.addToHistory(this.getName()+" (Player "+this.getId()+") mortgaged his own on "+cell.getDescription());
		cell.repaint();
	}
	
	
	
	public int getHousesCount(){
		return houses;
	}
	
	public int getHotelsCount(){
		return hotels;
	}
	public int getUtiliesCount(){
		int counter=0;
		for (int i=0; i<owns.size(); i++){
			if (owns.get(i).getType()==12 || owns.get(i).getType()==13)
				counter++;
		}
		return counter;
		
	}
	public int getRailRoadCount(){
		int counter=0;
		for (int i=0; i<owns.size(); i++){
			if (owns.get(i).getType()>=24  && owns.get(i).getType()<=27)
				counter++;
		}
		return counter;
	}
	
	public int getMortgagesCount(){
		return mortgages;
	}
	
	public Vector<Cell> getMortgages(){
		Vector<Cell> mortgages=new Vector<Cell>();
		
		for(Cell cell: getOwns()){
			if (cell.isMortgaged())
				mortgages.add(cell);
		}
		return mortgages;
	}

	public void payRent()
	{
		if (paidRent)
			return;
		Cell cell=this.getOnCell(); //plirwnw Rent gia to tetragwno pou eimai...
		money -= cell.getRent();
		paidRent=true;
		if (cell.getRent()>0)
			parent.addToHistory(this.getName()+" (Player "+this.getId()+") paid "+cell.getRent()+" to the opponent");
	}
	
	public void setPaidRent(boolean bool){
		paidRent=bool;
	}
	
	public boolean getPaidRent(){
		return paidRent;
	}
	
	public Color getPlayerColor(){
		return playerColor;
	}
	public void payMortgage(Cell cell)
	{
		if(cell!=null){
		money -= cell.getMortgage();
		cell.setMortgaged(false);
		cell.setHighlighted(false);
		if(cell.getMortgage()>0)
			parent.addToHistory(this.getName()+" (Player "+this.getId()+") paid back mortgaged own on "+cell.getDescription()+"and earned"+ cell.getMortgage());
		}
		
		
	}

	public void setMoney(int money) {
		if (money<0)
			System.out.println("Problem with money call Mortage");
		this.money=money;	
	}
	public String toString(){
		return "Player "+getId()+" "+getPlayerColor();
	}
	public void movePlayerTo(Cell cell){
		cell.removePlayer(this);
		cell.addPlayer(this);
	}
}
