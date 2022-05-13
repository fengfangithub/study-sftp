package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by fengfan on 2021/11/30 21:19
 */
@Data
public class SftpPool {

    @Value("${sftp.pool.max-total}")
    private int maxTotal;
    @Value("${sftp.pool.max-idle}")
    private int maxIdle;
    @Value("${sftp.pool.min-idle}")
    private int minIdle;

    private GenericObjectPool<ChannelSftp> pool;

    public SftpPool(SftpFactory factory) {
        GenericObjectPoolConfig<ChannelSftp> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMinIdle(minIdle);
        this.pool = new GenericObjectPool<>(factory, genericObjectPoolConfig);
    }

    /**
     * 获取一个sftp连接对象
     *
     * @return sftp连接对象
     */
    public ChannelSftp borrowObject() throws Exception {
        return pool.borrowObject();
    }

    /**
     * 归还一个sftp连接对象
     *
     * @param channelSftp sftp连接对象
     */
    public void returnObject(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            pool.returnObject(channelSftp);
        }
    }

}
