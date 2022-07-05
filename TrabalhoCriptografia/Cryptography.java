import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class Cryptography {
    public static Random rnd = new Random();
    BigInteger n, e, d, p, q;

    public void criptic(int bitlen){
        keyGenerator(bitlen);
        try {
            readFileEncrypt("/home/admin/Documents/Braia/CAL/CAL/", "originalFile.txt", 
                "encryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BigInteger encode(BigInteger m) {
        BigInteger c;

        c = m.modPow(e, n);

        return c;
    }

    public static void decode() {

    }

    private void keyGenerator(int bitlen) {

        p = generateRandomPrime(bitlen);
        q = generateRandomPrime(bitlen);

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

    public void readFileEncrypt(String pathName, String orginalFile, String encryptedFile) throws IOException{
        Scanner in = new Scanner(new FileReader(orginalFile));
        FileOutputStream out = new FileOutputStream(encryptedFile);
        while(in.hasNextLine()){
            String line = in.nextLine();
            line = line +"\n";
            BigInteger bigByte = new BigInteger(line.getBytes());
            out.write(encode(bigByte).toByteArray());
        }
        out.close();
    }
}