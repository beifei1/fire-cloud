package cn.fire.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication(scanBasePackages = {"cn.fire.user","cn.fire.common.web"})
public class FireUserProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUserProducerApplication.class, args);
    }

}
