package cn.fire.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"cn.fire.common.web","cn.fire.user"})
public class FireUserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUserConsumerApplication.class, args);
    }

}
