import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;

public class MDialog extends JPanel
{
	public MDialog(String info,String firstChoice,String secondChoice)
	{
		//super();
		this.setLayout(null);
		if(secondChoice==null)
			secondChoice="";
		this.setSize(240, 80);
		
		Font myFont = new java.awt.Font( "Arial" , Font.BOLD, 13) ;
		
		
		JLabel firstLabel = new JLabel(firstChoice);
		firstLabel.setFont(myFont);
		FontMetrics fm = firstLabel.getFontMetrics(myFont);
		firstLabel.setBounds(3,78-fm.getHeight(),fm.stringWidth(firstChoice),fm.getHeight());
		this.add(firstLabel);
		
		
		JLabel secondLabel = new JLabel(secondChoice);
		secondLabel.setFont(myFont);
		fm = secondLabel.getFontMetrics(myFont);
		secondLabel.setBounds(237-fm.stringWidth(secondChoice), 78-fm.getHeight(), fm.stringWidth(secondChoice), fm.getHeight());
		this.add(secondLabel);
		
		
		
		JLabel infoLabel = new JLabel(info);
		infoLabel.setFont(myFont);
		fm = infoLabel.getFontMetrics(myFont);
		infoLabel.setBounds(10, 10, fm.stringWidth(info), 50);
		this.add(infoLabel);
		
		infoLabel.setOpaque(true);
		infoLabel.setBackground(Color.YELLOW);
		this.setBackground(Color.blue);
	}
}
