package project1;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import javax.swing.*;// Using AWT event classes and listener interfaces
import java.sql.*;

 class balance extends Frame implements ActionListener,WindowListener {
    private Label lblnm, lblac, lblbal;
    private Button btnbck,btnexit;
    private JLabel img3;
    private ImageIcon image;
    private Font font; 

    String s,s1,s2,s3;
    Connection con=connect.c();
    ResultSet r;
    
    public balance(){
        setLayout(null);
        img3=new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg") );
        img3.setBounds(0, 0,1400,800);
        add(img3);
        image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());

         try{
        
                    PreparedStatement ps=con.prepareStatement("Select name,accno,balance from userinfo where accno=?");
                    ps.setString(1,atm.acc);
                    r=ps.executeQuery();
                    if(r.next())
                    {
                      s=r.getString("name");
                        lblnm=new Label("NAME: "+s,Label.CENTER);
                        lblnm.setBounds(300,200,250,40);
                        img3.add(lblnm);
                        font=new Font("Arial",Font.BOLD,16);
                        lblnm.setFont(font);
                        s1=r.getString("accno");
                        lblac=new Label("ACCOUNT NUMBER: "+s1,Label.CENTER);
                        lblac.setBounds(300,300,250,40);
                        img3.add(lblac);
                        lblac.setFont(font);

                        s2=r.getString("balance");
                        lblbal=new Label("BALANCE: "+s2,Label.CENTER);
                        lblbal.setBounds(300, 400, 250, 40);
                        img3.add(lblbal);
                        lblbal.setFont(font);

                    }
                    
            }
         catch(Exception e)
         {
             System.out.println(e);
         }
         btnbck=new Button("BACK");
         btnbck.setBounds(300,500,250,40);
         btnbck.addActionListener(this);
         img3.add(btnbck);
         
         btnexit=new Button("EXIT");
         btnexit.setBounds(600,500,250,40);
         btnexit.addActionListener(this);
         img3.add(btnexit);
         addWindowListener(this);
         setTitle("BALANCE ENQUIRY");
         setSize(1400,1500);
         setVisible(true);
         
    }
     @Override
   public void actionPerformed(ActionEvent evt) {
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
