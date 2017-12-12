package com.wdl.spring.service;

import com.wdl.spring.dao.ITestMapper;
import com.wdl.spring.dao.UserMapper;
import com.wdl.spring.model.Goods;
import com.wdl.spring.model.User;
import com.wdl.spring.model.UserKey;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
    private Logger logger = Logger.getLogger(TestServiceImpl.class);

    @Autowired
    private ITestMapper testMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    IGoodsService goodsService;

    @Override
    public void test() {
        logger.debug("test start");
    }

    @Override
    public int deleteByPrimaryKey(UserKey key) {
        return userMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(UserKey key) {
        return userMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        logger.info("**********************start*********************");
        int result = userMapper.updateByPrimaryKey(record);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@result:" + result);

        Goods goods = new Goods();
        goods.setId(1);
        goods.setName("apple");
        goods.setCount(101);
        int result1 = goodsService.updateByPrimaryKeySelective(goods);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@result1:" + result1);

      /*  record.setUid("admin");
        insertSelective(record);*/
        int i = 1 / 0;
        try {
            //int i = 1 / 0;
        } catch (Exception e) {

        }

        logger.info("**************end*************");
        return result;
    }
}
