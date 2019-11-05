package cpen221.mp2.graph;

import java.util.*;

public class Dijkstra<V extends Vertex, E extends Edge<V>>   {

    //private final Set<V> vertexSet;
    //private final Set<E> edgeSet;
    private Set<V> settled;
    private Set<V> unsettled;
    private Map<V, V> predecessors;
    private Map<V, Integer> distance;
    private Graph g = new Graph();

    public Dijkstra(ImGraph g) {
       // this.vertexSet = new HashSet<>(g.allVertices());
        //this.edgeSet = new HashSet<>(g.allEdges());
    }

    public List<V> algorithm(V source, V sink) {
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

    private void minDistance(V node) {
        Map<V, E> neighbours = g.getNeighbours(node);
        Set<V> neighbourNodes = neighbours.keySet();
        for (V vertex : neighbourNodes) {
            if (shortestDistance(vertex) > shortestDistance(node) + getDistance(node, vertex)) {
                distance.put(vertex, shortestDistance(node) + getDistance(node, vertex));
                predecessors.put(vertex, node);
                unsettled.add(vertex);
            }
        }
    }



    private int shortestDistance(V destination) {
        Integer i = distance.get(destination);
        if (i == null) {
            return Integer.MAX_VALUE;
        } else {
            return i;
        }
    }
    private int getDistance(V node, V target) {

       /* for (Edge edge : this.edgeSet) {
            if (edge.v1().equals(node)
                    && edge.v2().equals(target)) {
                return edge.length();
            }
        }*/
        return 0;
    }

}
