package cn.fire.user;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.fire.user")
public class FireUserProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUserProducerApplication.class, args);
    }

    @NacosValue("${redis.name}")
    private String name;

    @Bean
    CommandLineRunner run () {
        return (arg) -> {
            System.out.println("==================:" + name);
        };
    }
}
