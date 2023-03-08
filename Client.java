import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends Thread implements KeyListener{
    private Socket sock;
    private String ip;
    private int port;
    private String name;
    private JTextArea messagestxt;
    private JTextArea composetxt;
    private JTextArea memberstxt;
    private PrintWriter pw;
    private GWackClientGUI gui;
    private PrintWriter out;
    private BufferedReader in;
    Client(String ip, int port, String name, JTextArea messagestxt, JTextArea composetxt, JTextArea memberstxt,GWackClientGUI gui){
        sock=null;       
        this.ip=ip;
        this.name=name;
        this.port=port; 
        this.composetxt=composetxt;
        this.messagestxt=messagestxt;
        this.memberstxt=memberstxt;
        this.gui=gui;
        try{
           sock = new Socket(ip,port);
        }catch(Exception e){
            composetxt.setText("Cannot Connect");
            System.err.println("Cannot Connect");
            System.exit(1);
        }
    }
    public void run(){
        try{
            pw = new PrintWriter(sock.getOutputStream());
            pw.println("SECRET");
            pw.flush();
            pw.println("3c3c4ac618656ae32b7f3431e75f7b26b1a14a87");
            pw.flush();
            pw.println("NAME");
            pw.flush();
            pw.println(this.name);
            pw.flush();
            
            composetxt.addKeyListener(this);
            
            in =
                new BufferedReader(
                         new InputStreamReader(sock.getInputStream()));
            out =
                new PrintWriter(sock.getOutputStream(), true);
            
            String reply = in.readLine();
            
            boolean members = true;
            boolean clear=false;
            boolean skip=false;
            while(reply!=null){   
                if(reply.equals("END_CLIENT_LIST")){
                    System.out.println("wors");
                    members=false;
                    skip=true;
                }
                
                if(reply.contains("START_CLIENT_LIST")){
                    System.out.println("wors2");
                    members=true;
                    clear=true;
                    skip=true;
                }             
                System.out.println(reply);
                if (!skip){
                    if(members){
                        if (clear){
                            
                            memberstxt.setText("");
                            clear=false;
                        }
                        System.out.println("member append"+reply);
                        memberstxt.append(reply+"\n");                    
                    }
                    else{

                        messagestxt.append(reply+"\n");
                        
                        
                        
                    }
                }
                
                reply = in.readLine();
                
                
                skip = false;
                
            }   
            

            System.out.print("errtest");
            pw.close();
            in.close();
            sock.close();
        }catch(Exception e){
            System.out.print("errtest2");
            composetxt.setText("IOException");
            System.err.print("IOException");
        }
    }
    public void sendmsg(String msg){
            System.out.println(msg+" 2");
            String mess=msg.replace("\n", "");
            out.println(mess);
            composetxt.setText("");
    }
    public void disconnect(){
        pw.close();
        
        try {
            in.close();
            sock.close();
        } catch (IOException e) {
            System.err.print("Could not close socket");
            e.printStackTrace();
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        
    }
    @Override
    public void keyPressed(KeyEvent e) {

        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep(); 
            this.sendmsg(composetxt.getText());
            composetxt.setText("");
        }
    }
}
