import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JTable;
import java.awt.event.MouseEvent;
import java.awt.event.*;
 
class PatientTable extends JFrame
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;

    PatientTable()
    {
        Vector columnNames = new Vector();
        Vector data = new Vector();
 
        try
        {
            //  Connect to the Database
 
	String driver = "oracle.jdbc.driver.OracleDriver";
 
	try{
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","neeraj"); 
	//DriverManager.getConnection("Jdbc:Odbc:doc");
	}

	catch(Exception e)
		{
			System.out.println(e);
		}	




 
            //  Read data from a table
 
            String sql = "Select * from Patient";
			Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
			
            int columns = md.getColumnCount();
 
            //  Get column names
 
            for (int i = 1; i <= columns; i++)
            {
				columnNames.addElement(md.getColumnName(i));
            }
 
            //  Get row data
 
            while (rs.next())
            {
                Vector row = new Vector(columns);
 
                for (int i = 1; i <= columns; i++)
                {
				   row.addElement(rs.getObject(i)); 

                }
 
                data.addElement( row );
            }
 
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
 
        //title for table data
			ImageIcon icon = new ImageIcon("img/Patient record.jpg");
			JLabel lab = new JLabel(icon);
			JPanel buttonPane2 = new JPanel();
			buttonPane2.add(lab);
			getContentPane().add( buttonPane2, BorderLayout.PAGE_START);
		
		//  Create table with database data
 
		JTable table = new JTable(data, columnNames);
		

 
		JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add( scrollPane );
 
        JPanel buttonPanel = new JPanel();
		getContentPane().add( buttonPanel, BorderLayout.SOUTH );
		
		
    }
 
    public static void main(String[] args)
    {
			PatientTable frame = new PatientTable();

			frame.setDefaultCloseOperation( EXIT_ON_CLOSE);
			frame.pack();
			frame.setTitle("Patient Records");
			frame.setVisible(true);
			ImageIcon ficon = new ImageIcon("img/HpIcon.png");
			frame.setIconImage(ficon.getImage());
    }
}

