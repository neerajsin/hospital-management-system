import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ModifyDocInfo extends JFrame
{
	
	ResultSet rs; //rs2
	Statement smt;
	PreparedStatement smt1;
	Connection con;
	int i;
	
	clsSettings settings = new clsSettings();
	
	ModifyDocInfo()
	{
		JFrame frame = new JFrame("Modify Doctor Information");
		frame.setBounds(0,0,1024,768);
		frame.setVisible(true);
		
		ImageIcon ficon = new ImageIcon("img/HpIcon.png");
		frame.setIconImage(ficon.getImage());
		
		// title of frame
		ImageIcon title = new ImageIcon("img/updatedoctor.jpg");
		JPanel panel = new JPanel();
		panel.setBounds(0,0,1366,60);
		panel.setLayout(null);
		
		JLabel lb= new JLabel(title);
		lb.setBounds(0,0,1366,58);
		panel.add(lb);
		frame.add(panel);
		
		//fonts 
		//Font f1 = new Font("Algerian",Font.BOLD,30);
		
		ImageIcon icon = new ImageIcon("img/backg.jpg");
		
		//lables
		//JLabel lb1 = new JLabel("Update Doctor Information");
		JLabel lb2 = new JLabel("Name :");
		JLabel lb3 = new JLabel("Address :");
		JLabel lb4 = new JLabel("Specialization :");
		JLabel lb5 = new JLabel("Doctor ID :");
		JLabel lb6 = new JLabel("Contact :");
		JLabel lb7 = new JLabel("Working Hours:");
		JLabel lb8 = new JLabel("From :");
		JLabel lb9 = new JLabel("To :");
		
		JLabel bkg = new JLabel(icon);
		bkg.setBounds(0,0,1366,768);
		
		//lb1.setFont(f1);
		
		//Text fields
		JTextField text1 = new JTextField();
		JTextField text2 = new JTextField();
		JTextField text3 = new JTextField();
		settings.Numvalidator(text3);
		JTextField text4 = new JTextField();
		JTextField text5 = new JTextField();
		
		//Text area
		JTextArea ta1 = new JTextArea();
		JTextArea ta2 = new JTextArea();
		
		//scoll pane
		JScrollPane sc1 = new JScrollPane(ta1);
		JScrollPane sc2 = new JScrollPane(ta2);
		
		//labels bounds
		lb2.setBounds(100,100,100,30);		//name
		lb3.setBounds(100,200,100,30);		//address
		lb4.setBounds(100,300,100,30);		//specialization
		lb5.setBounds(600,100,60,30);		//doctor id
		lb6.setBounds(600,200,60,30);		//contact
		lb7.setBounds(600,300,100,30);		//working hours
		lb8.setBounds(700,300,50,30);		//from
		lb9.setBounds(800,300,50,30);		//to

		//text fields bounds
		text1.setBounds(200,110,200,20);	 //name
		text2.setBounds(665,110,100,20);	//Id
		text3.setBounds(665,210,100,20);	//contact
		text4.setBounds(740,310,50,20);		//from
		text5.setBounds(830,310,50,20);		//to
		
		sc1.setBounds(200,210,200,80);
		sc2.setBounds(200,310,200,80);
		
		//button creation
		JButton btn1 = new JButton("Search");
		JButton btn2 = new JButton("Clear");
		JButton btn3 = new JButton("Update");
		JButton btn4 = new JButton("Back");
		
		//button bounds
		btn1.setBounds(150,480,100,30);
		btn2.setBounds(300,480,100,30);
		btn3.setBounds(450,480,100,30);
		btn4.setBounds(600,480,100,30);
		
		//adding lables
		frame.add(lb2);	//name
		frame.add(lb3);	//address
		frame.add(lb4);	//speciallization
		frame.add(lb5);	//doctor id
		frame.add(lb6);	//contact
		frame.add(lb7);	//working hours
		frame.add(lb8);	//from
		frame.add(lb9);	//to
		
		//adding text fields
		frame.add(text1);	//name
		frame.add(text2);	//Id
		frame.add(text3);	//contact
		frame.add(text4);	//from
		frame.add(text5);	//to
		
		//adding text area's
		frame.add(sc1);
		frame.add(sc2);
		
		frame.add(btn1);	//search 
		frame.add(btn2);	//clear 
		frame.add(btn3);	//modify
		frame.add(btn4);	//back
		
		//adding action listener for back button
		btn4.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn4)
			{
				frame.setVisible(false);
			}
		}
		});
		
		//adding action listener for clear
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn2)
				{
					text2.setText("");	//doctor id
					text1.setText("");	//name
					ta1.setText("");	//address
					ta2.setText("");	//specialization
					text3.setText("");	//contact
					text4.setText("");	//hr from
					text5.setText("");	//hr to
				}
			}
		});
	
		//action listener for searchin
			btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn1)
				{
					try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");  
						smt=con.createStatement();
						rs=smt.executeQuery("SELECT * FROM doctor");
						
						while(rs.next())
						{
							if(text2.getText().equals(rs.getString(1)))
							{
								i=1;
								break;			
							}
						}
							if(i==1)
							{
								text2.setText(rs.getString(1));		//Doctor Id
								text1.setText(rs.getString(2));		//name
								ta1.setText(rs.getString(3));		//address
								ta2.setText(rs.getString(4));		//specialization
								text3.setText(rs.getString(5));		//contact
								text4.setText(rs.getString(6));		//hr_from
								text5.setText(rs.getString(7));		//hr_to
								
							}
							else
								{
									JOptionPane.showMessageDialog(null,"Data Not Found");
								}
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			}
		});
		
		//action listener for modify data
		btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn3)
				{
					try
					{
						smt1=con.prepareStatement("Update doctor set doctor_id=?,name=?,address=?,specialization=?,contact=?,work_hr_from=?,work_hr_to=? where doctor_id=?");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Update this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						String name = text1.getText();
						if(ch==0)
						{
							smt1.setString(8,text2.getText()); 	//doctorid
							smt1.setString(1,text2.getText()); 	//doctorid
							
							//for geting name
								if(name.equals(""))
								{
										JOptionPane.showMessageDialog(null,"Please fill the name.");
								}
								if (!(Pattern.matches("^[\\p{L} .'-]+$", text1.getText())))
								{
									JOptionPane.showMessageDialog(null, "Please enter a valid character name", "Error", JOptionPane.ERROR_MESSAGE);
								}  
								else
								{
										smt1.setString(2,text1.getText()); 	//name
								}
							
							smt1.setString(3,ta1.getText()); 	//address
							smt1.setString(4,ta2.getText()); 	//specialization
							smt1.setString(5,text3.getText()); 	//contact
							smt1.setString(6,text4.getText()); 	//hr_from
							smt1.setString(7,text5.getText()); 	//hr_to
							
							smt1.executeUpdate();
							JOptionPane.showMessageDialog(null,"Data Has Been Updated.");
						}
					}
					catch(Exception exp)
					{
						System.out.println(exp);
					}
				}
			}
		});
		
		
		
		//background image
		frame.add(bkg);
		
		
	}
	
	public static void main(String[] arr)
	{
		new ModifyDocInfo();
	}
}