import java.math.BigInteger;

public class Inverse {
    private static String ALPHABET = " abcdefghijklmnopqrstuvwxyz";
    private static BigInteger TWENTY_SEVEN = BigInteger.valueOf(27);
    
    public static void main(String[] args) {
       BigInteger hashValue = new BigInteger(args[0]);
        String result = reverseHash(hashValue);
        System.out.println(result);
    }
    
    public static String reverseHash(BigInteger hashValue) {
        if (hashValue.equals(BigInteger.ZERO)) {
            return " ";
        }
        
        StringBuilder result = new StringBuilder();
        BigInteger remaining = hashValue;
        
        while (remaining.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divMod = remaining.divideAndRemainder(TWENTY_SEVEN);
            int charIndex = divMod[1].intValue();
            result.append(ALPHABET.charAt(charIndex));
            remaining = divMod[0];
        }
        
        return result.reverse().toString();
    }
}