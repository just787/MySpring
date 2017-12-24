import com.alibaba.fastjson.JSON;
import com.wdl.spring.controller.TestController;
import com.wdl.spring.model.User;
import com.wdl.spring.service.ITestService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 接口测试demo。 注意：当类中有多线程进行接口测试时候，主线程(即Tester类)的事务回滚无效，此时需要在@After方法中还原数据
 *
 * @author wuduolin
 */
@RunWith(SpringJUnit4ClassRunner.class) // 让测试运行于Spring测试环境
// 导入spring配置文件
@ContextConfiguration(locations = {"classpath:spring.xml"})
// 在需要事务管理的地方加 @Transactional 注解。 @Transactional注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。
/*@Transactional(transactionManager = "transactionManager")
@Rollback(value = false)*/
public class Tester {
    Logger logger = Logger.getLogger(Tester.class);
    // 接口注入
    @Autowired
    private ITestService testService;

    @Autowired
    private TestController testController;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        try {
            //MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
            //mockHttpServletRequest.addParameter("name", "wdl");
            //String r = testController.test(mockHttpServletRequest, "abc");

            //ValueOperations operations = redisTemplate.opsForValue();
            //operations.set("option1", "v1");



            ZSetOperations<String, User> zSetOperations = redisTemplate.opsForZSet();
            User user1 = new User();
            user1.setUid("u1");
            user1.setName("name1");
            user1.setId(1);

            User user2 = new User();
            user2.setUid("u2");
            user2.setName("name2");
            user1.setId(2);


            Set<ZSetOperations.TypedTuple<User>> userTypedTuple = new HashSet<ZSetOperations.TypedTuple<User>>();

            ZSetOperations.TypedTuple<User> typedTuple = new DefaultTypedTuple<User>(user1,1.0);
            ZSetOperations.TypedTuple<User> typedTuple1 = new DefaultTypedTuple<User>(user2,2.0);

            userTypedTuple.add(typedTuple);
            userTypedTuple.add(typedTuple1);

            zSetOperations.add("sk",userTypedTuple);

            Set<User> users = zSetOperations.range("sk",0,0);
            System.out.println("*****************************");
            System.out.println(JSON.toJSON(users.contains(user1)));
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