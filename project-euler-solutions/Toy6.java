// Answer: 25164150

public class Toy6 {
    public static void main(String[] args) {
        int n = 100;
        
       
        long sumOfSquares = 0;
        for (int i = 1; i <= n; i++) {
            sumOfSquares += i * i;
        }
        
     
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        long squareOfSum = sum * sum;
        
        // Calculate difference
        long difference = squareOfSum - sumOfSquares;
        
        System.out.println(difference);
    }
}