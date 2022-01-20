package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;

/**
 * 连接池配置
 *
 * Created by fengfan on 2022/1/13 15:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SftpPoolProperties extends GenericObjectPoolConfig<ChannelSftp> {
    @Value("${sftp.pool.max-total}")
    private int maxTotal = DEFAULT_MAX_TOTAL;
    @Value("${sftp.pool.max-idle}")
    private int maxIdle = DEFAULT_MAX_IDLE;
    @Value("${sftp.pool.min-idle}")
    private int minIdle = DEFAULT_MIN_IDLE;

    public SftpPoolProperties() {
        super.setMaxIdle(maxIdle);
        super.setMaxTotal(maxTotal);
        super.setMinIdle(minIdle);
    }
}
