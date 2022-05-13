package cn.fengfan.sftptest.config;

import cn.fengfan.sftptest.util.SftpFactory;
import cn.fengfan.sftptest.util.SftpPool;
import cn.fengfan.sftptest.util.SftpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fengfan on 2021/11/30 21:20
 */
// sftp配置
@Configuration
public class SftpConfig {

    /**
     * 工厂
     *
     * @author fengfan
     * @date 2022/1/14 14:37
     * @return
     */
    @Bean
    public SftpFactory sftpFactory() {
        return new SftpFactory();
    }

    /**
     * 连接池
     *
     * @author fengfan
     * @date 2022/1/14 14:38
     * @param sftpFactory
     * @return
     */
    @Bean
    public SftpPool sftpPool(SftpFactory sftpFactory) {
        return new SftpPool(sftpFactory);
    }

    @Bean
    public SftpUtil sftpUtil(SftpPool sftpPool){
       return new SftpUtil(sftpPool);
    }

}
