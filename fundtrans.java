package project1;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;// Using AWT event classes and listener interfaces
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class fundtrans extends Frame implements ActionListener, WindowListener{
    private Label lblfund, lblcash,lbltrans,lblacc,lblinsuf,lblincrct,lblsuc,lblsame;
    private TextField tffund,tfacc;
    private Button btntrans, btnbck,btnexit;
    private JLabel img6;
              private ImageIcon image;
    private Font font, f; 
    DateFormat dateFormat;
    Date date;
    Connection con=connect.c();
    ResultSet r,r1,r2,r3;
    String s,s1,s2,s3,s4;
    int b1,bal1,bal2,i;
    public fundtrans()
    {
        setLayout(null);
         img6= new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
         img6.setBounds(0,0,1400,800);
                        add(img6);
                        image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
         this.setIconImage(image.getImage());
         font=new Font("Arial",Font.BOLD,25);
         f=new Font("Arial",Font.PLAIN,15);

         lblfund=new Label("FUND TRANSFER",Label.CENTER);
         lblfund.setBounds(400,100,600,50);
         img6.add(lblfund);
         lblfund.setFont(font);
         lblacc=new Label("RECEPIENT'S ACCOUNT NUMBER",Label.CENTER);
         lblacc.setBounds(400, 250, 275, 50);
         img6.add(lblacc);
         lblacc.setFont(f);
         tfacc=new TextField("",20);
         tfacc.setBounds(700,250,250,50);
         img6.add(tfacc);
         lblcash=new Label("ENTER THE AMOUNT TO BE TRANSFERED",Label.CENTER);
         lblcash.setBounds(400,350,325,50);
         img6.add(lblcash);
         lblcash.setFont(f);
         tffund=new TextField("",15);
         tffund.setBounds(750,350,200,50);
         img6.add(tffund);
         btntrans=new Button("TRANSFER");
         btntrans.setBounds(600,500,200,50);
         img6.add(btntrans);
         btntrans.addActionListener(this);
         btnbck=new Button("BACK");
         btnbck.setBounds(525,600,150,50);
         img6.add(btnbck);
         btnbck.addActionListener(this);
         btnexit=new Button("EXIT");
         btnexit.setBounds(700,600,150,50);
         img6.add(btnexit);
         btnexit.addActionListener(this);
         addWindowListener(this);
         setTitle("FUND TRA50NSFER");  // "super" Frame sets its title
         setSize(1400, 1500);        // "super" Frame sets its initial window size
 
         setVisible(true);
      
    }
    
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        s1=tffund.getText();
        s2=tfacc.getText();
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
        if(s.equals("TRANSFER"))
        {
          if(s2.equals(atm.acc))
          {
              lblsame=new Label("RECEPIENT's ACCOUNT NUMBER CANNOT BE SAME AS YOURS",Label.CENTER);
              lblsame.setBounds(980,250 ,400, 50);
              img6.add(lblsame);
              lblsame.setForeground(Color.red);
              lblsame.setVisible(true);
                        }
          else
          {     
            try{
            PreparedStatement ps=con.prepareStatement("Select balance from userinfo where accno=?");
            ps.setString(1, atm.acc);
            r=ps.executeQuery();
            if(r.next())
            {
                s3=r.getString("balance");
                
            }
             PreparedStatement ps1=con.prepareStatement("Select balance from userinfo where accno=?");
            ps1.setString(1, s2);
            r1=ps1.executeQuery();
            if(r1.next())
            {
                s4=r1.getString("balance");
                 bal1=Integer.parseInt(s3);
            bal2=Integer.parseInt(s4);
            b1=Integer.parseInt(s1);
            if(bal1<b1)
            {
                lblinsuf=new Label("INSUFICIENT BALANCE",Label.CENTER);
                lblinsuf.setBounds(1000,350,200,50);
                img6.add(lblinsuf);
                lblinsuf.setForeground(Color.red);
                lblincrct.setVisible(false);
                lblsuc.setVisible(false);
                lblinsuf.setVisible(true);
            }
            else
            {
                try{
                PreparedStatement ps5=con.prepareStatement("select max(sno) from transactions");
                r3=ps5.executeQuery();
                if(r3.next())
                {
                    atm.count=r3.getInt("max(sno)");
                    
                    atm.count++;
                     bal1=bal1-b1;
                     bal2=bal2+b1;
     
            s3=Integer.toString(bal1);
            s4=Integer.toString(bal2);
             try{
             PreparedStatement ps2=con.prepareStatement("Update userinfo set balance=? where accno=?");
             ps2.setString(1,s3);
             ps2.setString(2,atm.acc);
             ps2.executeUpdate();
             PreparedStatement ps3=con.prepareStatement("Update userinfo set balance=? where accno=?");
             ps3.setString(1,s4);
             ps3.setString(2,s2);
             ps3.executeUpdate();
             dateFormat= new SimpleDateFormat();
             date=new Date();
             PreparedStatement ps4=con.prepareStatement("insert into transactions (accno,amount,action,datetrans,sno)values(?,?,?,?,?)");
             ps4.setString(1,atm.acc);
             ps4.setString(2,s1);
             ps4.setString(3,"Transferred to "+s2);
             ps4.setString(4,dateFormat.format(date));
             ps4.setInt(5,atm.count);
             i=ps4.executeUpdate();
             lblsuc=new Label("TRANSFER SUCCESSFUL");
             lblsuc.setBounds(850,500,200,50);
             img6.add(lblsuc);
             lblincrct.setVisible(false);
             lblinsuf.setVisible(false);
             lblsuc.setForeground(Color.GREEN);
             lblsuc.setVisible(true);
            }
             catch(Exception ex){
                 System.out.println(ex);
                                 System.out.println("hello2");

             }
           
                 
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
                System.out.println("hello1");
            }
            }
                
            }
            else
            {
                lblincrct=new Label("INCORRECT ACCOUNT NUMBER!");
                lblincrct.setBounds(1000,250,250,50);
                img6.add(lblincrct);
                lblinsuf.setVisible(false);
                lblincrct.setForeground(Color.red);
                lblincrct.setVisible(true);
            }
            }
             catch(Exception e)
            {
                System.out.println(e);
                                System.out.println("hello3");

            }
           
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

