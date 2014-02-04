package com.acme.producer.model;

import java.io.Serializable;

public class NestedEntity implements Serializable {

    private String value;

    public NestedEntity() {
    }

    public NestedEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
