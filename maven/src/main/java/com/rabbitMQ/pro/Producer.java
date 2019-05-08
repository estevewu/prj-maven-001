package com.rabbitMQ.pro;

import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;  

public class Producer {  

    private final static String QUEUE_NAME = "hello2";// 队列名不能重复 之前已有就会失败  

    public static void main(String[] argv) throws java.io.IOException, TimeoutException {  

        /* 使用工厂类建立Connection和Channel，并且设置参数 */  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.74.136");// MQ的IP  
        factory.setPort(5672);// MQ端口  
        factory.setUsername("guest");// MQ用户名  
        factory.setPassword("guest");// MQ密码
        String message = "消息2";
        
        Connection connection = null;
        Channel channel = null;
        connection = factory.newConnection();  
        channel = connection.createChannel();  

        /* 创建消息队列，并且发送消息 */  
        for(int i=0;i<100;i++) {
        	channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
        	System.out.println("生产了个'" + message + "'");  
        }
        
        /* 关闭连接 */  
        channel.close();  
        connection.close();  

    }  

}  