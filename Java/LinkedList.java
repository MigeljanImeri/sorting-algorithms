public class LinkedList<T> {

    Node<T> head;
    public int length;

    public LinkedList() {
        head = null;
        length = 0;
    }

    public void add(T data) {
        
        this.length++;

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
