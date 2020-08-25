package cn.fire.common.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author: wangzc
 * @Date: 2020/8/25 10:34
 */

@Configuration
@ConditionalOnBean(DataSource.class)
public class GlobalMyBatisPlusConfig {

    @Bean
    PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDbType(DbType.MYSQL);
        interceptor.setLimit(-1);
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return interceptor;
    }

}
