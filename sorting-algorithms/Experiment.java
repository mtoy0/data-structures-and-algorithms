import java.util.Arrays;

public class Experiment {
  public static void main(String[] args) {
    int[] test = null;
    double start = -1, end = -1;
    int n = 1000000;
    int step = 1000;
    GodricsHat sorter = new Sorter();
    int i = 0, j = 1;
    for (i = 0; i < 6; i++) {
      for (j = 1; (j * step) <= n; j++) {
        test = randomIntegers(0, n, j * step);
        start = System.currentTimeMillis();
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
          case 5:
            Arrays.sort(test);
        }
        end = System.currentTimeMillis() - start;
        if ((j * step) < n)
          System.out.printf("(%d, %.0f), ", j, end);
      }
      System.out.printf("(%d, %.0f)%n", j, end);
    }
  }

  public static int[] randomIntegers(int min, int max, int n) {
    int[] ret = new int[n];
    for (int i = 0; i < n; i++)
      ret[i] = min + (int) (Math.random() * (max - min));
    return ret;
  }
}