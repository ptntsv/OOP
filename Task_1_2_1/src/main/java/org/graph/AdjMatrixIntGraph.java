package org.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Adjacency matrix graph implementation.
 */
public class AdjMatrixIntGraph extends AbstractIntGraph {

    /**
     * Adjacency matrix. adjMatrix[i][j] means weight from i-th to j-th vertex. There are 4
     * posibilities: POSITIVE_INFINITY (weight is unknow), 0 (weight from i-th to i-th vertex), NaN
     * (if vertex is removed), any other value - valid weight.
     */
    protected double[][] adjMatrix;
    /**
     * Number of vertices.
     */
    protected int nvertices;

    /**
     * Method to insert a vertex into graph. In order to add a vertex into adjacent matrix, it
     * (matrix) should be expanded on 1 column and 1 row.
     *
     * @param v Vertex to insert.
     * @return Inserted vertex.
     */
    @Override
    public Integer addVertex(Integer v) {
        var newMatrix = new double[nvertices + 1][nvertices + 1];
        for (int i = 0; i < nvertices; i++) {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, nvertices);
            newMatrix[i][nvertices] = Double.POSITIVE_INFINITY;
        }
        for (int i = 0; i <= nvertices; i++) {
            newMatrix[nvertices][i] = (i == nvertices) ? 0 : Double.POSITIVE_INFINITY;
        }
        nvertices++;
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
        if (v >= nvertices) {
            throw new NoSuchVertexException("Invalid vertex is provided into removeVertex");
        }
        for (int i = 0; i < nvertices; i++) {
            for (int j = 0; j < nvertices; j++) {
                if (i == v || j == v) {
                    adjMatrix[i][j] = Double.NaN;
                }
            }
        }
        return v;
    }

    /**
     * Method to add new edge.
     *
     * @param src    Source vertex.
     * @param dst    Destination vertex.
     * @param weight Weight.
     */
    @Override
    public void addEdge(Integer src, Integer dst, double weight) {
        if (dst >= nvertices || src >= nvertices || Double.isNaN(adjMatrix[dst][src])) {
            throw new NoSuchVertexException("Invalid vertices are provided into addEdge");
        }
        adjMatrix[src][dst] = weight;
    }

    /**
     * Method to remove edge.
     *
     * @param src Source vertex.
     * @param dst Destination vertex.
     */
    @Override
    public void removeEdge(Integer src, Integer dst) {
        if (dst >= nvertices || src >= nvertices || Double.isNaN(adjMatrix[src][dst])) {
            throw new NoSuchVertexException("Invalid vertices are provided into removeEdge");
        }
        adjMatrix[src][dst] = Double.POSITIVE_INFINITY;
    }

    /**
     * Method to get adjacent vertices.
     *
     * @param v Provided vertex.
     * @return List of adjacent vertices.
     */
    @Override
    public List<Integer> getAdjacent(Integer v) {
        if (v >= nvertices || Double.isNaN(adjMatrix[v][v])) {
            throw new NoSuchVertexException("Invalid vertex is provided into getAdjacent");
        }
        List<Integer> vs = new ArrayList<>();
        for (int i = 0; i < nvertices; i++) {
            if (isAdjacent(v, i)) {
                vs.add(i);
            }
        }
        return vs;
    }

    /**
     * Constructor.
     * @param n Number of nodes.
     */
    public AdjMatrixIntGraph(int n) {
        this.nvertices = n;
        adjMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
            }
        }
    }

    public AdjMatrixIntGraph(int n, List<Pair<Integer, Pair<Integer, Double>>> edges) {
        this.nvertices = n;
        adjMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
            }
        }
        for (var e : edges) {
            if (e.first >= nvertices || e.second.first > +nvertices) {
                throw new NoSuchVertexException(
                    "Invalid vertex is provided into AdjMatrixIntGraph");
            }
            adjMatrix[e.first][e.second.first] = e.second.second;
        }
    }

    public boolean isAdjacent(Integer v, Integer u) {
        return !Double.isNaN(adjMatrix[v][u]) && adjMatrix[v][u] != Double.POSITIVE_INFINITY;
    }

    @Override
    public int getVerticesN() {
        return this.nvertices;
    }
}
