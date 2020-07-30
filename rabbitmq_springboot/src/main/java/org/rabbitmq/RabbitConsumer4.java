package org.rabbitmq;

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
public class RabbitConsumer4 {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "direct"), // 自定义交换机名称
                    key = {"info","error","debug"}
            )
    )
    public void receivel1(String mes) {
        System.out.println("fanout模型 receivel1【mes=:" + mes + "】");
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "direct"),
                    key = {"info"}
            )
    )
    public void receivel2(String mes) {
        System.out.println("fanout模型 receivel2【mes=:" + mes + "】");
    }

}
