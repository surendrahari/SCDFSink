package com.example.SCDFSink.routing;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Processor.class)
public class Sink_Routing_SinkBased {

    private static final String PROCESS_QUEUE = "processQueue";
    private static final String ACKNOWLEDGE_QUEUE = "acknowledgeQueue";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @StreamListener(Sink.INPUT)
    public void commonQueue(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        System.out.println("==================== Sink : " + message + " consumerQueue : " + routingKey);

        Message<?> messageObject = null;
        if (PROCESS_QUEUE.equalsIgnoreCase(routingKey.trim())) {
            try {
                messageObject = dbPersist(message);
                System.out.println("successfully persisted");
            } catch (RuntimeException rte) {
                System.out.println("exception block :" + rte);
                messageObject = MessageBuilder.withPayload(message).build();
            } finally {
                amqpTemplate.convertAndSend(Processor.OUTPUT, ACKNOWLEDGE_QUEUE, messageObject);
            }
        } else {
            System.out.println("Ignore Other consumer queues : " + routingKey);
        }
    }

    private Message<?> dbPersist(String message) {
        System.out.println("db persis called :" + message);
        if ("ack".startsWith(message)) {
            throw new RuntimeException("ack queue");
        }
        return MessageBuilder.withPayload(message).build();
    }
}
