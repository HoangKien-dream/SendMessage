package com.example.sendrabbitmq.controller;

import com.example.sendrabbitmq.entity.Order;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path = "api/v1/order")
public class PushMessage {
    private final static String QUEUE_NAME = "hello";
    @RequestMapping(method = RequestMethod.POST)
    public String push(@RequestBody Order order){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Gson gson = new Gson();
            String data = gson.toJson(order);
            channel.basicPublish("", QUEUE_NAME, null, data.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + data + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return "Push successs !!";
    }
}
