package org.graph;

import java.util.List;

public class IncMatrixIntGraph extends AbstractIntGraph {

    @Override
    public Integer addVertex(Integer v) {
        return 0;
    }

    @Override
    public Integer removeVertex(Integer v) {
        return 0;
    }

    @Override
    public void addEdge(Integer dst, Integer src, double weight) {

    }

    @Override
    public void removeEdge(Integer dst, Integer src) {

    }

    @Override
    public List<Integer> getAdjacent(Integer v) {
        return List.of();
    }

    @Override
    public boolean isAdjacent(Integer v, Integer u) {
        return false;
    }
}
