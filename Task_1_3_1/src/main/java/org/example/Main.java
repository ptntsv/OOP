package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 */
public class Main {

    /**
     * Function for finding all incoming of substring into string that read from file. If file
     * couldn't open then IOException will be thrown.
     *
     * @param filePath Path of file that contains text.
     * @param subStr   Given substring.
     * @return List of positions (indices) of all occurrences of the substring in text.
     */
    public static List<Integer> find(String filePath, String subStr) throws IOException {
        Matcher kmpMatcher = new Matcher(subStr);
        var ans = new ArrayList<Integer>();
        try (var ir = new InputStreamReader(
            new FileInputStream(filePath), StandardCharsets.UTF_8)) {
            int totalIndex = 0;
            int ch;
            while ((ch = ir.read()) != -1) {
                if (kmpMatcher.match((char) ch)) {
                    ans.add(totalIndex - (subStr.length() - 1));
                }
                totalIndex++;
            }
        } catch (IOException e) {
            throw e;
        }
        return ans;
    }

    public static void main(String[] args) {
    }
}