import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Palindrome {
    public static void main(String[] args) {
        String[] lines = new String[370105];
        fillLines("../data/words_alpha.txt", lines);

        int count = 0;

        for (String ln : lines) {
            if (ln != null && isPalindrome(ln) && ln.length() >= 3) {
                System.out.printf("%s, ", ln);
                count++;
            }
        }

      
        System.out.println("\nTotal palindromes (3+ letters): " + count);
    }

    public static void fillLines(String path, String[] lines) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int i = 0;
            String line;
            while ((line = br.readLine()) != null)
                lines[i++] = line;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
