package ru.boraldan.producer.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.boraldan.producer.model.AllType;
import ru.boraldan.producer.model.User;
import ru.boraldan.producer.service.UserProducerService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserProducerService producer;

    @Operation(summary = "Отправить сообщение User")
    @PostMapping("/send-user")
    public void sendUser(@RequestBody User user) {
        producer.sendUserAvro(user);
    }

    @Operation(summary = "Отправить сообщение AllType")
    @PostMapping("/send-all-type")
    public void sendAllType(@RequestBody AllType allType) {
        producer.sendAllTypeAvro(allType);
    }
}
