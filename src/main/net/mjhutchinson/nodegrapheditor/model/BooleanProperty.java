package net.mjhutchinson.nodegrapheditor.model;

public class BooleanProperty implements Property<Boolean> {
    private static final boolean DEFAULT_VALUE = false;

    private boolean value;

    public BooleanProperty(boolean value) {
        if (!setValue(value)) setValue(DEFAULT_VALUE);
    }

    public BooleanProperty(BooleanProperty property) {
        value = property.value;
    }

    @Override
    public String getDisplayValue() {
        return value ? "True" : "False";
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean setValue(Boolean value) {
        return setValue(value.booleanValue());
    }

    public boolean setValue(boolean value) {
        this.value = value;
        return true;
    }
}
