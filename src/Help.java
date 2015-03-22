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
public class Help extends JPanel implements LaptopKeyboard {
	String message;
	Monopoli parent;
	JLabel messagelabel;
	JScrollPane jsp;
	Help(Monopoli parent,String message){
		this.parent=parent;
		this.message=message;
		this.setLayout(null);
		this.setSize(240, 200);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		JTextArea jta = new JTextArea(message);
		jsp = new JScrollPane(jta);	 
		jta.setFont(new Font("ARIAL" ,Font.PLAIN, 9));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(35,15,180,100);
		jsp.setBounds(35,15,180,100);
		jta.setBackground(new Color(0.0f,0.0f,0.0f));
		jsp.setBackground(new Color(0.0f,0.0f,0.0f));
		jta.setForeground(Color.WHITE);
		this.add(jsp);
		
		//FontMetrics fm = messagelabel.getFontMetrics(myFont);
		/*messagelabel = new JLabel(message);
		this.setSize(240, 200);
		
		messagelabel.setBounds(3,getHeight()-fm.getHeight(),fm.stringWidth(message),fm.getHeight());
		messagelabel.setForeground(Color.WHITE);
		messagelabel.setFont(myFont);
		add(messagelabel);*/
		JLabel back=new JLabel("  Back ");
		back.setFont(myFont);
		FontMetrics fm = back.getFontMetrics(myFont);
		back.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
		back.setForeground(Color.WHITE);
		back.setBorder(new LineBorder(Color.WHITE));
		this.add(back);
		
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.setLocation(0, 320-getHeight());
		getParent().repaint();
		
	}
	public JFrame getParent() {
		return parent;
	}
	
	
	
	public void keyHandler(KeyEvent e) {	
		if(e.getKeyCode()==select){ //Select
			((Monopoli)getParent()).RemoveDialogs(true);
			
		}
		
	}
	
	/*public void scrollUp(){
		Point pold=jsp.getViewport().getViewPosition();
		if (pold.y<=0)
			return;
		jsp.getViewport().setViewPosition(new Point(pold.x,pold.y-4));
		getParent().repaint();
	}
	public void scrollDown(){
		Point pold=jsp.getViewport().getViewPosition();
		System.out.println(pold);
		if (pold.y>=100) 
			return;
		jsp.getViewport().setViewPosition(new Point(pold.x,pold.y+4));
		getParent().repaint();
	}*/
}
