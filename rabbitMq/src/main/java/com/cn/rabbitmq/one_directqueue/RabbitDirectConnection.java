package com.cn.rabbitmq.one_directqueue;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @Description RabbitMQ第一种连接方式（直连型【Direct Queue】）
 * @Author ZHAOQIANG
 * @Date 2020/07/08
 */
public class RabbitDirectConnection {

    /**
     * RabbitMQ开发消息生产者
     *
     * @throws IOException      IO异常
     * @throws TimeoutException 连接超时异常
     */
    @Test
    public void rabbitConn() throws IOException, TimeoutException {
        Connection connection =  RabbitCollectionUtils.connectionInstance();

        // 创建rabbitmq通道
        Channel channel = connection.createChannel();
        /*
         * 队列声明
         * 参数一：通道名字
         * 参数二：是否持久化该队列
         * 参数三：是否独占队列，仅此通道可使用
         * 参数四：是否自动删除
         * 参数五：队列的其他属性设置
         */
        channel.queueDeclare("hello", false, false, false, null);

        /*
         * 发布消息
         * 参数一：交换机
         * 参数二：虚拟主机的名称
         * 参数三：消息其它属性
         * 参数四：将要缓存的消息
         */
        channel.basicPublish("", "hello", null, "hello rabbitmq3".getBytes());
        channel.close();
        connection.close();
    }

    /**
     * RabbitMQ消费者
     *
     * @throws IOException      IO异常
     * @throws TimeoutException 连接超时
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  RabbitCollectionUtils.connectionInstance();
        Channel channel = connection.createChannel();
        // 队列声明
        channel.queueDeclare("hello", false, false, false, null);
        /*
         * 消费队列
         * 参数一：队列名称
         * 参数二：
         */
        channel.basicConsume("hello", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println(new String(body));
            }
        });
    }
}
