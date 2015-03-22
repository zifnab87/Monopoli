import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
public class JViewPortBug extends JFrame {
	private JScrollPane jsp;
	public JViewPortBug() {
		this.setBounds(15,15,200,200);
		setVisible(true);
		JPanel jpanel = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpanel.setBounds(0,0,200,200);
		JTextArea jta = new JTextArea(50,10);
		jsp = new JScrollPane(jta);	
		jta.setText("Bla bla bla bla bla bla bla " +
				"bla bla bla Bla bla bla bla bla bnla " +
				"bla bla bla bla Bla bla bla bla bla bla " +
				"bla bla bla bla");
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jta.setBounds(30,15,180,100);
		jsp.setBounds(30,15,180,100);
		jsp.setBackground(Color.BLACK);
		
		getContentPane().add(jpanel);
		jpanel.add(jsp);
		moveScroll(); 
		repaint();
		//for debugging
		
		KeyAdapter kadapter = new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				moveScroll(); // it does the thing...
			}
		};
		this.addKeyListener(kadapter);
		
	}

	public static void main(String args[]){
		new JViewPortBug();
	}
	public void moveScroll(){
		System.out.println("before "+getPosition());
		jsp.getViewport().setViewPosition(new Point(10,10));
		System.out.println("after "+getPosition());
		repaint(); //not actually needed - just to be sure...
	}
	public Point getPosition(){
		return jsp.getViewport().getViewPosition();
	}
	
	
}
