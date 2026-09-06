import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public ArraySet() {
        this.items = (T[]) new Object[100];
        this.size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param x key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (x.equals(items[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * Throws an IllegalArgumentException if the key is null
     */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }

        // check contains
        if (contains(x)) {
            return;
        }

        // Todo: check sizing

        // add
        this.items[size] = x;

        // increase size
        this.size++;
    }

    /** Return the number of key-value mappings in this map */
    public int size() {
        return this.size;
    }

    /** Returns an iterator */
    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            this.wizPos = 0;
        }

        public boolean hasNext() {
            return this.wizPos < ArraySet.this.size;
        }

        public T next() {
            if (!hasNext()) {
                return null;
            }
            return ArraySet.this.items[wizPos++];
        }
    }

    @Override 
    public String toString() {
        StringBuilder returnString = new StringBuilder("{");
        for (int i = 0; i < size; i += 1) {
            returnString.append(this.items[i]);
            returnString.append(", ");
        }
        returnString.append("}");
        return returnString.toString();
    }

    @SuppressWarnings("uncheked")
    @Override 
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (! (other instanceof ArraySet)) {
            return false;
        }
        ArraySet<T> arraySet = (ArraySet<T>) other;
        if (this.size != arraySet.size()) {
            return false;
        }
        for(T a : this) {
            if (!arraySet.contains(a)) {
                return false;
            }
        }
        return false;
    }

    public static <E> ArraySet<E> of(E... elements) {
        ArraySet<E> arraySet = new ArraySet<E>();
        for(E x: elements) {
            arraySet.add(x);
        }
        return arraySet;
    }

    public static void main(String[] args) {

        Set<Integer> javaSet = new HashSet<Integer>();
        javaSet.add(5);
        javaSet.add(23);
        javaSet.add(42);
        // for (Integer i : javaSet) {
        // System.out.println(i);
        // }

        ArraySet<String> stringSet = new ArraySet<String>();
        // stringSet.add(null);
        stringSet.add("test 1");
        stringSet.add("test 2");
        stringSet.add("test 3");

        // Iterator<String> stringIterator = stringSet.iterator();
        // while (stringIterator.hasNext()) {
        // System.out.println(stringIterator.next());
        // }
        for (String value : stringSet) {
            System.out.println(value);
        }
    }

}
