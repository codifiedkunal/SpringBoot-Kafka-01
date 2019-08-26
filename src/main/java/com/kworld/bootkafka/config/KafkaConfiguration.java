package com.kworld.bootkafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.bootstrapAddress}")
    private String bootStrapAddress;

    @Value("${kafka.topicname}")
    private String topicName;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> kafkaAdminConfig = new HashMap<>();
        kafkaAdminConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootStrapAddress);
        return new KafkaAdmin(kafkaAdminConfig);
    }

    @Bean
    public NewTopic topic(){
        return new NewTopic(topicName, 1, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> producerFactoryConfig = new HashMap<>();
        producerFactoryConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootStrapAddress);
        return new DefaultKafkaProducerFactory<>(producerFactoryConfig,
                new StringSerializer(),
                new org.springframework.kafka.support.serializer.JsonSerializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory();
        listenerContainerFactory.setConsumerFactory(consumerFactory());

        return listenerContainerFactory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, Object> consumerFactoryConfig = new HashMap<>();
        consumerFactoryConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootStrapAddress);
        consumerFactoryConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "*");
        return new DefaultKafkaConsumerFactory<>(consumerFactoryConfig,
                new StringDeserializer(), new StringDeserializer());
                //new org.springframework.kafka.support.serializer.JsonDeserializer<>());
    }
}
