import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    int n = 7;
    String[] names = {"Insertion", "Merge", "Quick", "Quick Loopy", "Counting"};
    GodricsHat sorter = new Sorter();
    int[] test = new int[n];
    int[] bench = new int[test.length];
    int[] control = new int[test.length];
    boolean toBreak = false;
    for (int i = 0; i < 50; i++) {
      test = randomIntegers(0, n, n);
      System.arraycopy(test, 0, bench, 0, test.length);
      Arrays.sort(bench);
      System.arraycopy(test, 0, control, 0, test.length);
      for (int j = 0; j < names.length; j++) {
        System.arraycopy(control, 0, test, 0, test.length);
        usingAlgorithmI(test, sorter, j);
        if (toBreak = !Arrays.equals(test, bench)) {
          System.out.println(names[j] + " sort is not working!");
          System.out.println("Got:      " + Arrays.toString(test));
          System.out.println("Expected: " + Arrays.toString(bench));
          System.out.println("Original: " + Arrays.toString(control));
          break;
        }
      }
      if (toBreak)
        break;
    }
    if (!toBreak)
      System.out.println("All sorts functional!");
  }

  public static void usingAlgorithmI(int[] test, GodricsHat sorter, int i) {
    switch (i) {
    case 0:
      sorter.insertion(test);
      break;
    case 1:
      sorter.merge(test);
      break;
    case 2:
      sorter.quick(test, 0, test.length - 1);
      break;
    case 3:
      sorter.quickLoopy(test);
      break;
    case 4:
      sorter.counting(test);
      break;
    }
  }

  public static int[] randomIntegers(int min, int max, int n) {
    int[] ret = new int[n];
    for (int i = 0; i < n; i++)
      ret[i] = min + (int)(Math.random() * (max - min));
    return ret;
  }
}