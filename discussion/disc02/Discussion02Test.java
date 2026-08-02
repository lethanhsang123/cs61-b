import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Discussion02Test {

    @Test
    void testPlanet() {
        Discussion02.Planet p1 = new Discussion02.Planet(0, 0, 5);
        Discussion02.Planet p2 = new Discussion02.Planet(3, 4, 10);
        assertEquals(5, p1.distanceTo(p2));
        assertEquals(15, p1.totalMass(List.of(p2)));
    }

    @Test
    void testCommon() {
        List<String> list1 = List.of("apple", "banana", "cherry");
        List<String> list2 = List.of("banana", "date", "fig", "cherry");
        List<String> expected = List.of("banana", "cherry");
        assertEquals(expected, Discussion02.common(list1, list2));  
    }

    @Test
    void testCapitalize() {
        List<String> list = new ArrayList<>(List.of("apple", "banana", "cherry"));
        Discussion02.capitalize(list);
        List<String> expected = List.of("APPLE", "BANANA", "CHERRY");
        assertEquals(expected, list);
    }

    @Test
    void testBuildLessThanMap() {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5);
        Map<Integer, List<Integer>> expected = new HashMap<>();
        expected.put(3, List.of(1));
        expected.put(1, List.of());
        expected.put(4, List.of(3, 1));
        expected.put(5, List.of(3, 1, 4));
        assertEquals(expected, Discussion02.buildLessThanMap(numbers));
    }

    @Test 
    void testFilterPositive() {
        List<Integer> numbers = List.of(-3, 1, -4, 1, 5);
        int[] expected = new int[] {1, 1, 5};
        int[] filtered = Discussion02.filterPositive(numbers);
        assertArrayEquals(expected, filtered);
    }

}
