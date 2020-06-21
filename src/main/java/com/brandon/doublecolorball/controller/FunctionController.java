package com.brandon.doublecolorball.controller;

import com.brandon.doublecolorball.service.FunctionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/function", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FunctionController {
    @Resource(name = "functionServiceImpl")
    FunctionService functionService;


    @RequestMapping(value = "/initRecommendationGroup", method = RequestMethod.POST)
    public void initRecommendationGroup() throws Exception {
        functionService.initRecommendationGroup();
    }

}
