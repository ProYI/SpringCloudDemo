package vip.proyi.product.contrller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 声明调用User模块的哪些接口
 */
@FeignClient("user")
public interface UserClient {

    @GetMapping("/user/msg")
    String queryUserMsg();
}
