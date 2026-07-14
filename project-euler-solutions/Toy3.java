// Answer: 6857

public class Toy3 {
    public static void main(String[] args) {
        long number = 600851475143L;
        long largestFactor = -1;
       
        while (number % 2 == 0) {
            largestFactor = 2;
            number = number / 2;
        }

        for (long i = 3; i * i <= number; i += 2) {
            while (number % i == 0) {
                largestFactor = i;
                number = number / i;
            }
        }
        
        // If number is still greater than 1, it's a prime factor
        if (number > 1) {
            largestFactor = number;
        }
        
        System.out.println(largestFactor);
    }
}