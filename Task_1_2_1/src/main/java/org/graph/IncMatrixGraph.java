package org.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Incidence matrix graph implementation.
 */
public class IncMatrixGraph<T> extends AbstractGraph<T> {

    /**
     * Incidence matrix.
     */
    protected Matrix incMatrix;

    @Override
    public T addVertex(T v) {
        incMatrix.extend(1, 0);
        maps.insert(v);
        return v;
    }

    /**
     * Method to remove vertex from incidence matrix. this.incMatrix[v][i] = -2, where i in [0,
     * nedges). In other words, incMatrix[v][i] == -2 if and only if v is removed vertex.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    @Override
    public T removeVertex(T v) {
        int vKey = maps.gettIntHashMap().get(v);
        try {
            for (int i = 0; i < incMatrix.columns; i++) {
                incMatrix.set(vKey, i, -2);
            }
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(vKey);
        }
        maps.remove(v);
        return v;
    }

    @Override
    public void addEdge(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        incMatrix.extend(0, 1);
        try {
            incMatrix.set(srcKey, incMatrix.columns - 1, 1);
            incMatrix.set(dstKey, incMatrix.columns - 1, -1);
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    @Override
    public void removeEdge(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            for (int i = 0; i < incMatrix.columns; i++) {
                if (isAdjacent(src, dst)) {
                    incMatrix.set(srcKey, i, 0);
                    incMatrix.set(dstKey, i, 0);
                }
            }
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    @Override
    public List<T> getAdjacent(T v) {
        List<T> vs = new ArrayList<>();
        for (int i = 0; i < incMatrix.columns; i++) {
            for (int j = 0; j < incMatrix.rows; j++) {
                T jKey = maps.getIntTHashMap().get(j);
                if (isAdjacent(v, jKey)) {
                    vs.add(jKey);
                }
            }
        }
        return vs;
    }

    @Override
    public boolean isAdjacent(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        try {
            for (int i = 0; i < incMatrix.columns; i++) {
                if (incMatrix.get(srcKey, i) == 1 && incMatrix.get(dstKey, i) == -1) {
                    return true;
                }
            }
            return false;
        } catch (MatrixOutOfBoundsException e) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
    }

    @Override
    public int getVerticesN() {
        return incMatrix.rows;
    }

    @Override
    public List<T> getVertices() {
        return IntStream.rangeClosed(0, incMatrix.rows - 1).boxed().toList().stream()
            .map(i -> maps.getIntTHashMap().get(i)).toList();
    }

    public IncMatrixGraph(int nvertices, int nedges) {
        incMatrix = new Matrix(nvertices, nedges, 0.0);
    }

    /**
     * Constructor.
     *
     * @param nvertices Number of vertices.
     * @param nedges    Number of edges.
     */
    public IncMatrixGraph(
        int nvertices,
        int nedges,
        List<Pair<T, T>> edges) {
        this(nvertices, nedges);
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
}
