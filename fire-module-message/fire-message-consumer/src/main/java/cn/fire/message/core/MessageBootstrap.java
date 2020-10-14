package cn.fire.message.core;

import cn.fire.common.web.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: wangzc
 * @Date: 2020/10/14 9:39
 */
@Slf4j
@Component
public class MessageBootstrap implements CommandLineRunner {

    @Autowired
    private SpringUtil springUtil;

    @Override
    public void run(String... args) throws Exception {
        log.info("starting loop message job....");
    }

}
