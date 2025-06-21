package ru.boraldan.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.boraldan.producer.model.AllTypeAvro;
import ru.boraldan.producer.model.Status;
import ru.boraldan.producer.model.UserAvro;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserConsumerService {

    @KafkaListener(topics = "users", groupId = "user-group")
    public void listenUserAvro(UserAvro user) {
        System.out.println("Received user: " + user);
    }

    @KafkaListener(topics = "allTypeAvro", groupId = "allTypeAvro-group2", properties = {"auto.offset.reset=earliest"})
    public void listenAllType(AllTypeAvro allTypeAvro) {

        // варианты преобразования полей из AllTypeAvro в поля базового объекта AllType
        UUID id = allTypeAvro.getId();
        String name = allTypeAvro.getName().toString();
        Integer age = allTypeAvro.getAge();
        Boolean isActive = allTypeAvro.getIsActive();
        Double balance = allTypeAvro.getBalance();
        Float rating = allTypeAvro.getRating();
        Instant createdAt = allTypeAvro.getCreatedAt();

        Map<String, String> attributes = allTypeAvro.getAttributes().entrySet().stream()
                .collect(Collectors.toMap(
                        el -> el.getKey().toString(),
                        el -> el.getValue().toString()));

        Map<String, Integer> scores = allTypeAvro.getScores().entrySet().stream()
                .collect(Collectors.toMap(
                        el -> el.getKey().toString(),
                        Map.Entry::getValue
                ));

        List<String> comments = allTypeAvro.getComments().stream()
                .map(CharSequence::toString).toList();

        Status status = allTypeAvro.getStatus();

        System.out.println("Received allTypeAvro: " + allTypeAvro);
    }
}
