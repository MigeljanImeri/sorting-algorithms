public class Node<T> {
    Node<T> next;
    T data;

    public Node() {
        this.next = null;
        this.data = null;
    }

    public Node(T data) {
        this.next = null;
        this.data = data;
    }
}