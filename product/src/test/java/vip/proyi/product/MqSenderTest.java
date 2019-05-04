package vip.proyi.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 消息发送
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }

    @Test
    public void sendProduct() {
        amqpTemplate.convertAndSend("myProduct", "category1", "now " + new Date());
        amqpTemplate.convertAndSend("myProduct", "category2", "now " + new Date());
    }
}
