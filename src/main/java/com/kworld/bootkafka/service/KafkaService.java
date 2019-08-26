package com.kworld.bootkafka.service;

import com.kworld.bootkafka.domain.KafkaMessage;

public interface KafkaService {
    public void sendMessage(KafkaMessage kafkaMessage);
}
