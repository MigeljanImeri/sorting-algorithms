public class LinkedList<T> {

    Node<T> head;

    public LinkedList() {
        head = null;
    }

    public void add(T data) {

        if (this.head == null) {
            Node<T> newNode = new Node<T>(data);
            this.head = newNode;
            return;
        }

        Node<T> newNode = new Node<T>(data);
        newNode.next = this.head;
        this.head = newNode;
    }
}
