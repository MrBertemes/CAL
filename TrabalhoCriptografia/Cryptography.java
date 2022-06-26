import java.util.*;
import java.math.BigInteger;

//vou tomar cafe, volto logo

public class Cryptography {
    public static Random rnd = new Random();

    public static void encode() {

    }

    public static void decode() {

    }

    public void keyGenerator() {
        System.out.println("i am here");
        BigInteger n, d, e;

        BigInteger p = new BigInteger(generateRandomPrime());
        BigInteger q = new BigInteger(generateRandomPrime());

        // Compute n = p * q
        n = p.multiply(q);

        // Compute a função totiente phi(n) = (p -1) (q -1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Escolha um inteiro "e" , 1 < "e" < phi(n) , "e" e phi(n) sejam primos entre
        // si.
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1)
            e = e.add(new BigInteger("2"));

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(m);

        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + n);
        System.out.println("e:" + e);
        System.out.println("d:" + d);

    }

    private String generateRandomPrime() {
        boolean prime = false;
        long numberFound = 0;

        while (!prime) {
            numberFound = rnd.nextLong();
            prime = primalityTest(numberFound);
        }
        BigInteger bigNum = new BigInteger(Long.toString(numberFound));
        return Long.toString(numberFound);
    }

    private boolean primalityTest(long num) {// crivo de erastoteles
        boolean isPrime = false;
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }

        int n = (int) num;
        boolean prime[] = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; i * j <= n; j++) {
                prime[i * j] = false;
            }
        }
        if (prime[n]) {
            isPrime = true;
        }
        return isPrime;
    }
}