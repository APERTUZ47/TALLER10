import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main {

    private final static String ALGORITMO = "RSA";

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriba un mensaje de texto: ");
        String texto = scanner.nextLine();
        System.out.println("Input en texto plano: " + texto);

        byte[] textoClaro = texto.getBytes();
        System.out.print("Input en bytes: ");
        imprimir(textoClaro);

        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        byte[] textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, texto);
        System.out.print("Texto cifrado en byte[]: ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, textoCifrado);
        System.out.print("Input descifrado en byte[]: ");
        imprimir(textoDescifrado);

        String textoPlanoFinal = new String(textoDescifrado);
        System.out.println("Input descifrado convertido a texto plano: " + textoPlanoFinal);
    }
}