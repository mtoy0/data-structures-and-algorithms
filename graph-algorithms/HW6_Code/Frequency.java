import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class Frequency {
    public static void main(String[] args) {
        String contents = read("../H6_Novel.txt").toLowerCase().replace("\n", " ");
        StringBuilder filteredContents = new StringBuilder();
        for (int i = 0; i < contents.length(); i++) {
            char c = contents.charAt(i);
            if ('a' <= c && c <= 'z' || c == ' ') {
                filteredContents.append(c);
            }
        }
        String[] words = filteredContents.toString().split(" ");
        HashMap<String, Integer> counts = new HashMap<>();
        for (String word : words) {
           if(word.length() < 6)
           continue;
            if (counts.containsKey(word)) {
                counts.put(word, counts.get(word) + 1);
            } else {
                counts.put(word, 1);
            }
        }
        List<Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        int i = 0;
        for (String k : result.keySet()) {
       System.out.printf("%15s:%4d\n", k, result.get(k));
       if(++i > 10)
       break;
        }

    }

    public static String read(String path) {
        byte[] ret = null;
        try {
            ret = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(ret);
    }
}
