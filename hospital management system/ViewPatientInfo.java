import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class ViewPatientInfo extends JFrame
{
	
	ResultSet rs;
	Statement smt;
	Connection con;
	PreparedStatement smt1;
	int i;
	
	ViewPatientInfo()
	{
		JFrame frame = new JFrame("View & Delete Data");
		frame.setLayout(null);
		frame.setBounds(0,0,1024,768);
		frame.setVisible(true);
		
		ImageIcon ficon = new ImageIcon("img/HpIcon.png");
		frame.setIconImage(ficon.getImage());
		
		//Panel
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setBounds(0,0,1366,50);
		p1.setLayout(null);
		
		p2.setBounds(0,55,1366,768);
		p2.setLayout(null);
		
		frame.add(p1);
		frame.add(p2);
		
		ImageIcon icon = new ImageIcon("img/backg.jpg");
		ImageIcon icon2 = new ImageIcon("img/p3.jpg");
		
		
		//labels
		JLabel lb1 = new JLabel("Insert Patient Number for Search");
		JLabel lb2 = new JLabel("Add Personal Information");
		JLabel lb3 = new JLabel("Name :");
		JLabel lb4 = new JLabel("Address :");
		JLabel lb5 = new JLabel("Medical Information");
		JLabel lb6 = new JLabel("Blood Group :");
		JLabel lb7 = new JLabel("History :");
		JLabel lb8 = new JLabel("Type of Room :");
		JLabel lb9 = new JLabel("Patient No.:");
		JLabel lb10 = new JLabel("Room No.:");
		JLabel lb11 = new JLabel("Contact :");
		JLabel lb12 = new JLabel("Date of Addmission :");
		JLabel lb13 = new JLabel("Gender :");
		JLabel lb14 = new JLabel("Date of Birth :");
		JLabel lb15 = new JLabel("Current Problem :");
		JLabel lb16 = new JLabel("DD/MM/YYYY");
		JLabel lb19 = new JLabel("DD/MM/YYYY");
		JLabel lb20 = new JLabel("Attending Doctor :");
		
		
		JLabel lb17 = new JLabel(icon); //label 1 background
		JLabel lb18 = new JLabel(icon2); //label 2 background
		
		//background bounds
		lb17.setBounds(0,0,1366,768);
		lb18.setBounds(0,0,1366,50);
		
		
		//textfields
		JTextField tf1 = new JTextField();	 //name
		JTextField tf2 = new JTextField();	 //patient No.
		JTextField tf3 = new JTextField();	 //Room no.
		JTextField tf4 = new JTextField();	//contact
		JTextField tf5 = new JTextField();	//date of addmission
		JTextField tf6 = new JTextField();	//date of birth
		JTextField tf7 = new JTextField();	//attending doctor
		JTextField tf8 = new JTextField();	//blood group
		JTextField tf9 = new JTextField();	//type of room
		JTextField tf10 = new JTextField();	//gender
		
		
		//text aread's
		JTextArea ta1 = new JTextArea();
		JTextArea ta2 = new JTextArea();
		JTextArea ta3 = new JTextArea();
		
		//scroll panel
		JScrollPane scp = new JScrollPane(ta1);
		JScrollPane scp1 = new JScrollPane(ta2);
		JScrollPane scp2 = new JScrollPane(ta3);
		
		//font
		Font f1 = new Font("Algerian",Font.BOLD,30);
		Font f2 = new Font("Arial",Font.BOLD,16);
		
		//fontset
		lb2.setFont(f2);
		lb5.setFont(f2);
		lb1.setFont(f2);
		
		//labels bounds set
		lb1.setBounds(400,10,400,30);	//insert data
		lb2.setBounds(50,10,400,30);	// personal info
		lb3.setBounds(50,50,50,30);		// Name
		lb4.setBounds(50,100,70,30);	// Address
		lb5.setBounds(50,220,200,30);	// medical info
		lb6.setBounds(50,270,100,30);	// blood group
		lb7.setBounds(50,320,70,30);	// History
		lb8.setBounds(50,420,100,30);	// Type of Room
		lb9.setBounds(400,50,100,30);	// patient no
		lb10.setBounds(600,50,100,30);	// Room no
		lb11.setBounds(400,100,70,30);	// Contact 
		lb12.setBounds(400,150,150,30);	// date of addmission
		lb13.setBounds(400,200,70,30);	// Gender
		lb14.setBounds(400,250,150,30);	// date of birth
		lb15.setBounds(400,320,150,30);	// current problem
		lb16.setBounds(725,155,100,30);	// addmission date
		lb19.setBounds(720,255,100,30);	// birth date
		lb20.setBounds(400,420,130,30);	// attending doctor
		
		
		//text fields bounds
		tf1.setBounds(120,60,200,20); 	//name
		tf2.setBounds(470,60,100,20); 	//Patien no.
		tf3.setBounds(670,60,100,20); 	//room no.
		tf4.setBounds(470,110,200,20); 	//contact
		tf5.setBounds(520,160,200,20); 	//date of addmission
		tf6.setBounds(510,260,200,20); 	//date of birth
		tf7.setBounds(510,430,200,20); 	//attending doctor
		
		tf8.setBounds(130,280,53,20); 	//blood group
		tf9.setBounds(150,430,100,20); 	//type of room
		tf10.setBounds(460,210,80,20); 	//gender
		
		//scroll pane bounds
		scp.setBounds(120,110,200,80);	//address
		scp1.setBounds(120,330,200,80);	//history
		scp2.setBounds(510,330,200,80);	//current problem
		
		//Button
		JButton btn  = new JButton("Search");
		btn.setBounds(100,500,100,30);
		
		JButton btn2 = new JButton("Clear");
		btn2.setBounds(250,500,100,30);
		
		JButton btn3 = new JButton("Back");
		btn3.setBounds(550,500,100,30);
		
		JButton btn4 = new JButton("Delete");
		btn4.setBounds(400,500,100,30);
		
		//panel 1
		p1.add(lb18);
		
		//panel 2 labels
		p2.add(lb1);	//insert patient no.
		p2.add(lb2);	//personal info 
		p2.add(lb3);	//name
		p2.add(lb4);	//address
		p2.add(lb5);	//medical info
		p2.add(lb6);	//blood group
		p2.add(lb7);	//History
		p2.add(lb8);	//Type of Room
		p2.add(lb9);	//Patient no.
		p2.add(lb10);	//Room no.
		p2.add(lb11);	//contact.
		p2.add(lb12);	//date of addmission
		p2.add(lb13);	//Gender
		p2.add(lb14);	//date of birth
		p2.add(lb15);	//current problem
		p2.add(lb16);
		p2.add(lb19);
		p2.add(lb20);
		
		
		//panel 2 textfields
		p2.add(tf1);  // text fields name
		p2.add(tf2);	//patien no.
		p2.add(tf3);	//room no.
		p2.add(tf4);	//contact
		p2.add(tf5);	//date of addmission
		p2.add(tf6);	//dare of birth
		p2.add(tf7);	//attending doctor
		
		p2.add(tf8);	//blood group
		p2.add(tf9);	//type of room
		p2.add(tf10);	//gender
		
		//panel 2 scroll pane
		p2.add(scp);	//address
		p2.add(scp1);	//history
		p2.add(scp2);	//current problem
		
		//add buttons
		p2.add(btn); 	//search 
		p2.add(btn2);	//clear
		p2.add(btn3);	//back
		p2.add(btn4);	//delete
		
		//adding action listener for back button
		btn3.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn3)
			{
				frame.setVisible(false);
			}
		}
		});
		
		//adding action listener for search
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o=e.getSource();
				if(o==btn)
				{
					try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");  
						smt=con.createStatement();
						rs=smt.executeQuery("SELECT * FROM patient");
						
						String blood;
						String room;
						String gender;
						
						while(rs.next())
						{
							if(tf2.getText().equals(rs.getString(1)))
							{
								i=1;
								break;			
							}
						}
						
						
						if(i==1)
						{
								tf2.setText(rs.getString(1));		//patient no.
								tf1.setText(rs.getString(2));		//name
								ta1.setText(rs.getString(3));		//address
								
								tf8.setText(rs.getString(4));
								
								ta2.setText(rs.getString(5));		//history
								
								tf9.setText(rs.getString(6));
						
								tf3.setText(rs.getString(7));		//room no
								tf4.setText(rs.getString(8));		//contact
								tf5.setText(rs.getString(9));		//date of addmission
								
								tf10.setText(rs.getString(10));
								tf6.setText(rs.getString(11));	//date of birth
								ta3.setText(rs.getString(12));	//problem
								tf7.setText(rs.getString(13));	//doctor name
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
		
		//adding action Listener for clear
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn2)
				{
					tf1.setText("");	//name
					tf2.setText("");	//patient no
					tf3.setText("");	//room no
					tf4.setText("");	//contact
					tf5.setText("");	//date of addmission
					tf6.setText("");	//date of birth
					tf7.setText("");	//attending doctor
					tf8.setText("");	//blood group
					tf9.setText("");	//room type
					tf10.setText("");	//gender

					ta1.setText(""); 	//address
					ta2.setText("");	//History
					ta3.setText("");	//problem
				}
			}
		});
		
		// action listener for delete
		btn4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o=e.getSource();
				if(o==btn4)
				{
					try
					{
						smt1=con.prepareStatement("Delete  from patient where patient_no=?");
						smt1.setString(1,tf2.getText());
							int rowsDeleted = smt1.executeUpdate();
						if (rowsDeleted > 0)
						{
							JOptionPane.showMessageDialog(null,"Patient Record Deleted Successfully !");
							System.out.println("Patient Record Deleted Successfully !");
						}
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			}
		});
		
		
		//background
		p2.add(lb17);
	}
	
	public static void main(String[] arr)
	{
		new  ViewPatientInfo();
	}
}