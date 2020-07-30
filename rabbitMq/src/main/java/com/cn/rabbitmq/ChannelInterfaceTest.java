package com.cn.rabbitmq;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ChannelInterfaceTest {

    @Test
    public void getChannelNumber() throws IOException, TimeoutException {
        Connection connection = RabbitCollectionUtils.connectionInstance();

        Channel channel = connection.createChannel();

        channel.abort(1000, "this Channel is closed");

        channel.basicPublish("", "hello", null, "hello".getBytes());

    }

}
