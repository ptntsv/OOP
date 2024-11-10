import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for find method.
 */
public class FindTest {

    @Test
    void singletonTest0() {
        List<Integer> ans = Main.find("src/test/resources/input1.txt", "a", '#');
        assert ans != null;
        Assertions.assertIterableEquals(Collections.singleton(0), ans);
    }

    @Test
    void singletonTest1() {
        List<Integer> ans = Main.find("src/test/resources/input2.txt", "a", '#');
        assert ans != null;
        Assertions.assertEquals(0, ans.size());
    }

    @Test
    void commonTest0() {
        List<Integer> ans = Main.find("src/test/resources/input3.txt", "abc", '#');
        assert ans != null;
        Assertions.assertIterableEquals(Arrays.asList(3, 6, 12, 24), ans);
    }

    @Test
    void commonTest1() {
        List<Integer> ans = Main.find("src/test/resources/input4.txt", "abab", '#');
        assert ans != null;
        Assertions.assertIterableEquals(Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20), ans);
    }

    @Test
    void commonTest2() {
        List<Integer> ans = Main.find("src/test/resources/input5.txt", "peat", '#');
        assert ans != null;
        Assertions.assertIterableEquals(Arrays.asList(2, 9, 16, 23, 30), ans);
    }

    @Test
    void genTest0() {
        String path = "src/test/resources/genTest0.txt";
        try (Writer writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            for (int i = 0; i < 1000; i++) {
                writer.append("abcd");
            }
            writer.append("efgh");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Integer> ans = Main.find(path, "efgh", '#');
        assert ans != null;
        Assertions.assertIterableEquals(Collections.singleton(4000), ans);
    }

    @Test
    void genTest1() {
        String path = "src/test/resources/genTest1.txt";
        try (Writer writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            for (int i = 0; i < 333; i++) {
                writer.append("123");
            }
            writer.append("1234");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Integer> ans = Main.find(path, "231", '#');
        assert ans != null;
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 999; i += 3) {
            expected.add(i);
        }
        Assertions.assertIterableEquals(expected, ans);
    }
}
