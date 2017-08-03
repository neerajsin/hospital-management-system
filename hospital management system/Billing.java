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
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;

public class Billing extends JFrame
{	
	ResultSet rs;
	Statement smt;
	PreparedStatement smt1;
	Connection con;
	int i;
	
	Billing()
	{
		JFrame frame = new JFrame("Bill");
		frame.setVisible(true);
		frame.setBounds(0,0,1024,768);
		frame.setLayout(null);
		
		ImageIcon ficon = new ImageIcon("img/HpIcon.png");
		frame.setIconImage(ficon.getImage());
		
		// title of frame
		ImageIcon title = new ImageIcon("img/patientbill.jpg");
		JPanel panel = new JPanel();
		panel.setBounds(0,0,1366,60);
		panel.setLayout(null);
		
		JLabel lb= new JLabel(title);
		lb.setBounds(0,0,1366,58);
		panel.add(lb);
		frame.add(panel);
		
		
		//button 
		JButton btn = new JButton("Search");
		btn.setBounds(100,550,100,30);
		
		JButton btn1= new JButton("Save");
		btn1.setBounds(250,550,100,30);
		
		JButton btn2= new JButton("Clear");
		btn2.setBounds(400,550,100,30);
		
		JButton btn3= new JButton("Calc.");
		btn3.setBounds(550,550,100,30);
		
		//font
		Font f1 = new Font("Arial",Font.BOLD,18);
		
		//Image icon
		ImageIcon icon = new ImageIcon("img/backg.jpg");
		
		//Label define
		JLabel lb2 = new JLabel("Search by Patient No.");
		JLabel lab1 = new JLabel("Patient Name :");
		JLabel lab2 = new JLabel("Date of Admission :");
		JLabel lab3 = new JLabel("Room Type :");
		JLabel lab4 = new JLabel("Pharmacy :");
		JLabel lab5 = new JLabel("Patinet No.:");
		JLabel lab6 = new JLabel("Date of Discharge :");
		JLabel lab7 = new JLabel("Guardian name :");
		JLabel lab8 = new JLabel("Relation with Patient :");
		//JLabel lab9 = new JLabel("Payment Method :");
		JLabel lab10 = new JLabel("Lab Charges :");
		JLabel lb1 = new JLabel("DD/MM/YYYY");
		JLabel roomChr = new JLabel("Room Charges :");
		JLabel otherChr = new JLabel("Other Charges :");
		JLabel total = new JLabel("Total :");
		JLabel payment = new JLabel("Payment");
		JLabel perday = new JLabel("Per Day ");
		
		JLabel rno = new JLabel("Room No. :");
		rno.setBounds(500,140,150,30);
		frame.add(rno);
		
		
		JLabel backg = new JLabel(icon);
		backg.setBounds(0,0,1366,768);
		
		lb2.setFont(f1);
		
		//TextField
		JTextField text1 = new JTextField();	//patient name
		text1.setEditable(false);
		
		JTextField text2 = new JTextField();	//date of admission
		text2.setEditable(false);
		
		JTextField text3 = new JTextField();	//Room type
		text3.setEditable(false);
		
		JTextField text4 = new JTextField();	//Pharmacy
		JTextField text5 = new JTextField();	//Patient NO.
		JTextField text6 = new JTextField();	//Date of Discharge
		text6.setEditable(false);
		
		JTextField text7 = new JTextField();	//guardian name
		JTextField text8 = new JTextField();	//relation with patient
		JTextField text9 = new JTextField();	//lab charges
		JTextField roomChT = new JTextField();	//room charges 
		roomChT.setEditable(false);
		JTextField othertf = new JTextField();	//other charges 
		JTextField totaltf = new JTextField();	//total
		
		JTextField rnotf = new JTextField();  // room no.
		rnotf.setEditable(false);
		
		rnotf.setBounds(700,150,150,20);
		frame.add(rnotf);
		
		
		//checkbox 
		
		//drop downlist payment 
		Choice chpay = new Choice();  //Payment Method
		chpay.setBounds(250,460,150,20);
		chpay.addItem("Paid");
		chpay.addItem("Due");
		
		

		
		
		//label bounds
		lb2.setBounds(500,70,300,30);		//title
		lab1.setBounds(100,100,150,30);		//patient name
		lab2.setBounds(100,200,150,30);		//date of admission	
		lab3.setBounds(100,250,150,30);		//Room type
		lab4.setBounds(100,350,150,30);		//pharmacy
		lab5.setBounds(500,100,150,30);		//Patient NO.
		lab6.setBounds(500,200,150,30);		//Date of Discharge
		lab7.setBounds(500,250,150,30);		//guardian
		lab8.setBounds(500,300,150,30);		//relation with patient
		//lab9.setBounds(500,350,150,30);		//payment method
		lab10.setBounds(100,300,150,30);		//lab charges
		lb1.setBounds(860,205,100,30);			//date
		roomChr.setBounds(500,350,150,30);		//room charges
		otherChr.setBounds(100,400,150,30);			//other charges
		total.setBounds(500,400,150,30);			//total
		payment.setBounds(100,450,150,30);			//payment
		perday.setBounds(860,350,100,30);			//perday
		
		//text field bounds
		text1.setBounds(250,110,150,20);	//patient name
		text2.setBounds(250,210,150,20);	//date of admission	
		text3.setBounds(250,260,150,20);	//Room type
		text4.setBounds(250,360,150,20);	//pharmacy
		text5.setBounds(700,110,150,20);	//Patient NO.
		text6.setBounds(700,210,150,20);	//Date of Discharge
		text7.setBounds(700,260,150,20);	//guardian
		text8.setBounds(700,310,150,20);	//relation with patient 
		text9.setBounds(250,310,150,20);	//lab charges
		roomChT.setBounds(700,360,150,20);	//room charges
		othertf.setBounds(250,410,150,20);	//other charges
		totaltf.setBounds(700,410,150,20);	//total
		
		//lables 
		frame.add(lb2);
		frame.add(lab1);
		frame.add(lab2);
		frame.add(lab3);
		frame.add(lab4);
		frame.add(lab5);
		frame.add(lab6);
		frame.add(lab7);
		frame.add(lab8);
		//frame.add(lab9);
		frame.add(lab10);
		frame.add(lb1);
		frame.add(roomChr);
		frame.add(otherChr);
		frame.add(total);
		frame.add(payment);
		
		frame.add(perday);
		
		
		frame.add(chpay);
		
		//text field 
		frame.add(text1);
		frame.add(text2);
		frame.add(text3);
		frame.add(text4);
		frame.add(text5);
		frame.add(text6);
		frame.add(text7);
		frame.add(text8);
		frame.add(text9);
		frame.add(roomChT);
		frame.add(othertf);
		frame.add(totaltf);
		
		//frame.add(chpay);
		
		//adding buttons 
		frame.add(btn);		//search 
		frame.add(btn1);	//paid 
		frame.add(btn2);	//clear 
		frame.add(btn3);	//back
		
		// date section discharge date
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String today = localDate.format(formatter);
	
		text6.setText(today);
		text6.setEditable(false);
		
		//action listener for clear
			btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==btn2)
				{
					text5.setEditable(true);
					text1.setText("");		//patient name
					text2.setText("");		//date of admission	
					text3.setText(""); 		//Room type
					text4.setText(""); 		//pharmacy
					text5.setText(""); 		//Patient NO.
					text6.setText(""); 		//Date of Discharge
					text7.setText(""); 		//guardian name
					text8.setText(""); 		//relation with patient
					text9.setText(""); 		//lab charges
					roomChT.setText(""); 		//room charges
					othertf.setText(""); 		//other
					totaltf.setText(""); 		//total
					rnotf.setText(""); 		//room no.
		
				}
			}
		});
		
		//action listener for searching
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
						
						while(rs.next())
						{
							if(text5.getText().equals(rs.getString(1)))
							{
								i=1;
								break;			
							}
						}
							if(i==1)
							{
								text5.setEditable(false);
									text5.setText(rs.getString(1));
									text1.setText(rs.getString(2));
									text3.setText(rs.getString(6));
									rnotf.setText(rs.getString(7));
									text2.setText(rs.getString(9));
									text5.setText(rs.getString(1));
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
		
		
		
		//action listener for save button
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
						smt1=con.prepareStatement("insert into bill values(?,?,?,?,?,?,?,?,?,?,?)");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Save this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						
						if(ch==0)
							{								
								smt1.setString(1,text5.getText());			//patient no
								smt1.setString(2,text1.getText());			//name
								smt1.setString(3,rnotf.getText());			//room no.
								smt1.setString(4,text6.getText());			//discharge date
								smt1.setString(5,text7.getText());			//guardian
								smt1.setString(6,text8.getText());			//relation
								smt1.setString(7,text9.getText());			//lab charges
								smt1.setString(8,text4.getText());			//pharmacy
								smt1.setString(9,othertf.getText());		//other charges
								smt1.setString(10,totaltf.getText());			//Total
								smt1.setString(11,chpay.getSelectedItem());			//payment
								//smt1.setString(7,chpay.getSelectedItem());		//type of payment
			
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
		
		
		//adding action listener for cal. button
		btn3.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==btn3)
			{
				String rtp = text3.getText();
									if(rtp.equals("Deluxe"))
									{
										roomChT.setText("2500");
									}
									if(rtp.equals("Private"))
									{
										roomChT.setText("2000");
									}
									if(rtp.equals("Semi-Private"))
									{
										roomChT.setText("1500");
									}
									if(rtp.equals("General"))
									{
										roomChT.setText("1000");
									}
									else
									{
										System.out.println("No Room");
									}
									
				int a = Integer.parseInt(text9.getText());  //lab charges
				int b = Integer.parseInt(text4.getText());	//pharmacy
				int c = Integer.parseInt(othertf.getText());	//other charges
				int d = Integer.parseInt(roomChT.getText());	//room charges
				
				final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				final String firstInput = text2.getText();
				final String secondInput = text6.getText();
				final LocalDate firstDate = LocalDate.parse(firstInput, formatter);
				final LocalDate secondDate = LocalDate.parse(secondInput, formatter);
				final long days = ChronoUnit.DAYS.between(firstDate, secondDate);
				System.out.println("Days between: " + days);
			
				int dy = (int) days;
				int tdy;
				int total2;
				
				if(days!=0)
				{
					tdy = d*dy;
					total2 = tdy+a+b+c;
				}
				else{
					total2=a+b+c+d;
				}
				
				totaltf.setText(String.valueOf(total2));
				
				
			}
		}
		});
		
		
		frame.add(backg);
		
		
	}
	
	
	public static void main(String[] arr)
	{
		new Billing();
	}
}