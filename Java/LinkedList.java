public class LinkedList<T> {

    public Node<T> head;
    public int length;

    public LinkedList() {
        head = null;
        length = 0;
    }

    public LinkedList(int n) {
        head = null;
        for (int i = 0; i < n;i++) {
            add(null);
        }
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

    public void set (int index, T value) {
        Node<T> traversal = this.head;
        int i = 0;

        while (i != index) {
            traversal = traversal.next;
            i++;
        }

        traversal.data = value;
    }

    public T get (int index) {
        Node<T> traversal = this.head;
        int i = 0;

        while (i != index) {
            traversal = traversal.next;
            i++;
        }

        return traversal.data;
    }

    public Node<T> lastNode() {
        Node<T> traversal = this.head;

        while (traversal.next != null) {
            traversal = traversal.next;
        }

        return traversal;
    }

    public LinkedList<T> clone() {
        LinkedList<T> clone = new LinkedList<>();
        Node<T> traversal = this.head;

        while (traversal != null) {
            clone.add(traversal.data);
            traversal = traversal.next;
        }
        return clone;
    }
}
