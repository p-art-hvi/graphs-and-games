package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class GraphTest {
    /**
     * Adds a vertex and an edge to the existing graph and checks whether the graph contains the vertex and edge.
     */
    @Test
    public void testSmallGraph(){
        Vertex v1 = new Vertex(1, "neuron1");
        Vertex v2 = new Vertex(2, "neuron2");
        Vertex v3 = new Vertex(3, "neuron3");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 4);
        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(e1);
        g.addEdge(e2);

        assertTrue(g.edge(e2));
        assertTrue(g.edge(v1, v2));
        assertTrue(g.vertex(v3));
    }

    /**
     * Gets all of the vertices and edges for a graph
     */
    @Test
    public void testVerticesAndEdges(){
        Vertex v1 = new Vertex(6, "Carl");
        Vertex v2 = new Vertex(8, "George");
        Vertex v3 = new Vertex(10, "Parthvi");
        Vertex v4 = new Vertex(19, "Laia");

        Edge<Vertex> e1 = new Edge<Vertex>(v1, v2);
        Edge<Vertex> e2 = new Edge<Vertex>(v2, v3);
        Edge<Vertex> e3 = new Edge<Vertex>(v3, v4);
        Edge<Vertex> e4 = new Edge<Vertex>(v2, v4);
        Edge<Vertex> e5 = new Edge<Vertex>(v1, v4);

        Graph<Vertex, Edge<Vertex>> g = new Graph<Vertex, Edge<Vertex>>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        Set<Vertex> vertexSet = new HashSet<Vertex>();
        vertexSet.add(v1);
        vertexSet.add(v2);
        vertexSet.add(v3);
        vertexSet.add(v4);

        Set<Edge<Vertex>> edgeSet1 = new HashSet<Edge<Vertex>>();
        edgeSet1.add(e3);
        edgeSet1.add(e4);
        edgeSet1.add(e5);

        Set<Edge<Vertex>> edgeSet2 = new HashSet<Edge<Vertex>>();
        edgeSet2.add(e1);
        edgeSet2.add(e2);
        edgeSet2.add(e3);
        edgeSet2.add(e4);
        edgeSet2.add(e5);

        assertEquals(e3, g.getEdge(v3, v4));
        assertEquals(edgeSet2, g.allEdges());
        assertEquals(edgeSet1, g.allEdges(v4));
        assertEquals(vertexSet, g.allVertices());
    }

    /**
     * Gets all of the edges of a graph, checks edgeLength and edgeLengthSum
     */
    @Test
    public void testEdgeAndRemoveMethods(){
        Vertex v1 = new Vertex(6, "Jorge");
        Vertex v2 = new Vertex(8, "Jeorge");
        Vertex v3 = new Vertex(333, "Jheiorge");
        Vertex v4 = new Vertex(10, "Gorge");
        Vertex v5 = new Vertex(19, "George");
        Vertex v6 = new Vertex(22, "Gheorge");

        Edge<Vertex> e1 = new Edge<Vertex>(v1, v2);
        Edge<Vertex> e2 = new Edge<Vertex>(v1, v3);
        Edge<Vertex> e3 = new Edge<Vertex>(v1, v4);
        Edge<Vertex> e4 = new Edge<Vertex>(v1, v5);
        Edge<Vertex> e5 = new Edge<Vertex>(v1, v6);
        Edge<Vertex> e6 = new Edge<Vertex>(v2, v3);
        Edge<Vertex> e7 = new Edge<Vertex>(v2, v6);
        Edge<Vertex> e8 = new Edge<Vertex>(v3, v5);
        Edge<Vertex> e9 = new Edge<Vertex>(v4, v5);
        Edge<Vertex> e10 = new Edge<Vertex>(v2, v4);

        Graph<Vertex, Edge<Vertex>> g = new Graph<Vertex, Edge<Vertex>>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        g.addEdge(e10);

        int sum = e1.length() + e2.length() + e3.length() + e4.length() + e5.length()
                + e6.length() + e7.length() + e8.length() + e9.length() + e10.length();
        assertTrue(g.edge(e6));
        assertTrue(g.edge(v2, v6));
        assertEquals(e8.length(), g.edgeLength(v3, v5));
        assertEquals(e10.length(), g.edgeLength(v2, v4));
        assertEquals(sum, g.edgeLengthSum());

        g.remove(v3);
        assertFalse(g.vertex(v3));
        g.addVertex(v3);

        Set<Vertex> vertices = new HashSet<Vertex>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        assertEquals(vertices, g.allVertices());

        g.remove(e1);
        g.remove(e2);
        g.remove(e3);
        g.remove(e4);
        g.remove(e5);
        g.remove(e6);
        g.remove(e7);
        g.remove(e8);
        g.remove(e9);
        g.remove(e10);
        Set<Edge<Vertex>> edgeSet = new HashSet<Edge<Vertex>>();
        assertEquals(0, g.edgeLengthSum());
        assertEquals(edgeSet, g.allEdges());
    }
    @Test
    public void testCreateGraph() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        assertEquals(e2, g.getEdge(v2, v3));
        //assertEquals(21, g.pathLength(shortestPath(v3, v4)));
    }

}
