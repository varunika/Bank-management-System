package project1;
import java.awt.*;        
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class passwordchange extends Frame implements ActionListener,WindowListener {
    private Label lblpswd,lblentr,lblchng,lblold,lblcnfrm,lblinv,lblnomatch;
    private TextField tfpswd,tfcnfrm,tfold;
    private Button btnchng, btnbck, btnexit;
    private JLabel img7;
              private ImageIcon image;
    private Font font,f;
    String s,s1,s2,s3,s4;
    Connection con=connect.c();
    ResultSet r;
    public passwordchange()
    {
        setLayout(null);
         img7= new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
            img7.setBounds(0,0,1400,800);
            add(img7);
            image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());
 font=new Font("Arial",Font.BOLD,25);
                f=new Font("Arial",Font.PLAIN,15);

         lblpswd=new Label("CHANGE YOUR PASSWORD HERE!",Label.CENTER);
         lblpswd.setBounds(400,100,600,50);
         img7.add(lblpswd);
         lblpswd.setFont(font);
         lblold=new Label("CURRENT PASSWORD",Label.CENTER);
         lblold.setBounds(500,250,200,50);
         img7.add(lblold);
         tfold=new TextField("",20);
         tfold.setBounds(725,250,200,50);
         img7.add(tfold);
         tfold.setEchoChar('*');
         lblentr=new Label("NEW PASSWORD");
         lblentr.setBounds(500,350,200,50);
         img7.add(lblentr);
         tfpswd=new TextField("",20);
         tfpswd.setBounds(725,350,200,50);
         img7.add(tfpswd);
         tfpswd.setEchoChar('*');
         lblcnfrm=new Label("CONFIRM NEW PASSWORD");
         lblcnfrm.setBounds(500,450,200,50);
         img7.add(lblcnfrm);
         tfcnfrm=new TextField("",20);
         tfcnfrm.setBounds(725,450,200,50);
                  img7.add(tfcnfrm);
                  tfcnfrm.setEchoChar('*');
         btnchng=new Button("CHANGE");
         btnchng.setBounds(600,550,200,50);
         img7.add(btnchng);
         btnchng.addActionListener(this);
         btnbck=new Button("BACK");
         btnbck.setBounds(525,650,150,50);
         img7.add(btnbck);
         btnbck.addActionListener(this);
         btnexit=new Button("EXIT");
         btnexit.setBounds(715,650,150,50);
         img7.add(btnexit);
         btnexit.addActionListener(this);
         addWindowListener(this);
          setTitle("CHANGE PASSWORD");  // "super" Frame sets its title
         setSize(1400, 1500);        // "super" Frame sets its initial window size
 
         setVisible(true);
      
        
    }
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        s1=tfold.getText();
        s2=tfpswd.getText();
        s3=tfcnfrm.getText();
         s=evt.getActionCommand();
       if(s.equals("BACK"))
       {
       atm.m.setVisible(true);
       this.setVisible(false);
       }
       if(s.equals("EXIT"))
       {
           this.setVisible(false);
           System.exit(0);
       }
       if(s.equals("CHANGE"))
       {
           try{
               
            PreparedStatement ps=con.prepareStatement("Select pin from password where accnum=?");
            ps.setString(1,atm.acc);
            r=ps.executeQuery();
            if(r.next())
            {
                s4=r.getString("pin");
                if(s1.equals(s4))
                {
                   if(s2.equals(s3))
                   {
                       PreparedStatement ps1=con.prepareStatement("Update password set pin=? where accnum=?");
                       ps1.setString(1,s2);
                       ps1.setString(2, atm.acc);
                       ps1.executeUpdate();
                       lblchng=new Label("PASWORD CHANGED!",Label.CENTER);
                       lblchng.setBounds(850,550,200,50);
                       lblchng.setForeground(Color.green);
                       img7.add(lblchng);
                       lblinv.setVisible(false);
                       lblnomatch.setVisible(false);
                       lblchng.setVisible(true);
                   }
                   else
                   {
                       lblnomatch=new Label("ENTER THE NEW PASSWORD CORRECTLY",Label.CENTER);
                       lblnomatch.setBounds(975,350,300,50);
                       img7.add(lblnomatch);
                       lblinv.setVisible(false);
                       lblchng.setVisible(false);
                       lblnomatch.setForeground(Color.red);
                       lblnomatch.setVisible(true);
                       
                   }
                }
                else
                {
                    lblinv=new Label("INVALID CURRENT PASSWORD",Label.CENTER);
                    lblinv.setBounds(975,250,200,50);
                    img7.add(lblinv);
                    lblnomatch.setVisible(false);
                    lblchng.setVisible(false);
                    lblinv.setForeground(Color.red);
                    lblinv.setVisible(true);
                    
                }
            }
           }
           catch(Exception excp)
           {
               System.out.println(excp);
           }
       }
        
    }
   @Override
       public void windowClosing(WindowEvent e)
       {
           System.exit(0);
       }
       @Override
       public void windowDeactivated(WindowEvent e)
       {
       }
       
       @Override
       public void windowActivated(WindowEvent e)
       {
       }
       @Override
       public void windowIconified(WindowEvent e)
       {
       }
       @Override
       public void windowDeiconified(WindowEvent e)
       {
       }
       
       @Override
       public void windowClosed(WindowEvent e)
       {
       }
       @Override
       public void windowOpened(WindowEvent e)
       {
       }  
    
}
