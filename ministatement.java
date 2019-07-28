package project1;

import java.awt.*;        
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class ministatement extends Frame implements ActionListener,WindowListener {
    private Label lblmini,lblamount,lblaction,lbldate;
    private Label lbldetails[]=new Label[5];
    private JLabel img8;
              private ImageIcon image;
    private Font font,f1;
    private Button btnbck, btnexit;
    String balance,action,date,s;
    Connection con=connect.c();
    ResultSet r;
    public ministatement()
    {       int i=0,j=0;

        setLayout(null);
        addWindowListener(this);
        setTitle("MINI STATEMENT");  // "super" Frame sets its title
         setSize(1400, 1500);        // "super" Frame sets its initial window size
 
         setVisible(true);
            font=new Font("Arial",Font.BOLD,25);
        f1=new Font("Arial",Font.ITALIC,15);

         img8= new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
         img8.setBounds(0,0,1400,800);
         add(img8);
         image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());

         lblmini=new Label("YOUR LAST 5 TRANSACTION DETAILS :",Label.CENTER);
         lblmini.setBounds(420,100,500,50);
         img8.add(lblmini);
         lblmini.setFont(font);
         lblamount=new Label("AMOUNT",Label.CENTER);
         lblamount.setBounds(400,200,100,50);
         img8.add(lblamount);
         lblamount.setFont(f1);
         lblaction=new Label("ACTION TAKEN",Label.CENTER);
         lblaction.setBounds(500,200,250,50);
         img8.add(lblaction);
         lblaction.setFont(f1);
         lbldate=new Label("DATE",Label.CENTER);
         lbldate.setBounds(750,200,200,50);
         img8.add(lbldate);
         lbldate.setFont(f1);
         btnbck=new Button("BACK");
         btnbck.setBounds(490,600,150,50);
         img8.add(btnbck);
         btnbck.addActionListener(this);
         
         btnexit=new Button("EXIT");
         btnexit.setBounds(690,600,150,50);
         img8.add(btnexit);
         btnexit.addActionListener(this);
         try{
             PreparedStatement ps=con.prepareStatement("Select amount,action,datetrans from transactions where accno=? order by sno desc");
             ps.setString(1,atm.acc);
             r=ps.executeQuery();
             while(r.next())
             {
                balance=r.getString("amount");
                action=r.getString("action");
                date=r.getString("datetrans"); 
                s= balance+"                                     "+action+"                                        "+date;
             if(i<5)
             {
                 lbldetails[i]=new Label(s,Label.CENTER);
                 lbldetails[i].setText(s);
                 lbldetails[i].setBounds(400,300+j,550,50);
                 img8.add(lbldetails[i]);
                 lbldetails[i].setFont(f1);
             }
       i++;
       j=j+60;
             }
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
         }
         @Override
    public void actionPerformed(ActionEvent evt)
    {
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
