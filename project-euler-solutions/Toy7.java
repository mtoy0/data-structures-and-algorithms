// Answer: 104743

public class Toy7 {
    public static void main(String[] args) {
        int n = 10001;
        int count = 1; 
        int candidate = 3; 
        
        while (count < n) {
            if (isPrime(candidate)) {
                count++;
            }
            if (count < n) {
                candidate += 2; 
            }
        }
        
        System.out.println(candidate);
    }
    
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
 
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}