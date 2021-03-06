package cpen221.mp2.graph;

/**
 * Represents a graph vertex. Each vertex has an associated id and name.
 * No two vertices in the same graph should have the same id.
 */
public class Vertex {
 /*
    Rep Invariant:
    -- each vertex must not be null

    Abstraction Function:
    -- id is the value which represents the vertex
       each vertex has a unique id
    -- name is used to identify a vertex
    */

    private final int id;
    private String name;

    /**
     * Create a new vertex
     *
     * @param id   is a numeric identifier for the vertex
     * @param name is a name for the vertex
     */
    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Find out whether the two vertex objects are equal to each other.
     *
     * @param o is the vertex object you want to compare the vertex to
     * @return true if the object equals the vertex object, otherwise return false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex other = (Vertex) o;
            if (other.id == this.id && other.name.equals(this.name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtain a unique integer for the vertex object
     *
     * @return the vertex object's hashcode
     */
    @Override
    public int hashCode() {
        return name.hashCode() + id;
    }

    /**
     * Obtain the id of the vertex
     * @return the vertex id
     */
    public int id() {
        return id;
    }

    /**
     * Obtain the vertex name
     *
     * @return the vertex name
     */
    public String name() {
        return name;
    }

    /**
     * Update the vertex name
     *
     * @param name the new vertex name
     */
    public void updateName(String name) {
        this.name = name;
    }

}
