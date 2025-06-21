package ru.boraldan.producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.boraldan.producer.model.AllType;
import ru.boraldan.producer.model.AllTypeAvro;
import ru.boraldan.producer.model.User;
import ru.boraldan.producer.model.UserAvro;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserProducerService {

    private final KafkaTemplate<String, UserAvro> kafkaTemplateUserAvro;
    private final KafkaTemplate<String, AllTypeAvro> kafkaTemplateAllTypeAvro;

    public void sendUserAvro(User user) {
        UserAvro userAvro = new UserAvro();
        userAvro.setId(user.getId());
        userAvro.setName(user.getName());
        userAvro.setEmail(user.getEmail());
        userAvro.setTitle(user.getTitle());

        kafkaTemplateUserAvro.send("users", userAvro);
    }

    public void sendAllTypeAvro(AllType allType) {
        AllTypeAvro allTypeAvro = AllTypeAvro.newBuilder()
                .setId(allType.getId())
                .setName(allType.getName())
                .setEmail(allType.getEmail())
                .setAge(allType.getAge())
                .setIsActive(allType.getIsActive())
                .setBalance(allType.getBalance())
                .setRating(allType.getRating())
                .setCreatedAt(allType.getCreatedAt())
                .setAttributes(new HashMap<CharSequence, CharSequence>(allType.getAttributes()))
                .setScores(new HashMap<CharSequence, Integer>(allType.getScores()))
                .setComments(new ArrayList<>(allType.getComments()))
                .setStatus(allType.getStatus())
                .build();

        kafkaTemplateAllTypeAvro.send("allTypeAvro", allTypeAvro);
    }
}