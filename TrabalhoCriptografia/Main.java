import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.math.*;
// import Cryptography;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Cryptography cripto = new Cryptography();
        //cripto.keyGenerator(64);
        try {
            cripto.readFileEncrypt("/home/admin/Documents/Braia/CAL/CAL/", "originalFile.txt", 
            "encryptedFile.txt");
        } catch (IOException e) {
            System.out.println("Oh no...");
            e.printStackTrace();
        }
    }
}