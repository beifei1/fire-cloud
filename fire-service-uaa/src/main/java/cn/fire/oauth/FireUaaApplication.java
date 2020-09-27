package cn.fire.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.fire.common.web","cn.fire.oauth"})
public class FireUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUaaApplication.class, args);
    }

}
