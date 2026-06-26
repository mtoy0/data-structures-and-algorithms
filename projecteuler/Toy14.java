// Answer: 837799

public class Toy14 {
    public static void main(String[] args) {
        int maxNumber = 0;
        int maxLength = 0;
        
        for (int i = 1; i < 1000000; i++) {
            int length = getCollatzLength(i);
            if (length > maxLength) {
                maxLength = length;
                maxNumber = i;
            }
        }
        
        System.out.println(maxNumber);
    }
    
    private static int getCollatzLength(long n) {
        int length = 1;
        
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            length++;
        }
        
        return length;
    }
}
