package com.acme.producer.api;

import com.acme.producer.model.NestedEntity;
import java.io.Serializable;

public class ParentEntity implements Serializable {

    private NestedEntity value;

    public ParentEntity() {
    }

    public ParentEntity(NestedEntity value) {
        this.value = value;
    }

    public NestedEntity getValue() {
        return value;
    }

    public void setValue(NestedEntity value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value != null ? value.toString() : "null";
    }
}
