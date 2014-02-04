package com.acme.consumer;

import com.acme.producer.api.ParentEntity;
import com.acme.producer.api.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {

    private static final Logger log = LoggerFactory.getLogger(Example.class);

    private Producer service;
    private Thread worker;

    public void setService(Producer service) {
        this.service = service;
    }

    public void start() throws Exception {
        worker = new Thread() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    try {
                        ParentEntity e = service.sayHello("hi! " + System.currentTimeMillis());
                        log.info("producer says '{}'", e);
                    } catch (Exception e) {
                        log.error("producer call fails", e);
                    }
                }
            }
        };

        worker.start();
    }

    public void stop() throws Exception {
        if (worker != null) {
            worker.interrupt();
            worker = null;
        }
    }
}
