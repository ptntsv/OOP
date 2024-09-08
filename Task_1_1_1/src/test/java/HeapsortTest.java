import java.util.Arrays;
import java.util.Random;
import org.example.Heap;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeapsortTest {

    private static Random rand;
    private static Heap testHeap;

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

    @BeforeAll
    public static void setup() {
        rand = new Random();
    }

    @BeforeEach
    public void generate() {
        int[] data = new int[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt();
        }
        testHeap = new Heap(data);
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
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = rand.nextInt();
        }
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testHeapify() {
        Assertions.assertTrue(isHeap(testHeap));
    }

    @Test
    public void testExtractMin() {
        while (!testHeap.isEmpty()) {
            int min = Arrays.stream(testHeap.getHeap()).min().getAsInt();
            Assertions.assertEquals(testHeap.extractMin(), min);
            Assertions.assertTrue(isHeap(testHeap));
        }
    }
}
