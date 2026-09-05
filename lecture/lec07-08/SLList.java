
public class SLList<T> {

    private static class Node<E> {
        public E item;
        public Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;
    
    public SLList() {
        this.sentinel = new Node<T>(null, null);
        this.size = 0;
    }

    public SLList(T x) {
        this();
        this.sentinel.next = new Node<T>(x, null);
        this.size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T x) {
        this.sentinel.next = new Node<T>(x, this.sentinel.next);
        this.size++;
    }

    public T getFirst() {
        return this.sentinel.next.item;
    }

    /** Add an item to the end of the list. */
    public void addLast(T x) { 
        Node<T> p = sentinel;

        /* Move p until it reaches the end of the list. */
        while(p.next != null) {
            p = p.next;
        }
        p.next = new Node<T>(x, null);
        this.size++;
    }

    /** Returns the size of the list. */
    public int size() { return this.size; }
}
