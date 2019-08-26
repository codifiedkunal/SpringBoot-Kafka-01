package com.kworld.bootkafka.service;

import com.kworld.bootkafka.domain.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService implements KafkaService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topicname}")
    private String topicName;

    @Override
    public void sendMessage(KafkaMessage kafkaMessage) {
        kafkaTemplate.send(topicName, kafkaMessage);
    }

    @KafkaListener(topics = "${kafka.topicname}")
    public void listen(String message){
        System.out.println("Received Message " + message);
    }
}
