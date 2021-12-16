package home;
import java.awt.*;
import javax.swing.*;

public class AlreadyAMember extends JFrame{
	public AlreadyAMember()
	{
	JLabel l=new JLabel("You are already a Genix member.");
	JLabel l2=new JLabel("Please go Login!");
	add(l);
	add(l2);
	setLayout(null);
	setVisible(true);
	this.setSize(500, 500);
	l.setBounds(200,200,200,20);
	l2.setBounds(200,230,200,20);
	}
}
