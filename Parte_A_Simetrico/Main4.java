import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main4 {

    public static void main(String[] args) throws Exception {
        FileInputStream archivo = new FileInputStream("llave.secreta");
        ObjectInputStream ois = new ObjectInputStream(archivo);
        SecretKey llave = (SecretKey) ois.readObject();
        ois.close();

        archivo = new FileInputStream("mensaje.cifrado");
        ois = new ObjectInputStream(archivo);
        byte[] textoCifrado = (byte[]) ois.readObject();
        ois.close();

        byte[] textoDescifrado = Simetrico.descifrar(llave, textoCifrado);
        String mensaje = new String(textoDescifrado);
        System.out.println("Texto descifrado: " + mensaje);
    }
}