import java.util.concurrent.atomic.AtomicInteger;

public class Euclides{

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
}