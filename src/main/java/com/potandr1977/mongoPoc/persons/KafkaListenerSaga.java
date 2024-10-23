package com.potandr1977.mongoPoc.persons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenerSaga {
    @KafkaListener(topics="save-person-topic", groupId="java-group")
    void listen(String data) {
        var m = data;
    }
}
