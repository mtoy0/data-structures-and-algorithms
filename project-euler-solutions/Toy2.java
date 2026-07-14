// Answer: 4613732

public class Toy2 {
    public static void main(String[] args) {
        int limit = 4000000;
        long sum = 0;
        
        int fibPrev = 1;
        int fibCurr = 2;
        
        // Process the first even term (2)
        if (fibCurr % 2 == 0) {
            sum += fibCurr;
        }
        
        // Generate Fibonacci terms until we exceed the limit
        while (true) {
            int fibNext = fibPrev + fibCurr;
            
            if (fibNext > limit) {
                break;
            }
            
            if (fibNext % 2 == 0) {
                sum += fibNext;
            }
            
            fibPrev = fibCurr;
            fibCurr = fibNext;
        }
        
        System.out.println(sum);
    }
}