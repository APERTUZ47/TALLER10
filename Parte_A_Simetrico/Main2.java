import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class Main2 {

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
        System.out.print("Ingrese el mensaje de entrada: ");
        String mensaje = scanner.nextLine();

        KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
        SecretKey k1 = keygen.generateKey();
        SecretKey k2 = keygen.generateKey();

        byte[] tc1 = Simetrico.cifrar(k1, mensaje);
        System.out.print("Texto cifrado con k1 (tc1): ");
        imprimir(tc1);

        byte[] tc2 = Simetrico.cifrar(k2, mensaje);
        System.out.print("Texto cifrado con k2 (tc2): ");
        imprimir(tc2);

        byte[] descifrado = Simetrico.descifrar(k1, tc1);
        String resultado = new String(descifrado);
        System.out.println("Descifrado de tc1 con k1: " + resultado);
    }
}