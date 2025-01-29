package com.vishalpal555.kafkaClient.controller;

import com.vishalpal555.kafkaClient.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.concurrent.*;

@RestController
@RequestMapping()
public class RootController {
    private static Logger LOG = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


}
