package net.mjhutchinson.nodegrapheditor.model;

public interface Property<T> {
    String getDisplayValue();
    T getValue();
    boolean setValue(T value);
}
