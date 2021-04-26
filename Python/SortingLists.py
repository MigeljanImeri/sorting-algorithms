import time


def get_list():
    list = []
    f = open("numbers.txt", "r")
    for num in f:
        list.append((int(num)))
    return list

# https://stackabuse.com/bubble-sort-in-python/
def bubble_sort(our_list):
    has_swapped = True

    num_of_iterations = 0

    while(has_swapped):
        has_swapped = False
        for i in range(len(our_list) - num_of_iterations - 1):
            if our_list[i] > our_list[i+1]:
                # Swap
                our_list[i], our_list[i+1] = our_list[i+1], our_list[i]
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
def radixSort(array):
    # Get maximum element
    max_element = max(array)

    # Apply counting sort to sort elements based on place value.
    place = 1
    while max_element // place > 0:
        countingSort(array, place)
        place *= 10


list = get_list()
t0 = time.time_ns()
bubble_sort(list)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Bubble Sort Time: ", total)

list1 = get_list()
t0 = time.time_ns()
quick_sort(list1, 0, len(list1) - 1)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Quick Sort Time: ", total)

list2 = get_list()
t0 = time.time_ns()
radixSort(list2)
t1 = time.time_ns()
total = (t1 - t0)/1e6  # convert time from nanoseconds into microseconds
print("Radix Sort Time: ", total)

