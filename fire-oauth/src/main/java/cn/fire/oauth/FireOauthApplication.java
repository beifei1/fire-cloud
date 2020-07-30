package cn.fire.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FireOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireOauthApplication.class, args);
    }

}
