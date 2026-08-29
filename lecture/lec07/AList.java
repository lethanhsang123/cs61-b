public class AList {
    private int[] items;
    private int size;

    public AList() {
        this.items = new int[999];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void addLast(int value) {
        if (this.size == this.items.length) {
            resize(size + 1);
        }
        this.items[size] = value;
        this.size++;
    }

    private void resize(int capacity) {
        int[] newItems = new int[capacity];
        for (int i = 0; i < this.items.length; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    public int removeLast() {
        int lastItem = this.items[size - 1];
        this.items[size - 1] = 0;
        size --;
        return lastItem;
    }

    public int get(int index) {
        return this.items[index];
    }
}
