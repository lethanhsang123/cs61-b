
public class AList<T> implements List61B<T> {
    private T[] items;
    private int size;

    public AList() {
        this.items = (T[]) new Object[999];
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void addFirst(T value) {
        // check length, if exceed -> resize
        if (this.size == this.items.length) {
            resize(size + 1);
        }
        // move forward
        this.moveForward(0);
        this.size++;
        // add first
        this.items[0] = value;
    }

    private void moveForward(int position) {
        for (int i = size - 1; i >= position; i--) {
            // move i forward to i+1
            this.items[i + 1] = this.items[i];
            // remove i
            this.items[i] = null;
        }
    }

    @Override
    public void addLast(T value) {
        if (this.size == this.items.length) {
            resize(size + 1);
        }
        this.items[size] = value;
        this.size++;
    }

    @Override
    public T getFirst() {
        if (this.size <= 0) {
            return null;
        }
        return this.items[0];
    }

    @Override
    public T getLast() {
        if (this.size <= 0) {
            return null;
        }
        return this.get(size - 1);
    }

    @Override
    public T removeLast() {
        if (this.size <= 0) {
            return null;
        }
        T lastItem = this.items[size - 1];
        this.items[size - 1] = null;
        size --;
        return lastItem;
    }

    @Override
    public T get(int index) {
        return this.items[index];
    }

    @Override
    public void insert(T item, int position) {
        // check length, if exceed -> resize
        if (this.size == this.items.length) {
            resize(size + 1);
        }
        // move forward
        this.moveForward(position);
        this.size++;
        this.items[position] = item;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

}
