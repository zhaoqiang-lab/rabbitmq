package com.cn.rabbitmq.other.collectionutils;

import com.cn.rabbitmq.other.vo.ConnectionFactoryBuilder;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitCollectionUtils {
    // public static ConnectionFactory connectionFactoryInstance() {
    //     // 创建rabbitMq连接工厂
    //     ConnectionFactory connectionFactory = new ConnectionFactory();
    //     //设置工厂ip
    //     connectionFactory.setHost("114.67.98.231");
    //     // 设置工厂端口
    //     connectionFactory.setPort(5672);
    //     // rabbitmq用户名和密码
    //     connectionFactory.setVirtualHost("ems");
    //     connectionFactory.setUsername("ems");
    //     connectionFactory.setPassword("123");
    //     // rabbitmq虚拟主机
    //     return connectionFactory;
    // }


    public static Connection connectionInstance() throws IOException, TimeoutException {
        ConnectionFactoryBuilder.builder()
                // 工厂ip设置
                .host("114.67.98.231")
                // 工厂端口设置
                .port(5672)
                // 虚拟主机设置
                .virtualHost("ems")
                // 虚拟主机用户名密码
                .userNameAndPaswd("ems", "123");

        return ConnectionFactoryBuilder.build().newConnection();
    }
}
