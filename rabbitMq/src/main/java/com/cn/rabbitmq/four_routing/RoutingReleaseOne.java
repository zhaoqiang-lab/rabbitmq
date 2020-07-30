package com.cn.rabbitmq.four_routing;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发布者
 *
 * @Description 第三种模式 fanout【也称为广播】
 * Routing 之订阅模型-Direct(直连)【在fanout模式中，一条消息会被所有订阅的队列消费；但是在某些场景下，我们希望不同的消息能被不同的dui】
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
public class RoutingReleaseOne {

    /**
     * 消费者1 的开发
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        Connection connection = RabbitCollectionUtils.connectionInstance();

        // 获取通道
        Channel channel = connection.createChannel();

        // 声明通道交换机以及交换类型
        channel.exchangeDeclare("logs_direct", "direct");

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于route key 绑定队列和交换机
        channel.queueBind(queue, "logs_direct", "error");

        // 获取消费消息
        channel.basicConsume(queue, true,new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者ONE：" + new String(body));
            }
        });

    }


}

