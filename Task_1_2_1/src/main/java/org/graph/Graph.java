package org.graph;

import java.util.List;

/**
 * Contract for Graph implementation.
 *
 * @param <T> User's specified graph vertex type.
 */
public interface Graph<T> {

    /**
     * Adding new vertex. If such vertex already in graph then nothing happens.
     *
     * @param v Vertex to add.
     * @return Added vertex.
     */
    T addVertex(T v);

    /**
     * Removing a vertex. If no such vertex in graph, NoSuchVertexException is thrown.
     *
     * @param v Vertex to remove.
     * @return Removed vertex.
     */
    T removeVertex(T v);

    /**
     * Adding a new edge. If graph doesn't contain source or destination vertices then
     * NoSuchVertexException is thrown.
     *
     * @param src Source vertex.
     * @param dst Destination  vertex.
     */
    void addEdge(T src, T dst);

    /**
     * Removing an edge. If graph doesn't contain source or destination vertices then
     * NoSuchVertexException is thrown.
     *
     * @param dst Destination vertex.
     * @param src Source vertex.
     */
    void removeEdge(T dst, T src);

    /**
     * Getting adjacent vertices. If graph doesn't contain provided vertex then
     * NoSuchVertexException is thrown.
     *
     * @param v Source vertex.
     * @return List of adjacent vertices.
     */
    List<T> getAdjacent(T v);

    /**
     * Either two vertices are adjacent or not. If graph doesn't contain source or destination
     * vertices then NoSuchVertexException is thrown.
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
     * Graphs with different implementations could be equals.
     *
     * @param obj Graph to check
     * @return Equals or not.
     */
    boolean equals(Object obj);

    String toString();
}
