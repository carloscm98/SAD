import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author virtual
 */
public class Server implements Runnable {

    public static ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<String, MySocket>();
    public static boolean t = true;
    MySocket s;
    String nombre;

    public Server(String nombre, MySocket s) {
        this.s = s;
        this.nombre = nombre;
    }

    public static void main(String[] args) throws Exception {
        MyServerSocket ss = new MyServerSocket(3000);
        System.out.println("The server is running...");

        while (true) {
            MySocket ms = ss.accept();
            while (t) {
                ms.println("Introduce tu nombre de usuario: ");
                String nombre = ms.readLine();
                if (clients.containsKey(nombre)) {
                    ms.println("El nombre de usuario " + nombre + " ya existe.");
                } else {
                    System.out.println("Se ha conectado " + nombre);
                    clients.put(nombre, ms);
                    new Thread(new Server(nombre, ms)).start();
                    t = false;
                }
            }
            t = true;
        }
    }

    public void run() {
        String linea;
        while((linea = clients.get(nombre).readLine()) != null) {
            for (ConcurrentHashMap.Entry<String, MySocket> entry : clients.entrySet()) {
                if (!entry.getKey().equals(nombre)) {
                    System.out.println("Linea: "+linea);
                    entry.getValue().println("-> "+nombre + ": " + linea);
                }
            }
        }
        clients.get(nombre).close();
        System.out.println(nombre+" se ha desconectado");
        clients.remove(nombre);
    }
}
