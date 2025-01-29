package com.vishalpal555.kafkaServer.controller;

import com.vishalpal555.kafkaServer.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping()
public class RootController {
    private static Logger LOG = LoggerFactory.getLogger(RootController.class);
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static Future<?> FUTURE = null;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(){
        try {
            if(FUTURE != null && !FUTURE.isCancelled() && !FUTURE.isDone()){
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("already running");
            }
            LOG.info("starting caller.... ");
            SecureRandom secureRandom = new SecureRandom();
            FUTURE = executorService.scheduleWithFixedDelay(() -> {
                String location = String.format("%s,%s", secureRandom.nextInt(100), secureRandom.nextInt(100));
                kafkaTemplate.send(Constants.LOCATION_TOPIC_NAME, location);
            }, 0, 2, TimeUnit.SECONDS);
            try {
                FUTURE.get(10, TimeUnit.SECONDS);
            } catch (TimeoutException ignored){
                FUTURE.cancel(true);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e){
            LOG.error("handled general exception ", e);
        }
        return ResponseEntity.internalServerError().build();
    }
}
