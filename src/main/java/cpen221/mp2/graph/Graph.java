package cpen221.mp2.graph;

import java.util.*;

/**
 * Represents a graph with vertices of type V.
 *
 * @param <V> represents a vertex type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {
    /*
    Rep Invariant:
    -- each map pairing contains a vertex which is on the graph and a list
    of vertices which it shares edges with
    -- each vertex in the graph must be added with addVertex()
    -- each edge in the graph must be added with addEdge()
    -- each edge must correspond to two vertices on the graph
    -- all vertices must have at least one edge connecting them to the graph

    Abstraction Function:
    -- graph maps all vertices on a graph to a list of vertices
    they attach to (have edges with)
    -- edgeList represents a list containing all edges in the given map
    -- vertexList represents a list containing all vertices in a given map
     */
    private Map<V, Set<V>> graph = new HashMap<>();
    private Set<E> edgeList = new HashSet<>();
    private Set<V> vertexList = new HashSet<>();
    private Set<V> settled;
    private Set<V> unsettled;
    private Map<V, V> predecessors;
    private Map<V, Integer> distance;

    /**
     * Add a vertex to the graph
     *
     * @param v vertex to add
     * @return true if the vertex was added successfully and false otherwise
     */
    @Override
    public boolean addVertex(V v) {
        this.graph.putIfAbsent(v, this.vertexList);
        return vertex(v);
    }
    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * @return true of v is part of the graph and false otherwise
     */
    @Override
    public boolean vertex(V v) { return this.graph.containsKey(v); }

    /**
     * Add an edge of the graph
     *
     * @param e the edge to add to the graph
     * @return true if the edge was successfully added and false otherwise
     */
    @Override
    public boolean addEdge(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        this.edgeList.add(e);
        this.vertexList.add(v2);
        this.graph.put(v1,this.vertexList);
        this.vertexList.add(v1);
        this.graph.put(v2,this.vertexList);
        return edge(e);
    }
    /**
     * Check if an edge is part of the graph
     *
     * @param e the edge to check in the graph
     * @return true if e is an edge in the graph and false otherwise
     */
    @Override
    public boolean edge(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        return this.graph.get(v1).contains(v2) && this.graph.get(v2).contains(v1) && this.edgeList.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */

    @Override
    public boolean edge(V v1, V v2) {
        return this.graph.get(v1).contains(v2);
    }

    /**
     * Determine the length on an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * @return the length of the v1-v2 edge if this edge is part of the graph
     */
    @Override
    public int edgeLength(V v1, V v2) {
        for(E edge1: this.edgeList){
            if(edge1.v1() == v1 && edge1.v2() == v2){
                return edge1.length();
            }
        }
        return 0;
    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges in the graph
     */
    @Override
    public int edgeLengthSum() {
        int totalLength = 0;
        for(E e: this.edgeList){
            totalLength += e.length();
        }
        return totalLength;
    }
    /**
     * Remove an edge from the graph
     *
     * @param e the edge to remove
     * @return true if e was successfully removed and false otherwise
     */
    @Override
    public boolean remove(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        this.graph.get(v1).remove(v2);
        this.graph.get(v2).remove(v1);
        this.edgeList.remove(e);
        return (!this.graph.get(v1).contains(v2) && !this.graph.get(v2).contains(v1) && !this.edgeList.contains(e));
    }
    /**
     * Remove a vertex from the graph
     *
     * @param v the vertex to remove
     * @return true if v was successfully removed and false otherwise
     */
    @Override
    public boolean remove(V v) {
        this.graph.remove(v);
        return !this.graph.containsKey(v);
    }
    /**
     * Obtain a set of all vertices in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return a set of all vertices in the graph
     */
    @Override
    public Set<V> allVertices() { return this.graph.keySet(); }
    /**
     * Obtain a set of all vertices incident on v.
     * Access to this set **should not** permit graph mutations.
     *
     * @param v the vertex of interest
     * @return all edges incident on v
     * Two edges are called incident if they both share a vertex
     */
    @Override
    public Set<E> allEdges(V v) {
        Set<E> incidentEdges = new HashSet<E>();
        Set<E> allEdges = new HashSet<E>(allEdges());
        for(E edge: allEdges){
            if(edge.incident(v)){
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }
    /**
     * Obtain a set of all edges in the graph.
     * Access to this set **should not** permit graph mutations.
     *
     * @return all edges in the graph
     */
    @Override
    public Set<E> allEdges(){ return new HashSet<E>(this.edgeList); }

    /**
     * Obtain all the neighbours of vertex v.
     * Access to this map **should not** permit graph mutations.
     *
     * @param v is the vertex whose neighbourhood we want.
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     */
    @Override
    public Map<V, E> getNeighbours(V v) {
        Map<V, E> neighbourMap = new HashMap<V, E>();
        Set<V> vSet = allVertices();
        vSet.remove(v);
        for(V v2: vSet){
            E e = getEdge(v, v2);
            if(e != null){
                neighbourMap.put(v2, e);
            }
        }
        return neighbourMap;
    }
    /**
     * Compute the shortest path from source to sink
     *
     * @param source the start vertex
     * @param sink   the end vertex
     * @return the vertices, in order, on the shortest path from source to sink (both end points are part of the list)
     */
    @Override
    public List<V> shortestPath(V source, V sink) {
        distance = new HashMap<>();
        settled = new HashSet<>();
        unsettled = new HashSet<>();
        predecessors = new HashMap<>();

        distance.put(source, 0);
        unsettled.add(source);

        while (unsettled.size() > 0) {
            V node = getMin(unsettled);
            settled.add(node);
            unsettled.remove(node);
            minDistance(node);
        }

        LinkedList<V> path = new LinkedList<V>();
        V target = sink;

        if (predecessors.get(target) == null) {
            return null;
        }
        path.add(target);
        while (predecessors.get(target) != null) {
            target = predecessors.get(target);
            path.add(target);
        }
        Collections.reverse(path);
        return path;
    }

    // referenced https://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html

    /**
     * Compute the minimum spanning tree of the graph.
     * See https://en.wikipedia.org/wiki/Minimum_spanning_tree
     *
     * @return a list of edges that forms a minimum spanning tree of the graph
     */
    @Override
    public List<E> minimumSpanningTree() {
        Groups vertices = new Groups();
        Set <E> edges = allEdges();
        Set<V> allVertices = allVertices();
        List <E> MST = new ArrayList<>();
        E edge = null;
        V vertex1 = null;
        V vertex2 = null;


        for (V vertex : allVertices) {
            vertices.add(vertex);
        }

       while( edges.size() != 0) {
            edge = minWeight(edges);
            edges.remove(edge);
            vertex1 = edge.v1();
            vertex2 = edge.v2();
            if (vertices.find(vertex1) != vertices.find(vertex2)) {
                MST.add(edge);
                vertices.merge(vertex1, vertex2);
            }

        }

        return MST;
    }
//** TODO: write specs

    /**
     *
     * @param edges
     * @return
     */
    public E minWeight(Set<E> edges) {
        int min = Integer.MAX_VALUE;
        E minEdge = null;

        for (E edge: edges) {
            if (edge.length() < min) {
                minEdge = edge;
            }
        }
        return minEdge;
    }
//**TODO: write specs

    /**
     *
     * @param node
     */
    public void minDistance(V node) {
        Map<V, E> neighbours = getNeighbours(node);
        Set<V> neighbourNodes = neighbours.keySet();
        for (V vertex : neighbourNodes) {
            if (shortestDistance(vertex) > shortestDistance(node) + getDistance(node, vertex)) {
                distance.put(vertex, shortestDistance(node) + getDistance(node, vertex));
                predecessors.put(vertex, node);
                unsettled.add(vertex);
            }
        }
    }

    /**
     *
     * @param node
     * @param target
     * @return
     */
    private int getDistance(V node, V target) {
        for (Edge edge : edgeList) {
            if (edge.v1().equals(node)
                    && edge.v2().equals(target)) {
                return edge.length();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     *
     * @param vertices
     * @return
     */
    private V getMin(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (shortestDistance(vertex) < shortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /**
     * 
     * @param destination
     * @return
     */
    private int shortestDistance(V destination) {
        Integer i = distance.get(destination);
        if (i == null) {
            return Integer.MAX_VALUE;
        } else {
            return i;
        }
    }
    
    /**
     * Compute the length of a given path
     *
     * @param path indicates the vertices on the given path
     * @return the length of path
     */
    @Override
    public int pathLength(List<V> path) {
        int length = 0;
        E edge;
        for(int i = 0; i < path.size() - 1; i++){
            edge = getEdge(path.get(i), path.get(i + 1));
            length += edge.length();
        }
        return length;
    }
    /**
     * Obtain all vertices w that are no more than a <em>path distance</em> of range from v.
     *
     * @param v     the vertex to start the search from.
     * @param range the radius of the search.
     * @return a set of vertices that are within range of v (this set does not contain v).
     */
    @Override
    public Set<V> search(V v, int range) {
       Set<V> vSet = allVertices();
       Set<V> visited = new HashSet<>();

       Map<V, E> neighbourMap;
       Set<V> nodeSet;
       int length = 0;
       int tempPath;
       int distance = 1000;
       V u = v;
        while(!(vSet.isEmpty()) && length <= range){
            length = 0;
            vSet.remove(u);
            neighbourMap = getNeighbours(u);
            nodeSet = neighbourMap.keySet();

            for (V vertex : visited) {
                nodeSet.remove(vertex);
            }
            if(!nodeSet.isEmpty()){
                for (V vertex: nodeSet){
                    E edge = neighbourMap.get(vertex);
                    tempPath = length + edge.length();
                    if(tempPath < distance){
                        distance = tempPath;
                        u = vertex;
                    }
                }
                visited.add(u);
                length = length + distance;
            }
        }

        return visited;
    }
    /**
     * Compute the diameter of the graph.
     * <ul>
     * <li>The diameter of a graph is the length of the longest shortest path in the graph.</li>
     * <li>If a graph has multiple components then we will define the diameter
     * as the diameter of the largest component.</li>
     * </ul>
     *
     * @return the diameter of the graph.
     */
    @Override
    public int diameter() {
        Set<V> vSet1 = allVertices();
        Set<V> vSet2 = allVertices();
        int length;
        int longLength = 0;
        List<V> shortList;
        for(V v1: vSet1){
            for(V v2: vSet2){
                shortList = shortestPath(v1, v2);
                length = pathLength(shortList);
                if(length > longLength){
                    longLength = length;
                }
            }
        }
        return longLength;
    }
    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * @return the edge connecting v1 and v2 or return null
     */
    @Override
    public E getEdge(V v1, V v2) {
        E edge2 = null;
        for(E edge1: this.edgeList){
            if((edge1.v1() == v1 && edge1.v2() == v2) || (edge1.v1() == v2 && edge1.v2() == v1)){
                edge2 = edge1;
            }
        }
        return edge2;
    }

    //// add all new code above this line ////

    /**
     * This method removes some edges at random while preserving connectivity
     * <p>
     * DO NOT CHANGE THIS METHOD
     * </p>
     * <p>
     * You will need to implement allVertices() and allEdges(V v) for this
     * method to run correctly
     * </p>
     * <p><strong>requires:</strong> this graph is connected</p>
     *
     * @param rng random number generator to select edges at random
     */
    public void pruneRandomEdges(Random rng) {
        class VEPair {
            V v;
            E e;

            public VEPair(V v, E e) {
                this.v = v;
                this.e = e;
            }
        }
        /* Visited Nodes */
        Set<V> visited = new HashSet<>();
        /* Nodes to visit and the cpen221.mp2.graph.Edge used to reach them */
        Deque<VEPair> stack = new LinkedList<VEPair>();
        /* Edges that could be removed */
        ArrayList<E> candidates = new ArrayList<>();
        /* Edges that must be kept to maintain connectivity */
        Set<E> keep = new HashSet<>();

        V start = null;
        for (V v : this.allVertices()) {
            start = v;
            break;
        }
        if (start == null) {
            // nothing to do
            return;
        }
        stack.push(new VEPair(start, null));
        while (!stack.isEmpty()) {
            VEPair pair = stack.pop();
            if (visited.add(pair.v)) {
                keep.add(pair.e);
                for (E e : this.allEdges(pair.v)) {
                    stack.push(new VEPair(e.distinctVertex(pair.v), e));
                }
            } else if (!keep.contains(pair.e)) {
                candidates.add(pair.e);
            }
        }
        // randomly trim some candidate edges
        int iterations = rng.nextInt(candidates.size());
        for (int count = 0; count < iterations; ++count) {
            int end = candidates.size() - 1;
            int index = rng.nextInt(candidates.size());
            E trim = candidates.get(index);
            candidates.set(index, candidates.get(end));
            candidates.remove(end);
            remove(trim);
        }
    }
}