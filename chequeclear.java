package project1;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;// Using AWT event classes and listener interfaces
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class chequeclear extends Frame implements ActionListener,WindowListener {
    private Label lblcheque,lblfinal,lbltf,lblsuc ;
    private Button btncheque, btnback,btnex;
    private TextField tfcheque;
    private JLabel img5;
    private ImageIcon image;
    private Font font,f;
    String s,s1,s2,s3,s4;
    int b,i;
    static int bal;
    DateFormat dateFormat;
    Date date;
    Connection con=connect.c(),con2=connect.c();
    ResultSet r,r1,r2;
    public chequeclear()
    {
         img5= new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
            img5.setBounds(0,0,1400,800);
                        add(img5);
                        image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());
         font=new Font("Arial",Font.BOLD,25);
                f=new Font("Arial",Font.BOLD,15);

         lblcheque=new Label("CHEQUE CLEARENCE",Label.CENTER);
         lblcheque.setBounds(400,100,600,50);
         img5.add(lblcheque);
         lblcheque.setFont(font);
         lbltf=new Label("ENTER THE CHEQUE AMOUNT",Label.CENTER);
         lbltf.setBounds(400, 250, 350, 50);
         img5.add(lbltf);
         lbltf.setFont(f);
         tfcheque=new TextField("",10);
         tfcheque.setBounds(775,250,200,50);
         img5.add(tfcheque);
         btncheque=new Button("ADD AMOUNT");
         btncheque.setBounds(600,350,200,40);
         img5.add(btncheque);
         btncheque.addActionListener(this);
         btnback=new Button("BACK");
         btnback.setBounds(535,450,150,40);
         img5.add(btnback);
         btnback.addActionListener(this);
         btnex=new Button("EXIT");
         btnex.setBounds(715,450,150,40);
         img5.add(btnex);
         btnex.addActionListener(this);
         addWindowListener(this);
          setTitle("CHEQUE CLEARENCE");  // "super" Frame sets its title
          setSize(1400, 1500);        // "super" Frame sets its initial window size
 
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
       if(s.equals("ADD AMOUNT"))
        {
          
         try
         {
            PreparedStatement ps=con.prepareStatement("select balance from userinfo where accno=?");
            ps.setString(1,atm.acc);
            r=ps.executeQuery();
            if(r.next())
                s2=r.getString("balance");

                bal=Integer.parseInt(s2);
                b=Integer.parseInt(tfcheque.getText());
                bal=bal+b;
            
                s2=Integer.toString(bal);
              try{
                PreparedStatement ps4=con.prepareStatement("select max(sno) from transactions");
                r2=ps4.executeQuery();
                if(r2.next())
                {
                    atm.count=r2.getInt("max(sno)");
                    atm.count++;
                }
                 dateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                 date = new Date();
                PreparedStatement ps1=con2.prepareStatement("Update userinfo Set balance=? where accno=?");
                ps1.setString(1, s2);
                ps1.setString(2, atm.acc);
                ps1.executeUpdate();
                PreparedStatement ps2=con.prepareStatement("select balance from userinfo where accno=?");
                ps2.setString(1,atm.acc);
                r1=ps2.executeQuery();
                if(r1.next())
                {
                    s3=r1.getString("balance");
                    lblsuc=new Label("CHEQUE CLEARED");
                    lblsuc.setBounds(600,520,200,50);
                    img5.add(lblsuc);
                    lblsuc.setForeground(Color.red);
                    lblfinal=new Label("YOUR AMOUNT AFTER THE TRANSACTION IS "+s3);
                    lblfinal.setBounds(525,600,350,50);
                    img5.add(lblfinal);
                    lblfinal.setForeground(Color.red);
                }
             PreparedStatement ps3=con.prepareStatement("insert into transactions (accno,amount,action,datetrans,sno)values(?,?,?,?,?)");
             ps3.setString(1,atm.acc);
             ps3.setString(2,tfcheque.getText());
             ps3.setString(3,"Added ");
             ps3.setString(4,dateFormat.format(date));
             ps3.setInt(5,atm.count);

             i=ps3.executeUpdate();
//             if(i>=0)
//             {
//                 
//             }
            }
                catch(Exception ex)
                {
                    System.out.println(ex);
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
