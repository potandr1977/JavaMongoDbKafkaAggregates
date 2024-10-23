package com.potandr1977.mongoPoc.persons.handlers;

import an.awesome.pipelinr.Command;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PingHandler implements Command.Handler<PingCommand, String> {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String data) {
        try {
            kafkaTemplate.send("java-topic", data);
        }
        catch(Exception ex)
        {
            var mes  = ex.getMessage();
        }
    }

    @Override
    public String handle(PingCommand command) {
        sendMessage(command.data);

        return "Pong from "+command.data;
    }
}