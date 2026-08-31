import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Discussion03Test {
    
    public static Discussion03.IntList resource() {
        return new Discussion03.IntList(9, 
            new Discussion03.IntList(8, 
                new Discussion03.IntList(7, 
                    new Discussion03.IntList(6, 
                        new Discussion03.IntList(5, 
                            new Discussion03.IntList(4, 
                                new Discussion03.IntList(3, 
                                    new Discussion03.IntList(2, 
                                        new Discussion03.IntList(1, null)
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    @Test
    public void testReverse() {
        Discussion03.IntList resource = resource();
        resource = Discussion03.IntList.reverse(resource);
        assertEquals(9, resource.get(resource.size() - 1));
        assertEquals(8, resource.get(resource.size() - 2));
        assertEquals(7, resource.get(resource.size() - 3));
        assertEquals(6, resource.get(resource.size() - 4));
        assertEquals(5, resource.get(resource.size() - 5));
        assertEquals(4, resource.get(resource.size() - 6));
        assertEquals(3, resource.get(resource.size() - 7));
        assertEquals(2, resource.get(resource.size() - 8));
        assertEquals(1, resource.get(resource.size() - 9));
    }


    @Test
    public void testInterweave() {
        Discussion03.IntList resource = resource();
        Discussion03.IntList[] listInterweave = Discussion03.IntList.interweave(resource, 3);
        assertEquals(9, listInterweave[0].getFirst());
        assertEquals(8, listInterweave[1].getFirst());
        assertEquals(7, listInterweave[2].getFirst());
        assertEquals(6, listInterweave[0].getRest().getFirst());
        assertEquals(5, listInterweave[1].getRest().getFirst());
        assertEquals(4, listInterweave[2].getRest().getFirst());
        assertEquals(3, listInterweave[0].getRest().getRest().getFirst());
        assertEquals(2, listInterweave[1].getRest().getRest().getFirst());
        assertEquals(1, listInterweave[2].getRest().getRest().getFirst());
    }

    @Test
    public void testSkippify() {
        Discussion03.IntList resource = resource();
        resource.skippify();
        assertEquals(9, resource.getFirst());
        assertEquals(7, resource.getRest().getFirst());
        assertEquals(4, resource.getRest().getRest().getFirst());
    }
}
