import java.util.*;
import java.math.BigInteger;
import java.io.*;
import java.math.*;

public class Cryptography {
    public static Random rnd = new Random();
    private BigInteger n, e, d, p, q;

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public void criptic(int bitlen) {

        keyGenerator(bitlen);
        try {
            readFileEncrypt("/home/krischanski/Udesc/CAL/CAL/", "originalFile.txt",
                    "encryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readFileDecrypt("/home/krischanski/Udesc/CAL/CAL/", "encryptedFile.txt",
                    "decryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /// home/krischanski/Udesc/CAL/CAL/

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

        setP(generateRandomPrime(bitlen / 2));
        setQ(generateRandomPrime(bitlen / 2));

        setN(p.multiply(q));
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Escolha um inteiro "e" , 1 < "e" < phi(n) , "e" e phi(n) sejam primos entre
        // si.
        setE(new BigInteger("3"));
        while (phi.gcd(e).intValue() > 1) {
            setE(e.add(new BigInteger("2")));
        }

        // d seja inverso multiplicativo de "e"
        setD(e.modInverse(phi));
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
            out.write(encode(bigByte).toString().getBytes());
        }
        out.close();
    }

    public void readFileDecrypt(String pathName, String encryptedFile, String decryptedFile) throws IOException {
        Scanner in = new Scanner(new FileReader(encryptedFile));
        FileOutputStream out = new FileOutputStream(decryptedFile);
        String line;
        while (in.hasNextLine()) {
            line = in.nextLine();
            BigInteger bigByte = new BigInteger(line);
            out.write((decode(bigByte).toByteArray()));
        }
        out.close();
    }

    // public Object bruteForce(BigInteger number) {
    // int count = 0;
    // Object valuesResult[] = new Object[3];
    // BigInteger a = new BigInteger("3");
    // BigInteger b = new BigInteger("3");
    // BigInteger sum = new BigInteger("2");
    // BigInteger result = new BigInteger("1");
    // while (a.compareTo(number.sqrt()) == -1) {
    // a = a.add(sum);
    // b = new BigInteger("3");
    // while (b.compareTo(number.sqrt()) == -1) {
    // if (result.compareTo(number) == 0) {
    // System.out.println("yesssss");
    // valuesResult[0] = a;
    // valuesResult[1] = b;
    // valuesResult[2] = count;
    // return valuesResult;
    // }
    // b = b.add(sum);
    // count++;
    // result = a.multiply(b);
    // System.out.println(n);
    // System.out.println(result);
    // System.out.println(a);
    // System.out.println(b);
    // System.out.println(count);
    // System.out.println("------");

    // }
    // }
    // return valuesResult;
    // }

    public Object bruteForce(BigInteger number) {
        int count = 0;
        Object valuesResult[] = new Object[3];
        BigInteger a = new BigInteger("3");
        BigInteger b = new BigInteger("3");
        BigInteger sum = new BigInteger("2");
        BigInteger result = new BigInteger("1");
        while (a.compareTo(number) < 1) {
            
            b = new BigInteger("3");
            result = new BigInteger("1");
            while (result.compareTo(number) < 1) {

                if (result.compareTo(number) == 0) {
                    System.out.println("yesssss");
                    valuesResult[0] = a;
                    valuesResult[1] = b;
                    valuesResult[2] = count;
                    return valuesResult;
                }
                b = b.add(sum);
                count++;
                result = a.multiply(b);

                System.out.println(n);
                System.out.println(result);
                System.out.println(a);
                System.out.println(b);
                System.out.println(count);
                System.out.println("------");

            }
            a = a.add(sum);
        }
        return valuesResult;
    }

}