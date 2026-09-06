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

}


public class Discussion04 {

    
}