
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author virtual
 */
public class MySocket extends Socket{

    Socket sc;
    BufferedReader bf;
    PrintWriter pw;
    
    public MySocket(String host, int port){
        try {
            this.sc = new Socket(host, port);
            bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public MySocket(Socket socket){
        try{
            this.sc = socket;
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
        }catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    public void println(String s){
        pw.println(s);
        pw.flush();
    }

    public String readLine() {
        String s = null;
        try {
            s = this.bf.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }
       
    @Override
    public void close(){
        try {
            bf.close();
            pw.close();
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 }
