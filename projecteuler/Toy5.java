// Answer: 232792560

public class Toy5 {
    public static void main(String[] args) {
        long result = 1;
        
     
        for (int i = 2; i <= 20; i++) {
            result = lcm(result, i);
        }
        
        System.out.println(result);
    }
    
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}