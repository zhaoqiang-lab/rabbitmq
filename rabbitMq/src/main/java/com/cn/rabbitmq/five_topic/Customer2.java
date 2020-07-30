package com.cn.rabbitmq.five_topic;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/28
 */
public class Customer2 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();

        Channel channel = connection.createChannel();


        // 声明交换机及交换机类型  交换机名称  交换机类型
        channel.exchangeDeclare("topic", "topic");

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 绑定队列和交换机  routingkey 采用动态通配符的形式
        channel.queueBind(queue, "topic", "user.#");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }

}
