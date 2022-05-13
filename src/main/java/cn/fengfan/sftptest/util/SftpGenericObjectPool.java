package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author fengfan
 * @description sftp连接池
 * @date 2022/5/13 12:50
 */
public class SftpGenericObjectPool{

    private final GenericObjectPool<ChannelSftp> genericObjectPool;

    public SftpGenericObjectPool(SftpFactory sftpFactory, GenericObjectPoolConfig<ChannelSftp> sftpPoolConfig) {
        this.genericObjectPool = new GenericObjectPool<>(sftpFactory, sftpPoolConfig);
    }

    public ChannelSftp borrowObject() throws Exception {
        return genericObjectPool.borrowObject();
    }

    public void returnObject(ChannelSftp obj) {
        genericObjectPool.returnObject(obj);
    }
}
