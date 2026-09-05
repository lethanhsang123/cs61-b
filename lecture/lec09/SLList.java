
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
        if (this.size == 0) {
            return null;
        }
        return this.sentinel.next.item;
    }

    
    @Override
    public T getLast() {
        if (this.size == 0) {
            return null;
        }
        Node<T> node = this.sentinel;
        while (node.next != null) {
            node = node.next;
        }
        return node.item;
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
        if (this.size <= 0) {
            return null;
        }
        // Jump to size - 1 node;
        Node<T> previNode = this.sentinel;
        while (previNode.next.next != null) {
            previNode = previNode.next;
        }

        // remove next
        Node<T> lastNode = previNode.next;
        previNode.next = null;
        
        // decrease size
        this.size--;

        return lastNode.item;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        Node<T> node = this.sentinel;
        while (i >= 0) {
            node = node.next;
            i--;
        }
        return node.item;
    }

    @Override
    public void insert(T x, int position) {
        // validate
        if (position < 0 || position > size) {
            return;
        }

        // check add last
        if (position == size) {
            addLast(x);
            return;
        }

        // jump to previous node
        Node<T> previous = this.sentinel;
        while (position > 0) {
            previous = previous.next;
            position --;
        }

        // add new node
        Node<T> newNode = new Node<T>(x, previous.next);
        previous.next = newNode;

        // increase size
        this.size ++;
    }
}
