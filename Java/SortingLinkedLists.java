import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortingLinkedLists {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = getLinkedListOfNum();
        ListIterator<Integer> listIterator = numbers.listIterator();
        LinkedList<Integer> numbers1 = getLinkedListOfNum();
        ListIterator<Integer> listIterator1 = numbers1.listIterator();
        LinkedList<Integer> numbers2 = getLinkedListOfNum();
        ListIterator<Integer> listIterator2 = numbers2.listIterator();

        // Bubble Sort Time
        long start = System.nanoTime();
        BubbleSort(numbers);
        long end = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (end - start) / 1e6 + "ms");
        System.out.println("Array is Sorted: " + isSorted(numbers));

        //Quick Sort Time
        Node<Integer> startNode = numbers1.get(0);
        Node<Integer> endNode = numbers1.lastNode();
        start = System.nanoTime();
        QuickSort(startNode,endNode);
        end = System.nanoTime();
        System.out.println("Quick Sort Time: " + (end - start) / 1e6 + "ms");
        System.out.println("Array is Sorted: " + isSorted(numbers1));


        LinkedList<Integer> testing = new LinkedList<Integer>();
        testing.add(5);
        testing.add(3);
        testing.add(10);

        printLinkedList(testing);
        testing.set(2, 15);
        printLinkedList(testing);
        //Radix Sort Time
        start = System.nanoTime();
        //RadixSort(numbers2);
        end = System.nanoTime();
        System.out.println("Radix Sort Time: " + (end - start) / 1e6 + "ms");
        System.out.println("Array is Sorted: " + isSorted(numbers1));
        


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
        for (int num: list) {
            System.out.println(num);
        }
    }

    static boolean isSorted(LinkedList<Integer> list) {

        int current = list.get(0);
        int previous = list.get(0);
        ListIterator<Integer> listIterator = list.listIterator();

        while (listIterator.hasNext()) {
            if (previous > current) {
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
                         * // This is just a literal translation // of bubble sort in an array Node<Integer> temp
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

    //Code adapted from 
    //https://www.geeksforgeeks.org/quicksort-on-singly-linked-list/
    static void QuickSort(Node<Integer> start, Node<Integer> end) {

        if (start == null || start == end || start == end.next)
            return;

        // split list and partion recurse
        Node<Integer> pivot_prev = paritionLast(start, end);
        QuickSort(start, pivot_prev);

        // if pivot is picked and moved to the start,
        // that means start and pivot is same
        // so pick from next of pivot
        if (pivot_prev != null && pivot_prev == start){
            QuickSort(pivot_prev.next, end);
        }

        // if pivot is in between of the list,
        // start from next of pivot,
        // since we have pivot_prev, so we move two nodes
        else if (pivot_prev != null && pivot_prev.next != null) {
            QuickSort(pivot_prev.next.next, end);
        }

    }

    static Node<Integer> paritionLast(Node<Integer> start, Node<Integer> end) {
        if (start == end || start == null || end == null)
            return start;

        Node<Integer> pivot_prev = start;
        Node<Integer> curr = start;
        int pivot = end.data;

        // iterate till one before the end,
        // no need to iterate till the end
        // because end is pivot
        while (start != end) {
            if (start.data < pivot) {
                // keep tracks of last modified item
                pivot_prev = curr;
                int temp = curr.data;
                curr.data = start.data;
                start.data = temp;
                curr = curr.next;
            }
            start = start.next;
        }

        // swap the position of curr i.e.
        // next suitable index and pivot
        int temp = curr.data;
        curr.data = pivot;
        end.data = temp;

        // return one previous to current
        // because current is now pointing to pivot
        return pivot_prev;
    }

    static void RadixSort(LinkedList<Integer> list)
    {
        // Find the maximum number to know number of digits
        int m = getMax(list);
 
        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(list, exp);
    }

    static int getMax(LinkedList<Integer> list) {

        Node<Integer> traversal = list.head;
        int max = traversal.data;
        while (traversal != null) {
            if (traversal.data > max) {
                max = traversal.data;
            }
            traversal = traversal.next;
        }
        return max;
    }

    
    static void countSort(LinkedList<Integer> list, int exp)
    {
        LinkedList<Integer> output = new LinkedList<Integer>(list.length); // output array
        int i;
        LinkedList<Integer> count = new LinkedList<>();

        int counter = 0;
        do {
            count.add(0);
            counter++;
        } while (counter < 10);
 
        System.out.println("list length is: " + list.length);
        // Store count of occurrences in count[]
        for (i = 0; i < list.length; i++) {
            //count[(arr[i] / exp) % 10]++;
            //System.out.println(i);
            //System.out.println(list.get(i));
            int countCurrentIndex = (list.get(i) / exp) % 10;
            //System.out.println(countCurrentIndex);
            int countCurrentIndexValue = count.get(countCurrentIndex);
            //System.out.println("countCurrentIndexValue: " + countCurrentIndexValue++);
            count.set(countCurrentIndex, ++countCurrentIndexValue);
        }

        //printLinkedList(count);
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            //count[i] += count[i - 1];
            count.set(i, count.get(i) + count.get(i - 1));
        }

 
        // Build the output array
        for (i = list.length - 1; i >= 0; i--) {
            //output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            //count[(arr[i] / exp) % 10]--;
            //System.out.println(i);
            int countCurrentIndex = (list.get(i) / exp) % 10;
            //System.out.println(countCurrentIndex1);
            int countCurrentIndexValue = count.get(countCurrentIndex);
            //System.out.println(countCurrentIndexValue1);
            int outputCurrentIndex = countCurrentIndexValue - 1;
            int listCurrentIndexValue = list.get(i);
            System.out.println("listCurrentIndexValue " + listCurrentIndexValue);
            System.out.println("outputCurrentIndex " + outputCurrentIndex);
            output.set(outputCurrentIndex,listCurrentIndexValue);
            count.set(countCurrentIndex, countCurrentIndexValue--);
        }
        printLinkedList(output);
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < list.length; i++) {
            //arr[i] = output[i];
            list.set(i, output.get(i));
            //System.out.println("Setting list index: " + i + " to: " + output.get(i));
        }
            
    }
}
