import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import Rule.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache. kafka.clients.consumer.KafkaConsumer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
public class JavaConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        props.put("group.id", "Kafka1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", StringDeserializer.class.getName());
        props.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.setProperty("schema.registry.url", "http://127.0.0.1:8081");
        props.setProperty("specific.avro.reader", "true");
       // props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
       // props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        @SuppressWarnings("resource")
        KafkaConsumer<String, Data> consumer = new KafkaConsumer<String, Data>(props);
        consumer.subscribe(Arrays.asList("kafka1_assignment"));
        System.out.println("Waiting for data...");

        while (true) {
            System.out.println("Polling");

            ConsumerRecords<String, Data> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Data> record : records) {
                //System.out.printf("offset - %, key - %s, value - %s%n", record.offset(), record.key(), record.value());
                System.out.println(record);
            }
            consumer.commitSync();

        }
    }
}




