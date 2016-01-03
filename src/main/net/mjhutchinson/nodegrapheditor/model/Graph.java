package net.mjhutchinson.nodegrapheditor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Graph {
    private HashMap<String, Node> nodes;
    private HashMap<String, Edge> edges;

    public Graph() {
        this(1 << 4, 1 << 4);
    }

    public Graph(int initialNodes, int intialEdges) {
        nodes = new HashMap<>(initialNodes);
        edges = new HashMap<>(intialEdges);
    }

    public Graph(Graph graph) {
        nodes = new HashMap<>(graph.nodes);
        edges = new HashMap<>(graph.edges);
    }

    public boolean addNode(String key, Node node) {
        return nodes.putIfAbsent(key, node) != null;
    }

    /**
     * Gets a node from the graph.
     * @param key The unique identifier of the node.
     * @return The requested node or null if it did not exist
     */
    public Node getNode(String key) {
        return nodes.get(key);
    }

    public boolean nodeExists(String key) {
        return nodes.containsKey(key);
    }

    /**
     * Removes a node from the graph.
     * @param key The unique identifier of the node.
     * @return {@code true} if the node was successfully removed, {@code false} if it did not exist.
     */
    public boolean removeNode(String key) {
        return nodes.remove(key) != null;
    }

    public boolean edgeExists(Edge edge) {
        return !edges.containsValue(edge);
    }

    public boolean isValidEdge(Edge edge) {
        if (edgeExists(edge)) return false;
        String startNodeKey = edge.getStartNodeKey(), endNodeKey = edge.getEndNodeKey();
        if (!nodeExists(startNodeKey) || !nodeExists(endNodeKey)) return false;
        Node startNode = getNode(startNodeKey), endNode = getNode(endNodeKey);
        return !(startNode.hasOutputPort(edge.getStartPort()) < 0 || endNode.hasInputPort(edge.getEndPort()) < 0);
    }

    public boolean addEdge(String key, Edge edge) {
        if (!isValidEdge(edge) || edges.containsKey(key)) return false;
        edges.put(key, edge);
        return true;
    }

    public boolean addEdge(String key, String startNodeKey, String endNodeKey, Port startPort, Port endPort) {
        return addEdge(key, new Edge(startNodeKey, endNodeKey, startPort, endPort));
    }

    public boolean removeEdge(String key) {
        return edges.remove(key) != null;
    }

    public ArrayList<Edge> getEdgesStartingAtNode(String nodeKey) {
        return edges.values().stream().filter(e -> e.getStartNodeKey().equals(nodeKey)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Edge> getEdgesEndingAtNode(String nodeKey) {
        return edges.values().stream().filter(e -> e.getEndNodeKey().equals(nodeKey)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Edge> getEdgesStartingAtNodePort(String nodeKey, String portKey) {
        return getEdgesStartingAtNode(nodeKey).stream().filter(e -> e.getStartPort().equals(portKey)).collect(Collectors.toCollection(ArrayList::new));
    }

    public Edge getEdgeEndingAtNodePoint(String nodeKey, String portKey) {
        return getEdgesEndingAtNode(nodeKey).stream().filter(e -> e.getEndPort().equals(portKey)).collect(Collectors.toCollection(ArrayList::new)).get(0);
    }
}
