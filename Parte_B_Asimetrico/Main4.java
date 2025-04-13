import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

public class Main4 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) throws Exception {
        FileInputStream archivo = new FileInputStream("mensajeCifrado.dat");
        ObjectInputStream ois = new ObjectInputStream(archivo);
        byte[] textoCifrado = (byte[]) ois.readObject();
        ois.close();

        archivo = new FileInputStream("llavePrivada.dat");
        ois = new ObjectInputStream(archivo);
        PrivateKey llave = (PrivateKey) ois.readObject();
        ois.close();

        byte[] textoDescifrado = Asimetrico.descifrar(llave, ALGORITMO, textoCifrado);
        String mensaje = new String(textoDescifrado);

        System.out.println("Mensaje descifrado: " + mensaje);
    }
}