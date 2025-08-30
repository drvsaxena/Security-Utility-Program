import java.io.*;
import java.util.Scanner;

public class EncDec {
    private static String MARKER = "[ENCRYPTED]";

    public static String xorEncDec(String text) {
        String key = "SECRET";
        StringBuilder xorsb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char enc = (char) (ch ^ key.charAt(i % key.length()));
            xorsb.append(enc);
        }
        return xorsb.toString();
    }

    public static void encryptFile(String inpfl, String outfl) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inpfl));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfl));

            writer.write(MARKER);
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String encryptline = xorEncDec(line);
                writer.write(encryptline);
                writer.newLine();
            }
            reader.close();
            writer.close();

            System.out.println(">> Encryption started...");
            System.out.println(">> Encryption completed successfully!");
            System.out.println();
            System.out.println(">> Encrypted file saved as: " + outfl);
        } catch (IOException e) {
            System.out.println("Error encrypting file: " + e.getMessage());
        }
    }

    public static void decryptFile(String inpfl, String outfl) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inpfl));
            String firstline = reader.readLine();
            if (!MARKER.equals(firstline)) {
                System.out.println(">> Checking file status...");
                System.out.println(">> File already decrypted.");
                return;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfl));

            String line;
            while ((line = reader.readLine()) != null) {
                String dycryptline = xorEncDec(line);
                writer.write(dycryptline);
                writer.newLine();
            }
            reader.close();
            writer.close();

            System.out.println(">> Decryption in progress...");
            System.out.println(">> Decryption completed successfully!");
            System.out.println();
            System.out.println(">> Decrypted file saved as: " + outfl);
        } catch (IOException e) {
            System.out.println("Error decrypting file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("   FILE ENCRYPTION & DECRYPTION UTILITY");
        System.out.println(" Developed in Java | Cognifyz Technologies");
        System.out.println("===========================================");
        System.out.println();

        System.out.println("Choose an option:");
        System.out.println("[1] Encrypt a file");
        System.out.println("[2] Decrypt a file");
        System.out.println("-------------------------------------------");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = sc.nextInt();

        System.out.println();
        System.out.print("Enter input file name: ");
        String inpfl = sc.next();
        System.out.print("Enter output file name: ");
        String outfl = sc.next();

        File file = new File(inpfl);
        if (!file.exists()) {
            System.out.println("[ERROR] Input file does not exist.");
            sc.close();
            return;
        }

        // Confirm paths
        System.out.println();
        System.out.println(">> Input file: " + inpfl);
        System.out.println(">> Output file: " + outfl);
        System.out.println();

        if (choice == 1) {
            encryptFile(inpfl, outfl);
        } else if (choice == 2) {
            decryptFile(inpfl, outfl);
        } else {
            System.out.println("[ERROR] Invalid choice. Please select 1 (Encrypt) or 2 (Decrypt).");
        }

        System.out.println();
        System.out.println("===========================================");
        System.out.println("  Thank you for using SecureFile Utility!");
        System.out.println("===========================================");

        sc.close();
    }
}
