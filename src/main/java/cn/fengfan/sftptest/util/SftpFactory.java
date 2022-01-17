package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Properties;

/**
 * Created by fengfan on 2021/11/30 21:16
 */
public class SftpFactory extends BasePooledObjectFactory<ChannelSftp> {

    private SftpProperties properties;

    public SftpFactory(SftpProperties properties) {
        this.properties = properties;
    }

    @Override
    public ChannelSftp create() throws JSchException {
        JSch jsch = new JSch();
        Session sshSession = jsch.getSession(properties.getUsername(), properties.getHost(), properties.getPort());
        sshSession.setPassword(properties.getPassword());
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        ChannelSftp channel = (ChannelSftp) sshSession.openChannel("sftp");
        channel.connect();
        return channel;
    }

    @Override
    public PooledObject<ChannelSftp> wrap(ChannelSftp channelSftp) {
        return new DefaultPooledObject<>(channelSftp);
    }

    /**
     * 销毁对象
     *
     * @param p
     * @return
     * @author fengfan
     * @date 2022/1/14 15:26
     */
    @Override
    public void destroyObject(PooledObject<ChannelSftp> p) {
        ChannelSftp channelSftp = p.getObject();
        channelSftp.disconnect();
    }

    /**
     * 激活连接池里面的sftp连接
     *
     * @param p
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<ChannelSftp> p) throws Exception {
        ChannelSftp channelSftp = p.getObject();
        if(!channelSftp.isConnected()){
            channelSftp.connect();
        }

    }

    public SftpProperties getProperties() {
        return properties;
    }

    public void setProperties(SftpProperties properties) {
        this.properties = properties;
    }
}
