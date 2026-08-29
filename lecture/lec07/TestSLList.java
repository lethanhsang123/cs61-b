import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestSLList {


    @Test
    void testListInteger() {
        SLList<Integer> integerList = new SLList<>();
        integerList.addFirst(12);
        integerList.addLast(22);

        assertEquals(12, integerList.getFirst());
    }

    @Test 
    void testObjectSLList() {
        record Student(int age, String name) {}
        SLList<Student> students = new SLList<>(new Student(11, "A"));
        students.addFirst(new Student(9, "B"));
        students.addFirst(new Student(12, "D"));
        assertEquals(students.getFirst(), new Student(12, "D"));
    }

}
