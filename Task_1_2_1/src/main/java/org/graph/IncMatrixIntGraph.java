package org.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Incidence matrix graph implementation.
 */
public class IncMatrixIntGraph extends AbstractIntGraph {

    /**
     * Incidence matrix.
     */
    protected int[][] incMatrix;
    /**
     * Number of vertices.
     */
    protected int nvertices;
    /**
     * Number of edges.
     */
    protected int nedges;

    @Override
    public Integer addVertex(Integer v) {
        int[][] newMatrix = new int[nvertices + 1][nedges];
        for (int i = 0; i < nvertices; i++) {
            System.arraycopy(incMatrix[i], 0, newMatrix[i], 0, nedges);
        }
        nvertices++;
        incMatrix = newMatrix;
        return v;
    }

    /**
     * Method to remove vertex from incidence matrix. this.incMatrix[v][i] = -2, where 0 <= i <
     * this.nedges. In other words, incMatrix[v][i] == -2 if and only if v is removed vertex.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    @Override
    public Integer removeVertex(Integer v) {
        if (v >= nvertices) {
            throw new NoSuchVertexException("Invalid vertex is provided into removeVertex");
        }
        for (int i = 0; i < nedges; i++) {
            incMatrix[v][i] = -2;
        }
        return v;
    }

    @Override
    public void addEdge(Integer src, Integer dst, double weight) {
        var newMatrix = new int[nvertices][nedges + 1];
        for (int i = 0; i < nvertices; i++) {
            System.arraycopy(incMatrix[i], 0, newMatrix[i], 0, nedges);
        }
        newMatrix[src][nedges] = 1;
        newMatrix[dst][nedges] = -1;
        nedges++;
        incMatrix = newMatrix;
    }

    @Override
    public void removeEdge(Integer src, Integer dst) {
        for (int i = 0; i < nedges; i++) {
            if (incMatrix[src][i] == 1 && incMatrix[dst][i] == -1) {
                incMatrix[src][i] = 0;
                incMatrix[dst][i] = 0;
            }
        }
    }

    @Override
    public List<Integer> getAdjacent(Integer v) {
        List<Integer> vs = new ArrayList<>();
        for (int i = 0; i < nedges; i++) {
            if (incMatrix[v][i] == 1) {
                for (int j = 0; j < nvertices; j++) {
                    if (incMatrix[j][i] == -1) {
                        vs.add(j);
                    }
                }
            }
        }
        return vs;
    }

    @Override
    public boolean isAdjacent(Integer v, Integer u) {
        for (int i = 0; i < nedges; i++) {
            if (incMatrix[v][i] == 1 && incMatrix[u][i] == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVerticesN() {
        return this.nvertices;
    }

    public IncMatrixIntGraph(int nvertices, int nedges) {
        this.nvertices = nvertices;
        this.nedges = nedges;
        incMatrix = new int[nvertices][nedges];
    }
}
