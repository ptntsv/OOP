package org.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Abstract class that represents integer graph.
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();
    private int nextMapIndex = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var v : getVertices()) {
            sb.append(v);
            for (var adj : getAdjacent(v)) {
                sb.append(" ").append(adj);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    protected int getNextMapIndex() {
        return nextMapIndex++;
    }

    public AbstractGraph() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Graph<?> ag) {
            try {
                var another = (Graph<T>) ag;
                if (another.getVertices().size() != this.getVertices().size()) {
                    return false;
                }
                for (var v : another.getVertices()) {
                    Set<T> anotherVertices = new HashSet<>(another.getAdjacent(v));
                    Set<T> thisVertices = new HashSet<>(getAdjacent(v));
                    if (!thisVertices.equals(anotherVertices)) {
                        return false;
                    }
                }
                return true;
            } catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }

    /**
     * Deserialize list of edges from input string.
     *
     * @param str Source string.
     * @return List of edges to build graph of.
     */
    private static List<Pair<Integer, Integer>> deserializeFromString(String str) {
        ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();
        String[] lines = str.split("\n");
        for (var line : lines) {
            var es = line.split("\\|")[0];
            ArrayList<Integer> vs = new ArrayList<>(
                Arrays.stream(es.split(" ")).map(Integer::parseInt).toList());
            for (int i = 1; i < vs.size(); i++) {
                edges.add(new Pair<>(vs.get(0), vs.get(i)));
            }
        }
        return edges;
    }

    /**
     * Deserialize list of edges from file.
     *
     * @param fileName Filename.
     * @return List of deserialized edges.
     */
    public static List<Pair<Integer, Integer>> deserializeFromFile(String fileName) {
        File input = new File(fileName);
        StringBuilder inputSb = new StringBuilder();
        try {
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                inputSb.append(reader.nextLine());
                inputSb.append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fileName + " is not found");
        }
        return AbstractGraph.deserializeFromString(inputSb.toString());
    }
}
