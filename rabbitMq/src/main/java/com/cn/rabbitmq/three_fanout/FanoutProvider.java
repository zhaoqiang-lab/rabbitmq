package com.cn.rabbitmq.three_fanout;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 第三种模式 fanout【也称为广播，一条消息可以被多个消费着消费】
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
/**
 * 消息发布者
 */
public class FanoutProvider {

    @Test
    public void fanoutRelease() throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();

        // 通过 conn 连接获取连接通道
        Channel channel = connection.createChannel();
        /*
         * 这里相当于声明交换机
         *     参数一：交换机名称
         *     参数二：交换机类型
         */
        channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);
        //发布消息
        channel.basicPublish("logs","",null,"hello".getBytes());
        channel.close();
        connection.close();
    }
}
