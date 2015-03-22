import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class YesNoOkDialog extends JPanel 
{
	private JFrame parent;
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JLabel infoLabel;
	private JLabel highlighted;

	public YesNoOkDialog(JFrame parent,String info,String firstChoice,String secondChoice)
	{
		this.parent=parent;
		this.setLayout(null);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		firstLabel = new JLabel(firstChoice);
		firstLabel.setFont(myFont);
		this.setSize(240, 200);
		FontMetrics fm = firstLabel.getFontMetrics(myFont);
		firstLabel.setBounds(3,getHeight()-2*fm.getHeight(),fm.stringWidth(firstChoice),fm.getHeight());
		firstLabel.setForeground(Color.WHITE);
		this.add(firstLabel);
		highlightLabel(firstLabel, true);
		if (secondChoice!=null && !secondChoice.trim().equals("")){
			secondLabel = new JLabel(secondChoice);
			secondLabel.setFont(myFont);
			highlightLabel(secondLabel,false);
			fm = secondLabel.getFontMetrics(myFont);
			secondLabel.setBounds(237-fm.stringWidth(secondChoice)-3, getHeight()-2*fm.getHeight(), fm.stringWidth(secondChoice), fm.getHeight());
			secondLabel.setForeground(Color.WHITE);
			this.add(secondLabel);
			highlightLabel(secondLabel, false);
		}
		JLabel call=new JLabel("  Select  ");
		call.setFont(myFont);
		fm = call.getFontMetrics(myFont);
		call.setBounds(3,getHeight()-fm.getHeight(),fm.stringWidth(firstChoice)+20,fm.getHeight());
		call.setForeground(Color.GREEN);
		this.add(call);
		JLabel hangup=new JLabel("  Exit  ");
		hangup.setFont(myFont);
		fm = call.getFontMetrics(myFont);
		hangup.setBounds(237-fm.stringWidth("  Exit  "), getHeight()-fm.getHeight(),fm.stringWidth(firstChoice)+20,fm.getHeight());
		hangup.setForeground(Color.RED);
		this.add(hangup);
		
		infoLabel = new JLabel(info);
		infoLabel.setFont(myFont);
		fm = infoLabel.getFontMetrics(myFont);
		infoLabel.setBounds(10, 10, fm.stringWidth(info), 50);
		infoLabel.setForeground(Color.WHITE);
		this.add(infoLabel);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.setLocation(0, 320-getHeight());	
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
		if(e.getKeyCode()==37 || e.getKeyCode()==38){ //left or up
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
		}
		else if(e.getKeyCode()==82){ //S
			((Monopoli)getParent()).messageDispatcher(this, highlighted.getText());
			((Monopoli)getParent()).yesNoRemove();
		}
		
		else if(e.getKeyCode()==112){ //F1	
			((Monopoli)getParent()).startMenuInit(true);
		}
		else if(e.getKeyCode()==113){ //F2 	
			((Monopoli)getParent()).yesNoInit("<html>Μόλις σας έτυχε επιταγή των 200<p>Ευρώ Δέχεστε?</html>","  Ναι  "," Οχι ");
		}
		else if(e.getKeyCode()==114){ //F3	
			((Monopoli)getParent()).yesNoInit("Σας λύθηκε μια πολύτιμη απορία! Δώσατε 50$","  ΟΚ grrr  ",null);
		}
		else if(e.getKeyCode()==115){ //F4
			((Monopoli)getParent()).RemoveDialogs(true);
		}
		else  if(e.getKeyCode()==116){ //F5
			((Monopoli)getParent()).PlayerSettingInit("Player 1 Settings",Color.BLACK);
		}
	}
}
