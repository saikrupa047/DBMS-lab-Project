package home;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
class LoggedIn extends JFrame implements ActionListener
{
	static Choice choice;
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	static JTextField search;
JButton srch;
static ScrollPane pane;
static JLabel item;
public LoggedIn()
{
	choice=new Choice();
	choice.add("Resources");
	choice.add("Products");
	choice.add("Refresh");
search=new JTextField();
srch=new JButton("Search");
pane=new ScrollPane();
item=new JLabel();
search.setBounds(400,100,700,30);
srch.setBounds(1105,100,95,30);
pane.setBounds(400,140,800,800);
choice.setBounds(800,50,100,50);
add(search);
add(srch);
add(pane);
add(choice);
pane.add(item);
setLayout(null);
setVisible(true);
srch.addActionListener(this);

setSize(1600,1000);
setTitle("Genix");
item.setText("Your Result will be shown here");
item.setBounds(100,0,800,100);
pane.setVisible(true);
}

public static void main(String arg[])
{
new LoggedIn();
}

public static Statement connect()throws ClassNotFoundException,SQLException
{
Class.forName("oracle.jdbc.driver.OracleDriver");  
con=DriverManager.getConnection(  
"jdbc:oracle:thin:@DESKTOP-4IKRAQ2:1521:orcl","scott","krupa");  
Statement stmt=con.createStatement();
return stmt;
}

public static void searchResult() {
	String a;
	int c=0;
	String s=choice.getSelectedItem();
	try {
	stmt=LoggedIn.connect();
	a=search.getText().toString();
	
	if(s=="Resources") {
		rs=stmt.executeQuery("select pname from plant"); 
	while(rs.next()) {
		if(rs.getString(1).toString().trim().equalsIgnoreCase(a)) {
			c+=1;
			break;
		}
	}
	if(c==1) {
		item.setText(a);
	}
	else{
		item.setText("No Results Found");
	}
	}
	
	if(s=="Products") {
		rs=stmt.executeQuery("select mname from medicine"); 
		while(rs.next()) {
			if(rs.getString(1).toString().trim().equalsIgnoreCase(a)) {
				c+=1;
				break;
			}
		}
		if(c==1) {
			item.setText(a);
		}
		else if(c==0){
			item.setText("No Results Found");
		}
		else {
			item.setText("Your results will be shown here");
		}
		}
	
	if(s=="Refresh") {
		item.setText("Your Results will be shown here");
		search.setText("");
	}
	LoggedIn.Close();
	}
	catch(Exception e){
		System.out.print(e);
	}
	
}

public static void Close() throws Exception{
	con.close();
}

public void actionPerformed(ActionEvent ae) {
	if(ae.getSource()==srch) {
		LoggedIn.searchResult();
	}
}



}
