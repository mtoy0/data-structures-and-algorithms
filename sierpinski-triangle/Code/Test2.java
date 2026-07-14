public class Test2 {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < 10; i++)
            q.push(i);
        for (int i = 9; i >= 0; i--)
            System.out.println(q.pop());
    }
}
