import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class AuctionPanel extends JPanel implements LaptopKeyboard
{
	private Player winner;
	private int value;
	private Cell curCell;
	private Monopoli parent;
	private JTextField valueInsert;
	private JLabel valuelabel;
	public AuctionPanel(Cell curCell,Monopoli parent)
	{
		this.curCell=curCell;
		this.parent=parent;
		this.value=0;
		this.winner=null;
		
		this.setLayout(null);
		this.setSize(240, 200);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 11) ;
		//title Auction Panel
		JLabel auctiontitle=new JLabel("Auction this site");
		auctiontitle.setBackground(Color.WHITE);
		auctiontitle.setFont(myFont);
		FontMetrics fm = auctiontitle.getFontMetrics(myFont);
		auctiontitle.setBounds(getWidth()/2-fm.stringWidth(auctiontitle.getText())/2,3,fm.stringWidth(auctiontitle.getText()),fm.getHeight());
		//Player 1 / Done / Player 2 Dialogs
		
		JLabel player1 = new JLabel(" Player 1 ");
		player1.setFont(myFont);
		fm = player1.getFontMetrics(myFont);
		player1.setBounds(3,getHeight()-fm.getHeight(),fm.stringWidth(" Player 1 ")+3,fm.getHeight());
		player1.setForeground(this.parent.mainPanel.getPlayer(0).getPlayerColor());
		player1.setBorder(new LineBorder(this.parent.mainPanel.getPlayer(0).getPlayerColor()));
		JLabel player2 = new JLabel(" Player 2 ");
		player2.setFont(myFont);
		fm = player2.getFontMetrics(myFont);
		player2.setBounds(237-fm.stringWidth(" Player 2 ")-3, getHeight()-fm.getHeight(), fm.stringWidth(" Player 2 ")+3, fm.getHeight());
		player2.setForeground(this.parent.mainPanel.getPlayer(1).getPlayerColor());
		player2.setBorder(new LineBorder(this.parent.mainPanel.getPlayer(1).getPlayerColor()));
		JLabel done = new JLabel(" Done ");
		done.setFont(myFont);
		done.setForeground(Color.WHITE);
		done.setBorder(new LineBorder(Color.WHITE));
		fm = done.getFontMetrics(myFont);
		done.setBounds(this.getWidth()/2-fm.stringWidth(" Done ")/2, getHeight()-fm.getHeight(), fm.stringWidth(" Done ")+3, fm.getHeight());
		
		//"Site Info:"
		Box siteInfo = Box.createVerticalBox();
		siteInfo.add(Box.createHorizontalStrut(6));
		siteInfo.setBorder(new LineBorder(Color.WHITE));
		JLabel temp = new JLabel("Name:" + curCell.getDescription());
		temp.setForeground(Color.WHITE);
		siteInfo.add(temp);
		temp =new JLabel("Auctual Price:" + curCell.getPrice());
		siteInfo.add(temp);
		temp.setForeground(Color.WHITE);
		/*temp = new JLabel("Rent:" + curCell.getRent());
		siteInfo.add(temp);
		temp.setForeground(Color.WHITE);
		temp = new JLabel("House:" + curCell.getHousePrice());
		siteInfo.add(temp);
		temp.setForeground(Color.WHITE);
		temp=new JLabel("Hotel:" + curCell.getHotelPrice());
		siteInfo.add(temp);
		temp.setForeground(Color.WHITE);*/
		siteInfo.add(Box.createHorizontalStrut(3));
		siteInfo.setSize(190, 55);
		siteInfo.setLocation((getWidth()-siteInfo.getWidth())/2,25);
		
		siteInfo.setForeground(Color.WHITE);
		
		
		//"value :"
		JLabel valuetitle= new JLabel(" Value: ");
		valuetitle.setFont(myFont);
		valuetitle.setForeground(Color.WHITE);
		valuelabel=new JLabel("00000");
		valuelabel.setOpaque(true);
		valuelabel.setBackground(Color.BLACK);
		valuelabel.setForeground(Color.WHITE);
		Box valueBox = Box.createHorizontalBox();
		valueBox.add(valuetitle);
		valueBox.add(valuelabel);
		valueBox.setSize(80,15);
		valueBox.setLocation(80,115);
		
		valueInsert= new JTextField();
		valueInsert.setBounds(80,130,80,19);
		valueInsert.setFocusable(true);
		valueInsert.setOpaque(true);
		valueInsert.setBackground(Color.BLACK);
		valueInsert.setForeground(Color.WHITE);
		
		this.add(siteInfo);
		
		this.add(valueBox);
		this.add(valueInsert);

		
		this.add(player1);
		this.add(done);
		this.add(player2);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.setLocation(0, (320-getHeight()));	
	}
	
	
	public void keyHandler(KeyEvent e)
	{
		if(e.getKeyCode()!=F1 && e.getKeyCode()!=left &&  e.getKeyCode()!=select && e.getKeyCode()!=F2 && e.getKeyCode()!=48 && e.getKeyCode()!=49 && e.getKeyCode()!=50 && e.getKeyCode()!=51 && e.getKeyCode()!=52 && e.getKeyCode()!=53 && e.getKeyCode()!=54 && e.getKeyCode()!=55 && e.getKeyCode()!=56 && e.getKeyCode()!=57)
			return;
		if(e.getKeyCode()==left){
			if(this.valueInsert.getText().length()>0)
			this.valueInsert.setText(this.valueInsert.getText().substring(0, this.valueInsert.getText().length()-1));
		}
		else if(e.getKeyCode()==F1)
		{
			if(!this.valueInsert.getText().trim().equals("") && this.value<Integer.parseInt(this.valueInsert.getText()))
			{
				value=Integer.parseInt(this.valueInsert.getText());
				winner = this.parent.mainPanel.getPlayer(0);
				this.valueInsert.setText("");
				this.valuelabel.setText(""+value);
				this.valuelabel.setForeground(winner.getPlayerColor());
			}
		}
		else if(e.getKeyCode()==F2)
		{
			if(!this.valueInsert.getText().trim().equals("") &&  this.value<Integer.parseInt(this.valueInsert.getText()))
			{
				value=Integer.parseInt(this.valueInsert.getText());
				winner = this.parent.mainPanel.getPlayer(1);
				this.valueInsert.setText("");
				this.valuelabel.setText(""+value);
				this.valuelabel.setForeground(winner.getPlayerColor());
			}
		}
		else if(e.getKeyCode()==select)
		{
			if(winner==null)
				return;
			this.winner.auctionBuy(curCell,this.value);
			this.parent.auctionRemove();
			
		}
		else
		{
			this.valueInsert.setText(this.valueInsert.getText() + e.getKeyChar());
		}
		this.getParent().repaint();
		this.getParent().controlpanel.updateCP();
	}
	
	public Monopoli getParent()
	{
		return this.parent;
	}
}