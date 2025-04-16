import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe el nombre del archivo: ");
        String fileName = scanner.nextLine();

        byte[] md5Digest = Digest.getDigestFile("MD5", fileName);
        if (md5Digest != null) {
            String md5Hex = Digest.toHexString(md5Digest);
            System.out.println("MD5 Digest del archivo: " + md5Hex);

            System.out.print("Ingrese el MD5 obtenido: ");
            String md5Web = scanner.nextLine();

            if (md5Hex.equalsIgnoreCase(md5Web)) {
                System.out.println("¡El MD5 coincide!");
            } else {
                System.out.println("El MD5 no coincide.");
            }
        }

        byte[] sha1Digest = Digest.getDigestFile("SHA-1", fileName);
        if (sha1Digest != null) {
            String sha1Hex = Digest.toHexString(sha1Digest);
            System.out.println("SHA-1 Digest del archivo: " + sha1Hex);

            System.out.print("Ingrese el SHA-1 obtenido: ");
            String sha1Web = scanner.nextLine();

            if (sha1Hex.equalsIgnoreCase(sha1Web)) {
                System.out.println("¡El SHA-1 coincide!");
            } else {
                System.out.println("El SHA-1 no coincide.");
            }
        }

        scanner.close();
    }
}
