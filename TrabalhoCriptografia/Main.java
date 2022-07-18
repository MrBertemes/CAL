import java.util.*;

// import Cryptography;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Cryptography cripto = new Cryptography();
        cripto.criptic(4); // mudar aqui a quantidade de bits desejada
        cripto.bruteForce(cripto.getN());
    }
}