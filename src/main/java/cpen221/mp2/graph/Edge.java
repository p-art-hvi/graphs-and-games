package cpen221.mp2.graph;

import java.util.NoSuchElementException;

public class Edge<V extends Vertex> {

    private V v1;
    private V v2;
    private int length;

    /**
     * A constructor which creates an edge between two vertices v1-v2 with a length of 1.
     * @param v1 is the vertex at one end of the edge
     * @param v2 is the vertex at the other end of the edge
     */
    public Edge(V v1, V v2) {
        this(v1, v2, 1);
    }

    /**
     * A constructor which creates an edge between v1-v2 at a length which is passed
     * to the method.
     * @param v1 is the vertex at one end of the edge
     * @param v2 is the vertex at the other end of the edge
     * @param length is the edge length of the new edge
     */
    public Edge(V v1, V v2, int length) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vertices cannot be null");
        }
        if (v1.equals(v2)) {
            throw new IllegalArgumentException("The same vertex cannot be at both ends of an edge");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }
        this.v1 = v1;
        this.v2 = v2;
        this.length = length;
    }

    /**
     * Get the first vertex which the edge is connected to.
     * @return v1 which is the first vertex
     */
    public V v1() {
        return v1;
    }

    /**
     * Get teh second vertex which the edge is connected to.
     * @return v2 which is the second vertex
     */
    public V v2() {
        return v2;
    }

    /**
     * Get the edge length.
     * @return the edge length
     * where the length is greater than or equal to 0.
     */
    public int length() {
        return length;
    }

    /**
     * Checks whether two edge objects are equal
     *
     * @param o is an object you want to check the edge against
     * @return true if the edge equals the object passed as a parameter and false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<?> other = (Edge<?>) o;
            if (other.v1.equals(this.v1) && other.v2.equals(this.v2)) {
                return true;
            }
            return other.v1.equals(this.v2) && other.v2.equals(this.v1);
        }
        return false;
    }

    /**
     * Get the hashcode for the edge object
     *
     * @return a unique integer value for the edge object
     */
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }

    /**
     * Figure out whether the edge is incident to vertex v
     *
     * @param v is a vertex in the graph you want to check if
     *          either vertex in the edge equals v
     * @return true if one of edge's vertices is equal to v, otherwise return false.
     */
    public boolean incident(V v) {
        if (v == null) {
            return false;
        }
        if (v.equals(v1) || v.equals(v2)) {
            return true;
        }
        return false;
    }

    /**
     * Figure out whether edge intersects the edge e.
     *
     * @param e is the edge you want to compare an edge object to.
     * @return true if the edge intersects e, otherwise return false
     */
    public boolean intersects(Edge<V> e) {
        if (e == null) {
            return false;
        }
        return this.incident(e.v1) || this.incident(e.v2);
    }

    /**
     * Figure out at which vertex the edge e intersects with another edge.
     *
     * @param e is the edge you are comparing another edge to.
     * @return the vertex of type V where the two edges intersect each other.
     * @throws NoSuchElementException if no common vertex exists between the two.
     */
    public V intersection(Edge<V> e) throws NoSuchElementException {
        if (e == null) {
            throw new NoSuchElementException("No common vertex");
        }
        if (this.v1.equals(e.v1) || this.v1.equals(e.v2)) {
            return this.v1;
        }
        if (this.v2.equals(e.v1) || this.v2.equals(e.v2)) {
            return this.v2;
        }
        throw new NoSuchElementException("No common vertex");
    }

    /**
     * Find and return the vertex in the edge which is not equal to the vertex
     * passed to the method
     *
     * @param v is a vertex in the edge
     * @return the vertex which is not equal to v in the edge. (distinct vertex)
     */
    public V distinctVertex(V v) {
        if (this.v1.equals(v)) {
            return this.v2;
        } else {
            return this.v1;
        }
    }

    /**
     * Find and return the distinct vertex between the two edges.
     *
     * @param e is the edge which connects two vertexes which we need to check against
     *          another edge to find the distinct vertex between the two edges.
     * @return the vertex which is distinct between the two edges
     */
    public V distinctVertex(Edge<V> e) {
        if (this.equals(e)) {
            throw new NoSuchElementException("No distinct vertex");
        }
        V sv;
        try {
            sv = this.intersection(e);
        }
        catch (NoSuchElementException nse) {
            // when there is no common vertex,
            // return any vertex (deterministic choice of v1 is okay).
            return v1;
        }
        if (v1.equals(sv)) {
            return v2;
        } else {
            return v1;
        }
    }

}
