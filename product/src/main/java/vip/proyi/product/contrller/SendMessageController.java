package vip.proyi.product.contrller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.proyi.product.message.StreamClient;

import java.util.Date;

@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "Spring Cloud Stream test Message, now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }
}
