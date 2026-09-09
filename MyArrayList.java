public class MyArrayList<E> {
    private Object[] list;
    private int size;

    public MyArrayList() {
        list = new Object[10];
        size = 0;
    }

    public boolean add(E element) {
        ensureCapacity();
        list[size] = element;
        size++;
        return true;
    }

    /** Adds an element at a zero-based index. */
    public void add(int index, E element) {
        checkPositionIndex(index);
        ensureCapacity();

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkElementIndex(index);
        return (E) list[index];
    }

    public E remove(int index) {
        checkElementIndex(index);
        E removed = get(index);

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;
        return removed;
    }

    /** Removes the first element equal to target. */
    public boolean remove(Object target) {
        for (int i = 0; i < size; i++) {
            Object current = list[i];
            boolean matches;

            if (target == null) {
                matches = current == null;
            } else {
                matches = target.equals(current);
            }

            if (matches) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    public void set(int index, E element) {
        checkElementIndex(index);
        list[index] = element;
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";

        for (int i = 0; i < size; i++) {
            result += list[i];
            if (i < size - 1) {
                result += ", ";
            }
        }

        return result + "]";
    }

    private void ensureCapacity() {
        if (size < list.length) {
            return;
        }

        Object[] largerList = new Object[list.length * 2];
        for (int i = 0; i < size; i++) {
            largerList[i] = list[i];
        }
        list = largerList;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
