import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main3 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        FileOutputStream archivo = new FileOutputStream("llavePublica.dat");
        ObjectOutputStream oos = new ObjectOutputStream(archivo);
        oos.writeObject(llavePublica);
        oos.close();

        archivo = new FileOutputStream("llavePrivada.dat");
        oos = new ObjectOutputStream(archivo);
        oos.writeObject(llavePrivada);
        oos.close();

        System.out.print("Ingrese el mensaje a cifrar: ");
        String mensaje = scanner.nextLine();

        byte[] textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, mensaje);

        archivo = new FileOutputStream("mensajeCifrado.dat");
        oos = new ObjectOutputStream(archivo);
        oos.writeObject(textoCifrado);
        oos.close();

        System.out.println("Archivos generados: llavePublica.dat, llavePrivada.dat y mensajeCifrado.dat");
    }
}