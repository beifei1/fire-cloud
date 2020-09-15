package cn.fire.actuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class FireAdminActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireAdminActuatorApplication.class, args);
    }

}
