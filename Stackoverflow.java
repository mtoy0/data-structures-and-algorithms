public class Stackoverflow {
    public static void main(String[] args) {
        makeOverflow(1);
    }
    public static void makeOverflow(int i) {
        System.out.println(i);
        makeOverflow(i + 1);
    }
}
