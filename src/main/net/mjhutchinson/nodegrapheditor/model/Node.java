package net.mjhutchinson.nodegrapheditor.model;

import java.util.ArrayList;

public class Node {
    private ArrayList<Property> properties;
    private ArrayList<Port> inputPorts;
    private ArrayList<Port> outputPorts;

    public Node() {
        properties = new ArrayList<>();
        inputPorts = new ArrayList<>();
        outputPorts = new ArrayList<>();
    }

    public Node(Node node) {
        properties = new ArrayList<>(node.properties);
        inputPorts = new ArrayList<>(node.inputPorts);
        outputPorts = new ArrayList<>(node.outputPorts);
    }

    public boolean hasPort(Port port) {
        return inputPorts.contains(port) || outputPorts.contains(port);
    }

    public int hasPortWithDirection(Port port, int direction) {
        return getPortsByDirection(direction).indexOf(port);
    }

    public int hasInputPort(Port port) {
        return inputPorts.indexOf(port);
    }

    public int hasOutputPort(Port port) {
        return outputPorts.indexOf(port);
    }

    private boolean addPortAtTop(Port port, int direction) {
        return addPortAtPosition(port, 0, direction);
    }

    public boolean addInputPortAtTop(Port port) {
        return addPortAtTop(port, Port.INPUT);
    }

    public boolean addOutputPortAtTop(Port port) {
        return addPortAtTop(port, Port.OUTPUT);
    }

    private boolean addPortAtBottom(Port port, int direction) {
        return addPortAtPosition(port, -1, direction);
    }

    public boolean addInputPortAtBottom(Port port) {
        return addPortAtBottom(port, Port.INPUT);
    }

    public boolean addOutputPortAtBottom(Port port) {
        return addPortAtBottom(port, Port.OUTPUT);
    }

    private boolean addPortAtPosition(Port port, int position, int direction) {
        ArrayList<Port> ports = getPortsByDirection(direction);

        if (ports != null && port != null) {
            ports.add(position < 0 ? ports.size() : position, port);
            return true;
        }

        return false;
    }

    public boolean addInputPortAtPosition(Port port, int position) {
        return addPortAtPosition(port, position, Port.INPUT);
    }

    public boolean addOutputPortAtPosition(Port port, int position) {
        return addPortAtPosition(port, position, Port.OUTPUT);
    }

    private boolean addPortAroundExisting(Port newPort, Port existingPort, int direction, int side) {
        ArrayList<Port> ports = getPortsByDirection(direction);
        int index = -1;

        if (ports != null && existingPort != null) index = ports.indexOf(existingPort);

        return index >= 0 && addPortAtPosition(newPort, index + side, direction);

    }

    private boolean addPortBeforeExisting(Port newPort, Port existingPort, int direction) {
        return addPortAroundExisting(newPort, existingPort, direction, Port.BEFORE);
    }

    public boolean addInputPortBeforeExisting(Port newPort, Port existingPort) {
        return addPortBeforeExisting(newPort, existingPort, Port.INPUT);
    }

    public boolean addOutputPortBeforeExisting(Port newPort, Port existingPort) {
        return addPortBeforeExisting(newPort, existingPort, Port.OUTPUT);
    }

    private boolean addPortAfterExisting(Port newPort, Port existingPort, int direction) {
        return addPortAroundExisting(newPort, existingPort, direction, Port.AFTER);
    }

    public boolean addInputPortAfterExisting(Port newPort, Port existingPort) {
        return addPortAfterExisting(newPort, existingPort, Port.INPUT);
    }

    public boolean addOutputPortAfterExisting(Port newPort, Port existingPort) {
        return addPortAfterExisting(newPort, existingPort, Port.OUTPUT);
    }

    public ArrayList<Port> getPortsByDirection(int direction) {
        return direction == Port.INPUT ? inputPorts : direction == Port.OUTPUT ? outputPorts : null;
    }

    public ArrayList<Port> getInputPorts() {
        return inputPorts;
    }

    public ArrayList<Port> getOutputPorts() {
        return outputPorts;
    }
}
