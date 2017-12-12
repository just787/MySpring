package com.wdl.spring.controller;

import com.alibaba.fastjson.JSON;
import com.wdl.spring.model.Goods;
import com.wdl.spring.model.User;
import com.wdl.spring.service.IGoodsService;
import com.wdl.spring.service.ITestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/mvc")
public class TestController {
    private Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    private ITestService testService;
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request, String param) {
        logger.info("******hello *******");

        User user = new User();
        user.setId(1);
        user.setUid("admin");
        user.setName("admin1");

        try {
            testService.updateByPrimaryKey(user);
        } catch (Exception e) {
            logger.error(e);
        }


        User result = testService.selectByPrimaryKey(user);
        Goods result1 = goodsService.selectByPrimaryKey(1);
        return JSON.toJSONString(result) + "\r\n" + JSON.toJSONString(result1);
    }
}
