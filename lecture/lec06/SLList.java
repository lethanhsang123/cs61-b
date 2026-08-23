
public class SLList {

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode sentinel;
    private int size;
    
    public SLList() {
        this.sentinel = new IntNode(0, null);
        this.size = 0;
    }

    public SLList(int x) {
        this();
        this.sentinel.next = new IntNode(x, null);
        this.size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        this.sentinel.next = new IntNode(x, this.sentinel.next);
        this.size++;
    }

    public int getFirst() {
        return this.sentinel.next.item;
    }

    /** Add an item to the end of the list. */
    public void addLast(int x) { 
        IntNode p = sentinel;

        /* Move p until it reaches the end of the list. */
        while(p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        this.size++;
    }

    /** Returns the size of the list. */
    public int size() { return this.size; }

    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        System.out.println(L.size());
    }
}
