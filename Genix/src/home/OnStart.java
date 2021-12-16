package home;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
public class OnStart extends JFrame implements ActionListener{
	JButton login,register;
	JTextField uname,pwd;
	JLabel log,reg,un,pw;
	GridLayout g;
	JWindow w;
	static Connection con;

	OnStart()
	{
	w=new JWindow(this);
	setLayout(null);
	w.setLayout(null);
	setSize(1000,1000);
	setVisible(true);
	setTitle("Genix");
	setSize(500,400);
	login=new JButton("Login");
	register=new JButton("Register");
	uname=new JTextField(50);
	pwd=new JTextField(30);
	log=new JLabel("Already a member?");
	reg=new JLabel("New to Genix?");
	un=new JLabel("UserName:");
	pw=new JLabel("Password:");

	un.setBounds(100,50,120,15);    uname.setBounds(250,50,150,19);    
	pw.setBounds(100,70,120,15);    pwd.setBounds(250,70,150,19);
	reg.setBounds(100,90,120,15);   register.setBounds(250,90,100,19);
	log.setBounds(100,110,120,15);  login.setBounds(250,110,100,19);

	w.add(un);
	w.add(uname);
	w.add(pw);
	w.add(pwd);
	w.add(log);
	w.add(login);
	w.add(reg);
	w.add(register);
	w.setBounds(100,100,500,400);
	w.setBackground(Color.lightGray);
	w.setVisible(true);
	setBackground(Color.lightGray);
	login.addActionListener(this);
	register.addActionListener(this);
	login.setBackground(Color.cyan);
	register.setBackground(Color.cyan);
	}

	public static void main(String arg[])
	{
	OnStart o=new OnStart();
	}

	public static Connection connect()throws ClassNotFoundException,SQLException
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");  
	con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@DESKTOP-4IKRAQ2:1521:orcl","scott","krupa");  
	return con;
	}

	public void actionPerformed(ActionEvent ae)
	{
	int c=0;
	Statement stmt;
	ResultSet rs;
	if(ae.getSource()==register)
	{
	try
	{
	con=OnStart.connect();
	stmt=con.createStatement();
	String a=uname.getText();
	String b=pwd.getText();
	rs=stmt.executeQuery("select uname from customer");
	while(rs.next())
	{
	if (rs.getString(1).toString().equals(uname.getText()))
	{
	c+=1;
	break;
	}
	}
	if(c==0)
	{
	rs=stmt.executeQuery("insert into customer values( '"+a+"','"+b+"')" ); 
	SuccessfulRegister yesreg=new SuccessfulRegister();
	uname.setText("");
	pwd.setText("");
	}
	else {
		AlreadyAMember already=new AlreadyAMember();
		uname.setText("");
		pwd.setText("");
	}
	con.close();
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	
	if(ae.getSource()==login){
		try
		{
		con=OnStart.connect();
		stmt=con.createStatement();
		String a=uname.getText();
		String b=pwd.getText();
		rs=stmt.executeQuery("select password from customer where uname='"+a+"' ");
		Boolean bool=rs.next();
		if (rs.getString(1).toString().equals(pwd.getText()))
		{
		new LoggedIn();
		}
		else 
		{
			JOptionPane.showMessageDialog(null,ae);
		}
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	}

}
