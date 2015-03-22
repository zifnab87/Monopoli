import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class PlayerSettingDialog extends JPanel implements LaptopKeyboard {
	private JFrame parent;
	private JLabel firstLabel;
	private JLabel secondLabel;
	private JLabel[] letterlabels;
	private JLabel highlighted;
	private static String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZ<.- ";
	private static final int NAMECHOOSERMODE=0;
	private static final int COLORCHOOSERMODE=1;
	private static final int FINALCHOICEMODE=2;
	private static int mode;
	private Color colorused;
	public String titlestr;
	PlayerSettingDialog(JFrame parent,String titlestr,Color colorused){	
		this.colorused=colorused;
	    mode=NAMECHOOSERMODE;
		this.titlestr=titlestr;
		this.setLayout(null);
		this.parent=parent;
		this.setSize(240, 200);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
		this.setOpaque(true);
		JLabel title = new JLabel(titlestr);
		FontMetrics fm = title.getFontMetrics(myFont);
		title.setBounds(getWidth()/2-fm.stringWidth(titlestr)/2,0,fm.stringWidth(titlestr)*2,fm.getHeight()); //giati X2?! :(
		title.setForeground(Color.WHITE);
		this.add(title);
		JLabel jl=null;
		int offsetx=10;
		int offsety=20;
		//add letter labels
		for(int i=0; i<letters.length(); i++){
			jl=new JLabel("  "+Character.toString(letters.charAt(i)));
			if (i==29){
				jl=new JLabel("=>");
			}
			jl.setLocation(offsetx,offsety);
			jl.setOpaque(false);
			jl.setHorizontalTextPosition(SwingConstants.CENTER);
			jl.setForeground(Color.WHITE);
			jl.setSize(18,16);
			jl.repaint();
			this.add(jl,i);
			offsetx+=15;
			if (i==5 || i==11 || i==17 || i==23 || i==29){
				offsetx=10;
				offsety+=12;
			}
		}
		//add name taxtfield
		JTextField jtf = new JTextField(10);
		jtf.setEnabled(false);
		jtf.setBounds(120, 50, 100, 15);
		jtf.setBackground(new Color(0.0f,0.0f,0.0f));
		jtf.setOpaque(true);
		jtf.setForeground(Color.WHITE);
		this.add(jtf,30);
		//add color labels
		Color[] colors={new Color(0.8f,0.0f,0.0f),new Color(0.1f,0.4f,0.2f),new Color(0.1f,0.3f,0.8f),
				new Color(1.0f,0.89f,0.207f),new Color(0.211f,0.99f,0.4666f),new Color(0.8862f,0.2705f,0.9372f)};
		offsetx=35;
		JLabel jcolor=null;
		for (int i=31; i<=36; i++){
			
				jcolor=new JLabel("   ");
				jcolor.setSize(20,20);
				jcolor.setLocation(offsetx,100);
				jcolor.setBorder(new LineBorder(new Color(0.8f,0.8f,0.8f)));
				jcolor.setForeground(Color.WHITE);
				jcolor.setBackground(colors[i-31]);
				jcolor.setOpaque(true);
				if (colors[i-31].equals(colorused)){
					jcolor.setVisible(false);
				}
				this.add(jcolor,i);
				offsetx+=30;
			
		}
		
		firstLabel = new JLabel("OK");
		firstLabel.setFont(myFont);
		
		this.setSize(240, 200);
		FontMetrics fms= firstLabel.getFontMetrics(myFont);
		firstLabel.setBounds(3,getHeight()-fms.getHeight(),fms.stringWidth("OK"),fms.getHeight());
		firstLabel.setForeground(Color.WHITE);
		this.add(firstLabel);
		secondLabel = new JLabel("Back");
		secondLabel.setFont(myFont);
		highlightLabel(secondLabel,false);
		fms = secondLabel.getFontMetrics(myFont);
		secondLabel.setBounds(237-fms.stringWidth("Back"), getHeight()-fms.getHeight(), fms.stringWidth("Back"), fm.getHeight());
		secondLabel.setForeground(Color.WHITE);
		firstLabel.setVisible(false);
		this.add(secondLabel);
		highlightLabel(secondLabel, false);
		
		JLabel select=new JLabel("  Select  ");
		select.setFont(myFont);
		fm = select.getFontMetrics(myFont);
		select.setBounds(getWidth()/2-fm.stringWidth(" Select ")/2-3,getHeight()-fm.getHeight(),fm.stringWidth(" Select ")+10,fm.getHeight());
		select.setForeground(Color.WHITE);
		select.setBorder(new LineBorder(Color.WHITE));
		this.add(select);
		this.highlightLabel((JLabel)(this.getComponent(0)));
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.85f));
		this.setVisible(true);
		this.setLocation(0, 320-getHeight());
		getParent().repaint();
	}
	public JFrame getParent(){
		return parent;
	}
	private void highlightColorLabel(JLabel label){
		if (highlighted==null){
			((JLabel)(this.getComponent(29))).setOpaque(false);
			((JLabel)(this.getComponent(29))).setBackground(null);
			if(!this.getComponent(31).isVisible())
			{
				highlighted=((JLabel) (this.getComponent(32)));
			}
			else
				highlighted=((JLabel) (this.getComponent(31)));
			highlighted.setBorder(new LineBorder(Color.RED,3));
			return;
		}
		for (int i=31; i<=36; i++){
			if (((JLabel)(this.getComponent(i))).equals(label)){
				label.setBorder(new LineBorder(Color.RED,3));
				this.highlighted=label;
			}
			else {
				((JLabel)(this.getComponent(i))).setBorder(new LineBorder(new Color(0.8f,0.8f,0.8f)));
			}
		}
		getParent().repaint();
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
		getParent().repaint();
	}
	
	private void highlightLabel(JLabel label){
		//if no highlighted - highlight the first element...
		if (highlighted==null){
			highlighted=((JLabel) (this.getComponent(0)));
			highlighted.setBackground(new Color(0.3f,0.3f,0.3f));
			highlighted.setOpaque(true);
			getParent().repaint();
			return;
		}
		//highlight this
		//unhighlight all the others
		for (int i=0; i<30; i++){
			if (((JLabel)(this.getComponent(i))).equals(label)){
				label.setBackground(new Color(0.3f,0.3f,0.3f));
				label.setOpaque(true);
				this.highlighted=label;
			}
			else {
				((JLabel)(this.getComponent(i))).setOpaque(false);
				((JLabel)(this.getComponent(i))).setBackground(null);
			}
		}
		getParent().repaint();
	}
	
	public void keyHandler(KeyEvent e) {
		if(e.getKeyCode()==left || e.getKeyCode()==up){ //left or up			
			if(mode==NAMECHOOSERMODE){
				if (e.getKeyCode()==left){
					highlightLeftLetter();
				}
				if (e.getKeyCode()==up){
					highlightUpLetter();
				}
			}
			else if(mode==COLORCHOOSERMODE) {
				if (this.highlighted.equals(((JLabel)(this.getComponent(31))))){
					if (!(this.getComponent(36)).isVisible()){
						highlightLabel(((JLabel)(this.getComponent(35))));
					}
					else 
						highlightColorLabel(((JLabel)(this.getComponent(36))));
				}
				else {
					int highlightpos;
					for (highlightpos=32; highlightpos<=36; highlightpos++){
						if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
							break;
						}
					}
					if(highlightpos==32)
					{
						if (!(this.getComponent(31)).isVisible())
							highlightpos=36;
						else
							highlightpos=31;
					}
					else
					{
						highlightpos--;
					}
					highlightColorLabel(((JLabel)(this.getComponent(highlightpos))));
				}
			}
			else if(mode==FINALCHOICEMODE) {
				if (highlighted.equals(secondLabel)){
					highlightLabel(secondLabel, false);
					highlightLabel(firstLabel, true);	
				}
			}
		}	
		else if(e.getKeyCode()==right || e.getKeyCode()==down){  // right or down			
				if(mode==NAMECHOOSERMODE){
					if (e.getKeyCode()==right){
						highlightRightLetter();
					}
					else if(e.getKeyCode()==down){
						highlightDownLetter();
					}
				}
				else if(mode==COLORCHOOSERMODE) {
					if (this.highlighted.equals(((JLabel)(this.getComponent(36))))){
						if (!(this.getComponent(31)).isVisible()){
							highlightColorLabel(((JLabel)(this.getComponent(32))));
						}
						else 
							highlightColorLabel(((JLabel)(this.getComponent(31))));
					}
					else {
						int highlightpos;
						for (highlightpos=31; highlightpos<=36; highlightpos++){
							if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
								break;
							}
						}
						if (!(this.getComponent(highlightpos+1)).isVisible()){
							highlightpos++;
						}
						highlightColorLabel(((JLabel)(this.getComponent(highlightpos+1))));
					}
				}
				else if(mode==FINALCHOICEMODE) {
					if (highlighted.equals(firstLabel)){
						highlightLabel(firstLabel, false);
						highlightLabel(secondLabel, true);	
					}
				}
		}	
		else if(e.getKeyCode()==select){ //S	
			if (mode==NAMECHOOSERMODE){
				//oloklirw8ike to onoma
				if (((JTextField)(this.getComponent(30))).getText().length()==9 && !this.highlighted.getText().trim().equals("<") && !this.highlighted.getText().trim().equals("=>") ){ // an paei na ginei to onoma panw apo 10 stop! vale highlight sto =>
					this.highlightLabel((JLabel)this.getComponent(29));
					return;
				}
				else if(this.highlighted.getText().trim().equals("<") && (((JTextField)(this.getComponent(30))).getText()).length()!=0){ 
					((JTextField)(this.getComponent(30))).setText(((JTextField)(this.getComponent(30))).getText().substring(0, ((JTextField)(this.getComponent(30))).getText().length()-1));
				}
				else if(this.highlighted.getText().trim().equals("=>")){ 
					this.highlighted=null;
					this.mode=COLORCHOOSERMODE; // telos name chooser -> color chooser twra
					//steile minima sto JFrame gia to onoma tou paikti kai emfwleuse kai poios einai o 1 i o 2...
					((Monopoli)this.getParent()).messageDispatcher(this, this.titlestr+" ,"+((JTextField)this.getComponent(30)).getText()+" ,");
					this.highlightColorLabel((JLabel)this.getComponent(31));
					
				}
				else if (((JTextField)(this.getComponent(30))).getText().trim().equals("") && !this.highlighted.getText().trim().equals("<")){ //an einai to prwto gramma kanto kefalaio
					((JTextField)(this.getComponent(30))).setText(((JTextField)(this.getComponent(30))).getText()+highlighted.getText().trim().toUpperCase());
				}	//pros8ese to gramma apo to highlighted sto onoma paikti
				else if(!(this.highlighted.getText().trim().equals("<"))) {
					((JTextField)(this.getComponent(30))).setText(((JTextField)(this.getComponent(30))).getText()+highlighted.getText().trim().toLowerCase());
				}
			}
			else if (mode==COLORCHOOSERMODE) {
				//steile minima sto JFrame gia to xrwma tou paikti... kai emfwleuse pliroforia poios einai o 1 i o 2...
				((Monopoli)this.getParent()).messageDispatcher(this, this.titlestr+"|"+highlighted.getBackground().getRed()+"|"+highlighted.getBackground().getGreen()+"|"+highlighted.getBackground().getBlue());
				firstLabel.setVisible(true);
				highlightLabel(firstLabel, true);
				highlightLabel(secondLabel, false);
				mode=FINALCHOICEMODE; 
				
			}	
			else if (mode==FINALCHOICEMODE) {
				if (highlighted.getText().equalsIgnoreCase("Back")){
					this.mode=NAMECHOOSERMODE;
					highlighted=(JLabel)this.getComponent(0);
					highlightLabel((JLabel)this.getComponent(0));
					highlightLabel(secondLabel, false);
					firstLabel.setVisible(false);
					
					
				}
				else if(highlighted.getText().equalsIgnoreCase("OK")){
					((Monopoli)getParent()).PlayerSettingRemove();
					((Monopoli)getParent()).repaint();
					
					if (titlestr.contains("Player 1")){ //akolou8ei kai allos paiktis?
						((Monopoli)getParent()).addToHistory("Player 1 Configuration Completed");
						((Monopoli)getParent()).PlayerSettingInit("Player 2 Settings",colorused);
						((Monopoli)getParent()).repaint();
						mode=NAMECHOOSERMODE;
					}
					if (titlestr.contains("Player 2")){ //de akolou8ei allos paiktis
						((Monopoli)getParent()).addToHistory("Player 2 Configuration Completed");
						((Monopoli)getParent()).repaint();
					}
				}
			}		
		}
		else if(e.getKeyCode()==F1){ //F1	
			if (firstLabel.isVisible()){
				highlightLabel(firstLabel, true);
				((Monopoli)getParent()).PlayerSettingRemove();
				((Monopoli)getParent()).repaint();
				if (titlestr.contains("Player 1")){ //akolou8ei kai allos paiktis?
					((Monopoli)getParent()).PlayerSettingInit("Player 2 Settings",colorused);
					mode=NAMECHOOSERMODE;
				}
				if (titlestr.contains("Player 2")){ //de akolou8ei allos paiktis
					((Monopoli)getParent()).mainPanel.repaint();
				}
			}
			((Monopoli)getParent()).repaint();
		}
		else if(e.getKeyCode()==F2){ //F2 	
		
			highlightLabel(secondLabel, true);
			this.mode=NAMECHOOSERMODE;
			highlighted=(JLabel)this.getComponent(0);
			highlightLabel((JLabel)this.getComponent(0));
			highlightLabel(secondLabel, false);
			firstLabel.setVisible(false);
		}
		((Monopoli)getParent()).repaint();
	}
	public Color getColorused() {
		return colorused;
	}
	public void setColorused(Color colorused) {
		this.colorused = colorused;
	}
	public void highlightUpLetter(){
		int ref=-1;
		for (int i=0; i<30; i++){
			if (this.highlighted.equals(((JLabel)(this.getComponent(i))))){
				ref=i%6;
				break;
			}
		}
		if (this.highlighted.equals(((JLabel)(this.getComponent(0))))){
			highlightLabel(((JLabel)(this.getComponent(24))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(1))))){
			highlightLabel(((JLabel)(this.getComponent(25))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(2))))){
			highlightLabel(((JLabel)(this.getComponent(26))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(3))))){
			highlightLabel(((JLabel)(this.getComponent(27))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(4))))){
			highlightLabel(((JLabel)(this.getComponent(28))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(5))))){
			highlightLabel(((JLabel)(this.getComponent(29))));
			return;
		}
		int highlightpos;
		for (highlightpos=ref; highlightpos<=ref+4*6; highlightpos+=6){	
			if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
				break;
			}
		}		
		highlightLabel(((JLabel)(this.getComponent(highlightpos-6))));
	}
	public void highlightDownLetter(){
		int ref=-1;
		for (int i=0; i<30; i++){
			if (this.highlighted.equals(((JLabel)(this.getComponent(i))))){
				ref=i%6;
				break;
			}
		}
		if (this.highlighted.equals(((JLabel)(this.getComponent(24))))){
			highlightLabel(((JLabel)(this.getComponent(0))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(25))))){
			highlightLabel(((JLabel)(this.getComponent(1))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(26))))){
			highlightLabel(((JLabel)(this.getComponent(2))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(27))))){
			highlightLabel(((JLabel)(this.getComponent(3))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(28))))){
			highlightLabel(((JLabel)(this.getComponent(4))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(29))))){
			highlightLabel(((JLabel)(this.getComponent(5))));
			return;
		}
		int highlightpos;
		for (highlightpos=ref; highlightpos<=ref+4*6; highlightpos+=6){	
			if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
				break;
			}
		}		
		highlightLabel(((JLabel)(this.getComponent(highlightpos+6))));
	}
	public void highlightLeftLetter(){	
		int ref=-1;
		for (int i=0; i<30; i++){
			if (this.highlighted.equals(((JLabel)(this.getComponent(i))))){
				ref=i/6;
				break;
			}
		}
		if (this.highlighted.equals(((JLabel)(this.getComponent(0))))){
			highlightLabel(((JLabel)(this.getComponent(5))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(6))))){
			highlightLabel(((JLabel)(this.getComponent(11))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(12))))){
			highlightLabel(((JLabel)(this.getComponent(17))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(18))))){
			highlightLabel(((JLabel)(this.getComponent(23))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(24))))){
			highlightLabel(((JLabel)(this.getComponent(29))));
			return;
		}
		int highlightpos;
		for (highlightpos=ref*6; highlightpos<ref*6+6; highlightpos++){		
			if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
				break;
			}
		}		
		highlightLabel(((JLabel)(this.getComponent(highlightpos-1))));	
	}
	public void highlightRightLetter(){
		int ref=-1;
		for (int i=0; i<30; i++){
			if (this.highlighted.equals(((JLabel)(this.getComponent(i))))){
				ref=i/6;
				break;
			}
		}
		if (this.highlighted.equals(((JLabel)(this.getComponent(5))))){
			highlightLabel(((JLabel)(this.getComponent(0))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(11))))){
			highlightLabel(((JLabel)(this.getComponent(6))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(17))))){
			highlightLabel(((JLabel)(this.getComponent(12))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(23))))){
			highlightLabel(((JLabel)(this.getComponent(18))));
			return;
		}
		else if (this.highlighted.equals(((JLabel)(this.getComponent(29))))){
			highlightLabel(((JLabel)(this.getComponent(24))));
			return;
		}
		int highlightpos;
		for (highlightpos=ref*6; highlightpos<ref*6+6; highlightpos++){		
			if (((JLabel)(this.getComponent(highlightpos))).equals(this.highlighted)){
				break;
			}
		}		
		highlightLabel(((JLabel)(this.getComponent(highlightpos+1))));	
	}
}
