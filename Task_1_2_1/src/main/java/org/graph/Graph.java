package org.graph;

import java.util.List;

public class Graph<T> implements IGraph<T> {

    protected AbstractIntGraph abstractGraph;

    /**
     * Pair of maps.
     */
    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();

    @Override
    public T addVertex(T v) {
        maps.insert(v);
        abstractGraph.addVertex(maps.gettIntHashMap().get(v));
        return v;
    }

    @Override
    public T removeVertex(T v) {
        abstractGraph.removeVertex(maps.gettIntHashMap().get(v));
        maps.remove(v);
        return v;
    }

    @Override
    public void addEdge(T src, T dst, double weight) {
        abstractGraph.addEdge(maps.gettIntHashMap().get(src), maps.gettIntHashMap().get(dst),
            weight);
    }

    @Override
    public void removeEdge(T src, T dst) {
        abstractGraph.removeEdge(maps.gettIntHashMap().get(src), maps.gettIntHashMap().get(dst));
    }

    @Override
    public List<T> getAdjacent(T v) {
        var ints = abstractGraph.getAdjacent(maps.gettIntHashMap().get(v));
        return ints.stream()
            .map(i -> maps.getIntTHashMap().get(i))
            .toList();
    }

    public boolean isAdjacent(T v, T u) {
        return abstractGraph.isAdjacent(maps.gettIntHashMap().get(v), maps.gettIntHashMap().get(u));
    }

    /**
     * Default constructor (every T from provided T list is mapping on natural numbers (from 0 to n
     * - 1)).
     *
     * @param tList          Provided Ts.
     * @param representation The way how graph is represented.
     */
    public Graph(List<T> tList, GraphRepresentation representation) {
        for (T t : tList) {
            this.maps.insert(t);
        }
        switch (representation) {
            case ADJ_MATRIX: {
                this.abstractGraph = new AdjMatrixIntGraph(tList.size());
                break;
            }
            case ADJ_LIST: {
                this.abstractGraph = new AdjListIntGraph();
                break;
            }
            case INC_MATRIX: {
                this.abstractGraph = new IncMatrixIntGraph();
                break;
            }
        }
    }
}
