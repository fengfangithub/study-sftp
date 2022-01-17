package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by fengfan on 2021/11/30 21:19
 */
@Data
public class SftpPool {

    private GenericObjectPool<ChannelSftp> pool;

    public SftpPool(SftpFactory factory, SftpPoolProperties sftpPoolProperties) {
        this.pool = new GenericObjectPool<>(factory, sftpPoolProperties);
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
