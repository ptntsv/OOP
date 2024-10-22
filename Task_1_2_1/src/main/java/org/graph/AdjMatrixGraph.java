package org.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Adjacency matrix graph implementation.
 */
public class AdjMatrixGraph<T> extends AbstractGraph<T> {

    /**
     * Adjacency matrix.
     */
    protected Matrix adjMatrix;

    /**
     * Method to insert a vertex into graph. In order to add a vertex into adjacent matrix, it
     * (matrix) should be expanded on 1 column and 1 row.
     *
     * @param v Vertex to insert.
     * @return Inserted vertex.
     */
    @Override
    public T addVertex(T v) {
        adjMatrix.extend(1, 1);
        maps.insert(v);
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
    public T removeVertex(T v) {
        int vKey = maps.gettIntHashMap().get(v);
        try {
            for (int i = 0; i < adjMatrix.rows; i++) {
                for (int j = 0; j < adjMatrix.columns; j++) {
                    if (i == vKey || j == vKey) {
                        adjMatrix.set(i, j, Double.NaN);
                    }
                }
            }
            maps.remove(v);
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(vKey);
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
    public void addEdge(T src, T dst, double weight) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            adjMatrix.set(srcKey, dstKey, weight);
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    /**
     * Method to remove edge.
     *
     * @param src Source vertex.
     * @param dst Destination vertex.
     */
    @Override
    public void removeEdge(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            adjMatrix.set(srcKey, dstKey, Double.POSITIVE_INFINITY);
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    /**
     * Method to get adjacent vertices.
     *
     * @param v Provided vertex.
     * @return List of adjacent vertices.
     */
    @Override
    public List<T> getAdjacent(T v) {
        List<T> vs = new ArrayList<>();
        for (int i = 0; i < adjMatrix.columns; i++) {
            T iKey = maps.getIntTHashMap().get(i);
            if (isAdjacent(v, iKey)) {
                vs.add(iKey);
            }
        }
        return vs;
    }

    public AdjMatrixGraph(int capacity) {
        adjMatrix = new Matrix(capacity, capacity, Double.POSITIVE_INFINITY);
    }

    public AdjMatrixGraph(int capacity, ArrayList<Pair<T, Pair<T, Double>>> edges) {
        this(capacity);
        for (var e : edges) {
            if (!maps.gettIntHashMap().containsKey(e.first)) {
                addVertex(e.first);
            }
            if (!maps.gettIntHashMap().containsKey(e.second.first)) {
                addVertex(e.second.first);
            }
        }
        for (var e : edges) {
            addEdge(e.first, e.second.first, e.second.second);
        }
    }

    public boolean isAdjacent(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            return !Double.isNaN(adjMatrix.get(srcKey, dstKey))
                   && adjMatrix.get(srcKey, dstKey) != Double.POSITIVE_INFINITY;
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    @Override
    public int getVerticesN() {
        return adjMatrix.rows;
    }

    @Override
    public List<T> getVertices() {
        return IntStream.rangeClosed(0, adjMatrix.rows - 1).boxed().toList().stream()
            .map(i -> maps.getIntTHashMap().get(i)).toList();
    }

    @Override
    public void reset() {
        Matrix.fill(adjMatrix.matrix, adjMatrix.rowsCapacity, adjMatrix.columnsCapacity,
            adjMatrix.defVal);
    }
}
