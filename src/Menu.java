import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public abstract class Menu extends JPanel implements LaptopKeyboard {
	protected JFrame parent;
	protected Vector<JLabel> choices;
	protected JLabel highlighted;
	Menu(JFrame parent){
		choices=new Vector<JLabel>();
		this.setLayout(null);
		this.parent=parent;
		this.setSize(240, 200);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		//int offsety=40;
		this.setOpaque(true);
		if (this instanceof StartMenu){
			((StartMenu)this).fillChoiceVector();
		}
		else if (this instanceof OptionsMenu){
			((OptionsMenu)this).fillChoiceVector();
			
		}
	/*	for (int i=0; i<choices.length; i++){
			choices[i].setFont(myFont);
			choices[i].setOpaque(false);
			FontMetrics fm = choices[i].getFontMetrics(myFont);
			choices[i].setBounds(30,offsety,fm.stringWidth(choices[i].getText()),fm.getHeight());
			offsety=offsety+choices[i].getHeight()+4;
			choices[i].setForeground(Color.WHITE);
			this.add(choices[i]);
		}*/
		
		this.fillMenuWithChoices();
		
		JLabel select=new JLabel("  Select  ");
		select.setFont(myFont);
		FontMetrics fm = select.getFontMetrics(myFont);
		select.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
		select.setForeground(Color.WHITE);
		select.setBorder(new LineBorder(Color.WHITE));
		this.add(select);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.highlightLabel(choices.get(0));
		highlighted=choices.get(0);
		this.setLocation(0, 320-getHeight());
		repaint();
	}
	public void fillMenuWithChoices(){
		int offsety=40;
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		for (int i=0; i<choices.size(); i++){
			choices.get(i).setFont(myFont);
			choices.get(i).setOpaque(false);
			FontMetrics fm = choices.get(i).getFontMetrics(myFont);
			choices.get(i).setBounds(30,offsety,fm.stringWidth(choices.get(i).getText()),fm.getHeight());
			offsety=offsety+choices.get(i).getHeight()+4;
			choices.get(i).setForeground(Color.WHITE);
			this.add(choices.get(i));
		}
	}
	
	public JFrame getParent() {
		return parent;
	}
	public void highlightLabel(JLabel label){
		//if no highlighted - highlight the first element...
		if (highlighted==null){
			highlighted=choices.get(0);
			choices.get(0).setBackground(new Color(0.3f,0.3f,0.3f));
			choices.get(0).setOpaque(true);
			return;
		}
		//highlight this
		//unhighlight all the others
		for (int i=0; i<choices.size(); i++){
			if (choices.get(i).equals(label)){
				label.setBackground(new Color(0.3f,0.3f,0.3f));
				label.setOpaque(true);
				this.highlighted=label;
			}
			else {
				choices.get(i).setOpaque(false);
				choices.get(i).setBackground(null);
			}
		}
		getParent().repaint();
	}
	public void keyHandler(KeyEvent e) {
		if(e.getKeyCode()==left || e.getKeyCode()==up){ //left or up	
			if (this.highlighted.equals(choices.get(0))){
				highlightLabel(choices.get(choices.size()-1));
			}
			else {
				int highlightpos;
				for (highlightpos=1; highlightpos<choices.size(); highlightpos++){
					if (choices.get(highlightpos).equals(this.highlighted)){
						break;
					}
				}
				
				highlightLabel(choices.get(highlightpos-1));
			}
		}	
		else if(e.getKeyCode()==right || e.getKeyCode()==down){  // right or down			
			if (this.highlighted.equals(choices.get(choices.size()-1))){
				highlightLabel(choices.get(0));
			}
			else {
				int highlightpos;
				for (highlightpos=0; highlightpos<choices.size()-1; highlightpos++){
					if (choices.get(highlightpos).equals(this.highlighted)){
						break;
					}
				}
				
				highlightLabel(choices.get(highlightpos+1));
			}
		}
		else if(e.getKeyCode()==select){ 
			((Monopoli)getParent()).messageDispatcher(this, highlighted.getText());
		}
	}
	
}
