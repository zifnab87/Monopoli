import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.Serializable;
public class Central extends JPanel implements Serializable {
	JLabel dice1;
	JLabel dice2;
	Board parent;
	Box siteInfo;
	public Central(Board parent){
		this.parent=parent;
		setLayout(new BorderLayout());
		setBackground(new Color(0.0f,0.0f,0.0f));
		setSize(162,162);
		//this.setIcon(new ImageIcon("images\\dice.gif"));
		setOpaque(true);
		
		repaint();
		placeDescription(null);
		placeDice();
		updateDice(1,1);
	}
	public void placeDescription(Cell curCell){
		if (curCell==null)
			return;
		if(siteInfo!=null)
			this.remove(siteInfo);
		siteInfo = Box.createVerticalBox();
		siteInfo.add(Box.createHorizontalStrut(6));
		siteInfo.setBorder(new LineBorder(curCell.getParent().getCurrentPlayerTurn().getPlayerColor()));
		JLabel temp = new JLabel(curCell.getDescription());
		temp.setForeground(curCell.getParent().getCurrentPlayerTurn().getPlayerColor());
		siteInfo.add(temp);
		temp =new JLabel("Price:" + curCell.getPrice());
		siteInfo.add(temp);
		temp.setForeground(curCell.getParent().getCurrentPlayerTurn().getPlayerColor());
		temp = new JLabel("House:" + curCell.getHousePrice());
		siteInfo.add(temp);
		temp.setForeground(curCell.getParent().getCurrentPlayerTurn().getPlayerColor());
		temp=new JLabel("Hotel:" + curCell.getHotelPrice());
		siteInfo.add(temp);
		temp.setForeground(curCell.getParent().getCurrentPlayerTurn().getPlayerColor());
		siteInfo.add(Box.createHorizontalStrut(3));
		siteInfo.setSize(150, 85);
		siteInfo.setLocation((getWidth()-siteInfo.getWidth())/2,25);
		siteInfo.setForeground(curCell.getParent().getCurrentPlayerTurn().getPlayerColor());
		this.add(siteInfo,BorderLayout.NORTH);
		repaint();
	}
	
	public void placeDice(){
		
		Box box = Box.createHorizontalBox();
		dice1=new JLabel();
		dice2=new JLabel();
		dice1.setSize(23,23);
		dice2.setSize(23,23);
		dice1.setLocation(getWidth()-dice1.getWidth()-dice2.getWidth()-4, getHeight()-dice1.getHeight()-4);
		dice2.setLocation(getWidth()-dice2.getWidth()-4, getHeight()-dice2.getHeight()-4);
		box.add(dice1);
		box.add(dice2);
		this.add(box,BorderLayout.SOUTH);
		
	}
	
	public void updateDice(int x,int y){
		if(x<0 || x>6 || y<0 || y>6)
			return;
		dice1.setIcon(new ImageIcon("images\\"+x+".png"));
		dice2.setIcon(new ImageIcon("images\\"+y+".png"));
		repaint();
	}
}

