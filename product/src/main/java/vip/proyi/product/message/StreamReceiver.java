package vip.proyi.product.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * spring cloud stream 消息接收
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {


    @StreamListener("MyOutMessage")
    // 消息消费后需进行回应
    @SendTo("MyInMessage")
    public String process(Object message) {
        log.info("Stream Receiver : {}", message );

        return "Message 已收到";
    }

    @StreamListener("MyInMessage")
    public void process2(Object message) {
        log.info("回应消息队列 : {}", message );
    }
}
