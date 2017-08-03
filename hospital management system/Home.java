import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;

class Home extends JFrame
{
	JFrame f;
	JMenuBar mb;
	JMenu check,bill,doctor,help,Users,records;
	JMenuItem npatient,update,view,exit,billp,addDoc,doc,about,adduser,deluser,viewDoc,docTable,patientTable,billTable;
	JLabel bg;
	
	Home()
	{
	f = new JFrame();
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setBounds(0,0,800,600);
	f.setTitle("NORB HOSPITAL");
	
	//creating object for icons
	ImageIcon icon = new ImageIcon("img/HpIcon.png");
	ImageIcon icon1 = new ImageIcon("img/backg.png");
	
	//set icon to frame
	f.setIconImage(icon.getImage());
	
	//creating menu bar and adding to frame
	JMenuBar mb = new JMenuBar(); //creat menu bar
	f.setJMenuBar(mb);				//add menu bar to frame
	
	//creating menu for menubar
	check = new JMenu("Patient");		//menu item patient
	bill = new JMenu("Billing");		//menu item billing
	doctor = new JMenu("Doctors");		//menu item Doctors
	Users = new JMenu("Users");			//users
	records = new JMenu("Records");		//Records
	help = new JMenu("Help");			//menu item Help
	
	//adding menu item to menu bar
	mb.add(check);
	mb.add(bill);
	mb.add(doctor);
	mb.add(Users);
	mb.add(records);
	mb.add(help);
	
	//creating menu items for patient menu
	npatient = new JMenuItem("New Patient");
	update = new JMenuItem("Update");
	view = new JMenuItem("View & Delete");
	exit = new JMenuItem("Exit");
	
	//creating menu items for table Records
	docTable= new JMenuItem("Doctor Records");
	patientTable=new JMenuItem("Patient Records");
	billTable=new JMenuItem("Bill Records");
	
	records.add(docTable);
	records.add(patientTable);
	records.add(billTable);
	
	//creating menu for items
	adduser = new JMenuItem("Add User");
	deluser = new JMenuItem("Delete User");
	
	Users.add(adduser);
	Users.add(deluser);
	
	//adding patient menu items to patient 
	check.add(npatient);
	check.add(update);
	check.addSeparator();
	check.add(view);
	check.addSeparator();
	check.add(exit);
	
	//creating menu item for billing menu
	billp = new JMenuItem("Bill Payments");
	
	//adding bill menu item to billing menu
	bill.add(billp);
	
	//creating menu item for doctor
	addDoc=new JMenuItem("Add Doctors");
	doc=new JMenuItem("Update Dr. Info");
	viewDoc=new JMenuItem("View & Delete");
	
	
	//creating menu item for help menu
	about = new JMenuItem("About Us");
	
	//creating lable for backgound image
	bg = new JLabel(icon1);
	bg.setBounds(0,0,1366,768);
	
	//adding doctor menu items to doc menu
	doctor.add(addDoc);
	doctor.add(doc);
	doctor.add(viewDoc);
	
	
	//adding menu item to help menu
	help.add(about);
	
	//action Listener for new patient
	npatient.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==npatient)
			{
				new NewPatient();
			}
		}
	});
	
	//action listener for modify patient
	update.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==update)
			{
				new ModifyPatientInfo();
			}
		}
	});
	
	//action listener for view patient
	view.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==view)
			{
				new ViewPatientInfo();
			}
		}
	});
	
	//adding action listener for exit
		exit.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==exit)
			{
				System.exit(0);
			}
		}
		});
	
	//action listener for add doctor
	addDoc.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==addDoc)
			{
				new DoctorInfo();
			}
		}
	});
	
	//action listener for update doctor info
	doc.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==doc)
			{
				new ModifyDocInfo();
			}
		}
	});
	
	//action Listener for billing
	billp.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==billp)
			{
				new Billing();
			}
		}
	});
	
	//action Listener for adduser
	adduser.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==adduser)
			{
				new AddUser();
			}
		}
	});
	
	//action Listener for deluser
	deluser.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==deluser)
			{
				new DelUser();
			}
		}
	});
	
	//action Listener for about
	about.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==about)
			{
				new About();
			}
		}
	});
	
	//View doctor info
	viewDoc.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			if(o==viewDoc)
			{
				new ViewDocInfo();
			}
		}
	});
	
	//action Listener doctor table
	 docTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o=e.getSource();
				if(o==docTable)
				{
					new DoctTable();
					DoctTable frame=new DoctTable();
					frame.setTitle("Doctor Records");
					frame.setDefaultCloseOperation(1);
					frame.pack();
					frame.setVisible(true);
					ImageIcon ficon = new ImageIcon("img/HpIcon.png");
					frame.setIconImage(ficon.getImage());
				}
			}
		});
		
		//action listener for patient table
		 patientTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o=e.getSource();
				if(o==patientTable)
				{
					new PatientTable();
					PatientTable frame=new PatientTable();
					frame.setTitle("Patient Records");
					frame.setDefaultCloseOperation(1);
					frame.pack();
					frame.setVisible(true);
					ImageIcon ficon = new ImageIcon("img/HpIcon.png");
					frame.setIconImage(ficon.getImage());
				}
			}
		});
		
		//action listener for bill
		billTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Object o=e.getSource();
				if(o==billTable)
				{
					new BillTable();
					BillTable frame=new BillTable();
					frame.setTitle("Bill Records");
					frame.setDefaultCloseOperation(1);
					frame.pack();
					frame.setVisible(true);
					ImageIcon ficon = new ImageIcon("img/HpIcon.png");
					frame.setIconImage(ficon.getImage());
				}
			}
		});
	
	
	//addng backgound image to frame
	f.add(bg);
	}
	
	
	
	public static void main(String[] args)
	{
		new Home();
	}
}