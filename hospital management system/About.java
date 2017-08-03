import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class About extends JFrame
{
	About()
	{
		JFrame f = new JFrame("About Us");
		f.setBounds(100,100,726,393);
		f.setVisible(true);
		f.setLayout(null);
		f.setResizable(false);
		
		ImageIcon icon = new ImageIcon("img/about.jpg");
		ImageIcon ficon = new ImageIcon("img/HpIcon.png");
		f.setIconImage(ficon.getImage());
		
		JLabel lb1 = new JLabel(icon);
		lb1.setBounds(0,0,726,393);
		
		JButton b1 = new JButton("Back");
		b1.setBounds(580,330,70,20);
		
		//adding action listener for back button
		b1.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==b1)
			{
				f.setVisible(false);
			}
		}
		});

		
		
		f.add(b1);
		f.add(lb1);
		
	}
	
	public static void main(String[] arr)
	{
		new About();
	}
}