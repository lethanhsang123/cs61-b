
public class Discussion03 {

    static class IntList {

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
         * 
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
         * 
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

        /**
         * Replaces all instances of a with b in L.
         * Modfies the passed list. Non-recursive.
         */
        public static void replace1(IntList L, int a, int b) {
            IntList p = L;
            while (p != null) {
                if (p.first == a) {
                    p.first = b;
                }
                p = p.rest;
            }
        }

        /**
         * Returns a copy of the provided list but with all occurrences
         * of a replaced by b. Does not modify the current list. Recursive.
         */
        public static IntList replace2(IntList L, int a, int b) {
            if (L == null) {
                return null;
            }
            int value = L.first;
            if (L.first == a) {
                value = b;
            }
            return new IntList(value, replace2(L.rest, a, b));
        }

        /**
         * Replaces all occurrences of a with b. Modifies the current list. Recursive.
         */
        public static void replace3(IntList L, int a, int b) { 
            if (L == null) { return;}

            if (L.first == a) {
                L.first = b;
            }
            replace3(L.rest, a, b);
        }


        /**
         * Given a sorted linked list of items, remove the duplicates.
         */
        public static void removeDuplicates(IntList p) { 
            if (p == null || p.rest == null) { return; }
        }

    }

}
