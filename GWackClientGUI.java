import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;


public class GWackClientGUI extends JFrame implements ItemListener{
    //TODO

    private JComboBox<String> themecombo;
    private JButton disconnectbutton ;
    private JButton send ;
    private JButton connectbutton;
    private JPanel p1 ;
    private JPanel p2 ;
    private JPanel p3 ;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JPanel p7;
    private JPanel p8;
    private JPanel p9; // p5-9 are for text fields
    private JPanel p10; // for the bottom of the gui
    private JPanel p11; // for themes


    private JLabel namelabel ;
    private JLabel iplabel ;
    private JLabel portlabel ;
    private JLabel messageslabel;
    private JLabel composelabel ;
    private JLabel memberslabel;
    private JLabel themelabel ;
    private JTextField nametxt ;
    private JTextField iptxt ;
    private JTextField porttxt ;
    private JTextArea messagestxt ;
    private JTextArea composetxt;
    private JTextArea memberstxt ;
    private Color defaultcolor;

    private Client c;

    public GWackClientGUI() {
        super();
        this.setTitle("GWack -- GW Slack Simulator (connected)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        disconnectbutton = new JButton("Disconnect");
        connectbutton = new JButton("Connect");
        send = new JButton("Send");
        send.setPreferredSize(new Dimension(200, 40));
            
        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout()); 
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel(); 
        p9 = new JPanel();
        p10 = new JPanel();
        p11 = new JPanel();


        namelabel = new JLabel("Name");
        iplabel = new JLabel("IP Address");
        themelabel = new JLabel("Theme");
        portlabel = new JLabel("Port");
        messageslabel = new JLabel("Messages");
        composelabel = new JLabel("Compose");
        memberslabel = new JLabel("Members Online");
        nametxt = new JTextField(8);
        iptxt = new JTextField("ssh-cs2113.adamaviv.com",20);
        porttxt = new JTextField(4);
        messagestxt = new JTextArea(15,80);
        composetxt = new JTextArea(15,80);
        memberstxt = new JTextArea(31,15);
        memberstxt.setEditable(false);
        messagestxt.setEditable(false);
        defaultcolor= p1.getBackground ();
        
        
        String themeselect[] = {"light","dark","skye","rgx"};
        themecombo = new JComboBox<String>(themeselect);

        send.addActionListener((e) -> { c.sendmsg(composetxt.getText());
                                        System.out.println(composetxt.getText()+" 1");});
        themecombo.addItemListener(this);
        disconnectbutton.addActionListener((e) -> { c.disconnect();
                                                    p4.remove(disconnectbutton);
                                                    p4.add(connectbutton);
                                                    clearall();
                                                    this.seteditable();});

        connectbutton.addActionListener((e) -> {c=new Client(iptxt.getText(),Integer.parseInt(porttxt.getText()),nametxt.getText(),messagestxt,composetxt,memberstxt,this);
                                                c.start();
                                                this.setuneditable();
                                                p4.remove(connectbutton);
                                                p4.add(disconnectbutton);});
                                                
        

        p1.add(namelabel);
        p1.add(nametxt);
        p2.add(iplabel);
        p2.add(iptxt);
        p3.add(portlabel);
        p3.add(porttxt);
        p11.add(themelabel);
        p11.add(themecombo);

        p4.add(p11);
        p4.add(p1);
        p4.add(p2);
        p4.add(p3);
        p4.add(connectbutton);
        p8.setLayout(new FlowLayout());
        p5.setLayout(new BorderLayout());
        p5.add(memberslabel,BorderLayout.NORTH);
        p5.add(memberstxt,BorderLayout.SOUTH);
        p8.add(p5);
        p6.setLayout(new BorderLayout());
        p6.add(messageslabel, BorderLayout.NORTH);
        p6.add(messagestxt, BorderLayout.SOUTH);
        p7.setLayout(new BorderLayout());
        p7.add(composelabel, BorderLayout.NORTH);
        p7.add(composetxt, BorderLayout.SOUTH);
        p9.setLayout(new BorderLayout());
        p9.add(p6, BorderLayout.NORTH);
        p9.add(p7, BorderLayout.SOUTH);
        p8.add(p9);
        p10.setLayout(new BorderLayout());
        p10.add(send, BorderLayout.EAST);

        

        this.getContentPane().add(p4, BorderLayout.NORTH);
        this.getContentPane().add(p5, BorderLayout.WEST);
        this.getContentPane().add(p8, BorderLayout.CENTER);
        this.getContentPane().add(p10, BorderLayout.SOUTH);
        this.getContentPane().setBackground(Color.BLACK);
        
        
        




        //TODO: Complete the GUI



    }
    
    public void updatemessagestxt(String txt){
        messagestxt.append(txt+"\n");
    }
    public void updatememberstxt(String txt){
        memberstxt.append(txt+"\n");
    }
    public void clearmemberstxt(){
        memberstxt.setText("");
    }
    
    
    private void setuneditable(){
        nametxt.setEditable(false);
        porttxt.setEditable(false);
        iptxt.setEditable(false);
    }
    private void seteditable(){
        nametxt.setEditable(true);
        porttxt.setEditable(true);
        iptxt.setEditable(true);
    }
    private void clearall(){
        memberstxt.setText("");
        composetxt.setText("");
        messagestxt.setText("");
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == themecombo) {
            if(themecombo.getSelectedItem().equals("light")){
                p1.setBackground(defaultcolor);
                p2.setBackground(defaultcolor);
                p3.setBackground(defaultcolor);
                p4.setBackground(defaultcolor);
                p6.setBackground(defaultcolor);
                p7.setBackground(defaultcolor);
                p9.setBackground(defaultcolor);
                p8.setBackground(defaultcolor);
                p5.setBackground(defaultcolor);
                p10.setBackground(defaultcolor);
                p11.setBackground(defaultcolor);
                namelabel.setForeground(Color.BLACK);
                iplabel.setForeground(Color.BLACK);
                portlabel.setForeground(Color.BLACK);
                messageslabel.setForeground(Color.BLACK);
                composelabel.setForeground(Color.BLACK);
                memberslabel.setForeground(Color.BLACK);
                themelabel.setForeground(Color.BLACK);
                nametxt.setBackground(Color.white);
                iptxt.setBackground(Color.white);
                porttxt.setBackground(Color.white);
                composetxt.setBackground(Color.white);
                memberstxt.setBackground(Color.white);
                messagestxt.setBackground(Color.white);
                nametxt.setForeground(Color.BLACK);
                iptxt.setForeground(Color.BLACK);
                porttxt.setForeground(Color.BLACK);
                composetxt.setForeground(Color.BLACK);
                memberstxt.setForeground(Color.BLACK);
                messagestxt.setForeground(Color.BLACK);
                send.setBackground(Color.white);
                send.setForeground(Color.BLACK);
                disconnectbutton.setBackground(Color.white);
                disconnectbutton.setForeground(Color.BLACK);
                connectbutton.setBackground(Color.white);
                connectbutton.setForeground(Color.BLACK);
                themecombo.setBackground(Color.white);
                themecombo.setForeground(Color.black);
            }
            if(themecombo.getSelectedItem().equals("dark")){
                p1.setBackground(Color.BLACK);
                p2.setBackground(Color.BLACK);
                p3.setBackground(Color.BLACK);
                p4.setBackground(Color.BLACK);
                p6.setBackground(Color.BLACK);
                p7.setBackground(Color.BLACK);
                p9.setBackground(Color.BLACK);
                p8.setBackground(Color.BLACK);
                p5.setBackground(Color.BLACK);
                p10.setBackground(Color.BLACK);
                p11.setBackground(Color.BLACK);
                namelabel.setForeground(Color.white);
                iplabel.setForeground(Color.white);
                portlabel.setForeground(Color.white);
                messageslabel.setForeground(Color.white);
                composelabel.setForeground(Color.white);
                memberslabel.setForeground(Color.white);
                themelabel.setForeground(Color.white);
                nametxt.setBackground(Color.gray);
                iptxt.setBackground(Color.gray);
                porttxt.setBackground(Color.gray);
                composetxt.setBackground(Color.GRAY);
                memberstxt.setBackground(Color.GRAY);
                messagestxt.setBackground(Color.GRAY);
                nametxt.setForeground(Color.white);
                iptxt.setForeground(Color.white);
                porttxt.setForeground(Color.white);
                composetxt.setForeground(Color.white);
                memberstxt.setForeground(Color.white);
                messagestxt.setForeground(Color.white);
                send.setBackground(Color.black);
                send.setForeground(Color.white);
                disconnectbutton.setBackground(Color.black);
                disconnectbutton.setForeground(Color.white);
                connectbutton.setBackground(Color.black);
                connectbutton.setForeground(Color.white);
                themecombo.setBackground(Color.gray);
                themecombo.setForeground(Color.white);
            }
            if(themecombo.getSelectedItem().equals("skye")){
                p1.setBackground(Color.green);
                p2.setBackground(Color.green);
                p3.setBackground(Color.green);
                p4.setBackground(Color.green);
                p6.setBackground(Color.green);
                p7.setBackground(Color.green);
                p9.setBackground(Color.green);
                p8.setBackground(Color.green);
                p5.setBackground(Color.green);
                p10.setBackground(Color.green);
                p11.setBackground(Color.green);
                namelabel.setForeground(Color.yellow);
                iplabel.setForeground(Color.yellow);
                portlabel.setForeground(Color.yellow);
                messageslabel.setForeground(Color.yellow);
                composelabel.setForeground(Color.yellow);
                memberslabel.setForeground(Color.yellow);
                themelabel.setForeground(Color.yellow);
                nametxt.setBackground(Color.yellow);
                iptxt.setBackground(Color.yellow);
                porttxt.setBackground(Color.yellow);
                composetxt.setBackground(Color.yellow);
                memberstxt.setBackground(Color.yellow);
                messagestxt.setBackground(Color.yellow);
                nametxt.setForeground(Color.white);
                iptxt.setForeground(Color.white);
                porttxt.setForeground(Color.white);
                composetxt.setForeground(Color.white);
                memberstxt.setForeground(Color.white);
                messagestxt.setForeground(Color.white);
                send.setBackground(Color.yellow);
                send.setForeground(Color.green);
                disconnectbutton.setBackground(Color.yellow);
                disconnectbutton.setForeground(Color.green);
                connectbutton.setBackground(Color.yellow);
                connectbutton.setForeground(Color.green);
                themecombo.setBackground(Color.green);
                themecombo.setForeground(Color.yellow);
            }
            if(themecombo.getSelectedItem().equals("rgx")){
                p1.setBackground(Color.darkGray);
                p2.setBackground(Color.darkGray);
                p3.setBackground(Color.darkGray);
                p4.setBackground(Color.darkGray);
                p6.setBackground(Color.darkGray);
                p7.setBackground(Color.darkGray);
                p9.setBackground(Color.darkGray);
                p8.setBackground(Color.darkGray);
                p5.setBackground(Color.darkGray);
                p10.setBackground(Color.darkGray);
                p11.setBackground(Color.darkGray);
                namelabel.setForeground(Color.blue);
                iplabel.setForeground(Color.blue);
                portlabel.setForeground(Color.blue);
                messageslabel.setForeground(Color.blue);
                composelabel.setForeground(Color.blue);
                memberslabel.setForeground(Color.blue);
                themelabel.setForeground(Color.blue);
                nametxt.setBackground(Color.black);
                iptxt.setBackground(Color.black);
                porttxt.setBackground(Color.black);
                composetxt.setBackground(Color.black);
                memberstxt.setBackground(Color.black);
                messagestxt.setBackground(Color.black);
                nametxt.setForeground(Color.blue);
                iptxt.setForeground(Color.blue);
                porttxt.setForeground(Color.blue);
                composetxt.setForeground(Color.blue);
                memberstxt.setForeground(Color.blue);
                messagestxt.setForeground(Color.blue);
                send.setBackground(Color.black);
                send.setForeground(Color.blue);
                disconnectbutton.setBackground(Color.black);
                disconnectbutton.setForeground(Color.blue);
                connectbutton.setBackground(Color.black);
                connectbutton.setForeground(Color.blue);
                themecombo.setBackground(Color.darkGray);
                themecombo.setForeground(Color.blue);
            }

            System.out.print(themecombo.getSelectedItem() + " selected");
        }
    }
    public static void main(String args[]){
        GWackClientGUI f = new GWackClientGUI();
        f.pack();
        f.setVisible(true);
        
    }
    
    
}
