package cn.fengfan.sftptest.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * sftp登录配置
 *
 * Created by fengfan on 2021/11/30 21:13
 */
@Data
public class SftpProperties {
    @Value("${sftp.host}")
    private String host;
    @Value("${sftp.port}")
    private int port;
    @Value("${sftp.username}")
    private String username;
    @Value("${sftp.password}")
    private String password;
}
