import Rule.Alert;
import Rule.Data;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.*;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.metrics.Sensor;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.IntStream;

public class JavaProducer {


    public JavaProducer() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "Kafka1");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");//CHECK
        //properties.put("serializer.class", "kafka.serializer.DefaultEncoder");//SERIALIZER CONFLUENT REGISTRY
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //properties.put("schema.registry.url", "http://127.0.0.1:8081");

        //ProducerRecord producerRecord = new ProducerRecord("kafka1_assignment", "name", "selftuts");
        Producer<String , Data> kafkaProducer = new KafkaProducer<String , Data >(properties) ;
        Data data1 = Data.newBuilder()
                .setSensorId(120)
                .setTime(System.currentTimeMillis())
                .setStatus(Alert.CRITICAL)
                .build();
        ProducerRecord<String , Data> producerRecord = new ProducerRecord<String , Data >("kafka1_assignment", "helloKey", data1 ) ;

        //KafkaProducer kafkaProducer = new KafkaProducer(properties);
        kafkaProducer.send(producerRecord);
        kafkaProducer.close();
    }

    public static void main(String[] args) {
        JavaProducer p = new JavaProducer();
    }
}
