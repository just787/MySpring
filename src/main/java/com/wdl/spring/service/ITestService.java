package com.wdl.spring.service;

import com.wdl.spring.model.User;
import com.wdl.spring.model.UserKey;

public interface ITestService {
    void test();

    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UserKey key);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
