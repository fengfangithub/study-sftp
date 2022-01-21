package cn.fengfan.sftptest.config;

import cn.fengfan.sftptest.util.SftpFactory;
import cn.fengfan.sftptest.util.SftpPool;
import cn.fengfan.sftptest.util.SftpPoolProperties;
import cn.fengfan.sftptest.util.SftpProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fengfan on 2021/11/30 21:20
 */
// sftp配置
@Configuration
public class SftpConfig {

    /**
     * sftp配置
     *
     * @author fengfan
     * @date 2022/1/14 14:37
     * @return
     */
    @Bean
    public SftpProperties sftpProperties() {
        return new SftpProperties();
    }

    /**
     * 连接池配置
     *
     * @author fengfan
     * @date 2022/1/14 14:37
     * @return
     */
    @Bean
    public SftpPoolProperties sftpPoolProperties(){
        return new SftpPoolProperties();
    }

    /**
     * 工厂
     *
     * @author fengfan
     * @date 2022/1/14 14:37
     * @param sftpProperties
     * @return
     */
    @Bean
    public SftpFactory sftpFactory(SftpProperties sftpProperties) {
        return new SftpFactory(sftpProperties);
    }

    /**
     * 连接池
     *
     * @author fengfan
     * @date 2022/1/14 14:38
     * @param sftpFactory
     * @param sftpPoolProperties
     * @return
     */
    @Bean
    public SftpPool sftpPool(SftpFactory sftpFactory, SftpPoolProperties sftpPoolProperties) {
        return new SftpPool(sftpFactory, sftpPoolProperties);
    }

}
