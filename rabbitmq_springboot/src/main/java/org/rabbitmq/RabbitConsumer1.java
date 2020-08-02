package org.rabbitmq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/29
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))// 持久化  非独占  不是自动删除队列
public class RabbitConsumer1 {

    @RabbitHandler
    public void receivel(String mes){
        System.out.println("mes=:" + mes);
    }

    public static void main(String[] args) {
        System.out.println("rabbitmq-v1.0.0-20200731");
    }

}
