
package cjs.tcpserver1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author cherr
 */
public class EchoServer {
    static int port = 8080; //port and IP must not be hardcoded
    static String ip = "localhost";
    
    public static void handleClient(Socket s){
        try {
            Scanner scan = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            String msg = "";
            while(!msg.equalsIgnoreCase("STOP")){
                msg = scan.nextLine();
                Behaviours b = new Behaviours(msg);
                b.doCommands();
                pw.println(b.getResult());
                if(b.getResult().equals("stop")){
                    msg ="stop";
                }
            }
            scan.close();
            pw.close();
            s.close();
        } catch (IOException ex) {
            System.out.println(" Something went wrong: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws IOException {
        if(args.length == 2){
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip,port));
        System.out.println("Server started, listening on: " + port + " bound to " + ip);
        
        while (true) {
            Socket socket = ss.accept(); //blocking core, there's nothing we can see after this line if there's no connection: in a waiting state
            handleClient(socket);
            System.out.println("New Client connected");
        }
    }
}
