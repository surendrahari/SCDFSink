//package com.example.SCDFSink.partitioned;
//
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.messaging.handler.annotation.Header;
//
//@EnableBinding(Sink.class)
//public class Sink_partitioned_Based {
//
//    @StreamListener(Sink.INPUT)
//    public void commonQueue(String message, @Header(AmqpHeaders.CONSUMER_QUEUE) String consumerQueue) {
//        System.out.println("==================== Sink : " + message + " consumerQueue : " + consumerQueue);
////        throw new RuntimeException("dead-letter queue");
//    }
//}
