import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.JLabel;

public class CommunityChest extends JPanel implements LaptopKeyboard,Serializable
{
	private JFrame parent;
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JLabel infoLabel;
	private JLabel highlighted;
	private int id;
	public CommunityChest(JFrame parent,String info,String firstChoice,int id)
	{
		this.id=id;
		this.parent=parent;
		this.setLayout(null);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		this.setSize(240, 250);
		JLabel title = new JLabel(" COMMUNITY CHEST ");
		
		FontMetrics fm = title.getFontMetrics(myFont);
		title.setBounds((getWidth()-fm.stringWidth(" COMMUNITY CHEST "))/2,0,fm.stringWidth(" COMMUNITY CHEST ")*2,fm.getHeight());
		title.setForeground(Color.BLACK);
		this.add(title);
		JLabel chancelogo = new JLabel();
		chancelogo.setIcon(new ImageIcon("images//communitychest.PNG"));
		chancelogo.setOpaque(true);
		chancelogo.setSize(chancelogo.getIcon().getIconWidth(),chancelogo.getIcon().getIconHeight());
		chancelogo.setLocation((getWidth()-chancelogo.getWidth())/2,20);
		this.add(chancelogo);
		firstLabel = new JLabel(firstChoice);
		firstLabel.setFont(myFont);
		fm = firstLabel.getFontMetrics(myFont);
		firstLabel.setBounds(3,getHeight()-2*fm.getHeight(),fm.stringWidth(firstChoice),fm.getHeight());
		firstLabel.setForeground(Color.WHITE);
		this.add(firstLabel);
		highlightLabel(firstLabel, true);
		infoLabel = new JLabel(info);
		infoLabel.setFont(myFont);
		fm = infoLabel.getFontMetrics(myFont);
		infoLabel.setBounds(10, chancelogo.getHeight()+30, fm.stringWidth(info), 50);
		infoLabel.setForeground(Color.BLACK);
		this.add(infoLabel);
		this.setBackground(new Color(0.9450f,0.9647f,0.3294f,0.95f));
		this.setVisible(true);
		this.setLocation(0, (320-getHeight())/2);	
	}
	

	public JFrame getParent() {
		return parent;
	}
	
	public void highlightLabel(JLabel label,boolean bool){
		if (bool){
			label.setOpaque(true);
			label.setBackground(new Color(1.0f,0.4f,0.2f));
			highlighted=label;
		}
		else {
			label.setBackground(null);
			label.setOpaque(false);
		}
	}
	
	
	public void keyHandler(KeyEvent e) {	
		/*if(e.getKeyCode()==37 || e.getKeyCode()==38){ //left or up
			if(secondLabel==null)
				return;
			if (highlighted.equals(secondLabel)){
				highlightLabel(secondLabel, false);
				highlightLabel(firstLabel, true);	
			}
			this.getParent().repaint();
		} 
		
		else if(e.getKeyCode()==39 || e.getKeyCode()==40){ //right or down
			if(secondLabel==null)
				return;
			if (highlighted.equals(firstLabel)){
				highlightLabel(firstLabel, false);
				highlightLabel(secondLabel, true);	
			}
			this.getParent().repaint();
		}*/
		if(e.getKeyCode()==select || e.getKeyCode()==up || e.getKeyCode()==down || e.getKeyCode()==left || e.getKeyCode()==right || e.getKeyCode()==F1 || e.getKeyCode()==F2){ 
			((Monopoli)getParent()).messageDispatcher(this, highlighted.getText());
			((Monopoli)getParent()).RemoveDialogs(true);
		}
	}
	public void handle(Player p){
		System.out.println("Executing special function for Community Chest Card with id="+id+" for player "+p);
	}
	public int getId(){
		return id;
	}
}