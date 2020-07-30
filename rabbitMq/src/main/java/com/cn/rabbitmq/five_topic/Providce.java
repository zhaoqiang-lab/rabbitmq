package com.cn.rabbitmq.five_topic;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description Topic类型的Exchange与direct相比，都是可以根据Routing key把消息路由到不同的队列，只不过Topic 类型的exchange 可以让队列再绑定Routing key 的时候使用通配符！这种模型的routing key一般都是由一个或者多个单词组成，多个单词之间一“.” 分隔，例如：ite。insert
 * @Author ZHAOQIANG
 * @Date 2020/07/28
 */
public class Providce {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topic", "topic");

        String routingkey = "user.save";
        channel.basicPublish("topic", routingkey,null,("这里是topic动态路由模型：【"+ routingkey+"】").getBytes() );

        channel.close();
        connection.close();
    }

}
