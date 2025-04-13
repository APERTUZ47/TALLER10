import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main2 {

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

        System.out.print("Ingrese el mensaje a firmar (cifrar con llave privada): ");
        String texto = scanner.nextLine();

        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PublicKey llavePublica = keyPair.getPublic();
        PrivateKey llavePrivada = keyPair.getPrivate();

        byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMO, texto);
        System.out.print("Texto cifrado con llave privada: ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Asimetrico.descifrar(llavePublica, ALGORITMO, textoCifrado);
        System.out.print("Texto descifrado con llave pública: ");
        imprimir(textoDescifrado);

        String resultado = new String(textoDescifrado);
        System.out.println("Resultado final: " + resultado);
    }
}