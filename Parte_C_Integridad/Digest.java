import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.IOException;

public class Digest {

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
}
