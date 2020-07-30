package com.cn.rabbitmq.three_fanout;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 第三种模型，广播模式消费者
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
/*
   消息消费者
 */
public class FanoutConsumer_Three {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("logs", "fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //将临时队列绑定exchange
        channel.queueBind(queue, "logs", "");
        //处理消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3: " + new String(body));
            }
        });
    }
}
