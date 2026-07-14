// Answer: 31875000

public class Toy9 {
    public static void main(String[] args) {
  
        
        for (int a = 1; a < 333; a++) {
            for (int b = a + 1; b < 500; b++) {
                int c = 1000 - a - b;
               
                if (b >= c) {
                    continue;
                }
                
                
                if (a * a + b * b == c * c) {
                    System.out.println(a * b * c);
                    return;
                }
            }
        }
    }
}
