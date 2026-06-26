import java.util.ArrayDeque;
import java.util.Random;

public class Conversion {
    public static void main(String[] args) {
        Random rand = new Random();
        int trials = 1000;

        int totalFinalBalance = 0; // sum of final balances across trials
        int totalAbsImbalance = 0; // sum of absolute final balances
        int balancedCount = 0;     // fully balanced sequences (perfectly matched)
        int totalUnmatchedOpens = 0;   // diagnostic
        int totalUnmatchedCloses = 0;  // diagnostic

        for (int t = 0; t < trials; t++) {
            // generate 6 random parentheses
            char[] arr = new char[6];
            for (int i = 0; i < 6; i++) {
                arr[i] = rand.nextBoolean() ? '(' : ')';
            }

            // STACKER: compute balance using a stack
            ArrayDeque<Character> stack = new ArrayDeque<>();
            int unmatchedCloses = 0;

            for (char c : arr) {
                if (c == '(') {
                    stack.push('(');
                } else { // ')'
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        unmatchedCloses++; // ')' with no matching '('
                    }
                }
            }

            int unmatchedOpens = stack.size();
            int finalBalance = unmatchedOpens - unmatchedCloses;

            totalFinalBalance += finalBalance;
            totalAbsImbalance += Math.abs(finalBalance);
            totalUnmatchedOpens += unmatchedOpens;
            totalUnmatchedCloses += unmatchedCloses;

            if (unmatchedOpens == 0 && unmatchedCloses == 0) {
                balancedCount++;
            }
        }

        double averageAbsImbalance = (double) totalAbsImbalance / trials;
        double balancedDecimal = (double) balancedCount / trials; // <-- decimal fraction

        System.out.println("Trials: " + trials);
        System.out.println("Average absolute imbalance: " + averageAbsImbalance);
        System.out.printf("Balanced fraction (decimal): %.6f%n", balancedDecimal);
    }
}

