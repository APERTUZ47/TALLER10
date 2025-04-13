import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main3 {

    private final static String ALGORITMO = "AES";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
        SecretKey secretKey = keygen.generateKey();

        FileOutputStream archivoLlave = new FileOutputStream("llave.secreta");
        ObjectOutputStream oosLlave = new ObjectOutputStream(archivoLlave);
        oosLlave.writeObject(secretKey);
        oosLlave.close();

        System.out.print("Ingrese el texto a cifrar: ");
        String mensaje = scanner.nextLine();

        byte[] textoCifrado = Simetrico.cifrar(secretKey, mensaje);

        FileOutputStream archivoTexto = new FileOutputStream("mensaje.cifrado");
        ObjectOutputStream oosTexto = new ObjectOutputStream(archivoTexto);
        oosTexto.writeObject(textoCifrado);
        oosTexto.close();

        System.out.println("Llave y mensaje cifrado guardados correctamente.");
    }
}