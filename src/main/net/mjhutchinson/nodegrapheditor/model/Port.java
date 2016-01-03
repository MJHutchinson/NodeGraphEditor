package net.mjhutchinson.nodegrapheditor.model;

public class Port {
    public static final int INPUT = 0;
    public static final int OUTPUT = 1;

    public static final int BEFORE = 0;
    public static final int AFTER = 1;

    private String label;
    private String dataType;

    public Port(String label, int direction, String dataType) {
        this.label = label;
        this.dataType = dataType;
    }

    public Port(Port port) {
        label = port.label;
        dataType = port.dataType;
    }

    public String getLabel() {
        return label;
    }

    public boolean setLabel(String label) {
        if (label != null && !label.equals("")) {
            this.label = label;
            return true;
        }

        return false;
    }

    public String getDataType() {
        return dataType;
    }

    public boolean setDataType(String dataType) {
        if (dataType != null && !dataType.equals("")) {
            this.dataType = dataType;
            return true;
        }

        return false;
    }

    public boolean canConnectTo(Port port) {
        return port != null && dataType.equals(port.dataType);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Port &&
                label.equals(((Port) obj).label) &&
                dataType.equals(((Port) obj).dataType);
    }
}
