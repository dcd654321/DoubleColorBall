package com.brandon.doublecolorball.service.impl;

import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.mapper.HistoryMapper;
import com.brandon.doublecolorball.service.HistoryService;
import com.brandon.doublecolorball.utils.PubUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service(value = "historyServiceImpl")
public class HistoryServiceImpl implements HistoryService {
    @Resource(name = "historyMapper")
    private HistoryMapper hisMapper;

    @Override
    public List<History> getAllHis() {
        return hisMapper.getAllHis();
    }

    @Override
    public History getHisByPhase(Integer phase) {
        History his = hisMapper.getHisByPhase(phase);
        return his;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertHis(History his) {
        hisMapper.insertHis(his);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllHis() {
        hisMapper.deleteAllHis();
    }

    @Transactional(rollbackFor = Exception.class)
    public void initHis() throws IOException {
        hisMapper.deleteAllHis();

        List<History>  hisList;
        History his;
        for (int i = 0; i < 5; i++){
            String uri = "http://www.17500.cn/ssq/awardlist.php";
            if(i> 0){
                uri = uri + "?p=" + (i + 1);
            }

            hisList = PubUtils.getDoubeColorBallHistoryFromWeb(uri);
            for(int j = 0; j < hisList.size(); j++){
                his = hisList.get(j);
                hisMapper.insertHis(his);
            }
        }
    }

}
