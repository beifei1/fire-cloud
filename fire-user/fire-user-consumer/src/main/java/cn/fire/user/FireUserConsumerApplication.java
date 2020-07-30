package cn.fire.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(scanBasePackages = {"cn.fire.common.web"})
public class FireUserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireUserConsumerApplication.class, args);
    }

}
