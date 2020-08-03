package cn.fire.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.fire.user")
public class FireUserProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUserProducerApplication.class, args);
    }

}
