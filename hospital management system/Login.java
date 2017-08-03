import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

class Login extends JFrame
{
	JFrame f;
	JLabel l1,l2,l3;
	JButton b1,b2;
	JTextField j1;
	JPasswordField j2;
	
	String st;
	ResultSet rs;
	Statement smt;
	
	int i;
		Login()
		{
			f=new JFrame();
			f.setVisible(true);
			f.setSize(400,300);
			f.setLocationRelativeTo(null);
			f.setTitle("Login");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
			f.setLayout(null);
	
			ImageIcon icon = new ImageIcon("img/login.png");
			f.setIconImage(icon.getImage());
			
			l1 = new JLabel("Username");		
			JLabel l2 = new JLabel("Password");		//creation of password lable
	
			j1 = new JTextField();				//creation of username text field
			j2 = new JPasswordField();		//creation of password field

			l3= new JLabel("NORB HOSPITAL");
	
			b1 = new JButton("Login");
			b2 = new JButton("cancel");
	
			Font f1 = new Font("Algerian",Font.PLAIN,20);
			ImageIcon icon2 = new ImageIcon("img/username.png");
			ImageIcon icon3 = new ImageIcon("img/pass.png");
				
			l1.setIcon(icon2);
			l1.setBounds(20,60,150,50);			//username lable bounds set
			j1.setBounds(170,70,170,30);		// text field bounds set
			
			l2.setIcon(icon3);
			l2.setBounds(20,110,150,50);	//password lable bounds set
			j2.setBounds(170,120,170,30);	//password field bounds set
		
			l3.setBounds(120,10,350,40);
			l3.setFont(f1);
		
			b1.setBounds(170,180,70,30); //button login bounds
			b2.setBounds(260,180,80,30); //button cancel bounds
			
			b1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==b1)
				{
					try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj");
						smt=con.createStatement();
						rs=smt.executeQuery("SELECT * FROM Login");
						
						while(rs.next())
						{
							if(j1.getText().equals(rs.getString(1)) && j2.getText().equals(rs.getString(2)))
							{
								i= 1;
								break;
							}
						}
							if(i==1)
							{
								JOptionPane.showMessageDialog(null,"Login Successful.......|");
								//Login.this.dispose();
								f.setVisible(false);
								System.out.println("Login Successfull");
								new Home();
							}
							else
								{
									JOptionPane.showMessageDialog(null,"Login Not Successful.......|");
									System.out.println("Failer");
								}
								j1.setText("");
								j2.setText("");
						}
						catch(Exception ex)
						{
							System.out.println(ex);
						}
					
				}
			}
				
			});
		
		
			b2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Object o = e.getSource();
					if(o==b2)
					{
						System.exit(0);
					}
				}
			});
		
			f.add(l1);	// adding username lable to frame
			f.add(j1);	// adding textfield lable to frame
			f.add(l2);	// adding password lable to frame
			f.add(j2);	// adding password field lable to frame
			f.add(l3);	// title lable adding
			f.add(b1);	//button login
			f.add(b2);	//button cancel
	
			
		}
		
		public static void main(String[] args)
		{
			new Login();
		}
}
