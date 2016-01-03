package net.mjhutchinson.nodegrapheditor.model;

public class Edge {
    private final String startNodeKey;
    private final String endNodeKey;
    private final Port startPort;
    private final Port endPort;

    public Edge(String startNodeKey, String endNodeKey, Port startPort, Port endPort) {
        this.startNodeKey = startNodeKey;
        this.endNodeKey = endNodeKey;
        this.startPort = startPort;
        this.endPort = endPort;
    }

    public Edge(Edge edge) {
        startNodeKey = edge.startNodeKey;
        endNodeKey = edge.endNodeKey;
        startPort = edge.startPort;
        endPort = edge.endPort;
    }

    public String getStartNodeKey() {
        return startNodeKey;
    }

    public String getEndNodeKey() {
        return endNodeKey;
    }

    public Port getStartPort() {
        return startPort;
    }

    public Port getEndPort() {
        return endPort;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Edge &&
                startNodeKey.equals(((Edge) obj).startNodeKey) &&
                endNodeKey.equals(((Edge) obj).endNodeKey) &&
                startPort.equals(((Edge) obj).startPort) &&
                endPort.equals(((Edge) obj).endPort);
    }
}
