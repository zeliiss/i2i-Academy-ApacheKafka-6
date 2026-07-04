package com.i2i;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class BasicConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "staj-grubu");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // try bloğu içine alarak "Resource leak" uyarısını çözüyoruz
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList("my-test-topic"));
            
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Consumer dinliyor... Mesajlar bekleniyor.");

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        CustomMessage msg = objectMapper.readValue(record.value(), CustomMessage.class);
                        System.out.println("Yakalanan Obje -> Kullanıcı: " + msg.getUserId() + " | Mesaj: " + msg.getMessageContent());
                    } catch (Exception e) {
                        System.out.println("Farklı bir veri geldi: " + record.value());
                    }
                }
            }
        } // Program herhangi bir sebeple durduğunda consumer otomatik olarak kapatılacak.
    }
}