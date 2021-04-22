import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class SortingLinkedLists {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = getLinkedListOfNum();
        LinkedList<Integer> numbers1 = getLinkedListOfNum();
        LinkedList<Integer> numbers2 = getLinkedListOfNum();

        //Bubble Sort Time
        long start = System.nanoTime();

        

    }

    static LinkedList<Integer> getLinkedListOfNum() {
        File f = new File("numbers.txt");
        LinkedList<Integer> list = new LinkedList<>();
        Scanner input;

        try {
            input = new Scanner(f);
            while (input.hasNextInt()) {
                list.add(input.nextInt());
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
}
