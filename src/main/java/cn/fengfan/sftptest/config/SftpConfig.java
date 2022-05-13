package cn.fengfan.sftptest.config;

import cn.fengfan.sftptest.util.SftpFactory;
import cn.fengfan.sftptest.util.SftpGenericObjectPool;
import cn.fengfan.sftptest.util.SftpUtil;
import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    @Bean
    @ConfigurationProperties(prefix = "sftp.pool")
    public GenericObjectPoolConfig<ChannelSftp> sftpPoolConfig(){
        return new GenericObjectPoolConfig<>();
    }

    @Bean
    public SftpGenericObjectPool sftpPool(SftpFactory sftpFactory, GenericObjectPoolConfig<ChannelSftp> sftpPoolConfig){
        return new SftpGenericObjectPool(sftpFactory, sftpPoolConfig);
    }

    @Bean
    public SftpUtil sftpUtil(SftpGenericObjectPool sftpPool){
       return new SftpUtil(sftpPool);
    }

}
