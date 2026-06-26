public class Stack<T extends Comparable<T>> extends Structure<T> {

  public T pop() {
    if (this.isEmpty())
      return null;
    T ret = this.tail.x;
   this.tail.before.next = null;
    this.tail = this.tail.before;
    this.tail.next = null;
    return ret;
  }
}
