import java.io.*;
import java.util.*;
public class Hashbrown{
    
    public static void main(String[] args) {
        String filename = "words_alpha.txt";
        List<String> words = readWords(filename);
        
        System.out.println("Total words loaded: " + words.size());
        System.out.println();
        
        // Find collisions for m1 = Integer.MAX_VALUE
        findCollisions(words, Integer.MAX_VALUE);
        
        System.out.println();
        System.out.println();
        
        // Find collisions for m2 = Long.MAX_VALUE
        
        System.out.println("COLLISIONS FOR m2 ");

        findCollisions(words, Long.MAX_VALUE);
    }
    
    public static List<String> readWords(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        return words;
    }
    
    public static void findCollisions(List<String> words, long m) {
        // Map from hash value to list of words with that hash
        Map<Long, List<String>> hashMap = new HashMap<>();
        
        // Compute hash for each word and group by hash value
        for (String word : words) {
            long hashValue = hash(word, m);
            hashMap.computeIfAbsent(hashValue, k -> new ArrayList<>()).add(word);
        }
        
        // Find all collisions 
        List<Long> collisionHashes = new ArrayList<>();
        for (Map.Entry<Long, List<String>> entry : hashMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                collisionHashes.add(entry.getKey());
            }
        }
        
        // Sort collision hashes 
        Collections.sort(collisionHashes);
        
        System.out.println("Number of collision groups: " + collisionHashes.size());
        System.out.println();
        
        int collisionCount = 0;
        for (Long hashValue : collisionHashes) {
            List<String> collidingWords = hashMap.get(hashValue);
            Collections.sort(collidingWords); // Sort words alphabetically
            
            System.out.println("Hash value: " + hashValue);
            System.out.println("Colliding words (" + collidingWords.size() + "): " + collidingWords);
            
            // Count pairwise collisions
            int pairsInGroup = collidingWords.size() * (collidingWords.size() - 1) / 2;
            collisionCount += pairsInGroup;
            
            System.out.println();
        }
        
        System.out.println("Total pairwise collisions: " + collisionCount);
    }
    
    // Hash function based on HashBrown.java
    public static long base27(Object toHash) {
        String s = String.format("%s", toHash);
        long base = 27; // 26 alphabet and a blank-space
        long offset = 'a' - 1;
        long x = 0, p = 1;
        for (int i = s.length() - 1; i > -1; i--) {
            long bit = s.charAt(i);
            bit = bit == ' ' ? 0 : (bit - offset);
            x += bit * p;
            p *= base;
        }
        return Math.floorMod(x, Long.MAX_VALUE);
    }
    
    public static long hash(Object toHash, long m) {
        return Math.floorMod(base27(toHash), m);
    }
}