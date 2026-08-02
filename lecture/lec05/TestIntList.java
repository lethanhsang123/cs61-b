import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIntList {

    @Test
    void testIntList() {
        IntList list = new IntList(1, new IntList(2, new IntList(3, null)));
        assertEquals(3, list.size());

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(2));
        assertEquals(1, list.getIterative(0));
        assertEquals(3, list.getIterative(2));

        IntList incremented = list.incrementRecursiveNonDestructive();
        assertEquals(2, incremented.getIterative(0));
        assertEquals(4, incremented.getIterative(2));
    }

}
