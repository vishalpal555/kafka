package com.vishalpal555.kafkaClient.service;

import com.vishalpal555.kafkaClient.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private static Logger LOG = LoggerFactory.getLogger(KafkaService.class);

    @KafkaListener(topics = Constants.LOCATION_TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void getLocation(String location){
        LOG.info("location received {}", location);
    }
}
