import java.util.Random;

public class Balance {


    private static final int N = 6;
   
    private static final int TRIALS = 1_000_000;

    public static void main(String[] args) {
        Random rng = new Random(); 
        int balancedCount = 0;

        char[] buffer = new char[N];

        for (int t = 0; t < TRIALS; t++) {
         
            for (int i = 0; i < N; i++) {
                buffer[i] = rng.nextBoolean() ? '(' : ')';
            }

            if (isBalanced(buffer)) {
                balancedCount++;
            }
        }

        double ratio = balancedCount / (double) TRIALS;

      
        System.out.println("Balanced count: " + balancedCount + " out of " + TRIALS);
        System.out.printf("Ratio (decimal): %.6f%n", ratio);
    }

    private static boolean isBalanced(char[] seq) {
      
        Stack s = new Stack();

        for (char c : seq) {
            if (c == '(') {
                
                s.push(Character.valueOf('('));
            } else if (c == ')') {
        
                if (s.isEmpty()) {
                  
                    return false;
                }
                
                s.pop();
            } else {
               
                return false;
            }
        }

    
        return s.isEmpty();
    }
}

