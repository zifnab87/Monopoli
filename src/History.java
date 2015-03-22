import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;


public class History extends JPanel implements LaptopKeyboard {

		private JFrame parent;
		private JLabel firstLabel;
		private JLabel highlighted;
		private JScrollPane jsp;
		private JTextArea jta;
		private static String history="";

		public History(JFrame parent,String firstChoice)
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
			//this.add(firstLabel);
			//highlightLabel(firstLabel, true);
			JLabel back=new JLabel("  Back ");
			back.setFont(myFont);
			fm = back.getFontMetrics(myFont);
			back.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
			back.setForeground(Color.WHITE);
			back.setBorder(new LineBorder(Color.WHITE));
			this.add(back);
			jta = new JTextArea(history);
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
		public void addToHistory(String str){
			remove(jsp);
			history=new String(str+history);
			jta.setText(history);
			System.out.println("bika");
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
			repaint();
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
			
			jsp.getViewport().setViewPosition(new Point(pold.x,pold.y+4));
			getParent().repaint();
		}

}
