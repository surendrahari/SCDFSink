//package com.example.SCDFSink.header;
//
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.messaging.handler.annotation.Header;
//
//@EnableBinding(Sink.class)
//public class Sink_HeaderBased {
//
//    @StreamListener(Sink.INPUT)
//    public void commonQueue(String message, @Header("key") String rk) {
//
//        System.out.println("Received... : " + message + " routing key : " + rk);
//        if ("1".equalsIgnoreCase(rk.trim())) {
//            importer1();
//        } else {
//            importer2();
//        }
//    }
//
//    private void importer1() {
//        System.out.println("first importer.....");
//    }
//
//    private void importer2() {
//        System.out.println("2n importer.....");
//    }
//}
