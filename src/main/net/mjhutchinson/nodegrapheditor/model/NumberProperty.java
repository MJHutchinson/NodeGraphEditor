package net.mjhutchinson.nodegrapheditor.model;

public class NumberProperty implements Property<Float> {
    private static final float DEFAULT_VALUE = 0.0f;
    private static final float DEFAULT_MIN_VALUE = 0.0f;
    private static final float DEFAULT_MAX_VALUE = 1.0f;
    private static final float DEFAULT_STEP_SIZE = 0.01f;

    private float value;
    private float minValue;
    private float maxValue;
    private float stepSize;

    public NumberProperty(float value) {
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, DEFAULT_STEP_SIZE);
    }

    public NumberProperty(float value, float minValue, float maxValue, float stepSize) {
        if (!setValue(value)) setValue(DEFAULT_VALUE);
        if (!setMinValue(minValue)) setMinValue(DEFAULT_MIN_VALUE);
        if (!setMaxValue(maxValue)) setMaxValue(DEFAULT_MAX_VALUE);
        if (!setStepSize(stepSize)) setStepSize(DEFAULT_STEP_SIZE);
    }

    public NumberProperty(NumberProperty property) {
        value = property.value;
        minValue = property.minValue;
        maxValue = property.maxValue;
        stepSize = property.stepSize;
    }

    @Override
    public String getDisplayValue() {
        return null;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public boolean setValue(Float value) {
        return setValue(value.floatValue());
    }

    public boolean setValue(float value) {
        if (stepSize > 0) value = Math.round(value / stepSize) * stepSize;

        if (value >= minValue && value <= maxValue) {
            this.value = value;
            return true;
        }

        return false;
    }

    public float getMinValue() {
        return minValue;
    }

    public boolean setMinValue(float minValue) {
        if (minValue <= maxValue) {
            this.minValue = minValue;
            return true;
        }

        return false;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public boolean setMaxValue(float maxValue) {
        if (maxValue >= minValue) {
            this.maxValue = maxValue;
            return true;
        }

        return false;
    }

    public boolean setRange(float minValue, float maxValue) {
        return setMinValue(minValue) & setMaxValue(maxValue);
    }

    public float getStepSize() {
        return stepSize;
    }

    public boolean setStepSize(float stepSize) {
        if (stepSize >= 0) {
            this.stepSize = stepSize;
            return true;
        }

        return false;
    }
}
