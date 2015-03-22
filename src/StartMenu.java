/*import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
*/
import java.io.File;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartMenu extends Menu {
	private static JLabel[] possiblechoices={new JLabel(" Start New Game "),new JLabel(" Load "),new JLabel(" Save "),new JLabel(" History "),new JLabel(" Game Rules "),new JLabel(" Credits "),new JLabel(" Back "),new JLabel(" Exit ")};
	StartMenu(JFrame parent){
		super(parent);
	}

	public void fillChoiceVector(){
		super.choices.removeAllElements();
		for (int i=0; i<possiblechoices.length; i++){
			if (i==1){
				File file=new File("save.txt");
				if(file.exists()){
					super.choices.add(possiblechoices[i]);
				}
			}
			else
				super.choices.add(possiblechoices[i]);
		}
	}
}












/*import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class StartMenu extends JPanel implements LaptopKeyboard {
	private JFrame parent;
	private JLabel[] choices={new JLabel(" Start New Game "),new JLabel(" Game Rules "),new JLabel(" Credits "),new JLabel(" Exit ")};
	private JLabel highlighted;
	StartMenu(JFrame parent){
		this.setLayout(null);
		this.parent=parent;
		this.setSize(240, 200);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		int offsety=40;
		this.setOpaque(true);
		for (int i=0; i<choices.length; i++){
			choices[i].setFont(myFont);
			choices[i].setOpaque(false);
			FontMetrics fm = choices[i].getFontMetrics(myFont);
			choices[i].setBounds(30,offsety,fm.stringWidth(choices[i].getText()),fm.getHeight());
			offsety=offsety+choices[i].getHeight()+4;
			choices[i].setForeground(Color.WHITE);
			this.add(choices[i]);
		}
		JLabel select=new JLabel("  Select  ");
		select.setFont(myFont);
		FontMetrics fm = select.getFontMetrics(myFont);
		select.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
		select.setForeground(Color.WHITE);
		select.setBorder(new LineBorder(Color.WHITE));
		this.add(select);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
		this.highlightLabel(choices[0]);
		highlighted=choices[0];
		this.setLocation(0, 320-getHeight());
		repaint();
	}
	public JFrame getParent() {
		return parent;
	}
	public void highlightLabel(JLabel label){
		//if no highlighted - highlight the first element...
		if (highlighted==null){
			highlighted=choices[0];
			choices[0].setBackground(new Color(0.3f,0.3f,0.3f));
			choices[0].setOpaque(true);
			return;
		}
		//highlight this
		//unhighlight all the others
		for (int i=0; i<choices.length; i++){
			if (choices[i].equals(label)){
				label.setBackground(new Color(0.3f,0.3f,0.3f));
				label.setOpaque(true);
				this.highlighted=label;
			}
			else {
				choices[i].setOpaque(false);
				choices[i].setBackground(null);
			}
		}
		getParent().repaint();
	}
	public void keyHandler(KeyEvent e) {
		if(e.getKeyCode()==left || e.getKeyCode()==up){ //left or up
			
			if (this.highlighted.equals(choices[0])){
				highlightLabel(choices[choices.length-1]);
			}
			else {
				int highlightpos;
				for (highlightpos=1; highlightpos<choices.length; highlightpos++){
					if (choices[highlightpos].equals(this.highlighted)){
						break;
					}
				}
				
				highlightLabel(choices[highlightpos-1]);
			}
		}	
		else if(e.getKeyCode()==right || e.getKeyCode()==down){  // right or down
			
			if (this.highlighted.equals(choices[choices.length-1])){
				highlightLabel(choices[0]);
			}
			else {
				int highlightpos;
				for (highlightpos=0; highlightpos<choices.length-1; highlightpos++){
					if (choices[highlightpos].equals(this.highlighted)){
						break;
					}
				}
				
				highlightLabel(choices[highlightpos+1]);
			}
		}
		else if(e.getKeyCode()==select){ 
			((Monopoli)getParent()).messageDispatcher(this, highlighted.getText());
		}
	}
}
*/