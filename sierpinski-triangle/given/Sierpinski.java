public class Sierpinski {
  public static void main(String[] args) {
    new Canvas(new Sierpinski());
  }

  public void points(int x, int y, int w, int h, Queue<Coordinates> q, int r) {
    // implement me
    if (w <= 6 || h <= 6) {
      q.push(new Coordinates(x, y, w, h));
      return;
    }

    int halfW = w / 2;
    int halfH = h / 2;

    // Top sub-triangle
    points(x + w / 4, y, halfW, halfH, q, r + 1);
    // Bottom-left sub-triangle
    points(x, y + halfH, halfW, halfH, q, r + 1);
    // Bottom-right sub-triangle
    points(x + halfW, y + halfH, halfW, halfH, q, r + 1);
  }
}
