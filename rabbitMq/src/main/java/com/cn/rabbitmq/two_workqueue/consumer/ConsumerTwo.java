package com.cn.rabbitmq.two_workqueue.consumer;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
/* 任务消费者2：领取任务并完成任务，假设该消费者消费速度较快 */
@Slf4j
public class ConsumerTwo {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取RabbitMQ连接工厂
        Connection connection =  RabbitCollectionUtils.connectionInstance();

        final Channel channel = connection.createChannel();
        // 设置通道一次只能接收一条未确认消息
        channel.basicQos(1);
        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer Two 正在消费消息:" + new String(body));
                // 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
