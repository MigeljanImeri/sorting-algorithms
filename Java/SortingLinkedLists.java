import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortingLinkedLists {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = getLinkedListOfNum();
        // LinkedList<Integer> numbers1 = getLinkedListOfNum();
        // LinkedList<Integer> numbers2 = getLinkedListOfNum();

        // Bubble Sort Time
        long start = System.nanoTime();
        BubbleSort(numbers);
        long end = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (end - start) / 1e6 + "ms");
        System.out.println(isSorted(numbers));


    }

    static LinkedList<Integer> getLinkedListOfNum() {
        File f = new File("numbers.txt");
        LinkedList<Integer> list = new LinkedList<>();
        Scanner input;

        try {
            input = new Scanner(f);
            while (input.hasNextInt()) {
                list.add((Integer) input.nextInt());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
            System.out.println(ex.getMessage());
        }
        return list;
    }

    static void printLinkedList(LinkedList<Integer> list) {
        Node<Integer> head = list.head;
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    static void swap(Node<Integer> a, Node<Integer> b) {
        Integer temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    static boolean isSorted(LinkedList<Integer> list) {
        Node<Integer> traversal = list.head;
        Node<Integer> previous = list.head;

        while (traversal.next != null) {
            previous = traversal;
            traversal = traversal.next;

            if (previous.data > traversal.data) {
                return false;
            } 
        }
        return true;

    }

    // Code adapted from
    // https://stackoverflow.com/questions/29869094/bubble-sort-manually-a-linked-list-in-java
    static void BubbleSort(LinkedList<Integer> list) {

        if (list.length > 1) {
            boolean wasChanged;

            do {
                Node<Integer> current = list.head;
                Node<Integer> previous = null;
                Node<Integer> next = list.head.next;
                wasChanged = false;

                while (next != null) {
                    if (current.data > next.data) {
                        /*
                         * // This is just a literal translation // of bubble sort in an array Node temp
                         * = currentNode; currentNode = next; next = temp;
                         */
                        wasChanged = true;

                        if (previous != null) {
                            Node<Integer> sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            Node<Integer> sig = next.next;

                            list.head = next;
                            next.next = current;
                            current.next = sig;
                        }

                        previous = next;
                        next = current.next;
                    } else {
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                }
            } while (wasChanged);
        }
    }

    static void QuickSort(LinkedList<Integer> list) {
        
    }
}
