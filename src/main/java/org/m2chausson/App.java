package org.m2chausson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.m2chausson.RabbitMQ.RabbitMQMessage;
import org.m2chausson.RabbitMQ.RabbitMQMessageHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class App {

    private final static String QUEUE_NAME = "chausson";

    public static void main(String[] args) throws IOException, TimeoutException {
        RabbitMQMessageHandler rabbitMQMessageHandler = new RabbitMQMessageHandler();

        // Configurer la connexion à RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        // Créer une connexion et un canal
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Déclarer la file d'attente
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.exchangeDeclare("chausson_exchange", "fanout");
        channel.queueBind(QUEUE_NAME, "chausson_exchange", "chausson_routing_key");
        System.out.println(" [*] En attente de messages");

        // Définir le callback pour traiter les messages reçus
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                RabbitMQMessage message = mapper.readValue(delivery.getBody(), RabbitMQMessage.class);
//                RabbitMQMessage message = (RabbitMQMessage) objectStream.readObject();
                rabbitMQMessageHandler.HandleMessage(message);
                System.out.println(" [x] Message reçu : '" + message + "'");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Erreur de désérialisation des données", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        // Keep the main thread alive to keep the consumer running
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted: " + e.getMessage());
        }
    }
}
