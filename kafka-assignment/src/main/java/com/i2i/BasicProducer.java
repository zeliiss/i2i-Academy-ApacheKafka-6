package com.i2i;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class BasicProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ObjectMapper objectMapper = new ObjectMapper();
        String topic = "my-test-topic";

        try {
            // İzlenceye uygun custom object (obje) oluşturma
            CustomMessage myMessage = new CustomMessage("user123", "Bu bir custom object mesajidir!");
            
            // Objeyi JSON formatına çevirip Kafka'ya gönder
            String jsonMessage = objectMapper.writeValueAsString(myMessage);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, myMessage.getUserId(), jsonMessage);
            
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Mesaj basariyla gonderildi: " + jsonMessage);
                } else {
                    exception.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}