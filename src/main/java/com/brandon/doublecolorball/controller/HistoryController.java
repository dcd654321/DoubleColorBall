package com.brandon.doublecolorball.controller;

import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.service.HistoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HistoryController {
    @Resource(name = "historyServiceImpl")
    private HistoryService hisService;

    @RequestMapping(value = "/getAllHis",method = RequestMethod.GET)
    public List<History> getAllHis(){
        return hisService.getAllHis();
    }

    @RequestMapping(value = "/getHisByPhase/{phase}",method = RequestMethod.GET)
    public History getHisByPhase(@PathVariable int phase){
        return hisService.getHisByPhase(phase);
    }

    @RequestMapping(value = "/insertHis",method = RequestMethod.POST)
    public void insertHis(History his){
        hisService.insertHis(his);
    }

    @RequestMapping(value = "/deleteAllHis",method = RequestMethod.DELETE)
    public void deleteAllHis(){
        hisService.deleteAllHis();
    }

    @RequestMapping(value = "/initHis",method = RequestMethod.POST)
    public void initHis() throws IOException {
        hisService.initHis();
    }

}
