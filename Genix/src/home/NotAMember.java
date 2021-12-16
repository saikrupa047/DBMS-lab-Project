package home;
import java.awt.*;
import javax.swing.*;
public class NotAMember extends JFrame
{
public NotAMember()
{
JLabel l=new JLabel("You are not a Genix member.");
JLabel l2=new JLabel("Please go Register first!");
add(l);
add(l2);
setLayout(null);
setVisible(true);
l.setBounds(200,200,200,20);
l.setBounds(200,230,200,20);
}

}

