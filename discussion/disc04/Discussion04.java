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


class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T value;
        private Node next, prev;
        
        public Node(T value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public T getValue() {
            return this.value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        private Node getNext() {
            return this.next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getPrev() {
            return this.prev;
        }

        private void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private Node nextNode;

        public LinkedListDequeIterator() {
            this.nextNode = LinkedListDeque.this.sentinel.getNext();
        }

        public boolean hasNext() {
            return this.nextNode != null;
        }

        public T next() {
            if(!hasNext()) {
                // should throw exception
                return null;
            }
            T value = this.nextNode.getValue();
            this.nextNode = this.nextNode.getNext();
            return value;
        }



    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }


    @Override
    public int size() {
        return this.size;
    }
 
    @Override   
    public void addFirst(T e) {
        Node node = new Node(e, this.sentinel.getNext(), this.sentinel);
        Node oldFirtNode = this.sentinel.getNext();
        oldFirtNode.setPrev(node);
        this.sentinel.setNext(node);
        node.setNext(oldFirtNode);
        node.setPrev(this.sentinel);
        this.size++;
    }

    @Override
    public void enqueue(T e) {
        // travel to last node;
        Node last = this.sentinel;
        while(last.next != null) {
            last = last.next;
        }
        Node newNode = new Node(e, null, last);
        last.setNext(newNode); 
        this.size++;
    }

    @Override
    public boolean offerFirst(T e) { 
        Node node = new Node(e, this.sentinel.getNext(), this.sentinel);
        this.sentinel.setNext(node); 
        this.size++;
        return true;
    }


    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public T dequeue() {
        if (this.sentinel.getNext() == null) {
            return null;
        }
        Node first = this.sentinel.getNext();
        this.sentinel.setNext(first.getNext());
        if (first.getNext() != null) {
            first.getNext().setPrev(this.sentinel);
        } 
        this.size--;
        return first.getValue();
    }

    @Override
    public T removeLast() {
        if (this.sentinel.getNext() == null) {
            return null;
        }
        Node last = this.sentinel.getNext();
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.getPrev().setNext(null);
        last.setPrev(null); 
        this.size--;
        return last.getValue();
    }


    @Override
    public T pollLast() {
        if (this.sentinel.getNext() == null) {
            return null;
        }
        Node last = this.sentinel.getNext();
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.getPrev().setNext(null);
        last.setPrev(null); 
        this.size--;
        return last.getValue();
    }

    @Override
    public void push(T t) {
        Node node = new Node(t, this.sentinel.getNext(), this.sentinel);
        this.sentinel.setNext(node);
        this.size++;
    }

    @Override
    public T pop() {
        if (this.sentinel.getNext() == null) {
            return null;
        }
        Node last = this.sentinel.getNext();
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.getPrev().setNext(null);
        last.setPrev(null); 
        this.size--;
        return last.getValue();
    }

   
    /**
     * Rotates the Deque left by x places. Assume x is non-negative.
     */
    public void rotateLeft(int x) {

        if (
            x <= 0 
            || this.size <= 1
            || x % this.size == 0
        ) {return;}

        x = x % this.size();

        // find last node
        Node lastNode = this.sentinel.getNext();
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }
        
        // find first node
        Node firstNode = this.sentinel.getNext();

        // rebuild first node
        this.sentinel.setNext(firstNode.getNext());
        this.sentinel.getNext().setPrev(this.sentinel);
        
        // rebuild lastNode
        firstNode.setPrev(lastNode);
        firstNode.setNext(null);
        lastNode.setNext(firstNode);
        
        if (x > 1) rotateLeft( x - 1);
    }


}


public class Discussion04 {

    
}
