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
     * Method to insert a vertex into graph.
     *
     * @param v Vertex to insert.
     * @return Inserted vertex.
     */
    @Override
    public T addVertex(T v) {
        int nextMapIndex = getNextMapIndex();
        adjMatrix.ensureCapacity(nextMapIndex, nextMapIndex);
        maps.insert(v, nextMapIndex);
        return v;
    }

    /**
     * Method to remove vertex from graph.
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
     * @param src Source vertex.
     * @param dst Destination vertex.
     */
    @Override
    public void addEdge(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            adjMatrix.set(srcKey, dstKey, 1);
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
            adjMatrix.set(srcKey, dstKey, 0);
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
        adjMatrix = new Matrix(capacity, capacity, 0);
    }

    public AdjMatrixGraph(int capacity, List<Pair<T, T>> edges) {
        this(capacity);
        for (var e : edges) {
            if (!maps.gettIntHashMap().containsKey(e.first)) {
                addVertex(e.first);
            }
            if (!maps.gettIntHashMap().containsKey(e.second)) {
                addVertex(e.second);
            }
        }
        for (var e : edges) {
            addEdge(e.first, e.second);
        }
    }

    public boolean isAdjacent(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            return adjMatrix.get(srcKey, dstKey) == 1;
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    @Override
    public int getVerticesN() {
        return maps.gettIntHashMap().size();
    }

    @Override
    public List<T> getVertices() {
        return maps.gettIntHashMap().keySet().stream().toList();
    }
}
