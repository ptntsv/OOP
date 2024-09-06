import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Heapsort tests
 */
public class HeapsortTest {

    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testEmpty() {
        int arr[] = new int[]{};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSingleton() {
        int arr[] = new int[]{5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSorted() {
        int arr[] = new int[]{1, 2, 3, 4, 5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testReverseSorted() {
        int arr[] = new int[]{5, 4, 3, 2, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testSame() {
        int arr[] = new int[]{1, 1, 1, 1, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testAlreadySorted1() {
        int arr[] = new int[]{2, 3, 4, 5, 6, 1};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testAlreadySorted2() {
        int arr[] = new int[]{9, 1, 2, 3, 4, 5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testNegatives() {
        int arr[] = new int[]{-100, -120, -1, -2, -3, -4, -5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testNegativesAndPositives() {
        int arr[] = new int[]{-100, -120, -1, -2, -3, -4, -5};
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }

    @Test
    public void testRandomized() {
        Random rand = new Random();
        int arr[] = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = rand.nextInt();
        }
        Main.heapsort(arr);
        Assertions.assertTrue(isSorted(arr));
    }
}
