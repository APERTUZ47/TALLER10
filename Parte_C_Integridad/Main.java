import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static byte[] getDigest(String algorithm, byte[] buffer) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(buffer);
            return digest.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static void imprimirHexa(byte[] byteArray) {
        String out = "";
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) <= 0xf ) {
                out += "0";
            }
            out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
        }

        System.out.println(out);
    }

    public static String toHexString(byte[] data) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : data) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean verificarHex(String hex1, String hex2) {
        return hex1.equalsIgnoreCase(hex2);
    }

        public static byte[] getDigestFile(String algorithm, String fileName) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);

            FileInputStream in = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            in.close();

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo no soportado: " + algorithm);
            e.printStackTrace();
            return null;
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + fileName);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + fileName);
            e.printStackTrace();
            return null;
        }
        return md.digest();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe la entrada de texto: ");
        String texto = scanner.nextLine();
        System.out.println("Mensaje de entrada: " + texto);

        byte[] bytes = texto.getBytes();

        byte[] md5Digest = getDigest("MD5", bytes);
        System.out.print("MD5 Digest obtenido: ");
        String md5Hex = toHexString(md5Digest);
        System.out.println(md5Hex);

        byte[] sha1Digest = getDigest("SHA-1", bytes);
        System.out.print("SHA-1 Digest obtenido: ");
        String sha1Hex = toHexString(sha1Digest);
        System.out.println(sha1Hex);

        // Resultados de la comparación
        System.out.print("\nIngresa el MD5 Digest obtenido: ");
        String md5Web = scanner.nextLine();

        System.out.print("Ingresa el SHA-1 Digest obtenido: ");
        String sha1Web = scanner.nextLine();

        // Comparar los resultados
        boolean md5Coincide = verificarHex(md5Hex, md5Web);
        boolean sha1Coincide = verificarHex(sha1Hex, sha1Web);

        System.out.println("\nResultados de la comparación:");
        System.out.println("MD5 Coincide: " + md5Coincide);
        System.out.println("SHA-1 Coincide: " + sha1Coincide);
    }


}
