import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;
public class Monopoli extends JFrame implements LaptopKeyboard, Serializable
{	
	protected Board mainPanel;
	protected Chance chance;
	protected CommunityChest communitychest;
	protected YesNoOkDialog yesnookdialog;
	protected StartMenu startmenu;
	protected PlayerSettingDialog playersetting;
	protected GameRules gamerules;
	protected ControlPanel controlpanel;
	protected OptionsMenu options;
	protected Help help;
	protected AuctionPanel auction;
	protected History history;
	private final static int DONOTHINGMODE=-1;
	private final static int STARTMENUMODE=0;
	private final static int PLAYMODE=1; // oti grafei to controlpanel F1 Select F2
	private final static int YESNODIALOG=2;
	private final static int PLAYERSETTING=3;
	private final static int TEXTAREAMODE=4;
	private final static int CHANCEMODE=5;
	private final static int COMMUNITYCHESTMODE=6;
	private final static int OPTIONSMODE=7;
	protected final static int HIGHLIGHTBUILDMODE=8; // epilogi tatragwnou gia build 
	protected final static int HIGHLIGHTDESCRIPTMODE=9; // epilogi tatragwnou gia description
	protected final static int HIGHLIGHTMORTGAGEMODE=10; //epilogi tetragwnou gia mortgage
	protected final static int SELECTEDOWNTOMORTAGEMODE=11; // gia na patisw select kai na ginei mortgaged
	protected final static int HIGHLIGHTMORTGAGEDOWNS=12; //epilogi tetragwnou gia xeplirwma mortgage
	protected final static int SELECTEDMORTAGETOPAYMODE=13; // gia na patisw select kai na ginei unmortgaged
	protected final static int HELPMODE=14;
	protected final static int AUCTIONMODE=15;
	protected final static int HISTORYMODE=4;
	private int currentMode=STARTMENUMODE;
	public Monopoli()
	{
		buildWindow();
	}
	public static void main(String args[])
	{
		new Monopoli();
		MP3 intro=new MP3("images//intro.mp3");
		intro.play();
		
		
	}
	public void buildWindow()
	{	
		mainPanel = new Board(this); // edw exoun idi bei ta cells
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("Monopoly");
		
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(150,150,240,320);
		this.setUndecorated(true);		
		getContentPane().setBackground(Color.BLACK);
		this.getContentPane().add(mainPanel);
		this.getContentPane().add(controlpanel=new ControlPanel(this));
		startMenuInit(true);
		
		
		KeyAdapter kadapter = new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				MP3 song;
				if(e.getKeyCode()==48 || e.getKeyCode()==49 || e.getKeyCode()==50 || e.getKeyCode()==51 && e.getKeyCode()==52 || e.getKeyCode()==53 || e.getKeyCode()==54 || e.getKeyCode()==55 || e.getKeyCode()==56 || e.getKeyCode()==57){
					song=new MP3("images\\tick.mp3");
					song.play();
				}
				if(e.getKeyCode()==F1 || e.getKeyCode()==F2 || e.getKeyCode()==left ||  e.getKeyCode()==right ||  e.getKeyCode()==up ||  e.getKeyCode()==down){
					song=new MP3("images\\feed.mp3");
					song.play();
				}
				if( e.getKeyCode()==select ){
					song=new MP3("images\\golddrop.mp3");
					song.play();
				}
				
				
				
				if (e.getKeyCode()==sharp){
					if(currentMode==STARTMENUMODE){
						helpInit("Select with Up/down one of the options... Load / Save to Load: or Save your game. Help: for controls help. Credits: To view game credits. Back: To Go Back to the play mode . Exit: To exit the Game");
					}
					else if(currentMode==PLAYMODE){
						helpInit("With the middle button (Roll), you can roll the dice for the next player. Be sure to press the F2 button for the options (Request Rent, Buy, Build, etc). To access the start menu press the F1 button");
					}
					else if(currentMode==YESNODIALOG){
						helpInit("With the F1/F2/Select buttons you have to decide what will be done, or pass on the next state with an ok (pressing the select button)");
					}
					else if(currentMode==CHANCEMODE){
						helpInit("This is a chance card!. Press a key to continue");
					}
					else if(currentMode==COMMUNITYCHESTMODE){
						helpInit("This is a communitychestmode card!. Press a key to continue");
					}
					else if(currentMode==OPTIONSMODE){
						helpInit("This is the option menu. You can build,request a rent, buy a site, auction etc... Use the arrow keys and the middle one for select");
					}
					
					
					
					
				}
				else if (currentMode==STARTMENUMODE && startmenu!=null)
					startmenu.keyHandler(e);
				else if(currentMode==TEXTAREAMODE && gamerules!=null)
					gamerules.keyHandler(e);
				else if(currentMode==YESNODIALOG && yesnookdialog!=null)
					yesnookdialog.keyHandler(e);
				else if(currentMode==PLAYERSETTING && playersetting!=null)
					playersetting.keyHandler(e);
				else if (currentMode==CHANCEMODE && chance!=null)
					chance.keyHandler(e);
				else if (currentMode==COMMUNITYCHESTMODE && communitychest!=null)
					communitychest.keyHandler(e);
				else if (currentMode==OPTIONSMODE && options!=null)
					options.keyHandler(e);
				else if (currentMode==HELPMODE && help!=null)
					help.keyHandler(e);
				else if (currentMode==AUCTIONMODE && auction!=null)
					auction.keyHandler(e);
				else if (currentMode==HISTORYMODE && history!=null)
					history.keyHandler(e);
				else if (currentMode==DONOTHINGMODE){
					e.consume();
				}
				else if (currentMode==HIGHLIGHTBUILDMODE || currentMode==HIGHLIGHTDESCRIPTMODE || currentMode==HIGHLIGHTMORTGAGEMODE || 
						currentMode==SELECTEDOWNTOMORTAGEMODE || currentMode==HIGHLIGHTMORTGAGEDOWNS || currentMode==SELECTEDMORTAGETOPAYMODE){
					keyHandlerForHighlight(e);
				}
				else if (currentMode==PLAYMODE || startmenu==null || yesnookdialog==null || gamerules==null || chance==null || communitychest==null || options==null)
						keyHandler(e);
				
			}
		};	
		this.addKeyListener(kadapter);		
		setVisible(true);
	}
	public void keyHandlerForHighlight(KeyEvent e){
		if(e.getKeyCode()==left || e.getKeyCode()==up){
			if ( currentMode==SELECTEDOWNTOMORTAGEMODE)
				return;
			previousHighlight();
		} 
		else if(e.getKeyCode()==right || e.getKeyCode()==down){ 
			if ( currentMode==SELECTEDOWNTOMORTAGEMODE)
				return;
			nextHighlight();
		}
		else if(e.getKeyCode()==select){
			
			
			
			if (currentMode==HIGHLIGHTBUILDMODE){
				boolean done=false;
				for (Cell curCell:this.mainPanel.getCurrentPlayerTurn().getMonopoly(this.mainPanel.highlighted.getType()))
				{
					if(curCell.getHouses()<this.mainPanel.highlighted.getHouses())
					{
						done=true;
						curCell.setHouses(curCell.getHouses()+1);
						break;
					}
				}
				if(!done)
				{
					if(this.mainPanel.highlighted.getHouses()<5)
						this.mainPanel.highlighted.setHouses(this.mainPanel.highlighted.getHouses()+1);
				}
				
				repaint();
				
				
				
			}
			else if (currentMode==HIGHLIGHTMORTGAGEMODE){
				if(this.mainPanel.highlighted!=null)
					this.mainPanel.highlighted.setHighlighted(false);
				currentMode=SELECTEDOWNTOMORTAGEMODE;
				
				
				repaint();
			}
			if (currentMode==SELECTEDOWNTOMORTAGEMODE){
				//epanafere ta koubia opws itan...
				
				mainPanel.getCurrentPlayerTurn().mortgage(mainPanel.highlighted);
				
				repaint();
				//exei kai alla unmortaged owns...
				if (this.mainPanel.getCurrentPlayerTurn().getOwns().size() > (this.mainPanel.getCurrentPlayerTurn().getMortgages().size())){
					currentMode=HIGHLIGHTMORTGAGEMODE;
					firstHighlight();  
					
				}
				else {
					currentMode=PLAYMODE;
					controlpanel.buttonsSetVisible(true);
					
				}
				controlpanel.updateCP();
				repaint();
			}
			if (currentMode==HIGHLIGHTMORTGAGEDOWNS){
			
				this.mainPanel.highlighted.setHighlighted(false);
				mainPanel.getCurrentPlayerTurn().payMortgage(mainPanel.highlighted);
				
				currentMode=SELECTEDOWNTOMORTAGEMODE;
				
				if (this.mainPanel.getCurrentPlayerTurn().getMortgages().size()>0){
					currentMode=HIGHLIGHTMORTGAGEDOWNS;
					firstHighlight();  
				}
				else {
					currentMode=PLAYMODE;
					controlpanel.buttonsSetVisible(true);
					
				}
				controlpanel.updateCP();
			
				repaint();
			}
			
			
			
			
		}
		if(e.getKeyCode()==F1){ 
			if (currentMode==HIGHLIGHTMORTGAGEMODE || currentMode==SELECTEDOWNTOMORTAGEMODE)
				return;
			this.startMenuInit(true);
		}
		else if(e.getKeyCode()==F2){ 
			if (currentMode==SELECTEDOWNTOMORTAGEMODE)
				return;
			this.OptionsInit();
		}
	}
	
	
	public void keyHandler(KeyEvent e){ 
		if(e.getKeyCode()==select){
			
			mainPanel.nextMove();
			this.mainPanel.repaint();
			
		}
		else if(e.getKeyCode()==F1){ 
			this.startMenuInit(true);
		}
		else if(e.getKeyCode()==F2){ 
			this.OptionsInit();
		}
		
	 }	
	public void auctionInit(){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=AUCTIONMODE;
		auction=new AuctionPanel(this.mainPanel.getCurrentPlayerTurn().getOnCell(),this);
		this.getContentPane().add(auction);
		getContentPane().setComponentZOrder(auction, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void auctionRemove(){
		if(auction==null)
			return;
		getContentPane().remove(auction);
		getContentPane().validate();
		this.invalidate();
		repaint();
		controlpanel.buttonsSetVisible(true);
		this.currentMode=PLAYMODE;
		
	}
	public void helpInit(String msg){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=HELPMODE;
		help=new Help(this,msg);
		this.getContentPane().add(help);
		getContentPane().setComponentZOrder(help, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void helpRemove(){
		if(help==null)
			return;
		getContentPane().remove(help);
		getContentPane().validate();
		this.invalidate();
		//startMenuInit(false);
		controlpanel.buttonsSetVisible(true);
		repaint();
		
	}
	public void yesNoInit(String msg,String choice1,String choice2){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=YESNODIALOG;
		yesnookdialog=new YesNoOkDialog(this,msg,choice1,choice2);
		this.getContentPane().add(yesnookdialog);
		getContentPane().setComponentZOrder(yesnookdialog, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void gameRulesInit(String choice1){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=TEXTAREAMODE;
		gamerules=new GameRules(this,choice1);
		this.getContentPane().add(gamerules);
		getContentPane().setComponentZOrder(gamerules, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
		
	}
	public void historyInit(String choice1){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=TEXTAREAMODE;
		history=new History(this,choice1);
		this.getContentPane().add(history);
		getContentPane().setComponentZOrder(history, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void gameRuleRemove(){
		if(gamerules==null)
			return;
		getContentPane().remove(gamerules);
		getContentPane().validate();
		this.invalidate();
		//startMenuInit(false);
		controlpanel.buttonsSetVisible(true);
		repaint();
	}
	public void historyRemove(){
		if(history==null)
			return;
		getContentPane().remove(history);
		getContentPane().validate();
		this.invalidate();
		//startMenuInit(false);
		controlpanel.buttonsSetVisible(true);
		repaint();
	}
	public int getMode(){
		return currentMode;
	}
	public void setMode(int newmode){
		currentMode=newmode;
	}
	
	public Chance ChanceInit(){
		MP3 song = new MP3("images//fireball.mp3");
		song.play();
		int i=(int)(Math.random()*16);
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=CHANCEMODE;
		chance=mainPanel.getChanceCards().get(i);
		System.out.println(this.getContentPane().getComponentCount());
		for (Component comp : this.getContentPane().getComponents())
			System.out.println(comp.getClass().getName());
			
		this.getContentPane().add(chance);
		getContentPane().setComponentZOrder(chance, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
		return chance;
	}
	public void ChanceRemove(){
		if(chance==null)
			return;
		getContentPane().remove(chance);
		getContentPane().validate();
		this.invalidate();
		repaint();
		controlpanel.buttonsSetVisible(true);
	}
	
	
	public void OptionsInit(){
		RemoveDialogs(true);
		this.currentMode=OPTIONSMODE;
		this.controlpanel.buttonsSetVisible(false);
		options=new OptionsMenu(this);
		this.getContentPane().add(options);
		getContentPane().setComponentZOrder(options, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void OptionsRemove(){
		if(options==null)
			return;
		getContentPane().remove(options);
		getContentPane().validate();
		this.invalidate();
		this.controlpanel.buttonsSetVisible(true);
		repaint();
	}
	public CommunityChest CommunityChestInit(){
		MP3 song = new MP3("images//fireball.mp3");
		song.play();
		int i=(int)(Math.random()*16);
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=COMMUNITYCHESTMODE;
		communitychest=mainPanel.getCommunityChestCards().get(i);
		System.out.println(getContentPane().getComponentCount());
		for (Component comp : this.getContentPane().getComponents())
			System.out.println(comp.getClass().getName());
			
		this.getContentPane().add(communitychest);
		getContentPane().setComponentZOrder(communitychest, 0);
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
		return communitychest;
	}
	public void CommunityChestRemove(){
		if(communitychest==null)
			return;
		getContentPane().remove(communitychest);
		getContentPane().validate();
		this.invalidate();
		repaint();
		controlpanel.buttonsSetVisible(true);
	}
	
	
	public void yesNoRemove(){
		if(yesnookdialog==null)
			return;
		getContentPane().remove(yesnookdialog);
		getContentPane().validate();
		this.invalidate();
		repaint();
		controlpanel.buttonsSetVisible(true);
	}
	public void RemoveDialogs(boolean bool){
		this.currentMode=PLAYMODE;
		startMenuRemove();
		if (bool){
			helpRemove();
			historyRemove();
			gameRuleRemove();
		}
		yesNoRemove();
		PlayerSettingRemove();
		ChanceRemove();
		CommunityChestRemove();
		OptionsRemove();
		controlpanel.updateCP();
		repaint();
	}
	public void PlayerSettingRemove() {
		if(playersetting==null)
			return;
		getContentPane().remove(playersetting);
		getContentPane().validate();
		this.invalidate();
		this.currentMode=PLAYMODE;
		controlpanel.buttonsSetVisible(true);
	}

	public void startMenuInit(boolean bool){
		RemoveDialogs(bool);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=STARTMENUMODE;
		startmenu=new StartMenu(this);
		this.getContentPane().add(startmenu);
		getContentPane().setComponentZOrder(startmenu, 0); 
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	public void startMenuRemove(){
		if(startmenu==null)
			return;
		getContentPane().remove(startmenu);
		getContentPane().validate();
		this.invalidate();
		controlpanel.buttonsSetVisible(true);
	}
	public void PlayerSettingInit(String titlestr,Color colorused){
		RemoveDialogs(true);
		controlpanel.buttonsSetVisible(false);
		this.currentMode=PLAYERSETTING;
		playersetting=new PlayerSettingDialog(this,titlestr,colorused);
		this.getContentPane().add(playersetting);
		getContentPane().setComponentZOrder(playersetting, 0); 
		getContentPane().setComponentZOrder(mainPanel, 1);
		repaint();
	}
	
	public void messageDispatcher(JPanel Source,String message){
		if(Source instanceof Menu){
			if(message.trim().contains("New Game")){
				mainPanel = new Board(this);
				PlayerSettingInit("Player 1 Settings",Color.BLACK);
				repaint();
			}
			else if (message.trim().contains("Save")){
				try {
					File file = new File("save.txt");
					file.delete();	
					ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
					
					obj.writeObject(mainPanel.getPlayers());
					obj.writeObject(mainPanel.getCells());
					
					addToHistory("Game Saved");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (message.trim().contains("Load")){
				try {
					ObjectInputStream obj = new ObjectInputStream(new FileInputStream(new File("save.txt")));
					try {
						Vector<Player> players = (Vector<Player>)(obj.readObject());
						mainPanel.setPlayers(players);
						Vector<Cell> cells = new Vector<Cell>((Vector<Cell>)(obj.readObject()));
						mainPanel.removeAll();
						mainPanel.setCells(cells);// an kanw uncomment ta xalaei ola
						mainPanel.buildBoard();
						mainPanel.turn=mainPanel.getPlayer(0);
						repaint();
						controlpanel.updateCP();
						this.RemoveDialogs(true);

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					addToHistory("Game Loaded");
					firstHighlight();
					repaint();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(message.trim().contains("Normal")){	
				mainPanel.highlighted.setHighlighted(false);	//an itan return apo build/mortgage mode
				mainPanel.highlighted=null;
				RemoveDialogs(true);
				repaint();
			}
			else if(message.trim().equalsIgnoreCase("Pay Back some Mortgage")){
				RemoveDialogs(true);
				currentMode=HIGHLIGHTMORTGAGEDOWNS;
				this.firstHighlight();
				this.controlpanel.selectButtonOnly("Mortgage to Pay");				
				repaint();
			}
			else if(message.trim().contains("Back")){	
				RemoveDialogs(true);
				repaint();
			}
			
			else if(message.trim().contains("Buy")){	
				this.mainPanel.getCurrentPlayerTurn().buyFromBank();
				RemoveDialogs(true);
				this.OptionsInit();
				repaint();
			}
			else if(message.trim().contains("Rent")){	
				this.mainPanel.getNextPlayerTurn().payRent();
				RemoveDialogs(true);
				this.OptionsInit();
				//System.out.println("Rent Payed by Opponent");
				repaint();
			}
			else if(message.trim().contains("Auction")){	
				RemoveDialogs(true);
				this.auctionInit();
				repaint();
			}
			else if(message.trim().contains("Build")){
				RemoveDialogs(true);
				currentMode=HIGHLIGHTBUILDMODE;
				controlpanel.selectButtonOnly("Build");
				firstHighlight();
				repaint();
			}
			else if(message.trim().equalsIgnoreCase("Mortgage some own")){
				RemoveDialogs(true);
				currentMode=HIGHLIGHTMORTGAGEMODE;
				this.firstHighlight();
				this.controlpanel.selectButtonOnly("Mortgage");				
				repaint();
			}
			
			
			
			else if(message.trim().equalsIgnoreCase("Credits")){
				System.out.println("CREDITS OF THE GAME! ");
			}
			else if(message.trim().equalsIgnoreCase("Game rules")){
				//System.out.println("READ THE RULES OF THE GAME! ");
				gameRulesInit("OK");
			}
			else if(message.trim().contains("History")){
				historyInit("OK");
			}
			else if(message.trim().equalsIgnoreCase("Exit")){
				System.out.println("COULD WRITE R U SURE? - BUT BYE FOR NOW!");
				System.exit(0);
			}
			
		}
		else if(Source instanceof PlayerSettingDialog){
			if(!message.contains("|")){ // den exei pliroforia xrwmatos alla onomatos
				StringTokenizer strtok = new StringTokenizer(message,",");
				String token1=strtok.nextToken(); //contains player1 or 2
				String token2=strtok.nextToken(); //contains player name
				if (token2.trim().equals("")){
					
					if (token1.contains("Player 1 Settings"))
							mainPanel.getPlayer(0).setName("Player 1");
					else
							mainPanel.getPlayer(1).setName("Player 2");
				}
				else {
					if (token1.contains("Player 1 Settings"))
							mainPanel.getPlayer(0).setName(token2);
					else
							mainPanel.getPlayer(1).setName(token2);
				}
			}
			else {//exei pliroforia xrwmatos
				StringTokenizer strtok = new StringTokenizer(message,"|");
				String dummy=strtok.nextToken();
				String Red=strtok.nextToken();
				String Green=strtok.nextToken();
				String Blue=strtok.nextToken();
				System.out.print(message);
				Color col=new Color(Integer.parseInt(Red),Integer.parseInt(Green),Integer.parseInt(Blue));
				if (message.contains("Player 1 Settings")){
					
					mainPanel.getPlayer(0).setPlayerColor(col);
					((PlayerSettingDialog)Source).setColorused(col);
				}
				else
					mainPanel.getPlayer(1).setPlayerColor(col);			
			}
		}
	}
	
	public void nextHighlight(){
		repaint();
		Vector<Cell> vec=this.mainPanel.getCells();
		Vector<Cell> owns=new Vector<Cell>();
		for (Cell cell : vec){
			if (this.mainPanel.getCurrentPlayerTurn().equals(cell.getOwner()) && !cell.isMortgaged()){
				owns.add(cell);
			}
		}
		Vector<Vector<Cell>> monopolies=this.mainPanel.getCurrentPlayerTurn().getMonopolies();
		if (currentMode==HIGHLIGHTDESCRIPTMODE){
			if (vec==null)
				return;
			for (int i = 0; i < vec.size(); i++)
			{
				if (i == vec.size() - 1 && vec.get(i).isHighlighted())
				{
					vec.get(0).setHighlighted(true);
					vec.get(i).setHighlighted(false);
					break;
				}
				else if (i < vec.size() - 1 && vec.get(i).isHighlighted())
				{
					vec.get(i + 1).setHighlighted(true);
					vec.get(i).setHighlighted(false);
					break;
				}
				
			}
			
		}
		else if (currentMode==HIGHLIGHTMORTGAGEMODE){
			if (owns==null || owns.size()==0 || owns.size()==1)
				return;
		
			for (int i = 0; i < owns.size(); i++)
			{
				if (i == owns.size() - 1 && owns.get(i).isHighlighted())
				{
					owns.get(0).setHighlighted(true);
					owns.get(i).setHighlighted(false);
					break;
				}
				else if (i < owns.size() - 1 && owns.get(i).isHighlighted())
				{
					owns.get(i + 1).setHighlighted(true);
					owns.get(i).setHighlighted(false);
					break;
				}
				
			}

			
			
		}
		else if (currentMode==HIGHLIGHTBUILDMODE){
			Vector<Cell> placesInMonopoliesOwned = new Vector<Cell>();
			for(Cell curCell:this.mainPanel.getCells())
			{
				if(this.mainPanel.getCurrentPlayerTurn().equals(curCell.getOwner())&&  this.mainPanel.getCurrentPlayerTurn().hasMonopoly(curCell))
						{
							placesInMonopoliesOwned.add(curCell);
						}
			}


			if(mainPanel.highlighted.equals(placesInMonopoliesOwned.get(placesInMonopoliesOwned.size()-1)))
					{
						placesInMonopoliesOwned.get(0).setHighlighted(true);
						placesInMonopoliesOwned.get(placesInMonopoliesOwned.size()-1).setHighlighted(false);
					}
			else
			{
				for(int i=0;i<placesInMonopoliesOwned.size();i++)
				{
					if(placesInMonopoliesOwned.get(i).equals(mainPanel.highlighted))
					{
						placesInMonopoliesOwned.get(i+1).setHighlighted(true);
						placesInMonopoliesOwned.get(i).setHighlighted(false);
						break;
					}
				}
			}
			
			
			
			
			/*if (monopolies==null)
				return;
			for (int i=0; i<monopolies.size(); i++){
				if (monopolies.get(i).firstElement().getType()==8)
					mainPanel.highlightMonopoly(1,true);
				else
					mainPanel.highlightMonopoly(monopolies.get(i).firstElement().getType()+1,true);
				if (monopolies.get(i).firstElement().isHighlighted())
					mainPanel.highlightMonopoly(monopolies.get(i).firstElement().getType(),false);
			}	*/	
		}
		else if (currentMode==HIGHLIGHTMORTGAGEDOWNS){
			Vector<Cell> mortgages=this.mainPanel.getCurrentPlayerTurn().getMortgages();
			if (mortgages==null || mortgages.size()==0 || mortgages.size()==1)
				return;
		
			for (int i = 0; i < mortgages.size(); i++)
			{
				if (i == mortgages.size() - 1 && mortgages.get(i).isHighlighted())
				{
					mortgages.get(0).setHighlighted(true);
					mortgages.get(i).setHighlighted(false);
					break;
				}
				else if (i < mortgages.size() - 1 && mortgages.get(i).isHighlighted())
				{
					mortgages.get(i + 1).setHighlighted(true);
					mortgages.get(i).setHighlighted(false);
					break;
				}
				
			}

			
			
		}
		repaint();
	}
	
	
	public void previousHighlight(){
		repaint();
		Vector<Cell> vec=this.mainPanel.getCells();
		Vector<Cell> owns=new Vector<Cell>();
		for (Cell cell : vec){
			if (this.mainPanel.getCurrentPlayerTurn().equals(cell.getOwner()) && !cell.isMortgaged()){
				owns.add(cell);
			}
		}
		Vector<Vector<Cell>> monopolies=this.mainPanel.getCurrentPlayerTurn().getMonopolies();
		if (currentMode==HIGHLIGHTDESCRIPTMODE){
			if (vec==null)
				return;
			for (int i = 0; i < vec.size(); i++)
			{
				if (i == 0 && vec.get(i).isHighlighted())
				{
					vec.get(vec.size() - 1).setHighlighted(true);
					vec.get(i).setHighlighted(false);
					break;
				}
				else if (i > 0 && vec.get(i).isHighlighted())
				{
					vec.get(i - 1).setHighlighted(true);
					vec.get(i).setHighlighted(false);
					break;
				}
				
			}
		}
		else if (currentMode==HIGHLIGHTMORTGAGEMODE){
			if (owns==null || owns.size()==0 || owns.size()==1)
				return;
			for (int i = 0; i < owns.size(); i++)
			{
				if (i == 0 && owns.get(i).isHighlighted())
				{
					owns.get(owns.size() - 1) .setHighlighted(true);
					owns.get(i).setHighlighted(false);
					break;
				}
				else if (i > 0 && owns.get(i).isHighlighted())
				{
					owns.get(i - 1).setHighlighted(true);
					owns.get(i).setHighlighted(false);
					break;
				}
				
			}
		}
		else if (currentMode==HIGHLIGHTBUILDMODE){
			Vector<Cell> placesInMonopoliesOwned = new Vector<Cell>();
			for(Cell curCell:this.mainPanel.getCells())
			{
				if(this.mainPanel.getCurrentPlayerTurn().equals(curCell.getOwner())&&  this.mainPanel.getCurrentPlayerTurn().hasMonopoly(curCell))
						{
							placesInMonopoliesOwned.add(curCell);
						}
			}

			if(mainPanel.highlighted.equals(placesInMonopoliesOwned.get(0)))
					{
						placesInMonopoliesOwned.get(placesInMonopoliesOwned.size()-1).setHighlighted(true);
						placesInMonopoliesOwned.get(0).setHighlighted(false);
					}
			else
			{
				for(int i=0;i<placesInMonopoliesOwned.size();i++)
				{
					if(placesInMonopoliesOwned.get(i).equals(mainPanel.highlighted))
					{
						placesInMonopoliesOwned.get(i-1).setHighlighted(true);
						placesInMonopoliesOwned.get(i).setHighlighted(false);
						break;
					}
				}
			}
		}
		else if (currentMode==HIGHLIGHTMORTGAGEDOWNS){
			Vector<Cell> mortgages=this.mainPanel.getCurrentPlayerTurn().getMortgages();
			if (mortgages==null || mortgages.size()==0 || mortgages.size()==1)
				return;
			for (int i = 0; i < mortgages.size(); i++)
			{
				if (i == 0 && mortgages.get(i).isHighlighted())
				{
					mortgages.get(mortgages.size() - 1) .setHighlighted(true);
					mortgages.get(i).setHighlighted(false);
					break;
				}
				else if (i > 0 && mortgages.get(i).isHighlighted())
				{
					mortgages.get(i - 1).setHighlighted(true);
					mortgages.get(i).setHighlighted(false);
					break;
				}
			}
		}
		repaint();
	}
	
	public void firstHighlight(){
		Vector<Cell> vec=this.mainPanel.getCells();
		Vector<Cell> owns=new Vector<Cell>();
		for (Cell cell : vec){
			if (this.mainPanel.getCurrentPlayerTurn().equals(cell.getOwner()) && !cell.isMortgaged()){
				owns.add(cell);
			}
		}
		Vector<Vector<Cell>> monopolies=this.mainPanel.getCurrentPlayerTurn().getMonopolies();
		if (currentMode==HIGHLIGHTDESCRIPTMODE){
			if (vec==null && vec.size()==0 )
				return;
			vec.get(0).setHighlighted(true);
		}
		else if (currentMode==HIGHLIGHTMORTGAGEMODE){
			if (owns==null && owns.size()==0)
				return;
			for (int i=0; i<owns.size(); i++){
				owns.get(i).setHighlighted(true);
				break;
			}
		}
		else if (currentMode==HIGHLIGHTBUILDMODE){
			if (monopolies==null && owns.size()==0)
				return;
			//mainPanel.highlightMonopoly(1,true);
			Vector<Cell> placesInMonopoliesOwned = new Vector<Cell>();
			for(Cell curCell:this.mainPanel.getCells())
			{
				if(this.mainPanel.getCurrentPlayerTurn().equals(curCell.getOwner())&&  this.mainPanel.getCurrentPlayerTurn().hasMonopoly(curCell))
						{
							placesInMonopoliesOwned.add(curCell);
						}
			}

			if(placesInMonopoliesOwned.size()>0)
				placesInMonopoliesOwned.get(0).setHighlighted(true);
		}
		else if (currentMode==HIGHLIGHTMORTGAGEDOWNS){
			Vector<Cell> mortgages=this.mainPanel.getCurrentPlayerTurn().getMortgages();
			System.out.println("mortsize"+mortgages.size());
			if (mortgages==null && mortgages.size()==0)
				return;
			for (int i=0; i<mortgages.size(); i++){
				mortgages.get(i).setHighlighted(true);
				break;
			}
		}
		repaint();
	}
	
	public int getCurrentMode(){
		return currentMode;
	}
	
	public void addToHistory(String str){
		//System.out.println("\n"+"History:"+str);
		if (history==null){
			history=new History(this,"ok");
		}
		history.addToHistory(str+"\n");
		//centralpanel.addToHistory(str);
	}
	/*public Cell getHighlightedMortgage(){
		for (Cell cell : mainPanel.getCells()){
			if (cell.isMortgaged() && cell.isHighlighted()){
				return cell;
			}
		}
		return null;
	}*/
	
	
	
}

