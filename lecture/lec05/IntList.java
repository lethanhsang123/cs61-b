class IntList {

    private int first;
    private IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int getFirst() {
        return first;
    }

    public IntList getRest() {
        return rest;
    }

    /**
     * Return the size of the list using recursion.
     * @return the size of the list
     */
    public int size() {
        if (this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /**
     * Return the int at index i
     * @param i index
     * @return int
     */
    public int get(int i) {
        if (i == 0) {
            return this.first;
        }
        return this.rest.get(i - 1);
    }

    public int getIterative(int i) {
        IntList current = this;
        while (i > 0) {
            current = current.rest;
            i--;
        }
        return current.first;
    }

    public IntList incrementRecursiveNonDestructive() {
        IntList newRest = null;
        if (this.rest != null) {
            newRest = this.rest.incrementRecursiveNonDestructive();
        }
        return new IntList(this.first + 1, newRest);
    }

}