package cn.fengfan.sftptest.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import java.io.InputStream;

/**
 * Created by fengfan on 2021/11/30 21:20
 */
// sftp辅助类
public class SftpUtil {

    private final SftpPool pool;

    public SftpUtil(SftpPool pool) {
        this.pool = pool;
    }

    /**
     * 下载文件
     *
     * @param dir  远程目录
     * @param name 远程文件名
     * @return 文件字节数组
     */
    public InputStream download(String dir, String name) throws Exception {
        ChannelSftp sftp = pool.borrowObject();
        try {
            sftp.cd(dir);
            return sftp.get(name);
        } finally {
            pool.returnObject(sftp);
        }
    }

    /**
     * 上传文件
     *
     * @param dir  远程目录
     * @param name 远程文件名
     * @param in   输入流
     */
    public void upload(String dir, String name, InputStream in) throws Exception {
        ChannelSftp sftp = pool.borrowObject();
        try {
            mkdirs(sftp, dir);
            sftp.cd(dir);
            sftp.put(in, name);
        } finally {
            pool.returnObject(sftp);
        }
    }

    /**
     * 删除文件
     *
     * @param dir  远程目录
     * @param name 远程文件名
     */
    public void delete(String dir, String name) throws Exception {
        ChannelSftp sftp = pool.borrowObject();
        try {
            sftp.cd(dir);
            sftp.rm(name);
        } finally {
            pool.returnObject(sftp);
        }
    }

    /**
     * 递归创建多级目录
     *
     * @param dir 多级目录
     */
    private void mkdirs(ChannelSftp sftp, String dir) throws SftpException {
        String[] folders = dir.split("/");
        sftp.cd("/");
        for (String folder : folders) {
            if (folder.length() > 0) {
                try {
                    sftp.cd(folder);
                } catch (Exception e) {
                    sftp.mkdir(folder);
                    sftp.cd(folder);
                }
            }
        }
    }

}
