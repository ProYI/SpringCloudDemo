package vip.proyi.product.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收RabbitMQ消息
 */
@Slf4j
@Component
public class MQReceive {

    // 1. 需要手动创建 myQueue这个队列
//    @RabbitListener(queues = "myQueue")
//    public void process(String message) {
//        log.info("MqReceiver : {}", message);
//    }

    // 2. 自动创建队列
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
//    public void process(String message) {
//        log.info("MqReceiver : {}", message);
//    }

    // 3. 自动创建，Exchange和Queue绑定
//    @RabbitListener(bindings = @QueueBinding(
////            value = @Queue("myQueue"),
////            exchange = @Exchange("myExchange")
////    ))
////    public void process(String message) {
////        log.info("MqReceiver : {}", message);
////    }

    // 订阅消息，但只对消息中的某一类型消息感兴趣，就需要分队列
    // 接收 分类1 消息
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myProduct"),
            // rounting key 转发规则，消息区分
            key = "category1",
            value = @Queue("category1Queue")
    ))
    public void processProduct1(String message) {
        log.info("product category1 MqReceiver : {}", message);
    }

    // 接收 分类2 消息
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myProduct"),
            // 消息区分
            key = "category2",
            value = @Queue("category2Queue")
    ))
    public void processProduct2(String message) {
        log.info("product category2 MqReceiver : {}", message);
    }
}
