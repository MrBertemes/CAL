import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.math.*;
// import Cryptography;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Cryptography cripto = new Cryptography();
        cripto.keyGenerator(64);
    }

}