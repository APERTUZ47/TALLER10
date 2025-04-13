import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class Main {

    private final static String ALGORITMO = "AES";

    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escriba el texto que desea cifrar: ");
        String texto = scanner.nextLine();
        System.out.println("Mensaje de entrada en texto claro: " + texto);

        byte[] textoClaro = texto.getBytes();
        System.out.print("Texto claro: ");
        imprimir(textoClaro);

        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
        SecretKey secretKey = keygen.generateKey();

        byte[] textoCifrado = Simetrico.cifrar(secretKey, texto);
        System.out.print("Texto cifrado: ");
        imprimir(textoCifrado);

        byte[] textoDescifrado = Simetrico.descifrar(secretKey, textoCifrado);
        System.out.print("Texto descifrado: ");
        imprimir(textoDescifrado);

        String textoFinal = new String(textoDescifrado);
        System.out.println("Texto final: " + textoFinal);
    }
}