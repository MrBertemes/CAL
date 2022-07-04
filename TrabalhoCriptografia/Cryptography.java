import java.util.*;
import java.math.BigInteger;

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

        // Escolha um inteiro "e" , 1 < "e" < phi(n) , "e" e phi(n) sejam primos entre si.
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1){
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

        // while (!prime) {
        //     numberFound = randomCandidate(bitlen);
        //     prime = primalityTest(numberFound);
        // }
        
        BigInteger bigNum = randomCandidate(bitlen);
        return bigNum;
    }

    // private boolean primalityTest(BigInteger num) {// Miller Rabin
      
    // }

    private BigInteger randomCandidate(int bitlen){
        BigInteger candidate;
        int n,i;
        String bit = "1";
        int bits[] = new int[bitlen-1];
        bits[0] = 1;

        for(i= 1; i< bitlen - 1; i++){
            n = rnd.nextInt(2);
            bits[i] = n;
            bit = Integer.toString(n) + bit;
        }

        bit  = "1" + bit;
        bits[bitlen-1] = 1;
        candidate = new BigInteger(bit);
        return candidate;
    }
}