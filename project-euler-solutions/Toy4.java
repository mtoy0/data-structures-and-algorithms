// Answer: 906609

public class Toy4 {
    public static void main(String[] args) {
        int largestPalindrome = 0;
        
      
        for (int i = 999; i >= 100; i--) {
            for (int j = 999; j >= i; j--) {
                int product = i * j;
                
             
                if (product <= largestPalindrome) {
                    break;
                }
                
                if (isPalindrome(product)) {
                    largestPalindrome = product;
                }
            }
        }
        
        System.out.println(largestPalindrome);
    }
    
    private static boolean isPalindrome(int number) {
        String str = String.valueOf(number);
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}