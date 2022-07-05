import java.math.*;
import java.util.concurrent.atomic.AtomicInteger;
 
class Stuff {
    

    public static int extended_gcd(int a, int b, AtomicInteger x, AtomicInteger y){
        if (a == 0){
            x.set(0);
            y.set(1);
            return b;
        }
 
        AtomicInteger _x = new AtomicInteger(), _y = new AtomicInteger();
        int gcd = extended_gcd(b % a, a, _x, _y);
 
        x.set(_y.get() - (b/a) * _x.get());
        y.set(_x.get());
 
        return gcd;
    }
     
    // If n is prime, then always returns true,
    // If n is composite than returns false with
    // high probability Higher value of k increases
    //  probability of correct result.
    static boolean isPrime(BigInteger n, int k){

        BigInteger four = new BigInteger("4");
        BigInteger three = new BigInteger("3");
        BigInteger two = new BigInteger("2");
        // Corner cases
        if (n.compareTo(BigInteger.ONE) <= 0 || n.equals(four)) return false;
        if (n.compareTo(three) == 0) return true;
        
        // Try k times
        while (k > 0){
            // Pick a random number in [2..n-2]    
            // Above corner cases make sure that n > 4

            BigInteger dividend = new BigInteger(Integer.toString((int)Math.random()));
            BigInteger divisor = new BigInteger("4").negate().add(n);

            // a = (numeroAleatorio % n - 4) + 2
            BigInteger a = (BigInteger) ( dividend.mod(divisor)).add(two);
        
            // nMinusOne = n - 1
            BigInteger nMinusOne =  BigInteger.ONE.negate().add(n);
            if (! a.modPow(nMinusOne, n).equals(BigInteger.ONE)){
                return false;
            }
            k--;
        }
     
        return true;
   }

}