
package project1;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import javax.swing.*;// Using AWT event classes and listener interfaces
import java.sql.*;

class menu extends Frame implements ActionListener, WindowListener {
    private Label lblwel,lbltime;
    private Button balanceenq,ministat,cashwithdraw,cheque,fundtrans,pin,exit;
    private JLabel img2;
    private ImageIcon image;
    private Font font;
    String s,s1;
 Thread th;
 int k;
   Connection con=connect.c();
   ResultSet r;
  
    static balance b;
    static cash_withdraw cw;
    static chequeclear cq;
    static fundtrans ft;
    static ministatement ms;
    static passwordchange pc;
    
    public menu(){
        setLayout(null);
        img2=new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg") );
        img2.setBounds(0, 0,1400,800);
        add(img2);
        image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());
        font=new Font("Arial",Font.BOLD,20);
        try{
        PreparedStatement ps=con.prepareStatement("Select name from userinfo where accno=?");
        ps.setString(1,atm.acc);
        r=ps.executeQuery();
        if(r.next())
        {  
           s1=r.getString("name");
            lblwel=new Label("WELCOME"+" "+s1,Label.CENTER);
            lblwel.setBounds(500, 100, 400, 50);
            img2.add(lblwel);
            lblwel.setFont(font);
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
         balanceenq=new Button("BALANCE ENQUIRY");
         balanceenq.setBounds(150,200,250,40);
         img2.add(balanceenq);
         balanceenq.addActionListener(this);
         ministat=new Button("MINI STATEMENT");
         ministat.setBounds(150,300,250,40);
         img2.add(ministat);
         ministat.addActionListener(this);
         cashwithdraw=new Button("CASH WITHDRAWAL");
         cashwithdraw.setBounds(150,400,250,40);
         img2.add(cashwithdraw);
         cashwithdraw.addActionListener(this);
         cheque=new Button("CHEQUE CLEARANCE");
         cheque.setBounds(150,500,250,40);
         img2.add(cheque);
         cheque.addActionListener(this);
         fundtrans=new Button("FUND TRANSFER");
         fundtrans.setBounds(150,600,250,40);
         img2.add(fundtrans);
         fundtrans.addActionListener(this);
         pin=new Button("CHANGE PIN");
         pin.setBounds(150,700,250,40);
         img2.add(pin);
         pin.addActionListener(this);
         exit=new Button("EXIT");
         exit.setBounds(800,400,200,50);
         img2.add(exit);
         exit.addActionListener(this);
         addWindowListener(this);
         setTitle("MENU");
         setSize(1400,1500);
         setVisible(true);
          th=new Thread()
              {
                  public void run()
              {
                  lbltime=new Label();
                  lbltime.setBounds(700,500,400,50);
                  img2.add(lbltime);
              
                  
                  for( k=10; k>=0; k--)
              {
                  try{
                      Thread.sleep(1000);
                  }
                  catch(Exception ex)
              {
                  System.out.println(ex);
              }
                  lbltime.setText("YOUR SESSION EXPIRES IN "+k+" seconds");
                  lbltime.repaint();
                  lbltime.setFont(font);
                  
              }
                  JOptionPane.showMessageDialog(null, "YOUR SESSION EXPIRED!");
                  System.exit(0);
              }
              };
      th.start();
      
          }
     @Override
   public void actionPerformed(ActionEvent evt) {
    s=evt.getActionCommand();
    if(s.equals("BALANCE ENQUIRY"))
    {
        b= new balance();
             this.setVisible(false);
lbltime.setVisible(false);
    }
    if(s.equals("MINI STATEMENT"))
    {
        ms= new ministatement();
             this.setVisible(false);
             lbltime.setVisible(false);


    }
    if(s.equals("CASH WITHDRAWAL"))
    {
        cw=new cash_withdraw();
             this.setVisible(false);
             lbltime.setVisible(false);


    }
    if(s.equals("CHEQUE CLEARANCE"))
    {
        cq=new chequeclear();
             this.setVisible(false);
             lbltime.setVisible(false);


    }
    if(s.equals("FUND TRANSFER"))
    {
        ft= new fundtrans();
             this.setVisible(false);
             lbltime.setVisible(false);


    }
    if(s.equals("CHANGE PIN"))
    {
         pc=new passwordchange();
              this.setVisible(false);
              lbltime.setVisible(false);


    }
     if(s.equals("EXIT"))
    {
         this.setVisible(false);
         System.exit(0);

    }
   th.stop();
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
