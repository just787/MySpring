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
        int result = userMapper.updateByPrimaryKey(record);
        return result;
    }
}
