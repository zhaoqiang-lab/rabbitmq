package org.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/29
 */
@Component
public class RabbitConsumer3 {

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT)))
    public void receivel1(String mes) {
        System.out.println("fanout模型【mes=:" + mes + "】");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT)))
    public void receivel2(String mes) {
        System.out.println("fanout模型【mes=:" + mes + "】");
    }

}
