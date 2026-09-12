interface Deque<E> {

    void addFirst(E e);
    void addLast(E var1);

    boolean offerFirst(E var1);
    boolean offerLast(E var1);

    E removeFirst();
    E removeLast();

    E pollFirst();
    E pollLast();

    void push(E var1);
    E pop();

    /**
     * TODO: Remove all items from the queue
     */
    default void clear() {

    }

    /**
     * TODO: Removes all items equal to item from the queue
     * the remaining items should be in the same order as they were before
     * use .equals to compare items rather than ==
     */
    default void remove(E item) {

    }

    /**
     * Appends all items from the other queue into this queue
     * This method should be non-destructive on the otherQueue!
     */
    default void appendAll(Deque<E> otherQueue) {

    }
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

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return this.prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.size = 0;
    }


    
    public void addFirst(T e) {
        Node node = new Node(e, this.sentinel.getNext(), this.sentinel);
        this.sentinel.setNext(node);
        this.size++;
    }

    public void addLast(T e) {
        // travel to last node;
        Node last = this.sentinel;
        while(last.next != null) {
            last = last.next;
        }
        Node newNode = new Node(e, null, last);
        last.setNext(newNode); 
        this.size++;
    }

    public boolean offerFirst(T e) { 
        Node node = new Node(e, this.sentinel.getNext(), this.sentinel);
        this.sentinel.setNext(node); 
        this.size++;
        return true;
    }

    public boolean offerLast(T e) {    
        // travel to last node;
        Node last = this.sentinel;
        while(last.next != null) {
            last = last.next;
        }
        Node newNode = new Node(e, null, last);
        last.setNext(newNode); 
        this.size++;
        return true;
    }

    public T removeFirst() {
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

    public T pollFirst() {
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

    public void push(T t) {
        Node node = new Node(t, this.sentinel.getNext(), this.sentinel);
        this.sentinel.setNext(node);
        this.size++;
    }

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


}


public class Discussion04 {

    
}
