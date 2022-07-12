import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Cryptography {
    public static Random rnd = new Random();
    BigInteger n, e, d, p, q;

    public void criptic(int bitlen) {

        keyGenerator(bitlen);
        try {
            readFileEncrypt("/home/admin/Documents/Braia/CAL/CAL/", "originalFile.txt",
                    "encryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readFileDecrypt("/home/admin/Documents/Braia/CAL/CAL/", "encryptedFile.txt",
                    "decryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BigInteger encode(BigInteger m) {
        BigInteger c;

        c = m.modPow(e, n);

        return c;
    }

    private BigInteger decode(BigInteger c) {
        BigInteger m;

        m = c.modPow(d, n);
 
        return m;
    }

    private void keyGenerator(int bitlen) {

        p = generateRandomPrime(bitlen);
        q = generateRandomPrime(bitlen);

        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Escolha um inteiro "e" , 1 < "e" < phi(n) , "e" e phi(n) sejam primos entre
        // si.
        e = new BigInteger("3");
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(phi);
        // System.out.println("p:" + p );
        // System.out.println("q:" + q );
        // System.out.println("e:" + e );
    }

    private BigInteger generateRandomPrime(int bitlen) {
        boolean prime = false;
        BigInteger numberFound = BigInteger.ZERO;

        while (!prime) {
            numberFound = randomCandidate(bitlen);
            prime = Stuff.isPrime(numberFound, 10);
        }

        return numberFound;
    }

    private BigInteger randomCandidate(int bitlen) {
        BigInteger candidate;
        int i;
        byte n;

        byte bits[] = new byte[bitlen];
        bits[0] = 1;

        for (i = 1; i < bitlen - 1; i++) {
            n = Byte.decode(Integer.toString(rnd.nextInt(2)));
            bits[i] = n;
        }

        bits[bitlen - 1] = 1;
        candidate = new BigInteger(bits);
        return candidate;
    }

    public void readFileEncrypt(String pathName, String orginalFile, String encryptedFile) throws IOException {
        Scanner in = new Scanner(new FileReader(orginalFile));
        FileOutputStream out = new FileOutputStream(encryptedFile);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            BigInteger bigByte = new BigInteger(line.getBytes());
            out.write(encode(bigByte).toByteArray());
        }
        out.close();
    }

    public void readFileDecrypt(String pathName, String encryptedFile, String decryptedFile) throws IOException {
        Scanner in = new Scanner(new FileReader(encryptedFile));
        FileOutputStream out = new FileOutputStream(decryptedFile);
        byte line[] = new byte[500];
        int i = 0; 
        while (in.hasNextByte()) {
            line[i] = in.nextByte();
            i += 1;         
        }
        BigInteger bigByte = new BigInteger(line);
        System.out.println((char) decode(bigByte).byteValue());
        out.close();
    }

    private Object bruteForce(BigInteger number) {
        int count = 0;
        Object valuesResult[] = new Object[3];
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        BigInteger sum = new BigInteger("2");
        BigInteger result = new BigInteger("1");
        while (a.compareTo(number.pow(1/2) ) < 1) {
            a.add(sum);
            while (b.compareTo(number.pow(1/2)) < 1) {
                count++;
                result = a.multiply(b);
                if (result == number) {
                    valuesResult[0] = a;
                    valuesResult[1] = b;
                    valuesResult[2] = count;
                    return valuesResult;
                }
                b.add(sum);
            }
        }
        return valuesResult;
    }

}