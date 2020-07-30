package com.cn.rabbitmq.two_workqueue;

import com.cn.rabbitmq.other.collectionutils.RabbitCollectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description 任务发布者
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
/*
    第二种连接方式：任务模型【Work Queue】，也叫【Task queues】
    当消息处理比较耗时的时候，可能生产消息的速度会远远大于消息的消费速度。
    长此以往，消息就会堆积越来越多，无法及时处理。此时就可以使用work 模型：
    让多个消费者绑定到一个队列，共同消费队列中的消息。
    队列中的消息一旦消费，就会消失，因此任务是不会被重复执行的。
 */
public class WorkQueue {

    /**
     * RabbitMQ发布消息
     */
    @Test
    public void rabbitRelease() throws IOException, TimeoutException {
        // 创建RabbitMQ连接工厂
        Connection connection = RabbitCollectionUtils.connectionInstance();

        // 创建通道
        Channel channel = connection.createChannel();
        /*
         * 参数一：队列名称
         * 参数二：是否持久化
         * 参数三：是否独占队列
         * 参数四：是否自动删除
         * 参数五：其他参数
         */
        channel.queueDeclare("hello", true, false, false, null);
        for (int i = 1; i <= 20; i++) {
            /*
             * 参数一：路由名称
             * 参数二：队列名称
             * 参数三：消息其他属性
             * 参数四：需要发布的消息内容，需要转化为字节信息
             */
            channel.basicPublish("", "hello", null, ("hello RabbitMQ" + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
