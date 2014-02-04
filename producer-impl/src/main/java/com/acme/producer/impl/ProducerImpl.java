package com.acme.producer.impl;

import com.acme.producer.api.ParentEntity;
import com.acme.producer.api.Producer;
import com.acme.producer.model.NestedEntity;

public class ProducerImpl implements Producer {

    @Override
    public ParentEntity sayHello(String s) {
        NestedEntity n = new NestedEntity("returning: " + s);
        ParentEntity p = new ParentEntity(n);
        return p;
    }
}
