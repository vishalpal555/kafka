package com.vishalpal555.kafkaClient.config;

import com.vishalpal555.kafkaClient.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(Constants.LOCATION_TOPIC_NAME).build();
    }

}
