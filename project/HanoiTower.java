import java.util.ArrayDeque;
import java.util.Deque;

public class HanoiTower {

    private final Rod rod1;
    private final Rod rod2;
    private final Rod rod3;

    public HanoiTower(int numberOfDisks) {
        rod1 = new Rod("Rod 1");
        rod2 = new Rod("Rod 2");
        rod3 = new Rod("Rod 3");

        // Put disks on Rod 1 from largest to smallest.
        for (int disk = numberOfDisks; disk >= 1; disk--) {
            rod1.push(disk);
        }
    }

    /**
     * Move n disks from source to destination,
     * using auxiliary as the temporary rod.
     */
    private static void moveDisk(
            int n,
            Rod source,
            Rod auxiliary,
            Rod destination) {

        // Base case:
        // Move the only disk directly.
        if (n == 1) {
            move(source, destination);
            return;
        }

        // 1. Move n-1 disks:
        //    source -> auxiliary
        moveDisk(n - 1, source, destination, auxiliary);

        // 2. Move the largest disk:
        //    source -> destination
        move(source, destination);

        // 3. Move n-1 disks:
        //    auxiliary -> destination
        moveDisk(n - 1, auxiliary, source, destination);
    }

    /**
     * Move the top disk from source to destination.
     */
    private static void move(Rod source, Rod destination) {
        int disk = source.pop();
        destination.push(disk);

        System.out.println(
                "Move disk " + disk
                        + " from " + source.getName()
                        + " to " + destination.getName()
        );
    }

    public void solve() {
        int numberOfDisks = rod1.size();

        moveDisk(
                numberOfDisks,
                rod1,
                rod2,
                rod3
        );
    }

    public void print() {
        System.out.println(rod1);
        System.out.println(rod2);
        System.out.println(rod3);
    }

    /**
     * A Rod behaves like a stack.
     *
     * Top of the rod = top of the Deque.
     */
    private static class Rod {

        private final Deque<Integer> disks;
        private final String name;

        public Rod(String name) {
            this.name = name;
            this.disks = new ArrayDeque<>();
        }

        /**
         * Put a disk on top of this rod.
         *
         * Hanoi invariant:
         * a larger disk cannot be placed on a smaller disk.
         */
        public void push(int disk) {
            if (!disks.isEmpty() && disks.peek() < disk) {
                throw new IllegalStateException(
                        "Cannot put disk "
                                + disk
                                + " on top of smaller disk "
                                + disks.peek()
                );
            }

            disks.push(disk);
        }

        /**
         * Remove and return the top disk.
         */
        public int pop() {
            if (disks.isEmpty()) {
                throw new IllegalStateException(
                        name + " is empty"
                );
            }

            return disks.pop();
        }

        public int size() {
            return disks.size();
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + ": " + disks;
        }
    }

    public static void main(String[] args) {

        HanoiTower game = new HanoiTower(4);

        System.out.println("Initial state:");
        game.print();

        System.out.println("\nMoves:");
        game.solve();

        System.out.println("\nFinal state:");
        game.print();
    }
}