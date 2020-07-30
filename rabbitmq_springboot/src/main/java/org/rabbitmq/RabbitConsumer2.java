package org.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/29
 */
@Component
public class RabbitConsumer2 {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel1(String mes) {
        System.out.println("receivel1 mes=:【" + mes + "】");
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receivel2(String mes) {
        System.out.println("receivel2【mes=:" + mes + "】");
    }

}
