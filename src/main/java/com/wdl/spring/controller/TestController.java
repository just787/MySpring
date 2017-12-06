package com.wdl.spring.controller;

import com.wdl.spring.service.ITestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mvc")
public class TestController {
    private Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    /*@ResponseBody*/
    public String test() {
        //HttpServletRequest request, String param
        logger.info("******hello *******");
        return "login";
    }
}
