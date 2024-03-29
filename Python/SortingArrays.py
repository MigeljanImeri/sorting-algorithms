import numpy as np
import time


def get_array():
    array = np.arange(1000)
    f = open("numbers.txt", "r")
    i = 0
    for num in f:
        array[i] = int(num)
        i = i + 1
    return array


def bubble_sort(array):
    has_swapped = True

    num_of_iterations = 0

    while(has_swapped):
        has_swapped = False
        for i in range(len(array) - num_of_iterations - 1):
            if array[i] > array[i+1]:
                # Swap
                array[i], array[i+1] = array[i+1], array[i]
                has_swapped = True
        num_of_iterations += 1


def partition(arr, low, high):
    i = (low-1)
    pivot = arr[high]

    for j in range(low, high):

        if arr[j] <= pivot:

            i = i+1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i+1], arr[high] = arr[high], arr[i+1]
    return (i+1)

# https://www.geeksforgeeks.org/python-program-for-quicksort/
def quick_sort(arr, low, high):
    if len(arr) == 1:
        return arr
    if low < high:

        pi = partition(arr, low, high)

        quick_sort(arr, low, pi-1)
        quick_sort(arr, pi+1, high)


def countingSort(array, place):
    size = len(array)
    output = [0] * size
    count = [0] * 10

    # Calculate count of elements
    for i in range(0, size):
        index = array[i] // place
        count[index % 10] += 1

    # Calculate cumulative count
    for i in range(1, 10):
        count[i] += count[i - 1]

    # Place the elements in sorted order
    i = size - 1
    while i >= 0:
        index = array[i] // place
        output[count[index % 10] - 1] = array[i]
        count[index % 10] -= 1
        i -= 1

    for i in range(0, size):
        array[i] = output[i]


# https://www.programiz.com/dsa/radix-sort
def radix_sort(array):
    # Get maximum element
    max_element = max(array)

    # Apply counting sort to sort elements based on place value.
    place = 1
    while max_element // place > 0:
        countingSort(array, place)
        place *= 10


array = get_array()
t0 = time.time_ns()
bubble_sort(array)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Bubble Sort Time: ", total)

array1 = get_array()
t0 = time.time_ns()
quick_sort(array1, 0, len(array1) - 1)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Quick Sort Time: ", total)

array2 = get_array()
t0 = time.time_ns()
radix_sort(array2)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Quick Sort Time: ", total)
