package com.wdl.spring.service;

import com.wdl.spring.dao.ITestMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements ITestService {
    private Logger logger = Logger.getLogger(TestServiceImpl.class);

    @Autowired
    private ITestMapper testMapper;

    @Override
    public void test() {
        logger.debug("test start");
        testMapper.test();
        logger.info("test end");
    }
}
