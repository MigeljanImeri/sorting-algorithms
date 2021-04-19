import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class SortingArrays {

    public static void main(String[] args) {

        // Bubble Sort Time
        int[] numbers = getArrayOfNum();
        long start = System.nanoTime();
        bubbleSort(numbers);
        long end = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (end - start) / 1e6 + "ms");

        // Quick Sort Time
        int[] numbers1 = getArrayOfNum();
        start = System.nanoTime();
        quickSort(numbers1, 0, numbers1.length - 1);
        end = System.nanoTime();
        System.out.println("Quick Sort Time: " + (end - start) / 1e6 + "ms");

        // Radix Sort Time
        int[] numbers2 = getArrayOfNum();
        start = System.nanoTime();
        radixsort(numbers2, numbers2.length);
        end = System.nanoTime();
        System.out.println("Radix Sort Time: " + (end - start) / 1e6 + "ms");

    }

    static int[] getArrayOfNum() {
        File f = new File("numbers.txt");
        int[] numbers = new int[1000];
        Scanner input;

        try {
            input = new Scanner(f);
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = input.nextInt();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        return numbers;
    }

    static void printArray(int[] array) {
        for (int num : array) {
            System.out.println(num);
        }
    }

    // https://www.geeksforgeeks.org/bubble-sort/
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    // https://www.geeksforgeeks.org/quick-sort/
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // https://www.geeksforgeeks.org/radix-sort/
    static void radixsort(int arr[], int n) {
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

}