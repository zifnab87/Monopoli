import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements Serializable {
	private Vector<Player> players;
	private Vector<Cell> cells;
	private Vector<Chance> chancecards;
	private Vector<CommunityChest> communitychestcards;
	private JFrame parent;
	public Cell highlighted;
	public Cell selected;
	protected Player turn;
	protected int dice1;
	protected int dice2;
	public Central central;
	
	public void setPlayers ( Vector<Player> players){
		this.players=players;
	}
	public void setCells ( Vector<Cell> cells){
		this.cells=cells;
	}
	
	public Board(JFrame parent) {
		this.parent = parent;
		cells = new Vector<Cell>();
		players = new Vector<Player>();
		chancecards =new Vector<Chance>();
		communitychestcards = new Vector<CommunityChest>();
		Player pl1 = new Player((Monopoli)parent,0);
		Player pl2 = new Player((Monopoli)parent,1);
		this.players.add(pl1);
		this.players.add(pl2);
		//this.turn = pl1; // vale ton prwto paikti na xekinisei
		this.fillCellVector();
		this.fillChanceVector();
		this.fillCommunityChestVector();
		getCell(0).addPlayer(pl1); // vale ton paikti 1 sto prwto cell
		getCell(0).addPlayer(pl2); // vale ton paikti 2 sto prwto cell
		buildBoard(); // kateskeuase me to layout kai ta cells to board
		this.setOpaque(true);
		this.setSize(240, 240);
		this.setLocation(1, 2);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.setDoubleBuffered(true);
	}

	private void fillCellVector() {
		int[] prices = { 0, 220, 0, 220, 240, 200, 260, 260, 150, 280, 0, 300,
				300, 0, 320, 200, 0, 350, 0, 400, 0, 60, 0, 60, /**/200,
				200, 100, 0, 100, 120, 0, 140, 150, 140, 160, 200,180, 0, 180,
				200};
		int[] types = { 14, 1, 18, 1, 1, 24, 2, 2, 12, 2, 15, 3, 3, 21, 3, 25, 19, 4,
				28, 4, 16, 5, 22, 5, 29 , 26, 6, 20, 6, 6, 17, 7, 13, 7, 7, 27, 8, 23,
				8, 8 };
		String[] descriptions = { "Start", "Kentucky Avenue", "Chance", "Indiana Avenue", "Illinois Avenue",
				"B&O Railroad", "Atlantic Avenue", "Ventnor Avenue", "Water Works","Marvin Gardens", "Go To Jail!", "Pacific Avenue",
				"North Carolina Avenue", "Community Chest", "Pennsylvania Avenue", "Short Line", "Chance", "Park Place", "Luxury Tax",
				"Boardwalk", "GO: Get $200 of Money!", "Mediterranean Avenue", "Community Chest", "Baltic Avenue", "Income Tax",
				"Reading Railroad", "Oriental Avenue", "Chance", "Vermont Avenue", "Connecticut Avenue",
				"Just Visiting | In Jail!", "St. Charles Place", "Electric Company", "States Avenue",
				"Virginia Avenue", "Pennsylvania Railroad", "St. James Place", "Community Chest", "Tennessee Avenue", "New York Avenue" };
		for (int i = 0; i < 40; i++) {
			String iconpath = "images//" + Cell.whatTypeIs(types[i]) + ".png";
			Cell cell = new Cell(this, prices[i], types[i], iconpath,descriptions[i], i);
			cells.add(cell);
		}
	}
	private void fillChanceVector(){
		String[] descriptions = {
				"<html>Parking Fine: $15</html>",
				"<html>Pay Poor Tax: $15</html>",
				"<html>Bank Pays You Dividend of $50</html>",
				"<html>Advance Token to the Nearest Railroad</html>",
				"<html>Advance Token to the Nearest Railroad</html>",
				"<html>Advance Token to the Nearest Utility</html>",
				"<html>Elected Chairman of the Board: Pay Each<p>Player $50</html>",
				"<html>Advance token to the nearest utility. If<p>unowned you may buy it from bank. If owned,<p>throw dice and pay owner a total ten times the<p>amount thrown.</html>",
				"<html>Advance token to the nearest Railroad and<p>pay owner Twice the Rental to which he is<p>otherwise entitled. If Railroad is unowned, you<p>may buy it from the Bank.</html>",
				"<html>Advance token to the nearest Railroad and<p>pay owner Twice the Rental to which he is<p>otherwise entitled. If Railroad is unowned, you<p>may buy it from the Bank.</html>",
				"<html>You have been elected chairman of the<p>board Pay each player $50</html>",
				"<html>Pay school fees of $150</html>",
				"<html>You are assessed for street repairs:<p>$40 perhouse $115 per hotel</html>",
     			"<html>Drunk in charge</html>",
				"<html>Speeding fine £15</html>",
				"<html>You have won a crossword competition.<p>Collect $100</html>"};
		for (int i=0; i<descriptions.length; i++ ){
			Chance chance=new Chance(parent,descriptions[i],"OK",i);
			chancecards.add(chance);
		}
	}
	
	public void fillCommunityChestVector(){
		String[] descriptions = {
			    "<html>Everyone Must Donate 10% of His Holdings<p>to You in Cash</html>",
			    "<html>Go Back to  Avenue</html>",
			    "<html>Go to Income Tax or Jail</html>",
			    "<html>Advance Token to the Nearest Railroad</html>",
			    "<html>We're Off the Gold Standard, Collect $50</html>",
			    "<html>Pay Your Insurance Premium: $50</html>",
			    "<html>Pay School Tax $150</html>",
			    "<html>Your Xmas Fund Matures - collect $200</html>",
			    "<html>Grand Opera Opening, Collect $50 from<p>each player</html>",
			    "<html>Second Prize in a Beauty Contest<p>- Award $11</html>",
			    "<html>Life Insurance Matures – collect $100</html>",
			    "<html>Receive for services $25</html>",
			    "<html>You are Assessed for Street Repairs <p>–$40 per house, $115 per hotel</html>",
			    "<html>Receive interest on 7% preference shares:<p>$25</html>",
			    "<html>Pay a $10 fine or take a \"Chance\"</html>",
			    "<html>It is your birthday Collect $10 from<p>each player</html>"};
		for (int i=0; i<descriptions.length; i++ ){
			CommunityChest communitychest=new CommunityChest(parent,descriptions[i],"OK",i);
			communitychestcards.add(communitychest);
		}
	}
	
	public void buildBoard() {
		int counter = 0;
		Cell currentCell = this.cells.get(0);
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		// upper row
		this.add(currentCell, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 1), 0, 0));
		counter++;
		for (int i = 0; i < 9; i++) {
			currentCell = this.cells.get(counter + i);
			this.add(currentCell, new GridBagConstraints(i + 1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
					new Insets(0, 0, 0, 1), 0, 0));
		}
		counter += 9;

		// right column
		currentCell = this.cells.get(counter);
		this.add(currentCell, new GridBagConstraints(10, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		counter++;
		for (int i = 0; i < 9; i++) {
			currentCell = this.cells.get(counter + i);
			this.add(currentCell, new GridBagConstraints(10, i + 1, 1, 1, 0.0,
					0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
					new Insets(1, 0, 0, 0), 0, 0));
		}
		counter += 9;

		// bottom row starting from right
		currentCell = this.cells.get(counter);
		this.add(currentCell, new GridBagConstraints(10, 10, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(1, 0, 0, 0), 0, 0));
		counter++;
		for (int i = 0; i < 9; i++) {
			currentCell = this.cells.get(counter + i);
			this.add(currentCell, new GridBagConstraints(9 - i, 10, 1, 1, 0.0,
					0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE,
					new Insets(1, 0, 0, 1), 0, 0));
		}
		counter += 9;

		// left column starting form the bottom
		currentCell = this.cells.get(counter);
		this.add(currentCell, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(1, 0, 0, 1), 0, 0));
		counter++;
		for (int i = 0; i < 9; i++) {
			currentCell = this.cells.get(counter + i);
			this.add(currentCell, new GridBagConstraints(0, 9 - i, 1, 1, 0.,
					0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
					new Insets(1, 0, 0, 1), 0, 0));
		}
		counter += 9;

		// Center Cell
		central = new Central(this);
		central.setBounds(0, 0,162,162);
		this.add(central, new GridBagConstraints(1, 1, 9, 9, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						1, 0, 0, 0), 0, 0));
		/*JLabel center = new JLabel();
		center.setBounds(0, 0, 162, 162);
		// center.setIcon(new ImageIcon("images\\dice.gif"));
		this.add(center, new GridBagConstraints(1, 1, 9, 9, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));*/
	}

	
	 
	public void rollDice() {
		
		
		setNextPlayerTurn();
		double dice1 = 6 * Math.random();
		double dice2 = 6 * Math.random();
		
		if (dice1 >= 0.0d && dice1 < 1.0d) {
			this.dice1 = 1;
		} else if (dice1 >= 1.0d && dice1 < 2.0d) {
			this.dice1 = 2;
		} else if (dice1 >= 2.0d && dice1 < 3.0d) {
			this.dice1 = 3;
		} else if (dice1 >= 3.0d && dice1 < 4.0d) {
			this.dice1 = 4;
		} else if (dice1 >= 4.0d && dice1 < 5.0d) {
			this.dice1 = 5;
		} else if (dice1 >= 5.0d && dice1 <= 6.0d) {
			this.dice1 = 6;
		}
		if (dice2 >= 0.0d && dice2 < 1.0d) {
			this.dice2 = 1;
		} else if (dice2 >= 1.0d && dice2 < 2.0d) {
			this.dice2 = 2;
		} else if (dice2 >= 2.0d && dice2 < 3.0d) {
			this.dice2 = 3;
		} else if (dice2 >= 3.0d && dice2 < 4.0d) {
			this.dice2 = 4;
		} else if (dice2 >= 4.0d && dice2 < 5.0d) {
			this.dice2 = 5;
		} else if (dice2 >= 5.0d && dice2 <= 6.0d) {
			this.dice2 = 6;
		}
		central.updateDice(this.dice1, this.dice2);
		((Monopoli)this.getParent()).addToHistory(getCurrentPlayerTurn().getName()+" rolled the dice <"+this.dice1+","+this.dice2+">");
	}

	public Player getNextPlayerTurn() {
		
		
		if (getCurrentPlayerTurn()==null)
			return turn=this.players.get(0);
		getCurrentPlayerTurn().setPaidRent(false); //gia pi8anws proigoumeni plirwmi 
		if (this.dice1 != this.dice2) {
			for (int i = 0; i < 2; i++) {
				if (getCurrentPlayerTurn().equals(this.players.get(i))) {
					if (i == 0){
						return this.players.get(1);
					}
					else{
						
						return this.players.get(0);
					}
				}
			}
		}
		// an einai idia ta zaria xanapaizei
		//plirwse ypoxrewtika oti rent xreiazetai
		turn.payRent();
		return turn;
	}
	private Player setNextPlayerTurn(){
		
		return turn=getNextPlayerTurn();
	}

	public Player getCurrentPlayerTurn() {
		
		return turn;
	}

	public void nextMove() {
		rollDice();
		Player currentplayer = getCurrentPlayerTurn();
		Cell currcell = currentplayer.getOnCell();
		currcell.removePlayer(currentplayer); // afairese ton paikti pou 8a kini8ei
											// apo tin twrini tou thesi
		Cell nextcell = getCurrentPlusDiceCell(currcell, dice1, dice2);
		nextcell.addPlayer(currentplayer);
		((Monopoli)parent).repaint();
		central.placeDescription(getCurrentPlayerTurn().getOnCell());
	}
	

	public Vector<Cell> getCells() {
		return cells;
	}

	public Vector<Player> getPlayers() {
		return players;
	}
	public Vector<Chance> getChanceCards() {
		return chancecards;
	}
	public Vector<CommunityChest> getCommunityChestCards() {
		return communitychestcards;
	}

	public Cell getCell(int i) {
		return cells.get(i);
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public Cell getCurrentPlusDiceCell(Cell current, int dice1, int dice2) {
		int isPlaced = current.getId();
		if (dice1 + dice2 + isPlaced > 39) {
			int i;
			for (i = 0; i <= dice1 + dice2; i++) {
				if (i + isPlaced == 39) {
					break;
				}

			}
			isPlaced = dice1 + dice2 - i - 1;
		} else if (dice1 + dice2 + isPlaced <= 39) {
			isPlaced = isPlaced + dice1 + dice2;
		}
		return getCell(isPlaced); // new position

	}
	
	public void repaint() {
		super.repaint();
		if (parent!=null && ((Monopoli)parent).controlpanel!=null )
			((Monopoli)parent).controlpanel.updateCP();
		
		
	}
	public JFrame getParent(){
		return parent;
	}
	protected void setParent(JFrame jf){
		parent=jf;
	}
	
	public void requestRent(Player pl){
		Player playertopay=null;
		if (this.getPlayer(0).equals(pl)){
			playertopay=this.getPlayer(1);
		}
		else if (this.getPlayer(1).equals(pl)){
			playertopay=this.getPlayer(0);
		}
		playertopay.payRent();
	}
	
	public void highlightMonopoly(int i,boolean bool){ //for current player
		for(Cell cell: getCurrentPlayerTurn().getMonopoly(i)){
			cell.setHighlighted(bool);
		}
	}
	public void clearHighlights(){
		for (Cell cell: getCells()){
			cell.setHighlighted(false);
		}
	}


}
