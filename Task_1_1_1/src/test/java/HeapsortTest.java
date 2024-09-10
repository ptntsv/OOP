import java.util.Arrays;
import java.util.Random;
import org.example.Heap;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapsortTest {

    /**
     * Size of randomly generated array.
     */
    private static final int testArrSize = 1000;

    /**
     * Method checks whether array is binary heap or not.
     *
     * @return Does an array a heap or not
     */
    private boolean isHeap(Heap h) {
        for (int i = 0; i < h.getHeapLen(); i++) {
            if (!h.checkInvariant(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that generates array with randomized numbers for testing purposes.
     *
     * @return Array that contains random numbers.
     */
    private int[] generateRandomArr() {
        Random rand = new Random();
        int[] data = new int[testArrSize];
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt();
        }
        return data;
    }

    /**
     * Method that generates heap with randomized array for testing purposes.
     *
     * @return Heap that contains randomized array.
     */
    public Heap generateHeap() {
        int[] data = generateRandomArr();
        return new Heap(data);
    }


    @Test
    public void testEmpty() {
        int[] arr = new int[]{};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSingleton() {
        int[] arr = new int[]{5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSorted() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testReverseSorted() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSame() {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testAlreadySorted1() {
        int[] arr = new int[]{2, 3, 4, 5, 6, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testAlreadySorted2() {
        int[] arr = new int[]{9, 1, 2, 3, 4, 5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testNegatives() {
        int[] arr = new int[]{-100, -120, -1, -2, -3, -4, -5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testNegativesAndPositives() {
        int[] arr = new int[]{-100, -120, -1, -2, -3, -4, -5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testRandomized() {
        int[] arr = generateRandomArr();
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testHeapify() {
        Assertions.assertTrue(isHeap(generateHeap()));
    }

    @Test
    public void testExtractMin() {
        Heap h = generateHeap();
        while (!h.isEmpty()) {
            int min = Arrays.stream(h.getHeap()).min().getAsInt();
            Assertions.assertEquals(h.extractMin(), min);
            Assertions.assertTrue(isHeap(h));
        }
    }
}
