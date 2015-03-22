import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
class ControlPanel extends JPanel {
	private JFrame parent;
	private JLabel jpl1;
	private JLabel jpl2;
	private JLabel jmoney1;
	private JLabel jmoney2;
	private JLabel jown1;
	private JLabel jown2;
	private JLabel jmort1;
	private JLabel jmort2;
	private JLabel jmonop1;
	private JLabel jmonop2;
	private JLabel jhouses1;
	private JLabel jhouses2;
	private JLabel jhotels1;
	private JLabel jhotels2;
	private JLabel roll;
	private JLabel start;
	private JLabel options;
	ControlPanel(JFrame parent){
		this.parent=parent;
		setBounds(0,242, 239, 79);
		setOpaque(false);
		setLayout(null);
		if (((Monopoli)parent).mainPanel.getPlayers()!=null && ((Monopoli)parent).mainPanel.getPlayers().size()>0){
			jpl1=new JLabel();
			jpl2=new JLabel();
			jmoney1=new JLabel();
			jmoney2=new JLabel();
			jown1=new JLabel();
			jown2=new JLabel();
			jmort1=new JLabel();
			jmort2=new JLabel();
			jmonop1=new JLabel();
			jmonop2=new JLabel();
			jhouses1=new JLabel();
			jhouses2=new JLabel();
			jhotels1=new JLabel();
			jhotels2=new JLabel();
			roll=new JLabel("  Roll!  ");
			jpl1.setVisible(false);
			jpl2.setVisible(false);
			jmoney1.setVisible(false);
			jmoney2.setVisible(false);
			jown1.setVisible(false);
			jown2.setVisible(false);
			jmort1.setVisible(false);
			jmort2.setVisible(false);
			jmonop1.setVisible(false);
			jmonop2.setVisible(false);
			jhouses1.setVisible(false);
			jhouses2.setVisible(false);
			jhotels1.setVisible(false);
			jhotels2.setVisible(false);
			roll.setVisible(false);
			this.add(jpl1);
			this.add(jpl2);
			this.add(jmoney1);
			this.add(jmoney2);
			this.add(jown1);
			this.add(jown2);
			this.add(jmort1);
			this.add(jmort2);
			this.add(jmonop1);
			this.add(jmonop2);
			this.add(jhouses1);
			this.add(jhouses2);
			this.add(jhotels1);
			this.add(jhotels2);
			this.setLocation(0, 320-getHeight());
			this.add(roll);
			Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 9) ;
			FontMetrics fm = jpl1.getFontMetrics(myFont);
			start=new JLabel(" Start Menu ");
			start.setFont(myFont);
			fm = start.getFontMetrics(myFont);
			start.setBounds(3,getHeight()-fm.getHeight()-4,fm.stringWidth("  Start Menu  "),fm.getHeight());
			start.setForeground(Color.WHITE);
			start.setBorder(new LineBorder(Color.WHITE));
			this.add(start);
			options=new JLabel(" Options ");
			options.setFont(myFont);
			fm = options.getFontMetrics(myFont);
			options.setBounds(getWidth()-fm.stringWidth(" Options ")-9,getHeight()-fm.getHeight()-4,fm.stringWidth("  Options  "),fm.getHeight());
			options.setForeground(Color.WHITE);
			options.setBorder(new LineBorder(Color.WHITE));
			this.add(options);
		}
	}
	public void setParent(JFrame jf){
		parent=jf;
	}
	
	public void updateCP(){
		if (((Monopoli)parent).mainPanel==null || ((Monopoli)parent).mainPanel.getPlayers()==null )
			return;
		String player1="PL1: "+((Monopoli)parent).mainPanel.getPlayer(0).getName();
		String player2="PL2: "+((Monopoli)parent).mainPanel.getPlayer(1).getName();
		String money1="$ "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getMoney());
		String money2="$ "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getMoney());
		String own1="Owns: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getOwns().size());
		String own2="Owns: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getOwns().size());
		String mort1="Mort: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getMortgagesCount());
		String mort2="Mort: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getMortgagesCount());
		String monop1="Monop: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getMonopoliesCount());
		String monop2="Monop: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getMonopoliesCount());
		String houses1="Houses: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getHousesCount());
		String houses2="Houses: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getHousesCount());
		String hotels1="Hotels: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(0).getHotelsCount());
		String hotels2="Hotels: "+String.valueOf(((Monopoli)parent).mainPanel.getPlayer(1).getHotelsCount());
		jpl1.setText(player1);
		jpl2.setText(player2);
		jmoney1.setText(money1);
		jmoney2.setText(money2);
		jown1.setText(own1);
		jown2.setText(own2);
		jmort1.setText(mort1);
		jmort2.setText(mort2);
		jmonop1.setText(monop1);
		jmonop2.setText(monop2);
		jhouses1.setText(houses1);
		jhouses2.setText(houses2);
		jhotels1.setText(hotels1);
		jhotels2.setText(hotels2);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 9) ;
		FontMetrics fm1 = jpl1.getFontMetrics(myFont);
		FontMetrics fm2 = jpl2.getFontMetrics(myFont);
		FontMetrics fmmon1=jmoney1.getFontMetrics(myFont);
		FontMetrics fmmon2=jmoney2.getFontMetrics(myFont);
		FontMetrics fmown1=jown1.getFontMetrics(myFont);
		FontMetrics fmown2=jown2.getFontMetrics(myFont);
		FontMetrics fmmort1=jmort1.getFontMetrics(myFont);
		FontMetrics fmmort2=jmort2.getFontMetrics(myFont);
		FontMetrics fmmonop1=jmonop1.getFontMetrics(myFont);
		FontMetrics fmmonop2=jmonop2.getFontMetrics(myFont);
		FontMetrics fmhouses1=jhouses1.getFontMetrics(myFont);
		FontMetrics fmhouses2=jhouses2.getFontMetrics(myFont);
		FontMetrics fmhotels1=jhotels1.getFontMetrics(myFont);
		FontMetrics fmhotels2=jhotels2.getFontMetrics(myFont);
		jpl1.setFont(myFont);
		jpl2.setFont(myFont);
		jmoney1.setFont(myFont);
		jmoney2.setFont(myFont);
		jown1.setFont(myFont);
		jown2.setFont(myFont);
		jmort1.setFont(myFont);
		jmort2.setFont(myFont);
		jmonop1.setFont(myFont);
		jmonop2.setFont(myFont);
		jhouses1.setFont(myFont);
		jhouses2.setFont(myFont);
		jhotels1.setFont(myFont);
		jhotels2.setFont(myFont);
		if (player1!=null && !player1.trim().equals("") && !player1.trim().equals("PL1: null")){
			jpl1.setBounds(10,3,fm1.stringWidth(player1), fm1.getHeight());
			jpl1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jpl1.setVisible(true);
			jmoney1.setBounds(105,3,fmmon1.stringWidth(money1), fmmon1.getHeight());
			jmoney1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jmoney1.setVisible(true);
			jown1.setBounds(150,3,fmown1.stringWidth(own1), fmown1.getHeight());
			jown1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jown1.setVisible(true);
			jmort1.setBounds(195,3,fmmort1.stringWidth(mort1), fmmort1.getHeight());
			jmort1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jmort1.setVisible(true);
			jmonop1.setBounds(10,5+fm1.getHeight(),fmmonop1.stringWidth(monop1), fmmonop1.getHeight());
			jmonop1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jmonop1.setVisible(true);
			jhouses1.setBounds(78,5+fmmon1.getHeight(),fmhouses1.stringWidth(houses1), fmhouses1.getHeight());
			jhouses1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jhouses1.setVisible(true);
			jhotels1.setBounds(150,5+fmmon1.getHeight(),fmhotels1.stringWidth(hotels1), fmhotels1.getHeight());
			jhotels1.setForeground(((Monopoli)parent).mainPanel.getPlayer(0).getPlayerColor());
			jhotels1.setVisible(true);
			
		}
		if (player2!=null && !player2.trim().equals("") && !player2.trim().equals("PL2: null")){
			jpl2.setBounds(10,3+fm1.getHeight()*2+3,fm2.stringWidth(player2), fm2.getHeight());
			jpl2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jpl2.setVisible(true);
			jmoney2.setBounds(105,3+fm1.getHeight()*2+3,fmmon2.stringWidth(money2), fmmon2.getHeight());
			jmoney2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jmoney2.setVisible(true);
			jown2.setBounds(150,3+fm1.getHeight()*2+3,fmown2.stringWidth(own2), fmown2.getHeight());
			jown2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jown2.setVisible(true);
			jmort2.setBounds(195,3+fm1.getHeight()*2+3,fmmort2.stringWidth(mort2), fmmort2.getHeight());
			jmort2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jmort2.setVisible(true);
			jmonop2.setBounds(10,5+fm2.getHeight()+fm1.getHeight()*2+3,fmmonop2.stringWidth(monop2), fmmonop2.getHeight());
			jmonop2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jmonop2.setVisible(true);
			jhouses2.setBounds(78,5+fmmon2.getHeight()+fm1.getHeight()*2+3,fmhouses2.stringWidth(houses2), fmhouses1.getHeight());
			jhouses2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jhouses2.setVisible(true);
			jhotels2.setBounds(150,5+fmmon2.getHeight()+fm1.getHeight()*2+3,fmhotels2.stringWidth(hotels2), fmhotels1.getHeight());
			jhotels2.setForeground(((Monopoli)parent).mainPanel.getPlayer(1).getPlayerColor());
			jhotels2.setVisible(true);
		}
		roll.setFont(myFont);
		FontMetrics fm = roll.getFontMetrics(myFont);
		roll.setBounds(getWidth()/2-fm.stringWidth("Roll!")/2-3,getHeight()-fm.getHeight()-4,fm.stringWidth("Roll!")+10,fm.getHeight());
		//System.out.println("now:"+((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getPlayerColor());
		//System.out.println("next:"+((Monopoli)parent).mainPanel.getNextPlayerTurn().getPlayerColor());
		if (((Monopoli)parent).mainPanel.getCurrentPlayerTurn()!=null){
			this.setBorder(new LineBorder(((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getPlayerColor(),2));
		}
		roll.setForeground((((Monopoli)parent).mainPanel.getNextPlayerTurn().getPlayerColor()));
		roll.setBorder(new LineBorder((((Monopoli)parent).mainPanel.getNextPlayerTurn().getPlayerColor())));
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.7f));
		this.setVisible(true);
	}	
	
	public void buttonsSetVisible(boolean bool){
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 9) ;
		roll.setFont(myFont);
		roll.setText(" Roll!");
		FontMetrics fm = roll.getFontMetrics(myFont);
		roll.setBounds(getWidth()/2-fm.stringWidth("Roll!")/2-3,getHeight()-fm.getHeight()-4,fm.stringWidth("Roll!")+10,fm.getHeight());
		roll.setForeground((((Monopoli)parent).mainPanel.getNextPlayerTurn().getPlayerColor()));
		roll.setVisible(bool);
		start.setVisible(bool);
		options.setVisible(bool);
		repaint();
	}
	public void selectButtonOnly(String str){
		//System.out.println(str);
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 9) ;
		roll.setFont(myFont);
		roll.setText(" "+str+" ");
		FontMetrics fm = roll.getFontMetrics(myFont);
		roll.setBounds(getWidth()/2-fm.stringWidth(" "+str+" ")/2-3,getHeight()-fm.getHeight()-4,fm.stringWidth(" "+str +" ")+10,fm.getHeight());
		roll.setForeground(Color.WHITE);
		roll.setBorder(new LineBorder(Color.WHITE));
		start.setVisible(false);
		//options.setVisible(false);
		repaint();
	}
}