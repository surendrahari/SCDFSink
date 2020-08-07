package com.example.SCDFSink.custom;

import org.springframework.cloud.stream.messaging.Sink;

public interface MySinkBinding extends Sink, AckOutput {
}
