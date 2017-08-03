import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NewPatient extends JFrame
{
	Statement smt,smt3=null,smt4=null;
	PreparedStatement smt1;
	Connection con,con2;
	ResultSet rs,rs2,rs3;
	int i=0,j=0,k=0;
	int b=0,c=0;
	String[] doN =new String[50];
	
	clsSettings settings = new clsSettings();
	
	NewPatient()
	{
		//Auto number for patient no.
		char ch = 'P';
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
		
		//doctor list 
			doN[0]="Select Doctor";
			try
			{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con2=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");
			smt4=con2.createStatement();
			rs=smt4.executeQuery("SELECT name from doctor");
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
		
	
	
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(0,0,1024,768);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(1);
		frame.setTitle("New Patient");
		
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
		ImageIcon icon2 = new ImageIcon("img/p1.jpg");
		
		
		//labels
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
		
		JLabel pno = new JLabel();
		
		JLabel lb17 = new JLabel(icon);
		JLabel lb18 = new JLabel(icon2);
		
		lb17.setBounds(0,0,1366,768);
		lb18.setBounds(0,0,1366,50);
		
		
		//textfields
		JTextField tf1 = new JTextField();	 //name
		JTextField tf3 = new JTextField();	 //Room no.
		JTextField tf4 = new JTextField();	//contact
		JTextField tf5 = new JTextField();	//date of addmission
		

		pno.setText(""+ch+k+"");
		

		
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
		//lb1.setFont(f1);
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
		
		//choice list for room type
		Choice chrt = new Choice();  //room type
		chrt.setBounds(150,430,80,15);
		chrt.addItem("Deluxe");
		chrt.addItem("Private");
		chrt.addItem("Semi-Private");
		chrt.addItem("General");
		
		//Combo box for attending doctor
		JComboBox attd = new JComboBox(doN);
		attd.setBounds(510,430,200,20);
		
		//checkbox 
		CheckboxGroup cbmf = new CheckboxGroup();
		Checkbox cbm=new Checkbox("Male",cbmf,true);
		Checkbox cbf=new Checkbox("Female",cbmf,false);
		cbm.setBounds(479,210,50,15);
		cbf.setBounds(530,210,60,15);
		
		
		//labels bounds set
		lb2.setBounds(50,20,400,30);	// personal info
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
		
		pno.setBounds(473,55,100,20); 	//Patien no.
		
		//text fields bounds
		tf1.setBounds(120,60,200,20); 	//name
		tf3.setBounds(670,60,100,20); 	//room no.
		tf4.setBounds(470,110,200,20); 	//contact
		settings.Numvalidator(tf4);
		tf5.setBounds(520,160,200,20); 	//date of addmission
		

		
		//scroll pane bounds
		scp.setBounds(120,110,200,80);	//address
		scp1.setBounds(120,330,200,80);	//history
		scp2.setBounds(510,330,200,80);	//current problem
		
		//Button
		JButton btn  = new JButton("Save");
		btn.setBounds(150,500,100,30);
		
		JButton btn2 = new JButton("Add New");
		btn2.setBounds(350,500,100,30);
		
		JButton btn3 = new JButton("Clear");
		btn3.setBounds(550,500,100,30);
		
		//panel 1
		p1.add(lb18);
		
		//panel 2 labels 
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
		p2.add(lb16);	//admit date
		p2.add(lb19);	//birth date
		p2.add(lb20);	//attending doctor
		
		p2.add(pno);	//patient no
		
		//panel 2 textfields
		p2.add(tf1);  // text fields name
		p2.add(tf3);	//room no.
		p2.add(tf4);	//contact
		p2.add(tf5);	//date of addmission
		//p2.add(tf6);	//dare of birth
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
		p2.add(btn); 	//add
		p2.add(btn2);	//clear
		p2.add(btn3);	//back
		
		// date section addmissin date
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String today = localDate.format(formatter);
	
		tf5.setText(today);
		tf5.setEditable(false);
		
		
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
		btn2.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn2)
			{
				frame.setVisible(false);
				new NewPatient();
			}
		}
		});
		
	/* 	//contact
		tf4.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                char ch = e.getKeyChar();
                if(tf4.getText().length()<=10&&Character.isDigit(c)||(ch==KeyEvent.VK_DELETE||ch==KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
                else{
                    JOptionPane.showMessageDialog(null, "Enter valid number!");
                    tf4.setText(" ");
                }
            }
		}); */
		
	/* 	//Name 
		tf1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                char ch = e.getKeyChar();
                if(tf1.getText().length()<=9&&!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar()==KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
                else{
                    JOptionPane.showMessageDialog(null, "Only numbers are allowed!");
                    tf1.setText(" ");
                }
            }
		}); */
		//action listener for adding value to data base
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
						smt1=con.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Save this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						
						String name = tf1.getText();
						String bdate = tf6.getText();
						if(ch==0)
							{
								//patient no.
								smt1.setString(1,pno.getText());		 		//patient no
								
								//for geting name
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
								
								smt1.setString(3,ta1.getText());				//address
								smt1.setString(4,chbg.getSelectedItem()); 			//blood group
								smt1.setString(5,ta2.getText());				//history
								smt1.setString(6,chrt.getSelectedItem());			//room type
								smt1.setString(7,tf3.getText());				//room no.
								smt1.setString(8,tf4.getText());				//contact
								smt1.setString(9,tf5.getText());				//date of addmission
								
								
								String gender="";
								if(cbm.getState()==true)
									{
										gender="male";
									}
									if(cbf.getState()==true)
									{
										gender="female";
									}
								smt1.setString(10,gender);				//gender
								
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
								
								
								smt1.setString(12,ta3.getText());		//problem
								smt1.setString(13,attd.getSelectedItem().toString());		//attending doctor
								
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
		
		//clear button
		btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn3)
				{
					tf1.setText("");	//name
					tf3.setText("");	//room no
					tf4.setText("");	//contact
					tf5.setText("");	//date of addmission
					tf6.setText("");	//date of birth
					
					ta1.setText(""); 	//address
					ta2.setText("");	//History
					ta3.setText("");	//problem
				}
			}
		});
		
		
		//background image
		p2.add(lb17);
	}
	
	public static void main(String[] arr)
	{
		new NewPatient();
	}
}