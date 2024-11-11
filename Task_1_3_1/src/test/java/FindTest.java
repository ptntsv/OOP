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
    void singletonTest0() throws IOException {
        List<Integer> ans = Main.find("src/test/resources/input1.txt", "a");
        Assertions.assertNotNull(ans);
        Assertions.assertIterableEquals(Collections.singleton(0), ans);
    }

    @Test
    void singletonTest1() throws IOException {
        List<Integer> ans = Main.find("src/test/resources/input2.txt", "a");
        Assertions.assertNotNull(ans);
        Assertions.assertEquals(0, ans.size());
    }

    @Test
    void commonTest0() throws IOException {
        List<Integer> ans = Main.find("src/test/resources/input3.txt", "abc");
        Assertions.assertNotNull(ans);
        Assertions.assertIterableEquals(Arrays.asList(3, 6, 12, 24), ans);
    }

    @Test
    void commonTest1() throws IOException {
        List<Integer> ans = Main.find("src/test/resources/input4.txt", "abab");
        Assertions.assertNotNull(ans);
        Assertions.assertIterableEquals(Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20), ans);
    }

    @Test
    void commonTest2() throws IOException {
        List<Integer> ans = Main.find("src/test/resources/input5.txt", "peat");
        Assertions.assertNotNull(ans);
        Assertions.assertIterableEquals(Arrays.asList(2, 9, 16, 23, 30), ans);
    }

    @Test
    void genTest0() throws IOException {
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
        List<Integer> ans = Main.find(path, "efgh");
        Assertions.assertNotNull(ans);
        Assertions.assertIterableEquals(Collections.singleton(4000), ans);
    }

    @Test
    void genTest1() throws IOException {
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
        List<Integer> ans = Main.find(path, "231");
        Assertions.assertNotNull(ans);
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 999; i += 3) {
            expected.add(i);
        }
        Assertions.assertIterableEquals(expected, ans);
    }

    @Test
    void genTest2() throws IOException {
        String path = "src/test/resources/1gb.txt";
        try (Writer writer = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            for (int i = 0; i < Math.pow(2, 30); i++) {
                writer.append("1");
                if (i == 123123) {
                    writer.append("2");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Integer> ans = Main.find(path, "12");
        Assertions.assertIterableEquals(Collections.singleton(123123), ans);
    }
}
