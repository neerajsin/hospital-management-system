import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ModifyPatientInfo extends JFrame
{
	ResultSet rs;
	Statement smt,smt4=null;
	PreparedStatement smt1;
	Connection con,con2;
	int i=0;
	
	clsSettings settings = new clsSettings();
	ModifyPatientInfo()
	{
		
		JFrame f = new JFrame("Update Patient");
		f.setVisible(true);
		f.setBounds(0,0,1024,768);
		f.setLayout(null);
		
		
		ImageIcon icon3 = new ImageIcon("img/HpIcon.png");
		f.setIconImage(icon3.getImage());
		
		//Panel
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setBounds(0,0,1366,50);
		p1.setLayout(null);
		
		p2.setBounds(0,55,1366,768);
		p2.setLayout(null);
		
		f.add(p1);
		f.add(p2);
		
		ImageIcon icon = new ImageIcon("img/backg.jpg");
		ImageIcon icon2 = new ImageIcon("img/p2.jpg");
		
		
		//labels
		JLabel lb1 = new JLabel("Insert Patient Number To Search");
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
		
		JLabel lb17 = new JLabel(icon);
		JLabel lb18 = new JLabel(icon2);
		
		lb17.setBounds(0,0,1366,768);
		lb18.setBounds(0,0,1366,50);
		
		
		//textfields
		JTextField tf1 = new JTextField();	 //name
		JTextField tf2 = new JTextField();	 //patient No.
		JTextField tf3 = new JTextField();	 //Room no.
		JTextField tf4 = new JTextField();	//contact
		JTextField tf5 = new JTextField();	//date of addmission
		tf5.setEditable(false);
		//JTextField tf7 = new JTextField();	//attending doctors
		
		
		//text aread's
		JTextArea ta1 = new JTextArea();
		JTextArea ta2 = new JTextArea();
		JTextArea ta3 = new JTextArea();
		
		//scroll panel
		JScrollPane scp = new JScrollPane(ta1);
		JScrollPane scp1 = new JScrollPane(ta2);
		JScrollPane scp2 = new JScrollPane(ta3);
		
		//font
		Font f2 = new Font("Arial",Font.BOLD,16);
		
		//fontset
		lb1.setFont(f2);
		lb2.setFont(f2);
		lb5.setFont(f2);
		
		//drop down list
		Choice chbg = new Choice(); //blood group
		chbg.setBounds(150,280,53,15);
		chbg.addItem("A -ve");
		chbg.addItem("A +ve");
		chbg.addItem("B -ve");
		chbg.addItem("B +ve");
		chbg.addItem("AB -ve");
		chbg.addItem("AB +ve");
		chbg.addItem("O +ve");
		chbg.addItem("O -ve");
		
		Choice chrt = new Choice();  //room type
		chrt.setBounds(150,430,80,15);
		chrt.addItem("Deluxe");
		chrt.addItem("Private");
		chrt.addItem("Semi-Private");
		chrt.addItem("General");
		
		//checkbox 
		CheckboxGroup cbmf = new CheckboxGroup();
		Checkbox cbm=new Checkbox("Male",cbmf,true);
		Checkbox cbf=new Checkbox("Female",cbmf,false);
		cbm.setBounds(479,210,50,15);
		cbf.setBounds(530,210,60,15);
		
		
		//labels bounds set
		lb1.setBounds(400,10,400,30); 	// patient info
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
		settings.Numvalidator(tf4);
		tf5.setBounds(520,160,200,20); 	//date of addmission
		//tf7.setBounds(510,430,200,20); 	//attending doctor
		
		
		//scroll pane bounds
		scp.setBounds(120,110,200,80);	//address
		scp1.setBounds(120,330,200,80);	//history
		scp2.setBounds(510,330,200,80);	//current problem
		
		//Button
		JButton btn  = new JButton("Search");
		btn.setBounds(100,500,100,30);
		
		JButton btn2 = new JButton("Clear");
		btn2.setBounds(250,500,100,30);
		
		JButton btn3 = new JButton("Modify");
		btn3.setBounds(400,500,100,30);
		
		JButton btn4 = new JButton("Back");
		btn4.setBounds(550,500,100,30);
		
		//panel 1
		p1.add(lb18);
		
		String[] doN =new String[50];
		//doctors drop downlist 
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");
			smt4=con.createStatement();
			rs=smt4.executeQuery("select name from doctor");
			doN[0]="Select Doctor";
			while(rs.next())
			{
				doN[i+1]=rs.getString(1);			
				i++;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		//Combo box for attending doctor
		JComboBox attd = new JComboBox(doN);
		attd.setBounds(510,430,200,20);
		
		//panel 2 labels 
		p2.add(lb1);
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
		p2.add(attd);	//attending doctor
		
		
		//panel 2 scroll pane
		p2.add(scp);	//address
		p2.add(scp1);	//history
		p2.add(scp2);	//current problem
		
		//choice list 
		p2.add(chbg);	//blood group list
		p2.add(chrt);	//room type list
		
		p2.add(cbm);	//checkbox male
		p2.add(cbf);	//checkbox female
		
		//adding buttons
		p2.add(btn);		//search 
		p2.add(btn2);		//clear
		p2.add(btn3);		//modify 
		p2.add(btn4);		//back
		
		// date section addmissin date
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String today = localDate.format(formatter);
		
		//date of birth
		JFormattedTextField tf6 = new JFormattedTextField(formatter);
		tf6.setBounds(510,260,200,20); 	//date of birth
		tf6.setText("");
		
		tf6.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		{
        JOptionPane.showMessageDialog(null, "Please Enter Valid Date");
        e.consume();
		}
		}
		});
		 
		p2.add(tf6);	//date of birth
		
		//adding action listener for back button
		btn4.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn4)
			{
				f.setVisible(false);
			}
		}
		});
		
		//action Listener for clear
		btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn2)
				{
					tf2.setEditable(true);
					tf1.setText("");	//name
					tf2.setText("");	//patient no
					tf3.setText("");	//room no
					tf4.setText("");	//contact
					tf5.setText("");	//date of addmission
					tf6.setText("");	//date of birth
					//tf7.setText("");	//attending doctor

					ta1.setText(""); 	//address
					ta2.setText("");	//History
					ta3.setText("");	//problem
				}
			}
		});
		
		//actionListener for searching
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
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
						String doct;
						
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
								tf2.setEditable(false);
								tf2.setText(rs.getString(1));		//patient no.
								tf1.setText(rs.getString(2));		//name
								ta1.setText(rs.getString(3));		//address
								
								blood=rs.getString(4);
								chbg.select(blood);		//blood group
		
								ta2.setText(rs.getString(5));		//history
								
								room=rs.getString(6);
								chrt.select(room);		//room type
								
								tf3.setText(rs.getString(7));		//room no
								
								
								tf4.setText(rs.getString(8));		//contact
								tf5.setText(rs.getString(9));		//date of addmission
								
								gender=rs.getString(10);
								if(gender.equals("male"))
									{
										System.out.println(gender);
										cbm.setState(true);
									}
								if(gender.equals("female"))
									{
										System.out.println(gender);
										cbf.setState(true);
									}
								
								tf6.setText(rs.getString(11));	//date of birth
								ta3.setText(rs.getString(12));	//problem
								
								doct=rs.getString(13);
								attd.setSelectedItem(doct);
								//tf7.setText(rs.getString(13));	//doctor name 
								
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
						smt1=con.prepareStatement("Update Patient set PATIENT_NO=?,NAME=?,ADDRESS=?,BLOOD_GROUP=?,HISTORY=?,ROOM_TYPE=?,ROOM_NO=?,CONTACT=?,ADMIT_DATE=?,GENDER=?,D_O_B=?,PROBLEM=?,DOCTOR_NAME=? where PATIENT_NO=?");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Update this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						
						String sDoc = attd.getSelectedItem().toString();
						String name = tf1.getText();
						String bdate = tf6.getText();
						if(ch==0)
						{
							smt1.setString(14,tf2.getText()); 	//patient no
							
							smt1.setString(1,tf2.getText());	//patient no
							
							//for updating name
							if(name.equals(""))
								{
										JOptionPane.showMessageDialog(null,"Please fill the name.");
								}
								if (!(Pattern.matches("^[\\p{L} .'-]+$", tf1.getText())))
								{
									JOptionPane.showMessageDialog(null, "Please enter a valid character name", "Error", JOptionPane.ERROR_MESSAGE);
								}  
								else
								{
										smt1.setString(2,tf1.getText());				//name
								}
							
							smt1.setString(3,ta1.getText());					//address
							smt1.setString(4,chbg.getSelectedItem());					//blood group
							smt1.setString(5,ta2.getText());						// history
							smt1.setString(6,chrt.getSelectedItem());					//room type
							smt1.setString(7,tf3.getText());					//room no
							smt1.setString(8,tf4.getText());					//contact
							smt1.setString(9,tf5.getText());							//addmission date	
							
							String gender="";
								if(cbm.getState()==true)
									{
										gender="male";
									}
									if(cbf.getState()==true)
									{
										gender="female";
									}
							
							smt1.setString(10,gender);					//gender
							
							//birth date
								try
								{
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									Date date1 = sdf.parse(today);
									Date date2 = sdf.parse(bdate);
									
									if(date2.after(date1)) 
									{
										JOptionPane.showMessageDialog(null,"Invalid birth date", "Error", JOptionPane.ERROR_MESSAGE);
									}
									else{
										smt1.setString(11,tf6.getText());		//date of birth
									}
								}
								catch(ParseException ex) {
										ex.printStackTrace(); // or log it using a logging framework
								}
							

							smt1.setString(12,ta3.getText());					//problem
							//smt1.setString(13,tf7.getText());					//Doctor name
							//For Doctors
								if(sDoc=="Select Doctor")
								{
									JOptionPane.showMessageDialog(null,"Please select doctor");
								}
								else
								{
								smt1.setString(13,attd.getSelectedItem().toString());		//attending doctor	
								}
								
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
		
		
		
		
		p2.add(lb17);
		
	}
	
	public static void main(String[] arr)
	{
		new ModifyPatientInfo();
	}
}
