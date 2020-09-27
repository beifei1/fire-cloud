package cn.fire.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.fire.message","cn.fire.common.web"})
public class FireMessageProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireMessageProducerApplication.class, args);
    }

}
