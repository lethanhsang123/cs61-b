public class AList<T> {
    private T[] items;
    private int size;

    public AList() {
        this.items = (T[]) new Object[999];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void addLast(T value) {
        if (this.size == this.items.length) {
            resize(size + 1);
        }
        this.items[size] = value;
        this.size++;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < this.items.length; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    public T removeLast() {
        T lastItem = this.items[size - 1];
        this.items[size - 1] = null;
        size --;
        return lastItem;
    }

    public T get(int index) {
        return this.items[index];
    }
}
