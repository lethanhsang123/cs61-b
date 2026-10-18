import java.util.Iterator;


public class Discussion05 {


    
}

class IntList implements Iterable<Integer> {

    private int first;
    private IntList rest;


    public IntList(int f, IntList rest) {
        this.first = f;
        this.rest = rest;
    }


    public Iterator<Integer> iterator() {
        return new IntListIterator(this);
    }


    class IntListIterator implements Iterator<Integer> {

        private IntList curr;

        public IntListIterator(IntList list) {
            this.curr = list;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public Integer next() {
            int value = curr.first;
            curr = curr.rest;
            return value;
        }

    }
}

class TestGreaterThanIterator {

    

}
