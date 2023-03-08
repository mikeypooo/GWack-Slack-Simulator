import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class GWackChannel{
    ServerSocket serverSock;
    //ArrayList<String> users = new ArrayList<String>();
    private static ArrayList<ClientHandler> connectedclients;
    public GWackChannel(int port){
        try{
            serverSock = new ServerSocket(port);
            connectedclients= new ArrayList<ClientHandler>();
        }catch(Exception e){
            System.err.println("Cannot establish server socket");
            System.exit(1);
        }
    }
    
    public void serve(){

        while(true){
            try{
                //accept incoming connection
                Socket clientSock = serverSock.accept();
                System.out.println("New connection: "+clientSock.getRemoteSocketAddress());
                
                //start the thread
                (new ClientHandler(clientSock)).start();
                
                //continue looping
            }catch(Exception e){} //exit serve if exception
        }
    }
    private class ClientHandler extends Thread{
        String namestr;
        Socket sock;
        PrintWriter out=null;
        BufferedReader in=null;
        public ClientHandler(Socket sock){
            this.sock=sock;
        }

        public void run(){
            
            try{
                connectedclients.add(this);
                out = new PrintWriter(sock.getOutputStream());
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                


                boolean handshake=false;
                boolean secret=false;
                boolean pass=false;
                boolean name=false; 
                //read and echo back forever!
                while(true){
                    String msg = in.readLine();
                    if(msg == null) break; //read null, remote closed
                    if (handshake == false){
                            if (msg.contains("SECRET")) secret=true;
                            if (msg.contains("3c3c4ac618656ae32b7f3431e75f7b26b1a14a87")) pass=true;
                            if (msg.contains("NAME")) name=true;
                            if (secret && pass && name){
                                handshake=true;
                                msg = in.readLine();
                                this.namestr=msg;
                                //users.add(namestr);
                                System.out.println("handshake accepted msg="+msg);
                                printmembers();
                            }
                        
                    }
                    else{
                        System.out.println("server sends: "+msg);
                        for(ClientHandler client: connectedclients){
                            //if(!client.getNamestr().equals(this.getNamestr()))
                            //{
                                String serverMsg = "[" + this.getNamestr() +"] " + msg;
                                client.out.println(serverMsg);
                                client.out.flush();
                            //}
                        }


                        //out.println(msg);
                        //out.flush();
                    }
                }

                //close the connections
                out.close();
                in.close();
                sock.close();
                connectedclients.remove(this);
                printmembers();
                //note the loss of the connection
                System.out.println("Connection lost: "+sock.getRemoteSocketAddress());
                
            }catch(Exception e){}
            connectedclients.remove(this);
            printmembers();
            //note the loss of the connection
            System.out.println("Connection lost: "+sock.getRemoteSocketAddress());
        }
        private void printmembers(){
            // print out members to all clients
            for(ClientHandler client: connectedclients){
                System.out.println("server sends: START_CLIENT_LIST");
                client.out.println("START_CLIENT_LIST");
                for (int i = 0; i < connectedclients.size(); i++) {
                    System.out.println("server sends: "+connectedclients.get(i).getNamestr());
                    client.out.println(connectedclients.get(i).getNamestr());
        
                }
                System.out.println("server sends: END_CLIENT_LIST");
                client.out.println("END_CLIENT_LIST");
            }
        }
        public String getNamestr(){
            return this.namestr;
        }
    }
    
    public static void main(String args[]){
        if (args.length != 1) {
            System.err.println("Usage: java GWachChannel <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        GWackChannel server = new GWackChannel(portNumber);
        server.serve();
        


    }
    
}
