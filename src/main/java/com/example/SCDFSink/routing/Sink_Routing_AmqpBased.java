package com.example.SCDFSink.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Header;

@EnableBinding(Sink.class)
public class Sink_Routing_AmqpBased {

//    @StreamListener(Sink.INPUT)
//    public void commonQueue(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String rk) {
//        System.out.println("==================== Sink : " + message + " routing key : " + rk);
//        if ("org".equalsIgnoreCase(rk.trim())) {
//            importer1();
//        } else {
//            importer2();
//        }
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "output.outputQueue"),
            exchange = @Exchange(value = "output", type = "topic"),
            key = "org"))
    public void processQueue(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String rk) {
        System.out.println("ORG ... : " + message + " routing key : " + rk);
        importer1();
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "output.ackQueue"),
            exchange = @Exchange(value = "output", type = "topic"),
            key = "ack"))
    public void ackQueue(String message, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String rk) {
        System.out.println("ACK ... : " + message + " routing key : " + rk);
        importer2();
    }

    private void importer1() {
        System.out.println("first importer.....");
    }

    private void importer2() {
        System.out.println("2n importer.....");
    }
}
