/*import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;*/

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OptionsMenu extends Menu {
	private static JLabel[] possiblechoices={new JLabel(" Build Houses/Hotels "),new JLabel(" Request a Rent "),new JLabel(" Buy this site "),new JLabel(" Auction this Site "),new JLabel(" Pay Back some Mortgage "),new JLabel(" Mortgage some Own "),new JLabel(" Back ")};
	OptionsMenu(JFrame parent){
		//put active choices only
	super(parent);
	//choices.add
	}
	
	public void fillChoiceVector(){
		super.choices.removeAllElements();
		if(((Monopoli)parent).mainPanel.highlighted==null)
			super.choices.add(possiblechoices[6]);
		if(((Monopoli)parent).mainPanel.highlighted!=null)
			if(((Monopoli)parent).getCurrentMode()!=1) //giati erxetai me mode=7 meta to mortgage mias perioxis me options select?
				super.choices.add(new JLabel("Return to Normal Mode"));
		//can he build in this site?
		//is there a monopoly that is not built up with hotels only?
		if(((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMonopoliesCount()>0 ){
			dloop: for (Vector<Cell> vec : ((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMonopolies()){
				for (Cell cell : vec){
					if(cell.getHouses()<5){
						super.choices.add(possiblechoices[0]);
						break dloop;
					}
				}
			}
		}
		//can he request a rent = is opponent on owns cell?
		if(	((Monopoli)parent).mainPanel.getNextPlayerTurn().getOnCell().getOwner()!=null && //o allos paiktis den vrisketa se tetragwno me null owner
				((Monopoli)parent).mainPanel.getNextPlayerTurn().getOnCell().getOwner().equals(((Monopoli)parent).mainPanel.getCurrentPlayerTurn()) && // o allos paiktis vrisketai se tetragwno pou mou anikei
				((Monopoli)parent).mainPanel.dice1!=((Monopoli)parent).mainPanel.dice2 &&
				//ta zaria den einai diples dioti profanws autos o allos eimai egw kai ara 8a zitaei na plirwsw rent gia mena :P...
				!((Monopoli)parent).mainPanel.getNextPlayerTurn().getPaidRent() // kai den exei plirwsei to enoikio idi
			){
				
			
			super.choices.add(possiblechoices[1]);
		}
		//can he buy this site? (+ can he afford buying it)
		if((((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOnCell().canBeBought() && (((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMoney()-((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOnCell().getPrice())>0 )){
			super.choices.add(possiblechoices[2]);
		}
		//can he auction this site? (is it just buyable for some money (not zero))
		if((((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOnCell().canBeBought()) && ((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMoney()>0){
			super.choices.add(possiblechoices[3]);
		}
		//does he have any mortgaged owns?
		if(((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMortgages().size()>0){
			super.choices.add(possiblechoices[4]);
		}
		//does he have any unmortgaged owns?
		if(((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOwns().size()>((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getMortgages().size()){
			super.choices.add(possiblechoices[5]);
		}
		
		
		/*if (!choices.contains(possiblechoices[0])){//contains it doesnt contain build
			((Monopoli)parent).mainPanel.highlighted.setHighlighted(false);	
		}*/

	}
	
	
}

/*public void fillMenuWithChoices(){
int offsety=40;
//can he request a rent = is an opponent on owns cell?
if(	((Monopoli)parent).mainPanel.getNextPlayerTurn().getOnCell().getOwner()!=null &&
	((Monopoli)parent).mainPanel.getNextPlayerTurn().getOnCell().getOwner().equals(((Monopoli)parent).mainPanel.getCurrentPlayerTurn())){
	active.add(choices.get(0));
}
//can he but this site?
if((((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOnCell().canBeBought())){
	active.add(choices.get(1));
}
//can he build in this site?
if((((Monopoli)parent).mainPanel.getCurrentPlayerTurn().getOnCell().canBeBuilt())){
	active.add(choices.get(2));
}
active.add(new JLabel(" Back "));
Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 10) ;
for (int i=0; i<active.size(); i++){
	active.get(i).setFont(myFont);
	active.get(i).setOpaque(false);
	FontMetrics fm = active.get(i).getFontMetrics(myFont);
	active.get(i).setBounds(30,offsety,fm.stringWidth(active.get(i).getText()),fm.getHeight());
	offsety=offsety+active.get(i).getHeight()+4;
	active.get(i).setForeground(Color.WHITE);
	this.add(active.get(i));
}
}*/


