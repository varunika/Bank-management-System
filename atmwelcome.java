
package project1;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
import javax.swing.*;// Using AWT event classes and listener interfaces

public class atmwelcome extends Frame implements ActionListener, WindowListener {
    private Label lblwelcome;
    private Button btn1,btn2;
    private JLabel img1;
    private ImageIcon image;
    private Font font; 

    String s;
    static atmwelcome a1;
    public atmwelcome()
    {
        setLayout(null);
        img1=new JLabel(new ImageIcon("C:/Users/hp/Desktop/art/credit1.jpg"));
        image=new ImageIcon("C:/Users/hp/Desktop/art/logo.jpg");
        this.setIconImage(image.getImage());
        img1.setBounds(0,0,1400,800);
        add(img1);
        lblwelcome=new Label("WELCOME TO YOURBANK!", Label.CENTER) ;
        lblwelcome.setBounds(300,100,800,100);
        img1.add(lblwelcome);
        font=new Font("Arial",Font.BOLD,28);
        lblwelcome.setFont(font);
        btn1=new Button("CONTINUE");
        btn1.setBounds(500,300,300,50);
        img1.add(btn1);
        
        btn1.addActionListener(this);
        btn2=new Button("CLOSE");
        btn2.setBounds(500,400,300,50);
        img1.add(btn2);
        btn2.addActionListener(this);
        addWindowListener(this);
        setTitle("WELCOME");
        setSize(1400,1500);
        setVisible(true);
    }
       
       @Override
       public void actionPerformed(ActionEvent evt) {
           s=evt.getActionCommand();
           if(s.equals("CONTINUE"))
           {
           atm a=new atm();
           this.setVisible(false);
           }
           if(s.equals("CLOSE"))
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
       
       
       public static void main(String[] args)
       {
             a1=new atmwelcome();
        }
}
