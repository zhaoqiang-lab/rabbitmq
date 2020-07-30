package com.cn.rabbitmq.two_workqueue.consumer;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
/* 任务消费者1：领取任务并完成任务，利用 Thread.sleep 假设该消费者完成速度较慢 */
@Slf4j
public class ConsumerOne {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取RabbitMQ连接工厂
        Connection connection = RabbitCollectionUtils.connectionInstance();

        final Channel channel = connection.createChannel();
        // 设置通道一次只接收一条未确认消息
        channel.basicQos(1);

        /*
         * 这里模拟消费者one在消费queue中的数据进行信息时，消费者one需要的时间比消费者two时间长的情况
         * 参数一：队列名称
         * 参数二：是否自动确认【true：表示该消费者自动确认；false：表示该消费者不自动确认】
         */
        channel.basicConsume("hello", false, new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                Thread.sleep(1000);
                System.out.println("Consumer One 正在消费消息：" + new String(body));
                // 关闭消息自动确认，开启手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
        /*
         * 当ConsumerOne在消费过程中出现异常的情况时，消费者2会把当前消费者1中的信息重新进行消费【下面代码模拟了消费者one在消费过程中发产生异常时，异常推出的情况】
         */
        // channel.basicConsume("hello", false, new DefaultConsumer(channel) {
        //     @SneakyThrows
        //     @Override
        //     public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        //         System.out.println("Consumer One 正在消费消息："+ new String(body));
        //         // 关闭消息自动确认，开启手动确认消息
        //         // channel.basicAck(envelope.getDeliveryTag(), false);
        //         try {
        //             throw new Exception();
        //         } catch (Exception e) {
        //             channel.close();
        //             connection.close();
        //         }
        //     }
        // });
    }
}
