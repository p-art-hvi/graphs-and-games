package cpen221.mp2;

import cpen221.mp2.graph.Edge;
import cpen221.mp2.graph.Graph;
import cpen221.mp2.graph.Vertex;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void testSmallGraph(){
        Vertex v1 = new Vertex(1, "dendrites");
        Vertex v2 = new Vertex(2, "axon terminal");
        Vertex v3 = new Vertex(3, "synaptic cleft");

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

    @Test
    public void testVerticesAndEdges(){
        Vertex v1 = new Vertex(6, "Othello");
        Vertex v2 = new Vertex(8, "Hamlet");
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
    public void testGetNeighbours(){
        Vertex v1 = new Vertex(1, "Joe Jonas");
        Vertex v2 = new Vertex(2, "Kevin Jonas");
        Vertex v3 = new Vertex(3, "Nick Jonas");
        Vertex v4 = new Vertex(4, "Priyanka Chopra Jonas");

        Edge<Vertex> e1 = new Edge<Vertex>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<Vertex>(v1, v3, 7);
        Edge<Vertex> e3 = new Edge<Vertex>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<Vertex>(v2, v3, 19);
        Edge<Vertex> e5 = new Edge<Vertex>(v2, v4, 27);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        Map<Vertex, Edge> map = new HashMap<Vertex, Edge>();
        map.put(v2, e1);
        map.put(v3, e2);
        map.put(v4, e3);
        assertEquals(map, g.getNeighbours(v1));
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

    @Test
    public void testBasicMSP() {
        Vertex v1 = new Vertex(1, "A");
        Vertex v2 = new Vertex(2, "B");
        Vertex v3 = new Vertex(3, "C");
        Vertex v4 = new Vertex(4, "D");
        Vertex v5 = new Vertex(5, "E");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 7);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 10);
        Edge<Vertex> e5 = new Edge<>(v3, v5, 20);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addVertex(v5);

        Set<Edge> expected = new HashSet<>();
        expected.add(e1);
        expected.add(e2);
        expected.add(e3);
        expected.add(e4);

        Set <Edge> MSP = new HashSet<>();
        for (Edge e : g.minimumSpanningTree()) {
            MSP.add(e);
        }

        assertEquals(expected, MSP);
    }


    @Test
    public void testSearch1(){
        Vertex v1 = new Vertex(1, "Jake Peralta");
        Vertex v2 = new Vertex(2, "Amy Santiago Peralta");
        Vertex v3 = new Vertex(3, "Rosa Diaz");
        Vertex v4 = new Vertex(4, "Doug Judy");
        Vertex v5 = new Vertex(5, "Captain Raymond Holt");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e2 = new Edge<>(v1, v3, 10);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 6);
        Edge<Vertex> e4 = new Edge<>(v1, v5, 15);
        Edge<Vertex> e5 = new Edge<>(v2, v3, 2);
        Edge<Vertex> e6 = new Edge<>(v2, v4, 14);
        Edge<Vertex> e7 = new Edge<>(v2, v5, 99);

        Graph<Vertex, Edge<Vertex>> graph = new Graph<>();
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);

        Set<Vertex> vSet = new HashSet<>();
        vSet.add(v2);
        vSet.add(v3);
        vSet.add(v4);
        assertEquals(vSet, graph.search(v1, 6));
    }


    @Test
    public void testPathLength1(){
        Vertex v1 = new Vertex(1, "Jasmine");
        Vertex v2 = new Vertex(2, "Mulan");
        Vertex v3 = new Vertex(3, "Rapunzel");
        Vertex v4 = new Vertex(4, "Moana");
        Vertex v5 = new Vertex(5, "Pocahontas");
        Vertex v6 = new Vertex(6, "Belle");
        Vertex v7 = new Vertex(7, "Elsa");
        Vertex v8 = new Vertex(8, "Tiana");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e2 = new Edge<>(v2, v6, 6);
        Edge<Vertex> e3 = new Edge<>(v6, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 2);
        Edge<Vertex> e5 = new Edge<>(v5, v2, 4);
        Edge<Vertex> e6 = new Edge<>(v2, v7, 5);
        Edge<Vertex> e7 = new Edge<>(v7, v8, 10);
        Edge<Vertex> e8 = new Edge<>(v8, v1, 7);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);

        List<Vertex> list = new ArrayList<>();
        list.add(v1);
        list.add(v2);
        list.add(v6);
        list.add(v4);
        list.add(v5);
        list.add(v2);
        list.add(v7);
        list.add(v8);
        list.add(v1);
        list.add(v2);

        assertEquals(49, g.pathLength(list));
    }

    @Test
    public void testPathLength2(){
        Vertex v1 = new Vertex(1, "Deep-fried oreos");
        Vertex v2 = new Vertex(2, "Deep-fried ice-cream");
        Vertex v3 = new Vertex(3, "Deep-fried pickles");
        Vertex v4 = new Vertex(4, "Deep-fried donuts");
        Vertex v5 = new Vertex(5, "Deep-fried chicken");
        Vertex v6 = new Vertex(6, "Deep-fried apples");
        Vertex v7 = new Vertex(7, "Deep-fried cheetos");
        Vertex v8 = new Vertex(8, "Deep-fried water");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 3);
        Edge<Vertex> e2 = new Edge<>(v1, v3, 6);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 9);
        Edge<Vertex> e4 = new Edge<>(v1, v5, 2);
        Edge<Vertex> e5 = new Edge<>(v3, v6, 4);
        Edge<Vertex> e6 = new Edge<>(v4, v7, 5);
        Edge<Vertex> e7 = new Edge<>(v5, v8, 10);
        Edge<Vertex> e8 = new Edge<>(v6, v8, 7);
        Edge<Vertex> e9 = new Edge<>(v7, v8, 14);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);

        List<Vertex> list = new ArrayList<Vertex>();
        list.add(v1);
        list.add(v3);
        list.add(v6);
        list.add(v8);

        assertEquals(17, g.pathLength(list));
    }

    @Test
    public void testShortestPath1(){
        Vertex v1 = new Vertex(1, "Scully");
        Vertex v2 = new Vertex(2, "Hitchcock");
        Vertex v3 = new Vertex(3, "Pimento");
        Vertex v4 = new Vertex(4, "Caleb the Cannibal");
        Vertex v5 = new Vertex(5, "Charles Boyle");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v4, 2);
        Edge<Vertex> e3 = new Edge<>(v1, v3, 9);
        Edge<Vertex> e4 = new Edge<>(v1, v4, 16);
        Edge<Vertex> e5 = new Edge<>(v2, v5, 13);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        List<Vertex> shortestVertices = g.shortestPath(v1, v4);
        int shortestPath = g.pathLength(shortestVertices);
        assertEquals(4, shortestPath);
    }


    @Test      
    public void testShortestPath2(){
        Vertex v1 = new Vertex(1, "pen and ink");
        Vertex v2 = new Vertex(2, "oil paint");
        Vertex v3 = new Vertex(3, "acrylic paint");
        Vertex v4 = new Vertex(4, "crayons");
        Vertex v5 = new Vertex(5, "pastels");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v4, 2);
        Edge<Vertex> e3 = new Edge<>(v2, v3, 2);
        Edge<Vertex> e4 = new Edge<>(v1, v5, 2);
        Edge<Vertex> e5 = new Edge<>(v2, v5, 2);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        List<Vertex> shortestVertices = g.shortestPath(v2, v5);
        int shortestPath = g.pathLength(shortestVertices);
        assertEquals(2, shortestPath);
    }

    @Test
    public void testShortestPath3(){
        Vertex v1 = new Vertex(1, "please");
        Vertex v2 = new Vertex(2, "let");
        Vertex v3 = new Vertex(3, "this");
        Vertex v4 = new Vertex(4, "testcase");
        Vertex v5 = new Vertex(5, "work");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 6);
        Edge<Vertex> e3 = new Edge<>(v2, v4, 13);
        Edge<Vertex> e4 = new Edge<>(v3, v4, 20);
        Edge<Vertex> e5 = new Edge<>(v2, v5, 3);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        List<Vertex> shortestVertices = g.shortestPath(v1, v4);
        int shortestPath = g.pathLength(shortestVertices);
        assertEquals(15, shortestPath);
    }

    @Test
    public void testShortestPath4(){
        Vertex v1 = new Vertex(1, "hopefully");
        Vertex v2 = new Vertex(2, "this");
        Vertex v3 = new Vertex(3, "testcase");
        Vertex v4 = new Vertex(4, "also");
        Vertex v5 = new Vertex(5, "works");
        Vertex v6 = new Vertex(5, "otherwise");
        Vertex v7 = new Vertex(5, "I");
        Vertex v8 = new Vertex(5, "will");
        Vertex v9 = new Vertex(5, "be");
        Vertex v10 = new Vertex(5, "sad");

        Edge<Vertex> e1 = new Edge<>(v1, v10, 10000);
        Edge<Vertex> e2 = new Edge<>(v1, v2, 2);
        Edge<Vertex> e3 = new Edge<>(v2, v4, 2);
        Edge<Vertex> e4 = new Edge<>(v4, v5, 2);
        Edge<Vertex> e5 = new Edge<>(v5, v6, 2);
        Edge<Vertex> e6 = new Edge<>(v6, v7, 2);
        Edge<Vertex> e7 = new Edge<>(v7, v8, 2);
        Edge<Vertex> e8 = new Edge<>(v8, v9, 2);
        Edge<Vertex> e9 = new Edge<>(v9, v10, 2);
        Edge<Vertex> e10 = new Edge<>(v1, v3, 60);
        Edge<Vertex> e11 = new Edge<>(v2, v3, 70);
        Edge<Vertex> e12 = new Edge<>(v3, v4, 80);
        Edge<Vertex> e13 = new Edge<>(v3, v5, 20);
        Edge<Vertex> e14 = new Edge<>(v3, v6, 30);
        Edge<Vertex> e15 = new Edge<>(v3, v7, 400);
        Edge<Vertex> e16 = new Edge<>(v1, v7, 50);
        Edge<Vertex> e17 = new Edge<>(v1, v8, 60);
        Edge<Vertex> e18 = new Edge<>(v2, v10, 3000);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);

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
        g.addEdge(e11);
        g.addEdge(e12);
        g.addEdge(e13);
        g.addEdge(e14);
        g.addEdge(e15);
        g.addEdge(e16);
        g.addEdge(e17);
        g.addEdge(e18);

        List<Vertex> shortestVertices = g.shortestPath(v1, v10);
        int shortestPath = g.pathLength(shortestVertices);
        assertEquals(16, shortestPath);
    }

    @Test
    public void testDiameter1(){
        Vertex v1 = new Vertex(1, "Stir fry");
        Vertex v2 = new Vertex(2, "Pad thai");
        Vertex v3 = new Vertex(3, "Poke");
        Vertex v4 = new Vertex(4, "Sushi");
        Vertex v5 = new Vertex(5, "Tempura");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 1);
        Edge<Vertex> e2 = new Edge<>(v1, v3, 2);
        Edge<Vertex> e3 = new Edge<>(v1, v4, 3);
        Edge<Vertex> e4 = new Edge<>(v1, v5, 40);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);

        assertEquals(40, g.diameter());
    }

    @Test
    public void testDiameter2(){
        Vertex v1 = new Vertex(1, "Gamora");
        Vertex v2 = new Vertex(2, "Groot");
        Vertex v3 = new Vertex(3, "Rocket");
        Vertex v4 = new Vertex(4, "Star Lord");
        Vertex v5 = new Vertex(5, "Drax 'The Destroyer' ");

        Edge<Vertex> e1 = new Edge<>(v1, v2, 5);
        Edge<Vertex> e2 = new Edge<>(v2, v3, 10);
        Edge<Vertex> e3 = new Edge<>(v3, v4, 10);
        Edge<Vertex> e4 = new Edge<>(v1, v4, 15);
        Edge<Vertex> e5 = new Edge<>(v1, v3, 500);
        Edge<Vertex> e6 = new Edge<>(v3, v5, 50);

        Graph<Vertex, Edge<Vertex>> g = new Graph<>();
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);

        assertEquals(550, g.diameter());
    }
}
