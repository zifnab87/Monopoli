import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;


public class GameRules extends JPanel implements LaptopKeyboard {

	private JFrame parent;
	private JLabel firstLabel;
	private JLabel highlighted;
	private JScrollPane jsp;

	public GameRules(JFrame parent,String firstChoice)
	{
		this.parent=parent;
		this.setLayout(null);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		firstLabel = new JLabel(firstChoice);
		firstLabel.setFont(myFont);
		this.setSize(240, 200);
		FontMetrics fm = firstLabel.getFontMetrics(myFont);
		firstLabel.setBounds(3,getHeight()-fm.getHeight(),fm.stringWidth(firstChoice),fm.getHeight());
		firstLabel.setForeground(Color.WHITE);
		this.add(firstLabel);
		highlightLabel(firstLabel, true);
		JLabel back=new JLabel("  Back ");
		back.setFont(myFont);
		fm = back.getFontMetrics(myFont);
		back.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
		back.setForeground(Color.WHITE);
		back.setBorder(new LineBorder(Color.WHITE));
		this.add(back);
		String rules="Players take turns in order, with the initial player determined by chance before the game. A typical turn begins with the rolling of the dice and advancing clockwise around the board the corresponding number of squares. Landing on Chance or Community Chest, a player draws the top card from the respective pile. If the player lands on an unowned property, whether street, railroad, or utility, he can buy the property for its listed purchase price. If he declines this purchase, the property is auctioned off by the bank to the highest bidder. If the property landed on is already owned and unmortgaged, he must pay the owner a given rent, the price dependent on whether the property is part of a monopoly or its level of development. If a player rolls doubles, he rolls again after completing his turn. Three sets of doubles in a row, however, land the player in jail. During a turn, players may also choose to develop or mortgage properties. Development involves the construction, for given amounts of money paid to the bank, of houses or hotels. Development must be uniform across a monopoly, such that a second house cannot be built on one property in a monopoly until the others have one house. No merges between players are allowed. All developments must be sold before a property can be mortgaged. The player receives money from the bank for each mortgaged property, which must be repaid with interest to unmortgage. Houses are returned to the bank for half their purchase price."
			+"Parker Brothers' official instructions have long encouraged the use of House Rules, specific additions to or subtractions from the official rule sets. Many casual Monopoly players are surprised to discover that some of the rules that they are used to are not part of the official rules. Many of these house rules tend to make the game longer by randomly giving players more money. ";
		JTextArea jta = new JTextArea(rules);
		jsp = new JScrollPane(jta);	 
		jta.setFont(new Font("ARIAL" ,Font.PLAIN, 9));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(35,15,180,100);
		jsp.setBounds(35,15,180,100);
		jta.setBackground(new Color(0.0f,0.0f,0.0f));
		jsp.setBackground(new Color(0.0f,0.0f,0.0f));
		jta.setForeground(Color.WHITE);
		this.add(jsp);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.setLocation(0, 320-getHeight());
		getParent().repaint();
	}
	

	public JFrame getParent() {
		return parent;
	}
	
	public void highlightLabel(JLabel label,boolean bool){
		if (bool){
			label.setOpaque(true);
			label.setBackground(new Color(0.3f,0.3f,0.3f));
			highlighted=label;
		}
		else {
			label.setBackground(null);
			label.setOpaque(false);
		}
	}
	
	
	public void keyHandler(KeyEvent e) {	
		if(e.getKeyCode()==up){ 
			scrollUp();
		} 
		
		else if(e.getKeyCode()==down){ //down
			scrollDown();
		}
		else if(e.getKeyCode()==select){ //S or F1
			((Monopoli)getParent()).RemoveDialogs(true);
			
		}
		
	}
	
	public void scrollUp(){
		Point pold=jsp.getViewport().getViewPosition();
		if (pold.y<=0)
			return;
		jsp.getViewport().setViewPosition(new Point(pold.x,pold.y-4));
		getParent().repaint();
	}
	public void scrollDown(){
		Point pold=jsp.getViewport().getViewPosition();
		System.out.println(pold);
		if (pold.y>=537) 
			return;
		jsp.getViewport().setViewPosition(new Point(pold.x,pold.y+4));
		getParent().repaint();
	}
	
	
}
