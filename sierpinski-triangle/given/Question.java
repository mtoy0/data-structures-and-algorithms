import java.util.PriorityQueue;
import java.util.Scanner;

public class Question implements Comparable<Question> {
    public static void main(String[] args) {
       PriorityQueue<Question> heap = new PriorityQueue<>();
        //BinaryHeap<Question> heap = new BinaryHeap<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                heap.add( new Question(i+2, j+2));
            
            }
        }
        while (true){
            Question current = heap.poll();
            current.ask();
            heap.add(current);
        }
    }

    public String statement;
    public String answer;
    public long duration;
    public long missed;

    public Question(int a, int b) {
        statement = String.format("%d x %d = ", a, b);
        answer = String.format("%d", a * b);
        duration = 0;
        missed = 0;
    }

    public void ask() {
        Scanner scanner = new Scanner(System.in);
        long start = System.nanoTime();
        System.out.printf("%s ", statement);
        String given = scanner.nextLine().trim();
        duration += System.nanoTime() - start;
        //scanner.close();
        missed += given.equals(answer) ? 0 : 1;
    }

    public int compareTo(Question other) {
        return -1 * Long.compare(this.key(), other.key());

    }
public String toString() {
        return String.format("%d: %s", this.key(), statement);
    }
    private long key() {
       if (missed + duration == 0) 
        return Long.MAX_VALUE;
    return missed + duration / 1000000;
    }
}
