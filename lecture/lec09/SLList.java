
public class SLList<T> implements List61B<T> {

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
    @Override
    public void addFirst(T x) {
        this.sentinel.next = new Node<T>(x, this.sentinel.next);
        this.size++;
    }

    @Override
    public T getFirst() {
        return this.sentinel.next.item;
    }

    
    @Override
    public T getLast() {
        // Todo
        return null;
    }

    /** Add an item to the end of the list. */
    @Override
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
    @Override
    public int size() { return this.size; }

    @Override
    public T removeLast() {
        // Todo
        return null;
    }

    @Override
    public T get(int i) {
        // Todo
        return null;
    }

    @Override
    public void insert(T x, int position) {
        // Todo
    }
}
