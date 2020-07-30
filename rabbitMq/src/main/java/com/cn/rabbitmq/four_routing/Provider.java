package com.cn.rabbitmq.four_routing;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/28
 */
public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接对象
        Connection connection = RabbitCollectionUtils.connectionInstance();
        // 获取连接通道对象
        Channel channel = connection.createChannel();

        // 通过通道声明交换机
        // 参数一：交换机名称； 参数二：路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        // 发送消息
        String routkey = "debug";
        channel.basicPublish("logs_direct", routkey, null, ("这是direct发布的基于route key:[" + routkey + "]的消息").getBytes());

        channel.close();
        connection.close();
    }

}
