import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.accessibility.*;
class demo extends JFrame implements ActionListener
{
      Font f=new Font("sans-serif",Font.BOLD,20);
      JPanel p=new JPanel();
      JPanel p1=new JPanel();
      ImageIcon login=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\login\\login.jpg");
      ImageIcon username=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\username.jpg");
      ImageIcon password=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\password.jpg");
      ImageIcon admin=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\admin.jpg");
      ImageIcon submit=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\submit.jpg");
      ImageIcon refresh=new ImageIcon("C:\\Users\\hem viraj naik\\Desktop\\refresh.jpg");
      JLabel l=new JLabel(login);
      JLabel l2=new JLabel(password);
      JLabel l1=new JLabel(username);
      JLabel l3=new JLabel(admin);


      JTextField t1=new JTextField();
      JPasswordField t2=new JPasswordField();
      JComboBox c=new JComboBox();
      JButton b1=new JButton("Login",submit);
      JButton b2=new JButton("Refresh",refresh);
      demo()
      {
            this.setLocation(500,200);
            this.setResizable(false);
            this.setLayout(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Login System");
            this.setSize(500,300);
            this.setVisible(true);


            p.setBounds(0,0,210,300);
            p.setBackground(Color.white);
            p.setLayout(null);
            p1.setBounds(210,0,500,300);
            p1.setBackground(Color.gray);
            p1.setLayout(null);
            this.add(p);
            this.add(p1);

            l.setBounds(5,30,200,200);
            l1.setBounds(20,30,50,40);
            t1.setBounds(80,30,150,40);
            l2.setBounds(20,90,50,40);
            t2.setBounds(80,90,150,40);
            l3.setBounds(20,150,50,40);
            c.setBounds(80,150,150,40);
            b1.setBounds(20,210,110,30);
            b2.setBounds(135,210,120,30);
            FlowLayout f1=new FlowLayout(FlowLayout.LEFT);
            b1.setLayout(f1);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b2.setLayout(f1);
            c.setFont(f);
            c.addItem("Admin");
            c.addItem("User");
            c.addItem("Staff");
            t1.setFont(f);
            t2.setFont(f);

            p1.add(l1);
            p1.add(t1);
            p1.add(l2);
            p1.add(t2);
            p1.add(l3);
            p1.add(c);
            p1.add(b1);
            p1.add(b2);
            p.add(l);
      }
      public void actionPerformed(ActionEvent k)
      {
            if(k.getSource()==b1) {

                  String username=t1.getText();
                  String password=t2.getText();
                  String user_type=c.getSelectedItem().toString();

                 System.out.println(username+password+user_type);
                  try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginsystem", "root", "password");
                        JOptionPane.showMessageDialog(null,"Connectionn Established!!!","Connection Status",JOptionPane.INFORMATION_MESSAGE);
                       
                        String sqlquery="SELECT * FROM user_detail WHERE password LIKE"+'"'+password+'"';
                        Statement s=con.createStatement();
                        s.execute(sqlquery);
                        ResultSet rs=s.executeQuery(sqlquery);
                        while(rs.next())
                        {
                              if(username.equals(rs.getString(1))&&password.equals(rs.getString(2))&&user_type.equals(rs.getString(3)))
                              {
                                     JOptionPane.showMessageDialog(null,"Login Successful!!!","Login Status",JOptionPane.INFORMATION_MESSAGE);
                              }
                              else
                              {
                                    JOptionPane.showMessageDialog(null,"Login Failed!!!","Login Status",JOptionPane.INFORMATION_MESSAGE);
                              }
                        }
                      }
                  
               
                  catch(Exception k1)
                  {
                        //JOptionPane.showMessageDialog(null,"Connection failed","Connection Status",JOptionPane.ERROR_MESSAGE);
						System.out.println(k1);
                  }
            }
            if(k.getActionCommand()=="Refresh")
            {
                  int x=JOptionPane.showConfirmDialog(null,"Do you want to Refresh?","Refresh",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                  if(x==0){
                        t1.setText(null);
                        t2.setText(null);
                        c.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null,"Fields refreshed!!!","Refresh Message",JOPtionPane.INFORMATION_MESSAGE);
                  }
                  else{
                        repaint();
                  }

            }
      }
}
public class d1
{
      public static void main(String arguments[])
      {
            demo d=new demo();
      }
}
