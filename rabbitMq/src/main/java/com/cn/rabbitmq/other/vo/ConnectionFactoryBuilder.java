package com.cn.rabbitmq.other.vo;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/09
 */
public class ConnectionFactoryBuilder {

    private static ConnectionFactory connectionFactory;

    private ConnectionFactoryBuilder() {
        connectionFactory = new ConnectionFactory();
    }

    public static ConnectionFactory build() {
        return connectionFactory;
    }

    public static ConnectionFactoryBuilder builder() {
        return new ConnectionFactoryBuilder();
    }

    /**
     * 设置需要连接的RabbitMQ的连接地址
     *
     * @param host
     * @return
     */
    public ConnectionFactoryBuilder host(String host) {
        connectionFactory.setHost(host);
        return this;
    }

    /*
     * 设置RabbitMQ中的连接端口号
     */
    public ConnectionFactoryBuilder port(int port) {
        connectionFactory.setPort(port);
        return this;
    }

    /*
     * 设置RabbitMQ连接的RabbitMQ中的虚拟主机中的名字
     */
    public ConnectionFactoryBuilder virtualHost(String virtualHost) {
        connectionFactory.setVirtualHost(virtualHost);
        return this;
    }

    /*
     * 设置工厂连接用户名和密码
     */
    public ConnectionFactoryBuilder userNameAndPaswd(String userName, String passWord) {
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(passWord);
        return this;
    }
}
