import java.util.Iterator;

interface Queue<E> {

    /**
     * Add an element to the end of the queue
     */
    void enqueue(E e);

    /**
     * Removes and returns the front of the queue
     */
    E dequeue();

    /**
     * Return true if the queue is empty
     */
    boolean isEmpty();


    /**
     * Returns the number of elements in the queue
     */
    int size();


    /**
     * Remove all items from the queue
     */
    default void clear() {
        System.out.print("Clear: [");
        while (!isEmpty()) {
            E value = dequeue();
            System.out.print(value + " ");
        }
        System.out.println("]");
    }

    /**
     * Removes all items equal to item from the queue
     * the remaining items should be in the same order as they were before
     * use .equals to compare items rather than ==
     */
    default void remove(E item) {
        int sizeCounter = size();
        while (sizeCounter > 0) {
            E value = dequeue();
            if (!value.equals(item)) {
                enqueue(value);
            }
            sizeCounter--;
        }
    }

    /**
     * Appends all items from the other queue into this queue
     * This method should be non-destructive on the otherQueue!
     */
    default void appendAll(Deque<E> otherQueue) {
       
        int otherQueueSize = otherQueue.size();
        while (otherQueueSize > 0) {
            E value = otherQueue.dequeue();
            enqueue(value);
            otherQueue.enqueue(value);
        }
    }

    E get(int index);

    E getRecursive(int index);
}


interface Deque<E> extends Queue<E>, Iterable<E> {

    void addFirst(E e);
    boolean offerFirst(E var1);

    E removeLast();
    E pollLast();

    void push(E var1);
    E pop();

    int size();
}


class CircleArrayDeque61B<T> implements Deque<T> {

    private class CircleArrayDeque61BIterator implements Iterator<T> {


        public CircleArrayDeque61BIterator() {
        }

        public boolean hasNext() {
            // TODO: implements
            return false;
        }

        public T next() {
            // TODO: implements
            return null;
        }

        

    }

    public static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int size;
    private int front;
    
    public CircleArrayDeque61B() {
        // Generic can not init instance
        this.items = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
    }

    public CircleArrayDeque61B(int initCapacity) {
        this.items = (T[]) new Object[initCapacity];
        this.size = 0;
        this.front = 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new CircleArrayDeque61BIterator();
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int logicalIndex) {
        /* NOTE: logicalIndex bounb checking with size instead length 
        because Deque concept get(index) is get existing elements not get elements in array index.*/
        if (logicalIndex < 0 || logicalIndex >= this.size) {
            // TODO: Should throw Index Out Of Bound exception
            return null;
        }
        int phisicalIndex = (this.front + logicalIndex) % this.items.length; 
        return items[phisicalIndex];
    }

    @Override
    public T getRecursive(int logicalIndex) {
        if (logicalIndex < 0 || logicalIndex >= this.size) {
            // TODO: Should throw Index Out Of Bound exception
            return null;
        }
        // TODO: implements
        return this.get(logicalIndex);
    }

    private void grow() {
        T[] newItems = (T[]) new Object[this.items.length + DEFAULT_CAPACITY];
        for (int newPhysicalIndex = 0; newPhysicalIndex < this.size; newPhysicalIndex++) {
            int oldPhysicalIndex = (newPhysicalIndex + this.front) % this.items.length;
            newItems[newPhysicalIndex] = this.items[oldPhysicalIndex];
        }
        this.items = newItems;
    }

     
    @Override   
    public void addFirst(T e) {
        // TODO: implements
    }

    @Override
    public void enqueue(T e) {
        // TODO: implements
    }

    @Override
    public boolean offerFirst(T e) {
        // TODO: implements
        return false;
    }


    @Override
    public boolean isEmpty() {
        // TODO: implements
        return false;
    }

    @Override
    public T dequeue() {
        // TOOD: implements
        return null;
    }

    @Override
    public T removeLast() {
        // TODO: implements
        return null;
    }


    @Override
    public T pollLast() {
        // TODO: implements
        return null;
    }

    @Override
    public void push(T t) {
        // TODO: implements
    }

    @Override
    public T pop() {
        // TODO: implements
        return null;
    }

   
    /**
     * Rotates the Deque left by x places. Assume x is non-negative.
     */
    public void rotateLeft(int x) {
        // TODO: implements
    }


}


public class Project02 {}
