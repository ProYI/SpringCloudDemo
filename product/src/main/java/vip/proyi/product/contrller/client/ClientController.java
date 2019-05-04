package vip.proyi.product.contrller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 商品服务 通过微服务模式 调用用户服务的接口
 */

@RestController
@RequestMapping("/product")
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    /**
     * 使用Feign来调用User服务msg接口
     * @return
     */
    @GetMapping("/userMsg4")
    public String userMsg4() {
        String response = userClient.queryUserMsg();

        log.info("response={}", response);
        return response;
    }

    @GetMapping("/userMsg3")
    public String userMsg3() {
        // 3.第三种方式(利用@LoadBalanced, 可在restTemplate里使用应用名)
        /**
         * restTemplate.getForObject("http://EUREKA上注册的服务的名字/user/msg", String.class);
         */
        String response = restTemplate.getForObject("http://USER/user/msg", String.class);
        log.info("response={}", response);
        return response;
    }

    @GetMapping("/userMsg2")
    public String userMsg2() {
        // 2.第二种方式(利用loadBalanceClient通过应用名获取url，然后再使用restTemplate)
        /**
         * loadBalancerClient.choose("在EUREKA上注册的服务的名字")
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("USER");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/user/msg";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
        return response;
    }


    @GetMapping("/userMsg1")
    public String userMsg1() {
        // 1.第一种方式(直接使用RestTemplate，url写死)
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject("http://localhost:8081/user/msg", String.class);
        log.info("response={}", response);
        return response;
    }
}
