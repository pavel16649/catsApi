//package ru.pavel16649.Config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfiguration {
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new CachingConnectionFactory("localhost");
//    }
//
//    public Queue catDeleteQueue() {
//        return new Queue("catDeleteQueue");
//    }
//
//    public Queue catAddQueue() {
//        return new Queue("catAddQueue");
//    }
//
//    public Queue ownerDeleteQueue() {
//        return new Queue("ownerDeleteQueue");
//    }
//
//    public Queue ownerAddQueue() {
//        return new Queue("ownerAddQueue");
//    }
//
//    public Queue catOwnerDeleteQueue() {
//        return new Queue("catOwnerDeleteQueue");
//    }
//
//    public Queue friendshipDeleteQueue() {
//        return new Queue("friendshipDeleteQueue");
//    }
//
//    public Queue friendshipAddQueue() {
//        return new Queue("friendshipAddQueue");
//    }
//
//    public Queue allCatFriendshipsDeleteQueue() {
//        return new Queue("allCatFriendshipsDeleteQueue");
//    }
//}
