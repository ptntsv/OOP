package org.graph;

import java.util.List;

/**
 * Contract for Graph implementation.
 *
 * @param <T> User's specified graph vertex type.
 */
public interface IGraph<T> {

    /**
     * Adding new vertex.
     *
     * @param v Vertex to add.
     * @return Added vertex.
     */
    T addVertex(T v);

    /**
     * Removing a vertex.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    T removeVertex(T v);

    /**
     * Adding a new edge.
     *
     * @param dst    Destination vertex.
     * @param src    Source vertex.
     * @param weight Weight.
     */
    void addEdge(T dst, T src, double weight);

    /**
     * Removing an edge.
     *
     * @param dst Destination vertex.
     * @param src Source vertex.
     */
    void removeEdge(T dst, T src);

    /**
     * Getting adjacent vertices.
     *
     * @param v Source vertex.
     * @return List of adjacent vertices.
     */
    List<T> getAdjacent(T v);

    /**
     * Either two vertices are adjacent or not.
     *
     * @param src Source vertex.
     * @param dst Destination vertex.
     * @return Adjacent or not.
     */
    boolean isAdjacent(T src, T dst);

    /**
     * Get number of vertices.
     *
     * @return Number of vertices.
     */
    int getVerticesN();

    /**
     * Get list of vertices.
     *
     * @return List of vertices.
     */
    List<T> getVertices();

    /**
     * Resetting graph.
     */
    void reset();
}
