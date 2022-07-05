import java.util.*;
import java.math.BigInteger;
import java.io.*;

//vou tomar cafe, volto logo

public class Cryptography {
    public static Random rnd = new Random();

    public static void encode() {

    }

    public static void decode() {

    }

    public void keyGenerator(int bitlen) {

        BigInteger n, d, e;
        BigInteger p = generateRandomPrime(bitlen);
        BigInteger q = generateRandomPrime(bitlen);

        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Escolha um inteiro "e" , 1 < "e" < phi(n) , "e" e phi(n) sejam primos entre
        // si.
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(m);

        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + n);
        System.out.println("e:" + e);
        System.out.println("d:" + d);

    }

    private BigInteger generateRandomPrime(int bitlen) {
        boolean prime = false;
        BigInteger numberFound = BigInteger.ZERO;
        System.out.println("test");

        while (!prime) {
            numberFound = randomCandidate(bitlen);
            prime = Stuff.isPrime(numberFound, 10);
        }

        BigInteger bigNum = randomCandidate(bitlen);
        return bigNum;
    }

    private BigInteger randomCandidate(int bitlen) {
        BigInteger candidate;
        int i;
        byte n;
        // String bit = "1";
        byte bits[] = new byte[bitlen];
        bits[0] = 1;

        for (i = 1; i < bitlen - 1; i++) {
            n = Byte.decode(Integer.toString(rnd.nextInt(2)));
            bits[i] = n;
            // bit = Integer.toString(n) + bit;
        }

        // bit = "1" + bit;
        bits[bitlen - 1] = 1;
        candidate = new BigInteger(bits);
        return candidate;
    }

    // public void ReadFile(String pathName, String fileName) {
    //     Scanner in = new Scanner(new FileReader("arq_01.txt"));
    //     while (in.hasNextLine()) {
    //         String line = scanner.nextLine();
    //         System.out.println(line);
    //     }

    // }
    
}