package home;
import java.awt.*;
import javax.swing.*;
public class SuccessfulRegister extends JFrame
{
public SuccessfulRegister()
{
JLabel l=new JLabel("You have successfully Registered!");
JLabel l2=new JLabel("please go back to Login");
setLayout(null);
add(l);
add(l2);
l.setBounds(200,200,200,20);
l2.setBounds(200,230,200,20);
setVisible(true);
}

public static void main(String arg[])
{
	SuccessfulRegister a=new SuccessfulRegister();
	}
}