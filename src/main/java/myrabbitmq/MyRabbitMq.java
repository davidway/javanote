package myrabbitmq;

import com.rabbitmq.client.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyRabbitMq {
    /**https://my.oschina.net/hncscwc/blog/262246
     * rabbitmqctl list_users
     *rabbitmqctl  set_permissions -p / mq '.*' '.*' '.*'
     *
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void testBasicPublish() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
        factory.setUsername("mengday");
        factory.setPassword("mengday");

        // 新建一个长连接
        Connection connection = factory.newConnection();

        // 创建一个通道(一个轻量级的连接)
        Channel channel = connection.createChannel();

        // 声明一个队列
        String QUEUE_NAME = "hello";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送消息到队列中
        String message = "Hello RabbitMQ!";
        // 注意：exchange如果不需要写成空字符串，routingKey和队列名称保持一致
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send a message:" + message);

        // 关闭资源
        channel.close();
        connection.close();
    }

    @Test
    public void testBasicConsumer() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);    // 5672
        factory.setUsername("mengday");
        factory.setPassword("mengday");

        // 新建一个长连接
        Connection connection = factory.newConnection();

        // 创建一个通道(一个轻量级的连接)
        Channel channel = connection.createChannel();

        // 声明一个队列
        String QUEUE_NAME = "hello";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Consumer Wating Receive Message");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [C] Received '" + message + "'");
            }
        };

        // 订阅消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }


}
