package project1;
import java.awt.*;        
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


   class atm extends Frame implements ActionListener, WindowListener {
   private Label lblnumber,lblpswd,lblerr,lblerr1,lbltime,lblblck;  
   private Label lblatmpt;
   private TextField tfnumber, tfpswd;
   private Button btn;  
   private JLabel img;
   private ImageIcon image;
   private Font font,f1;
   static menu m;
   int i,c=3;
   String b,b1;
   static String acc,pin;
   static int count=0;
   Connection con=connect.c();
   ResultSet r,r1,r5;
   Thread t;
   // Constructor to setup GUI components and event handlers
   public atm () {
         // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
         // the components from left-to-right, and flow to next row from top-to-bottom.
               setLayout(null);
        font=new Font("Arial",Font.BOLD,20);
        f1=new Font("Arial", Font.PLAIN, 20);
      img= new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
      img.setBounds(0,0,1400,800);
      add(img);
      image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
      this.setIconImage(image.getImage());


      lblnumber = new Label("ACCOUNT NUMBER",Label.CENTER);
      lblnumber.setBounds(425,200,200,50);// construct the Label component
      img.add(lblnumber);                    // "super" Frame adds Label
      lblnumber.setFont(f1);
      tfnumber = new TextField("", 10); // construct the TextField component
      tfnumber.setEditable(true);   
      tfnumber.setBounds(725, 200,200,50);// set to read-only
      img.add(tfnumber);                     // "super" Frame adds TextField
       
      lblpswd=new Label("PASSWORD",Label.CENTER);
      lblpswd.setBounds(425,300,200,50);
      img.add(lblpswd);
      lblpswd.setFont(f1);
      tfpswd=new TextField("",10);
      tfpswd.setBounds(725,300,200,50);
      img.add(tfpswd);
      
      tfpswd.setEchoChar('*');
      
      btn = new Button("LOGIN");
            btn.setBounds(575,400,200,50);// construct the Label component
      img.add(btn);                    // "super" Frame adds Button
 
      btn.addActionListener(this);
         // btnCount is the source object that fires ActionEvent when clicked.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Clicking btnCount invokes actionPerformed().
         lblatmpt=new Label();
         lblatmpt.setBounds(500,700,300,50);
         addWindowListener(this);
      setTitle("ATM");  // "super" Frame sets its title
      setSize(1400, 1500);        // "super" Frame sets its initial window size
 
      setVisible(true);
      t=new Thread()
              {
                  public void run()
              {
                  lbltime=new Label("LOGIN WITHIN 30 seconds",Label.CENTER);
                  lbltime.setBounds(475,600,400,50);
                  img.add(lbltime);
              
                  
                  for( i=30; i>=0; i--)
              {
                  try{
                      Thread.sleep(1000);
                  }
                  catch(Exception ex)
              {
                  System.out.println(ex);
              }
                  lbltime.setText("LOGIN WITHIN "+i+" seconds");
                  lbltime.repaint();
                  lbltime.setFont(font);
                  lbltime.setForeground(Color.red);
                  
              }
                  atmwelcome a2=new atmwelcome();
                  setVisible(false);
              }
              };
      t.start();
   }
       @Override
   public void actionPerformed(ActionEvent evt) {
              acc=tfnumber.getText();
              pin=tfpswd.getText();

     try{
        
                    PreparedStatement ps=con.prepareStatement("Select * from password where accnum=? AND pin=?");
                    PreparedStatement ps7=con.prepareStatement("Select * from userinfo where accno=?");
     ps7.setString(1,acc);
     ResultSet r7=ps7.executeQuery();
     ps.setString(1,acc);
     ps.setInt(2,Integer.parseInt(pin));
                    
     r=ps.executeQuery();
     PreparedStatement ps1=con.prepareStatement("Select * from password where accnum=?");
     ps1.setString(1,acc);
     r1=ps1.executeQuery();
     if(r.next())
     {
       if(r7.next())
       { b1=r7.getString("status");
       System.out.println("P1");
         if(b1.equals("allowed"))
         {
             t.stop();
         m=new menu();
        this.setVisible(false);
         }
         else
         {
                    System.out.println("P2");

              JOptionPane.showMessageDialog(null, "YOUR ACCOUNT IS BLOCKED. CONTACT THE BANK");
                  System.exit(0);
         }
       }
         
     }
     else if(r1.next())
             {   
                 try{
                     
                  PreparedStatement ps5=con.prepareStatement("select * from userinfo where accno=?");
                  ps5.setString(1,acc);
                  r5=ps5.executeQuery();
                  if(r5.next())
                  {
                                        b=r5.getString("status");

                  if(b.equals("allowed"))
                  {
                      c--;
                      if(c>0)
                      {
                           lblerr=new Label("INVALID PIN!",Label.CENTER);
                           lblerr.setBounds(950,300,100,50);
                           img.add(lblerr);
                           lblerr.setForeground(Color.red);
                           lblerr1.setVisible(false);
                           lblerr.setVisible(true);                
                           lblatmpt.setText("YOU HAVE "+c+" ATTEMPTS LEFT");
                           img.add(lblatmpt);
                           lblatmpt.setVisible(true);
                
                      }
                      else
                      {
                          try
                          {
                            PreparedStatement ps6=con.prepareStatement("update userinfo set status=? where accno=?");
                            ps6.setString(1,"BLOCKED");
                            ps6.setString(2, acc);
                            int i=ps6.executeUpdate();
                            if(i>=0)
                            {
                               lblatmpt.setVisible(false);
                               lblblck=new Label("YOUR ACCOUNT HAS BEEN BLOCKED",Label.CENTER);
                               lblblck.setBounds(500,700,300,50);
                               img.add(lblblck);
                            lblblck.setVisible(true);
                               t.stop();
                            }
                              
                          }
                          catch(Exception e1)
                          {
                             System.out.println(e1); 
                                                          System.out.println("h1"); 

                          }
                      }
                  }  
                  }
                 }
                                   catch(Exception exc)
                                   {
                                       System.out.println(exc);
                                                                    System.out.println("h2"); 

                                   }

             }
     else
     {
         
        lblerr1=new Label("ACCOUNT NOT FOUND",Label.CENTER);
        lblerr1.setBounds(950, 200,200,50);
        lblerr1.setForeground(Color.red);
             img.add(lblerr1);
             lblerr.setVisible(false);
             lblerr1.setVisible(true);
           
      }
     
   }
     catch(Exception excp)
     {
         System.out.println(excp);
                 System.out.println("h3"); 

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
