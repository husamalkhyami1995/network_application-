package net.javaguides.springboot.Facade;


import net.javaguides.springboot.web.dto.StatisticsInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerImplementation {
    private final RabbitTemplate rabbitTemplate ;
//    @Value("${rabbit.queue.name}")
    private String queueName = "message_queue" ;

    public ProducerImplementation(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String statisticsInfo) {
        this.rabbitTemplate.convertAndSend( queueName , statisticsInfo  );
    }
}

