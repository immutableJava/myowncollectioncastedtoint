package pro.sky.java.course2.owncollectionwithint;

import java.util.Arrays;

public class IntListImpl implements IntList {
    private int[] storage;
    private int size;

    public IntListImpl() {
        storage = new int[10];
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


    @Override
    public int add(int item) {
        return add(size, item);
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
        int index = indexOf(item);
        return removeByIndex(index);
    }

    public void sortWithQuickSort(int[] arr) {
        sortInsertion(arr);
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

    @Override
    public int removeByIndex(int index) {
        validateIndex(index);
        int item = storage[index];
        int i = indexOf(item);
        System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
        size--;
        if (size < storage.length / 2) {
            resize();
        }
        return item;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, (int) (size * 1.5));
    }

    @Override
    public boolean equals(IntList otherList) {

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

    private void resize() {
        storage = Arrays.copyOf(storage, size / 3);
    }
}
