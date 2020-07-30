package org.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/29
 */
@SpringBootTest(classes = RabbitmqApplication.class)
public class TestRabbitmq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    @Test
    public void testWork() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("work", "work模型" + i);
        }
    }

    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "fanout模型发送的消息");
    }

    @Test
    public void testRoute() {
        String route = "info";
        rabbitTemplate.convertAndSend("direct", route, "发送" + route + "的路由的信息");
    }


    @Test
    public void testTopic() {
        String route = "user.save.info";
        rabbitTemplate.convertAndSend("topic", route, "发送" + route + "的路由的信息");
    }
}
