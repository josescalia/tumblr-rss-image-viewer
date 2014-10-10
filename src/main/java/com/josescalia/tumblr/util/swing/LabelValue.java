package com.josescalia.tumblr.util.swing;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/11/12
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabelValue<V> implements Serializable {
    private static final long serialVersionUID = 826127134041455053L;

    private V value;
    private String label;

    public LabelValue() {
    }

    public LabelValue(V value) {
        this(value, null);
    }

    public LabelValue(V value, String label) {
        this.value = value;
        this.label = label;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 29 * hash + (this.label != null ? this.label.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LabelValue<V> other = (LabelValue<V>) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "LabelValue{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }
}
