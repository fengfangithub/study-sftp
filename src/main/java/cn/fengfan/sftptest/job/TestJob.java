package cn.fengfan.sftptest.job;

import cn.fengfan.sftptest.parsing.ParsingThread;
import cn.fengfan.sftptest.util.SftpUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by fengfan on 2021/12/2 15:20
 */
@JobHandler(value = "TestJob")
@Component
public class TestJob extends IJobHandler {
    Logger logger = LoggerFactory.getLogger(TestJob.class);
    @Resource
    private SftpUtil sftpUtil;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public ReturnT<String> execute(String s) {
        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        logger.info("job开始");
        ParsingThread parsingThread = new ParsingThread(sftpUtil);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> threadPoolExecutor.execute(parsingThread)).start();
        }
        logger.info("job结束");
        return IJobHandler.SUCCESS;
    }
}
