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
 *  消息消费者, 广播消费者
 *
 */
public class FanoutConsumer_One {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();

        Channel channel = connection.createChannel();
        /*
         * 绑定交换机
         *   交换声明
         */
        channel.exchangeDeclare("logs",BuiltinExchangeType.FANOUT);

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 将队列与交换级进行绑定
        //      参数一：指定队列
        //      参数二：交换机名称
        //      参数三：routkey 名称
        channel.queueBind(queue,"logs","");

        // 处理消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
