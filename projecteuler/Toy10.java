// Answer: 142913828922

public class Toy10 {
    public static void main(String[] args) {
        int limit = 2000000;
        boolean[] isPrime = sieveOfEratosthenes(limit);
        
        long sum = 0;
        for (int i = 2; i < limit; i++) {
            if (isPrime[i]) {
                sum += i;
            }
        }
        
        System.out.println(sum);
    }
    
    private static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n];
        

        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
     
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        return isPrime;
    }
}