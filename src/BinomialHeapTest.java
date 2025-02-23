import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class BinomialHeapTest {
    @Test
    public void testExtractMin(){
        BinomialHeap heap = new BinomialHeap();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        Integer minimum = heap.extractMin();
        assertEquals(Integer.valueOf(1), minimum);
    }

}
