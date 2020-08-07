package com.example.SCDFSink.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MySinkBinding.class)
public class MyCustomSink {

    @Autowired
    private MySinkBinding mySinkBinding;

    @StreamListener(MySinkBinding.INPUT)
    public void listen(String message) {
        try {
            persist(message);
        } catch (RuntimeException rte) {
            System.out.println("due to error message send to error queue");
            mySinkBinding.ackoutput()
                    .send(MessageBuilder.withPayload(message).build());
        }
    }

    public void persist(String message) {
        // DB persist
        System.out.println("message successfully received :" + message);
        // tapping
        if ("SUBERROR".startsWith(message.trim())) {
            throw new RuntimeException("error happend on the message");
        }
    }
}
