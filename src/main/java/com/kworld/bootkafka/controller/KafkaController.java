package com.kworld.bootkafka.controller;

import com.kworld.bootkafka.domain.Message;
import com.kworld.bootkafka.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @PostMapping("/message")
    public ResponseEntity<String> produceMessage(@RequestBody Message message) {

        kafkaMessageService.sendMessage(message);

        return ResponseEntity.ok("Message Produced Successfully");
    }

}
