package com.kobe.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
    	for (int i = 0; i < 1000000; i++) {
    		//创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置RabbitMQ相关信息
            factory.setHost("localhost");
          //factory.setUsername("lp");
          //factory.setPassword("");
         // factory.setPort(2088);
            //创建一个新的连接
            Connection connection = factory.newConnection();
            //创建一个通道
            Channel channel = connection.createChannel();
            //  声明一个队列        
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "第"+i+"条"+"Hello RabbitMQ";
            //发送消息到队列中
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            //System.out.println("Producer Send +'" + message + "'");
            //关闭通道和连接
            channel.close();
            connection.close();
		}
        
    }
}