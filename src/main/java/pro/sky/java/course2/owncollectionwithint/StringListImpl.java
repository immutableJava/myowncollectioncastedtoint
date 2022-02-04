package pro.sky.java.course2.owncollectionwithint;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] storage;
    private int size;

    public StringListImpl() {
        storage = new String[10];
    }

    public static void validateItem(String item) {
        if (item == null) {
            throw new NullInputParameterException();
        }
    }


    @Override
    public String add(String item) {
        if (size == storage.length) {
            grow();
        }
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (size == storage.length) {
            grow();
        }
        validateItem(item);
        validateIndex(index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        if (indexOf(item) == -1) {
            throw new ElementNotFoundException();
        }
            int i = indexOf(item);
            System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
            size--;

        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = storage[index];
        int i = indexOf(item);
        System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
        size--;

        return item;
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);
        return indexOf(item) != -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new NullPointerException();
        }
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    public void grow() {
            storage = Arrays.copyOf(storage, size * 2);
    }

    @Override
    public boolean equals(StringList otherList) {

        if (otherList == null) {
            throw new NullInputParameterException();
        }
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < storage.length; i++) {
            if (!storage[i].equals(otherList.get(i))) {
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
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new BeyondTheSizeException();
        }
    }
}
