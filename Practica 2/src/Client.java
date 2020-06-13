import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author virtual
 */


public class Client {
    public static final int SERVER_PORT = 3000;
    public static final String SERVER_HOST = "localhost";
    public static void main(String[] args) throws IOException{
        MySocket sc = new MySocket(SERVER_HOST, SERVER_PORT);
        //Input
        new Thread() {
            public void run(){
                String linia;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while((linia = kbd.readLine()) != null){
                        sc.println(linia);
                    }
                    sc.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
           
        // Output thread
        new Thread() {
            public void run(){
                String line;
                while((line=sc.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }

   
    
}
