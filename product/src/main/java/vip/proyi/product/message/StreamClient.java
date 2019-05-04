package vip.proyi.product.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    @Output("MyInMessage")
    SubscribableChannel input();

    @Output("MyOutMessage")
    MessageChannel output();
}
