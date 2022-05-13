package cn.fengfan.sftptest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * Created by fengfan on 2022/1/20 15:25
 */
@Configuration
public class ThreadPoolConfig {
    //参数可以整合到配置文件中
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(2,
                8,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10)
        );

    }
}
