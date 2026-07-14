// Answer: 76576500

public class Toy12 {
    public static void main(String[] args) {
        int n = 1;
        long triangleNumber = 1;
        
        while (true) {
            int divisorCount = countDivisors(triangleNumber);
            
            if (divisorCount > 500) {
                System.out.println(triangleNumber);
                return;
            }
            
            n++;
            triangleNumber += n;
        }
    }
    

    private static int countDivisors(long number) {
        int count = 0;
        long sqrt = (long) Math.sqrt(number);
        
        for (long i = 1; i <= sqrt; i++) {
            if (number % i == 0) {
                
                count++;
               
                if (i != number / i) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
