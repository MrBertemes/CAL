import java.util.*;
import java.math.BigInteger;
import java.io.*;

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
            readFileEncrypt("/home/Documents/Braia/CAL/CAL/", "originalFile.txt",
                    "encryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readFileDecrypt("/home/Documents/Braia/CAL/CAL/", "encryptedFile.txt",
                    "decryptedFile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /// home/krischanski/Udesc/CAL/CAL/

    }

    private BigInteger encode(BigInteger m) {
        BigInteger c;
        
        long start = System.currentTimeMillis();

        c = m.modPow(e, n);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Tempo encode:"+elapsed+"ms");
        return c;
    }

    private BigInteger decode(BigInteger c) {
        BigInteger m;

        long start = System.currentTimeMillis();

        m = c.modPow(d, n);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Tempo decode:"+elapsed+"ms");

        return m;
    }

    private void keyGenerator(int bitlen) {

        long start = System.currentTimeMillis();

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

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Tempo keyGen:"+elapsed+"ms");
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

    private static int randomNumber(){
        return rnd.nextInt(2);
    }
    
    private static BigInteger randomCandidate(int bitlen){
        BigInteger candidate = new BigInteger("0");
        BigInteger helper;
        for(int i = 0; i < bitlen; i++){
            if(i == 0 || i == bitlen - 1){
                helper = new BigInteger("2");
                helper = helper.pow(i);
                candidate = candidate.add(helper);
            }else {
                if(randomNumber()==1){
                 helper = new BigInteger("2");    
                 helper = helper.pow(i);
                }else{
                 helper = new BigInteger("0");   
                }
                candidate = candidate.add(helper);
            }
        }
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
            line.concat("\n");
            BigInteger bigLine = new BigInteger(line);
            out.write((decode(bigLine).toByteArray()));
        }
        out.close();
    }

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
                    valuesResult[0] = a;
                    valuesResult[1] = b;
                    valuesResult[2] = count;
                    long start = System.currentTimeMillis();
                    System.out.println("------------------");
                    System.out.println(n+" = "+a+"*"+b);
                    System.out.println("Achou em "+count+" iterações");
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Tempo Força Bruta:"+elapsed+"ms");
                    System.out.println("------------------");
                    return valuesResult;
                }
                b = b.add(sum);
                count++;
                result = a.multiply(b);

            }
            a = a.add(sum);
        }
        return valuesResult;
    }

}