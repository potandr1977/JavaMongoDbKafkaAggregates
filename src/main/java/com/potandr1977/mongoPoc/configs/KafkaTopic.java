package com.potandr1977.mongoPoc.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("java-topic").build();
    }

    /*
    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic-2").partitions(3).build();
    }
     */
}
