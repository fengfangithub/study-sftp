package cn.fengfan.sftptest.parsing;

import cn.fengfan.sftptest.util.SftpPool;
import cn.fengfan.sftptest.util.SftpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.UUID;

/**
 * Created by fengfan on 2021/12/2 15:55
 */
public class ParsingThread implements Runnable{
    Logger logger = LoggerFactory.getLogger(ParsingThread.class);
    private final SftpUtil sftpUtil;

    public ParsingThread(SftpPool sftpPool) {
        this.sftpUtil = new SftpUtil(sftpPool);
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getName() + "文件上传开始");
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\fengfan\\Pictures\\Saved Pictures\\timg" + ".jpg");
            String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            sftpUtil.upload("/upload/" + Thread.currentThread().getName() + "/", fileName, fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName() + "文件上传结束");
    }
}
