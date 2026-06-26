import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListExperiment {
  public static void main(String[] args) {
    List<Integer> ls = null;
    StringBuilder sb = new StringBuilder();
    appendTimes((int)Math.pow(2, 20), ls, sb);
    searchTimes((int)Math.pow(2, 17), ls, sb);
  }

  /**
   * FOR ls IN [ArrayList, LinkedList]:
   *   i = 1
   *   WHILE i is less than n:
   *     record time for appending on ls
   *     i++
   *
   * At return, there should be a CSV file with two rows. The first
   * for the ArrayList and the second for the LinkedList. The ith
   * column of each row represents the nanoseconds taken to append
   * the ith element.
   *
   * @param n   The row length.
   * @param ls  The list that is either an array or linked list.
   * @param sb  The string builder to join the nanoseconds.
   */
 public static void appendTimes(int n, List<Integer> ls, StringBuilder sb) {
  // We'll produce 2 rows: ArrayList first, then LinkedList.
  sb.setLength(0); // clear any prior content
  // -------- ArrayList row --------
  ls = new ArrayList<>();
  for (int i = 1; i <= n; i++) {
    long t0 = System.nanoTime();
    ls.add(i); // value doesn't matter for append timing
    long t1 = System.nanoTime();
    sb.append(t1 - t0).append(",");
  }
  if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') sb.setLength(sb.length() - 1);
  sb.append("\n");

  // -------- LinkedList row --------
  ls = new LinkedList<>();
  for (int i = 1; i <= n; i++) {
    long t0 = System.nanoTime();
    ls.add(i);
    long t1 = System.nanoTime();
    sb.append(t1 - t0).append(",");
  }
  if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') sb.setLength(sb.length() - 1);

  // Write once
  write("appendTimes.csv", sb.toString(), false);
}


  /**
   * FOR ls IN [ArrayList, LinkedList]:
   *   i = 1
   *   WHILE 2^i is less than n:
   *     assure ls is empty
   *     fill ls with 2^i random integers
   *     let x be a random integer
   *     record time for binary searching x in ls
   *     i++
   *
   * At return, there should be a CSV file with two rows. The first
   * for the ArrayList and the second for the LinkedList. The ith
   * column of each row represents the nanoseconds taken to perform
   * the binary search on a list of size 2^i.
   *
   * @param n   The row length.
   * @param ls  The list that is either an array or linked list.
   * @param sb  The string builder to join the nanoseconds.
   */
  public static void searchTimes(int n, List<Integer> ls, StringBuilder sb) {
  // We'll produce 2 rows: ArrayList first, then LinkedList.
  sb.setLength(0); // clear any prior content

  // -------- ArrayList row --------
  int i = 1;
  while ((1 << i) < n) { // sizes: 2^i
    int size = 1 << i;
    ls = new ArrayList<>();
    fillList(0, size, ls);
    int target = randomInteger(0, size);

    long t0 = System.nanoTime();
    binarySearch(ls, target, 0, ls.size() - 1);
    long t1 = System.nanoTime();

    sb.append(t1 - t0).append(",");
    i++;
  }
  if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') sb.setLength(sb.length() - 1);
  sb.append("\n");

  // -------- LinkedList row --------
  i = 1;
  while ((1 << i) < n) {
    int size = 1 << i;
    ls = new LinkedList<>();
    fillList(0, size, ls);
    int target = randomInteger(0, size);

    long t0 = System.nanoTime();
    binarySearch(ls, target, 0, ls.size() - 1);
    long t1 = System.nanoTime();

    sb.append(t1 - t0).append(",");
    i++;
  }
  if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') sb.setLength(sb.length() - 1);

  // Write once
  write("searchTimes.csv", sb.toString(), false);
}


  /**
   * @param ls The list to binary search.
   * @param n  The integer to look for.
   * @param s  The starting bound.
   * @param r  The ending bound.
   * @return   Whether n was found in ls.
   */
 public static boolean binarySearch(List<Integer> ls, int n, int s, int r) {
    if (s > r) {
        return false; // base case: not found
    }
    int mid = s + (r - s) / 2; // avoid overflow
    int midVal = ls.get(mid);

    if (midVal == n) {
        return true; // found
    } else if (midVal > n) {
        return binarySearch(ls, n, s, mid - 1); // search left half
    } else {
        return binarySearch(ls, n, mid + 1, r); // search right half
    }
}


  /**
   * Write, appending or overwriting, a string to a file.
   *
   * @param path   The path of the file to write to.
   * @param line   The line to write to the file at that path.
   * @param append Whether to append or overwrite the file.
   */
  public static void write(String path, String line, boolean append) {
    try (FileWriter fw = new FileWriter(path, append)) {
      fw.write(line + "\n");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Fill the list ls with random integers and sort it.
   *
   * @param min  The lower bound (inclusive) for the random n.
   * @param max  The upper bound (exclusive) for the random n.
   * @param ls   The list to fill with random integers n.
   */
  public static void fillList(int min, int max, List<Integer> ls) {
    for (int i = 0; i < max; i++)
      ls.add(randomInteger(min, max));
    Collections.sort(ls);
  }

  /**
   * @param min The lower bound (inclusive) for the random n.
   * @param max The upper bound (exclusive) for the random n.
   * @return    A random integer n between min and max.
   */
  public static int randomInteger(int min, int max) {
    return min + (int)(Math.random() * (max - min));
  }
}