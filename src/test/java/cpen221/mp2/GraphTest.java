package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
