package pro.sky.java.course2.owncollectionwithint;

import java.util.Arrays;

public class intListImpl implements intList {
    private int[] storage;
    private int size;

    public intListImpl() {
        storage = new int[10];
    }

    public void sortWithQuickSort(int[] arr){
        long[] longs = new long[3];
        int[] firstCopy = createRandomArray(10_000);
        int[] secondCopy = Arrays.copyOf(firstCopy,firstCopy.length);
        int[] thirdCopy = Arrays.copyOf(firstCopy,firstCopy.length);

        long start = System.currentTimeMillis();
        sortBubble(firstCopy);
        long timeOfBubbleSort = System.currentTimeMillis() - start;
        System.out.println(timeOfBubbleSort);
        sortSelection(secondCopy);
        long timeOfSelectionSort = System.currentTimeMillis() - timeOfBubbleSort - start;
        System.out.println(System.currentTimeMillis() - start);
        sortInsertion(thirdCopy);
        long timeOfInsertionSort = System.currentTimeMillis() - timeOfSelectionSort - timeOfBubbleSort - start;
        System.out.println(System.currentTimeMillis() - start);
        longs[0] = timeOfBubbleSort;
        longs[1] = timeOfSelectionSort;
        longs[2] = timeOfInsertionSort;
        long min = longs[0];
        for (long aLong : longs) {
            if (aLong < min) {
                min = aLong;
            }
        }
        if(min == longs[0]){
            sortBubble(arr);
        }
        if(min == longs[1]){
            sortSelection(arr);
        }
        if(min == longs[2]){
            sortInsertion(arr);
        }
    }
    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static int[] createRandomArray(int length){
        int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        return ints;
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static int binarySearch(int[] sortedArray, int key, int low, int high) {
            int index = Integer.MAX_VALUE;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (sortedArray[mid] < key) {
                    low = mid + 1;
                } else if (sortedArray[mid] > key) {
                    high = mid - 1;
                } else if (sortedArray[mid] == key) {
                    index = mid;
                    break;
                }
            }
            return index;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    @Override
    public int add(int item) {
        if (size == storage.length) {
            grow();
        }

        storage[size++] = item;
        return item;
    }

    @Override
    public int add(int index, int item) {
        if (size == storage.length) {
            grow();
        }

        validateIndex(index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public int set(int index, int item) {

        validateIndex(index);
        storage[index] = item;
        return item;
    }

    @Override
    public int removeByItem(int item) {

        if (indexOf(item) == -1) {
            throw new ElementNotFoundException();
        }
            int i = indexOf(item);
            System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
            size--;

        return item;
    }

    @Override
    public int removeByIndex(int index) {
        validateIndex(index);
        int item = storage[index];
        int i = indexOf(item);
        System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
        size--;

        return item;
    }

    @Override
    public boolean contains(int item) {
        int[] ints = Arrays.copyOf(storage, storage.length);
        sortWithQuickSort(ints);
        return binarySearch(ints, item, 0, ints.length) != Integer.MAX_VALUE;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(int item) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        validateIndex(index);
        return storage[index];
    }

    public void grow() {
            storage = Arrays.copyOf(storage, size * 2);
    }

    @Override
    public boolean equals(intList otherList) {

        if (otherList == null) {
            throw new NullInputParameterException();
        }
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new BeyondTheSizeException();
        }
    }
}
