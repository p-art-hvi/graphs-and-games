package cpen221.mp2.graph;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents a graph with vertices of type V and edges (set of vertices) of type E
 * Graph implements methods of the interfaces ImGraph and IGraph
 * @param <V> represents a vertex type
 * @param <E> represents an edge type
 */
public class Graph<V extends Vertex, E extends Edge<V>> implements ImGraph<V, E>, IGraph<V, E> {
    /*
    Rep Invariant:
    -- each map pairing contains a vertex which is on the graph and a set
    of vertices which it shares edges with
    -- each edge must correspond to two vertices on the graph
    -- each vertex in the graph must have a unique id

    Abstraction Function:
    -- graph maps all vertices on a graph to a set of vertices
       they attach to (have edges with)
    -- edgeSet represents a set containing all edges in the given map
        edgeSet cannot contain edges not connected to vertices, edges
        cannot be connected to null vertices.
    -- vertexSet represents a set containing all vertices in a given map
        vertexSet cannot contain vertices which are not connected to any
        other vertices in graph. vertexSet cannot contain null values or duplicates.
    */

    private final Map<V, Set<V>> graph = new HashMap<>();
    private final Set<E> edgeSet = new HashSet<>();
    private final Set<V> vertexSet = new HashSet<>();

    public Graph() {
    }

    /**
     * Add a vertex to the graph if the vertex does not already exist in the graph
     *
     * @param v vertex to add to the graph
     * requires: v needs to be connected to other vertices in the graph
     *           vertexSet includes the vertices connected to v,
     *           while excluding v from the set.
     * @return true if the vertex was added successfully and false otherwise
     */
    @Override
    public boolean addVertex(V v) {
        this.graph.putIfAbsent(v, this.vertexSet);
        return vertex(v);
    }

    /**
     * Check if a vertex is part of the graph
     *
     * @param v vertex to check in the graph
     * requires: v cannot be null as the graph does not contain null values
     * @return true of v is part of the graph and false otherwise
     */
    @Override
    public boolean vertex(V v) {
        return this.graph.containsKey(v);
    }

    /**
     * Add an edge to the graph
     *
     * @param e the edge to add to the graph
     * requires: the edge must have two vertices that are not equal to each other
     *           and are not null values.
     * @return true if the edge was successfully added and false otherwise
     *
     */
    @Override
    public boolean addEdge(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        this.edgeSet.add(e);
        this.vertexSet.add(v2);
        this.graph.put(v1, this.vertexSet);
        this.vertexSet.add(v1);
        this.graph.put(v2, this.vertexSet);
        return edge(e);
    }

    /**
     * Checks if an edge exists in the graph by
     * checking if its vertices v1 and v2 are
     * contained in the graph and if edge e is contained
     * in the edgeSet.
     *
     * @param e the edge to check in the graph
     * requires: e cannot be a null value,
     *           v1 and v2 cannot be null either.
     * @return true if e is an edge in the graph and false otherwise
     */
    @Override
    public boolean edge(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        return this.graph.get(v1).contains(v2)
                && this.graph.get(v2).contains(v1) && this.edgeSet.contains(e);
    }

    /**
     * Check if v1-v2 is an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * requires: v1 cannot equal v2
     *           v1 and/or v2 cannot be null values
     * @return true of the v1-v2 edge is part of the graph and false otherwise
     */

    @Override
    public boolean edge(V v1, V v2) {
        return this.graph.get(v1).contains(v2);
    }

    /**
     * Determine the length of an edge in the graph
     *
     * @param v1 the first vertex of the edge
     * @param v2 the second vertex of the edge
     * requires: v1 cannot equal v2
     *           v1 and/or v2 cannot be null values
     * @return the length of the v1-v2 edge if this edge is part of the graph
     *          otherwise return the length of 0, indicating that the edge
     *          is not a part of the graph.
     *          length of the edge cannot be a negative value.
     */
    @Override
    public int edgeLength(V v1, V v2) {
        for (E edge1: this.edgeSet) {
            if (edge1.v1() == v1 && edge1.v2() == v2 || edge1.v1() == v2 && edge1.v2() == v1) {
                return edge1.length();
            }
        }
        return 0;
    }

    /**
     * Obtain the sum of the lengths of all edges in the graph
     *
     * @return the sum of the lengths of all edges belonging to the graph.
     *          the sum can only equal 0 if there are no edges in the graph
     *          or if edgeSet is empty.
     *          the sum can never be a negative number.
     */
    @Override
    public int edgeLengthSum() {
        int totalLength = 0;
        for (E e : this.edgeSet) {
            totalLength += e.length();
        }
        return totalLength;
    }

    /**
     * Remove a specific edge from the graph,
     * but does not remove the vertices that
     * the edge was connecting together
     * from the graph.
     *
     * @param e the edge to remove
     * requires: e can only be removed once,
     *           edgeSet does not contain duplicates
     * @return true if e was successfully removed and false otherwise
     */
    @Override
    public boolean remove(E e) {
        V v1 = e.v1();
        V v2 = e.v2();
        this.graph.get(v1).remove(v2);
        this.graph.get(v2).remove(v1);
        this.edgeSet.remove(e);
        return (!this.graph.get(v1).contains(v2)
                && !this.graph.get(v2).contains(v1) && !this.edgeSet.contains(e));
    }

    /**
     * Remove a vertex from the graph.
     * Any edges connecting the vertex to other vertices
     * must also be removed, since an edge cannot exist with
     * only one vertex.
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
     * Access to this set should not permit graph mutations.
     *
     * @return a set of all vertices belonging to the graph.
     *          The set cannot contain duplicate vertices or
     *          vertices of null value.
     */
    @Override
    public Set<V> allVertices() {
        return this.graph.keySet();
    }

    /**
     * Obtain a set of all edges incident on v.
     * Two edges are called incident if they both share a vertex.
     * Access to this set should not permit graph mutations.
     *
     * @param v the vertex of interest
     * requires: v cannot be null.
     * @return all edges incident on v
     *         the set of incident edges can be empty only if the graph
     *         is made of a single edge with two vertices.
     *         If the graph is made up of more than 1 edge, the set of
     *         incident edges must contain more than one element.
     *         Cannot return a set of null values.
     */
    @Override
    public Set<E> allEdges(V v) {
        Set<E> incidentEdges = new HashSet<>();
        Set<E> allEdges = new HashSet<>(allEdges());
        for (E edge : allEdges) {
            if (edge.incident(v)) {
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }

    /**
     * Obtain a set of all edges in the graph.
     * Access to this set should not permit graph mutations.
     *
     * @return all edges in the graph
     *          Can only return an empty set if the graph has no edges.
     *          Can never return a set of null values.
     */
    @Override
    public Set<E> allEdges() {
        return new HashSet<>(this.edgeSet);
    }

    /**
     * Obtain all the neighbours of vertex v.
     * Access to this map should not permit graph mutations.
     *
     * @param v is the vertex whose neighbourhood we want.
     * requies: v cannot be null
     * @return a map containing each vertex w that neighbors v and the edge between v and w.
     *          the map cannot contain vertex v that is being passed as a parameter
     *          since edge v-v does not exist.
     *          only adjacent vertices can be a part of the keySet in the map returned
     *          we do not consider vertices which are not directly connected to v
     *          by a single edge.
     */
    @Override
    public Map<V, E> getNeighbours(V v) {
        Map<V, E> neighbourMap = new HashMap<>();
        Set<V> vSet = allVertices();
        vSet.remove(v);
        for (V v2 : vSet) {
            E e = getEdge(v, v2);
            if (e != null) {
                neighbourMap.put(v2, e);
            }
        }
        return neighbourMap;
    }


    /**
     * Gets the shortest path length (distance) from a certain vertex
     * to another vertex.
     *
     * @param destination a vertex which is contained in the keySet of
     *                    the map named distance.
     * requires: It cannot be a null value.
     * @param distance is a map containing destination vertices and their
     *                 corresponding integer values which describe the length
     *                 of the path to get to the destination.
     * @return the shortest distance which is a value corresponding the key
     * which is a destination of type V.
     *
     */
    private int shortestDistance(V destination, Map<V, Integer> distance) {
        Integer i = distance.get(destination);
        if (i == null) {
            return Integer.MAX_VALUE;
        } else {
            return i;
        }
    }

    /**
     * Computes the shortest path from source to sink
     *
     * @param source the start vertex
     * requies: needs to be connected to an edge within the graph.
     * @param sink   the end vertex
     * requires: needs to be connected to an edge within the graph.
     * @return the vertices, in order, on the shortest path from source
     *         to sink (both end points are part of the list)
     *         the list has to contain at least two vertices.
     */
    @Override
    public List<V> shortestPath(V source, V sink) {
        Map<V, List<V>> allPaths = shortestMap(source);
        return allPaths.get(sink);
    }

    /**
     *
     *
     * @param source is the vertex which we want to start from.
     * requires: needs to be contained in the graph and connected to at least one other vertex by an edge.
     * @return a map containing each vertex and a list of vertices which it connects to.
     */
    private Map<V, List<V>> shortestMap(V source) {
        Map<V, Integer> distance = new HashMap<>();
        List<V> settled = new ArrayList<>();
        List<V> unsettled = new ArrayList<>();
        Map<V, V> predecessors = new HashMap<>();
        Map<V, List<V>> allPaths = new HashMap<>();

        distance.put(source, 0);
        unsettled.add(source);

        while (unsettled.size() > 0) {
            V node = getMin(unsettled, distance);
            settled.add(node);
            unsettled.remove(node);
            minDistance(node, distance, unsettled, predecessors);
        }

        for (int i = 0; i < settled.size(); i++) {
            List<V> path = new ArrayList<>();
            V target = settled.get(i);
            V initial = settled.get(i);
            path.add(target);
            while (predecessors.get(target) != null) {
                target = predecessors.get(target);
                path.add(target);
            }
            Collections.reverse(path);
            allPaths.put(initial, path);
        }
        return allPaths;
    }

    /**
     * Compute the minimum spanning tree of the graph.
     * See https://en.wikipedia.org/wiki/Minimum_spanning_tree
     *
     * @return a list of edges that forms a minimum spanning tree of the graph
     */
    @Override
    public List<E> minimumSpanningTree() {
        Groups vertices = new Groups();
        Set<E> edges = allEdges();
        Set<V> allVertices = allVertices();

        List<E> mst = new ArrayList<>();
        E edge;
        V vertex1;
        V vertex2;

        for (V vertex : allVertices) {
            vertices.add(vertex);
        }

        while (edges.size() != 0) {
            edge = minWeight(edges);
            edges.remove(edge);
            vertex1 = edge.v1();
            vertex2 = edge.v2();
            if (vertices.find(vertex1) != vertices.find(vertex2)) {
                mst.add(edge);
                vertices.merge(vertex1, vertex2);
            }
        }

        return mst;
    }

    /**
     * @param edges
     * @return
     */
    public E minWeight(Set<E> edges) {
        int min = Integer.MAX_VALUE;
        E minEdge = null;

        for (E edge : edges) {
            if (edge.length() < min) {
                minEdge = edge;
            }
        }
        return minEdge;
    }

    /**
     *
     * @param node
     * @param distance
     * @param unsettled
     * @param predecessors
     */
    public void minDistance(V node, Map<V, Integer> distance,
                            List<V> unsettled, Map<V, V> predecessors) {

        Map<V, E> neighbours = getNeighbours(node);
        Set<V> neighbourNodes = neighbours.keySet();
        for (V vertex : neighbourNodes) {
            if (shortestDistance(vertex, distance) > shortestDistance(node, distance)
                    + getDistance(node, vertex)) {
                distance.put(vertex, shortestDistance(node, distance) + getDistance(node, vertex));
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
        for (Edge edge : this.edgeSet) {
            if (edge.v1().equals(node)
                    && edge.v2().equals(target)) {
                return edge.length();
            }
        }
        return 0;
    }

    /**
     * Find the vertex in the map which has the smallest distance to the source
     * vertex.
     *
     * @param vertices is a list of vertices in which we need to find the vertex
     *                 that corresponds to the smallest integer distance in the map.
     * requires: at least one vertex in the list vertices is contained within
     *           the keySet of the map called distance.
     * @return a vertex of type V where V is the vertex corresponding with the
     *          smallest integer distance in the map.
     */
    private V getMin(List<V> vertices, Map<V, Integer> distance) {

        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (shortestDistance(vertex, distance) < shortestDistance(minimum, distance)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }


    /**
     * Compute the length of a given path
     *
     * @param path indicates the vertices on the given path
     * requires: path list cannot contain null values.
     * @return the length of path where the length is greater than
     *         or equal to 0.
     *         the length of the path can only equal 0 if the
     *         list of vertices passed to the method is empty.
     */
    @Override
    public int pathLength(List<V> path) {
        int length = 0;
        E edge;
        for (int i = 0; i < path.size() - 1; i++) {
            edge = getEdge(path.get(i), path.get(i + 1));
            length += edge.length();
        }
        return length;
    }
    /**
     * Obtain all vertices w that are no more than a path distance of range from v.
     *
     * @param v     the vertex to start the search from.
     * requires: v is a part of the graph and connected to edges.
     * @param range the radius or path distance of the search.
     * requires: range is greater than or equal to 0.
     * @return a set of vertices that are within range of v (this set does not contain v).
     *          the set returned can only be empty with range is less than the shortest edge
     *          length between v and its closest, adjacent vertex.
     *          the set returns can never contain null values.
     */

    @Override
    public Set<V> search(V v, int range) {
        Set<V> vSet = allVertices();
        Set<V> reached = new HashSet<>();
        Map<V, List<V>> map = new HashMap<>();
        List<V> path;
        Map<V, Integer> lengthMap = new HashMap<>();
        int length;
        List<V> vList = new CopyOnWriteArrayList<>(vSet);
        Map<V, List<V>> shortestMap = shortestMap(v);


        for (V vertex : vList) {

            List<V> shortest = shortestMap.get(vertex);
            if (shortest != null) {
                map.put(vertex, shortest);
            }
        }
        for (V vertex : map.keySet()) {
            path = map.get(vertex);
            length = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                length += edgeLength(path.get(i), path.get(i + 1));
            }
            lengthMap.put(vertex, length);
        }
        for (V vertex : lengthMap.keySet()) {
            if (lengthMap.get(vertex) <= range && lengthMap.get(vertex) != 0) {
                reached.add(vertex);
            }
        }

        return reached;
    }



    /**
     * Compute the diameter of the graph.
     * The diameter of a graph is the length of the longest shortest path in the graph.
     * We find all of the shortest paths between all possible sources (start nodes)
     * and all possible sinks (end nodes) and compare the path lengths to determine the
     * length of the longest shortest path in the graph.
     *
     * @return the diameter of the graph where the diameter has to be either 0 or greater.
     */

    @Override
    public int diameter() {
        Set<V> vSet1 = allVertices();
        List<V> vList1 = new ArrayList<>(vSet1);
        Set<V> vSet2 = allVertices();
        List<V> vList2 = new ArrayList<>(vSet2);
        int tempLength = 0;
        int diameter = 0;

        for (V v1 : vList1) {
            List<V> shortList;
            Map<V, List<V>> shortMap = shortestMap(v1);
            for (V v2 : vList2) {
                if (v1 != v2) {
                    if (getEdge(v1, v2) != null) {
                        E e = getEdge(v1, v2);
                        tempLength = e.length();
                    } else {
                        shortList = shortMap.get(v2);
                        if (shortList != null) {
                            tempLength = pathLength(shortList);
                        }
                    }
                    if (tempLength > diameter) {
                        diameter = tempLength;
                    }
                }
            }
        }
        return diameter;
    }
    

    /**
     * Find the edge that connects two vertices if such an edge exists.
     * This method should not permit graph mutations.
     *
     * @param v1 one end of the edge
     * @param v2 the other end of the edge
     * requires: v1 is not equal to v2
     *           v1 and/or v2 are not null values
     * @return the edge connecting v1 and v2 or return null if no such edge exists
     */
    @Override
    public E getEdge(V v1, V v2) {
        E edge2 = null;
        Set<E> edges = new HashSet<>(this.edgeSet);
        for (E edge1: edges) {
            if ((edge1.v1() == v1 && edge1.v2() == v2) || (edge1.v1() == v2 && edge1.v2() == v1)) {
                edge2 = edge1;
            }
        }
        return edge2;
    }

    /**
     * This method removes some edges at random while preserving connectivity
     * DO NOT CHANGE THIS METHOD
     *
     * You will need to implement allVertices() and allEdges(V v) for this
     * method to run correctly
     *
     * requires: this graph is connected
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
        // Visited Nodes
        Set<V> visited = new HashSet<>();
        // Nodes to visit and the cpen221.mp2.graph.Edge used to reach them
        Deque<VEPair> stack = new LinkedList<VEPair>();
        // Edges that could be removed
        ArrayList<E> candidates = new ArrayList<>();
        // Edges that must be kept to maintain connectivity
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