package net.mjhutchinson.nodegrapheditor.model;

public class StringProperty implements Property<String> {
    private static final String DEFAULT_VALUE = "";

    private String value;

    public StringProperty(String value) {
        if (!setValue(value)) setValue(DEFAULT_VALUE);
    }

    public StringProperty(StringProperty property) {
        value = property.value;
    }

    @Override
    public String getDisplayValue() {
        return value.replaceAll("\\p{C}", "?");
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean setValue(String value) {
        if (value != null) {
            this.value = value;
            return true;
        }

        return false;
    }
}
