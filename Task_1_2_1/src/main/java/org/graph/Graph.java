package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Graph wrapper.
 *
 * @param <T> Type of graph node, specified by user.
 */
public class Graph<T> implements IGraph<T> {

    protected AbstractIntGraph abstractGraph;

    /**
     * Pair of maps.
     */
    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();

    /**
     * Updates map and call addVertex from concreate graph implementation.
     *
     * @param v Vertex to add.
     * @return Added vertex.
     */
    @Override
    public T addVertex(T v) {
        maps.insert(v);
        abstractGraph.addVertex(maps.gettIntHashMap().get(v));
        return v;
    }

    /**
     * Updates map and call removeVertex from concreate graph implementation.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    @Override
    public T removeVertex(T v) {
        abstractGraph.removeVertex(maps.gettIntHashMap().get(v));
        maps.remove(v);
        return v;
    }

    /**
     * Calls addEdge from concreate graph implementation with proper arguments.
     *
     * @param src    Source vertex.
     * @param dst    Destination vertex.
     * @param weight Weight.
     */
    @Override
    public void addEdge(T src, T dst, double weight) {
        abstractGraph.addEdge(maps.gettIntHashMap().get(src), maps.gettIntHashMap().get(dst),
            weight);
    }

    /**
     * Calls removeEdge from concreate graph implementation with proper arguments.
     *
     * @param src Source vertex.
     * @param dst Destination vertex.
     */
    @Override
    public void removeEdge(T src, T dst) {
        abstractGraph.removeEdge(maps.gettIntHashMap().get(src), maps.gettIntHashMap().get(dst));
    }

    /**
     * Get adjacent vertices to provided.
     *
     * @param v Provided vertex.
     * @return List of adjacent vertices.
     */
    @Override
    public List<T> getAdjacent(T v) {
        var ints = abstractGraph.getAdjacent(maps.gettIntHashMap().get(v));
        return ints.stream()
            .map(i -> maps.getIntTHashMap().get(i))
            .toList();
    }

    /**
     * Either vertices are adjacents or not.
     *
     * @param v Vertex #1.
     * @param u Vertex #2.
     * @return Adjacent or not.
     */
    public boolean isAdjacent(T v, T u) {
        return abstractGraph.isAdjacent(maps.gettIntHashMap().get(v), maps.gettIntHashMap().get(u));
    }

    /**
     * Deep-first traverse.
     *
     * @param vertex   Vertex to visit.
     * @param sortList List with vertices.
     * @param visited  Hashmap of visited verices.
     */
    public void DFS(T vertex, HashMap<T, Boolean> visited, List<T> sortList) {
        var intV = maps.gettIntHashMap().get(vertex);
        visited.put(vertex, true);
        for (var u : abstractGraph.getAdjacent(intV)) {
            var tU = maps.getIntTHashMap().get(u);
            if (!visited.get(tU)) {
                DFS(tU, visited, sortList);
            }
        }
        sortList.add(vertex);
    }

    /**
     * Topological sort.
     *
     * @return List that contains topological order of vertices.
     */
    public List<T> toposort() {
        HashMap<T, Boolean> visited = new HashMap<>();
        List<T> topoSorted = new ArrayList<>();
        for (var v : maps.gettIntHashMap().keySet()) {
            visited.put(v, false);
        }

        for (var v : maps.gettIntHashMap().keySet()) {
            if (!visited.get(v)) {
                DFS(v, visited, topoSorted);
            }
        }
        Collections.reverse(topoSorted);
        return topoSorted;
    }

    /**
     * Get number of vertices.
     *
     * @return Current number of vertices.
     */
    public int getVerticesN() {
        return abstractGraph.getVerticesN();
    }

    /**
     * Method for graph deserialization. Format: v1 v2 v3 ... vn | w(v1->v2) w(v1->v3) ... w(v1->wn)
     * where w is weight (lines are separated by \n) Example: 1 2 3|10 11 2 5 6|9 77 Means that
     * there are 2 edges on the first line: 1 -> 2 with weight 10 and 1 -> 3 with weight 11 2 -> 5
     * with weight 9 and 2 -> 6 with weight 77 on the second.
     */
    public static Graph<Integer> deserializeIntGraph(String str,
        GraphRepresentation representation) {
        ArrayList<Pair<Integer, Pair<Integer, Double>>> edges = new ArrayList<>();
        Set<Integer> vertices = new HashSet<>();
        String[] lines = str.split("\n");
        for (var line : lines) {
            var es = line.split("\\|")[0];
            var weights = line.split("\\|")[1];
            ArrayList<Integer> vs = new ArrayList<>(
                Arrays.stream(es.split(" ")).map(Integer::parseInt).toList());
            vertices.addAll(vs);
            ArrayList<Double> ws = new ArrayList<>(
                Arrays.stream(weights.split(" ")).map(Double::parseDouble).toList());
            for (int i = 1; i < vs.size(); i++) {
                edges.add(new Pair<>(vs.get(0), new Pair<>(vs.get(i), ws.get(i - 1))));
            }
        }
        return new Graph<>(vertices.stream().toList(), edges, representation);
    }

    /**
     * Default constructor (every T from provided T list is mapping on natural numbers (from 0 to n
     * - 1)).
     *
     * @param vList          Provided vertices.
     * @param representation The way how graph is represented.
     */
    public Graph(List<T> vList, List<Pair<Integer, Pair<Integer, Double>>> eList,
        GraphRepresentation representation) {
        for (T t : vList) {
            this.maps.insert(t);
        }
        List<Integer> intV = vList.stream()
            .map(t -> maps.gettIntHashMap().get(t))
            .toList();
        List<Pair<Integer, Pair<Integer, Double>>> intE = eList.stream()
            .map(p -> new Pair<>(maps.gettIntHashMap().get(p.first),
                new Pair<>(maps.gettIntHashMap().get(p.second.first), p.second.second)))
            .toList();
        switch (representation) {
            case ADJ_MATRIX: {
                this.abstractGraph = new AdjMatrixIntGraph(intV.size(), eList);
                break;
            }
            case ADJ_LIST: {
                this.abstractGraph = new AdjListIntGraph(intV, intE);
                break;
            }
            case INC_MATRIX: {
                this.abstractGraph = new IncMatrixIntGraph(intV.size(), 0);
                break;
            }
        }
    }

    public Graph(List<T> vList, GraphRepresentation representation) {
        new Graph<T>(vList, new ArrayList<>(), representation);
    }
}
