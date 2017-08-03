import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

class AddUser extends JFrame
{
	JFrame f;
	JLabel l1,l2,l3;
	JButton b1,b2;
	JTextField j1;
	JPasswordField j2;
	
	String st;
	ResultSet rs;
	Statement smt;
	Connection con;
	PreparedStatement smt1;
	
	int i;
		AddUser()
		{
			f=new JFrame();
			f.setVisible(true);
			f.setSize(400,300);
			f.setLocationRelativeTo(null);
			f.setTitle("Add User");
			f.setResizable(false);
			f.setLayout(null);
	
			ImageIcon icon = new ImageIcon("img/login.png");
			f.setIconImage(icon.getImage());
			
			l1 = new JLabel("Username");		
			JLabel l2 = new JLabel("Password");		//creation of password lable
	
			j1 = new JTextField();				//creation of username text field
			j2 = new JPasswordField();		//creation of password field

			l3= new JLabel("Hospital Management System");
	
			b1 = new JButton("Add");
			b2 = new JButton("Back");
	
			Font f1 = new Font("Algerian",Font.PLAIN,20);
			ImageIcon icon2 = new ImageIcon("img/username.png");
			ImageIcon icon3 = new ImageIcon("img/pass.png");
				
			l1.setIcon(icon2);
			l1.setBounds(20,60,150,50);			//username lable bounds set
			j1.setBounds(170,70,170,30);		// text field bounds set
			
			l2.setIcon(icon3);
			l2.setBounds(20,110,150,50);	//password lable bounds set
			j2.setBounds(170,120,170,30);	//password field bounds set
		
			l3.setBounds(40,10,350,40);
			l3.setFont(f1);
		
			b1.setBounds(170,180,70,30); //button login bounds
			b2.setBounds(260,180,80,30); //button cancel bounds
			
			//action lister for adding user
			b1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
				Object o = e.getSource();
				if(o==b1)
				{
					try{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj"); 
						smt1=con.prepareStatement("insert into Login values(?,?)");
						int ch=JOptionPane.showConfirmDialog(null,"Do You Want to Save this Data ?","Confirm",JOptionPane.YES_NO_OPTION);
						
						
						if(ch==0)
							{
								smt1.setString(1,j1.getText());		 		//username
								smt1.setString(2,j2.getText());		 		//password
								
								smt1.executeUpdate();
								JOptionPane.showMessageDialog(null,"New user is added.");
							}
						
						}
						catch(Exception ex)
						{
							System.out.println(ex);
						}
					
				}
			}
				
			});
		
					
			//adding action listener for back button
			b2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==b2)
			{
				f.setVisible(false);
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
			new AddUser();
		}
}
