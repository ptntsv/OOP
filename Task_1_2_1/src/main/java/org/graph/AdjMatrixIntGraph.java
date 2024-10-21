package org.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjMatrixIntGraph extends AbstractIntGraph {

    protected double[][] adjMatrix;
    protected int n;

    /**
     * Method to insert a vertex into graph. In order to add a vertex into adjacent matrix, it
     * (matrix) should be expanded on 1 column and 1 row.
     *
     * @param v Vertex to insert.
     * @return Inserted vertex.
     */
    @Override
    public Integer addVertex(Integer v) {
        var newMatrix = new double[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, n);
            newMatrix[i][n] = Double.POSITIVE_INFINITY;
        }
        for (int i = 0; i <= n; i++) {
            newMatrix[n][i] = (i == n) ? 0 : Double.POSITIVE_INFINITY;
        }
        n++;
        adjMatrix = newMatrix;
        return v;
    }

    /**
     * Method to remove vertex from graph. Corresponding row and column, that mapped on vertex to
     * remove is filling with NaNs.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    @Override
    public Integer removeVertex(Integer v) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == v || j == v) {
                    adjMatrix[i][j] = Double.NaN;
                }
            }
        }
        return v;
    }

    /**
     * Method to add new edge.
     * @param src Source vertex.
     * @param dst Destination vertex.
     * @param weight Weight.
     */
    @Override
    public void addEdge(Integer src, Integer dst, double weight) {
        if (dst >= n || src >= n || Double.isNaN(adjMatrix[dst][src])) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        adjMatrix[src][dst] = weight;
    }

    /**
     * Method to remove edge.
     * @param src Source vertex.
     * @param dst Destination vertex.
     */
    @Override
    public void removeEdge(Integer src, Integer dst) {
        if (dst >= n || src >= n || Double.isNaN(adjMatrix[src][dst])) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        adjMatrix[src][dst] = Double.POSITIVE_INFINITY;
    }

    /**
     * Method to get adjacent vertices.
     * @param v Provided vertex.
     * @return List of adjacent vertices.
     */
    @Override
    public List<Integer> getAdjacent(Integer v) {
        if (v >= n || Double.isNaN(adjMatrix[v][v])) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        List<Integer> vs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isAdjacent(v, i)) {
                vs.add(i);
            }
        }
        return vs;
    }

    public AdjMatrixIntGraph(int n) {
        this.n = n;
        adjMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
            }
        }
    }

    public boolean isAdjacent(Integer v, Integer u) {
        return !Double.isNaN(adjMatrix[v][u]) && adjMatrix[v][u] != Double.POSITIVE_INFINITY;
    }
}
