import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DoctorInfo extends JFrame
{
	ResultSet rs2;
	Statement smt,smt3;
	PreparedStatement smt1;
	Connection con;
	int i=0,j=0,k=0;
	
	clsSettings settings = new clsSettings();
	
	DoctorInfo()
	{
		//Auto number doc id .
		char ch = 'D';
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");  
			smt3=con.createStatement();
			rs2=smt3.executeQuery("SELECT patient_no from patient");	
			
			while(rs2.next())
			{	
				j++;		//counting rows 
				k=j;
				
			}
			k=k+1;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		JFrame frame = new JFrame("ADD Doctor Information");
		frame.setVisible(true);
		frame.setBounds(0,0,1024,768);
		frame.setLayout(null);
		
		ImageIcon ficon = new ImageIcon("img/HpIcon.png");
		frame.setIconImage(ficon.getImage());
		
		// title of frame
		ImageIcon title = new ImageIcon("img/adddoctor.jpg");
		JPanel panel = new JPanel();
		panel.setBounds(0,0,1366,60);
		panel.setLayout(null);
		
		JLabel lb= new JLabel(title);
		lb.setBounds(0,0,1366,58);
		panel.add(lb);
		frame.add(panel);
		
		//image icon for backgound
		ImageIcon icon = new ImageIcon("img/backg.jpg");
		
		//lables
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
		
		
		//Text fields
		JTextField text1 = new JTextField();
		JTextField text2 = new JTextField(); //id
		text2.setText(""+ch+k+"");
		text2.setEditable(false);
		
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
		JButton btn1 = new JButton("Save");
		JButton btn2 = new JButton("Clear");
		JButton btn3 = new JButton("Add New");
		
		//button bounds
		btn1.setBounds(200,480,100,30);
		btn2.setBounds(400,480,100,30);
		btn3.setBounds(600,480,100,30);
		
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
		
		frame.add(btn1);	//save 
		frame.add(btn2);	//clear
		frame.add(btn3);	//add new

		//adding action listener for add new button
		btn3.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn3)
			{
				frame.setVisible(false);
				new DoctorInfo();
			}
		}
		});
		
		//adding action listener for save data
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
						smt1=con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?)");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Save this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						
						String name = text1.getText();
						if(ch==0)
							{
								smt1.setString(1,text2.getText());		 		//Doctor Id
								
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
										smt1.setString(2,text1.getText());				//name
								}
								
								smt1.setString(3,ta1.getText());				//address
								smt1.setString(4,ta2.getText());				//specialization
								smt1.setString(5,text3.getText());				//contact
								smt1.setString(6,text4.getText());				//hr from
								smt1.setString(7,text5.getText());				//hr to
								
								
								smt1.executeUpdate();
								JOptionPane.showMessageDialog(null,"Data Has Been Saved.");
							}
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
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
		
		frame.add(bkg);
	}
	
	public static void main(String[] arr)
	{
		new DoctorInfo();
	}
}