
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTesting {

    @Test
    void testSort() {
        String[] input = {"velociraptor", "arradium", "horse", "koshi"};
        // String[] expected = {"arradium", "horse", "koshi", "velociraptor"};
        String[] expected = {"horse", "arradium", "koshi", "velociraptor"};

        Sort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    void testFindSmallest() {
        String[] input = {"velociraptor", "arradium", "horse", "koshi"};
        int expectedSmallest = 2;

        int actualSmallest = Sort.findSmallest(input, 2);

        assertEquals(expectedSmallest, actualSmallest);
    }

    @Test
    void testSwap() {
        String[] input = {"velociraptor", "arradium", "horse", "koshi"};
        String[] expected = {"arradium", "velociraptor", "horse", "koshi"};

        Sort.swap(input, 0, 1);

        assertArrayEquals(expected, input);
    }
}
