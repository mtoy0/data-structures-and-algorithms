public class Test {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
       for (int i = 0; i < 10; i++)
         s.push(i);
        for (int i = 9; i >= 0; i--)
          System.out.println(s.pop());
    }
}
