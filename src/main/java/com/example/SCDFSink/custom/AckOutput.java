package com.example.SCDFSink.custom;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AckOutput {
    String ACKOUTPUT = "ackoutput";

    @Output("ackoutput")
    MessageChannel ackoutput();
}

/*
fixml-soure -> db-sink
            -> fixml-sink

1) db-sink -> fixml-sink (processor)
2) fixml-source -> db-sink (routing)
    bloom-source -> db-sink
        (common-queue)
3) source -> routing
4) sink : DLQ ( PMOS, FMS ) -> fixml-sink

        1) DLQ <- fixml-sink(process) -> rabbit
        2) DLQ ( data1, data2 ) <- fixml-sink -> rabbit

        3) Q <- sink(process)
           ack <- sink
 */