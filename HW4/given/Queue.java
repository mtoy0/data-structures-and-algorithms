public class Queue<T extends Comparable<T>> extends Structure<T> {
    public T pop() {

        T ret = this.head.next.x;
        this.head.next = this.head.next.next;
        if (this.head.next != null)
            this.head.next.before = this.head;
        else
            this.tail = this.head;
        return ret;
    }
}
