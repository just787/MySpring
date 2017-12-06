import com.wdl.spring.service.ITestService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * 接口测试demo。 注意：当类中有多线程进行接口测试时候，主线程(即Tester类)的事务回滚无效，此时需要在@After方法中还原数据
 *
 * @author wuduolin
 */
@RunWith(SpringJUnit4ClassRunner.class) // 让测试运行于Spring测试环境
// 导入spring配置文件
@ContextConfiguration(locations = {"classpath:spring.xml"})
@Transactional // 在需要事务管理的地方加 @Transactional 注解。 @Transactional
// 注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false) // 事务回滚,以免改动数据库
public class Tester {
    Logger logger = Logger.getLogger(Tester.class);
    // 接口注入
    @Autowired
    private ITestService testService;

    @Test
    public void test() {
        try {
            testService.test();
        } catch (Exception e) {
            logger.error(e);
        }

        //Assert.assertTrue(true);
    }

    @After
    public void destroy() {
        // 若test()方法多线程调用接口,则需要在此方法还原数据
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
            return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}