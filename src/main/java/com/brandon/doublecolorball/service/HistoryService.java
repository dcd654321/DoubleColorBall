package com.brandon.doublecolorball.service;

import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public interface HistoryService {
    public List<History> getAllHis();

    public History getHisByPhase(int phase);

    public void insertHis(History his);

    public void deleteAllHis();

    public void iniHis() throws IOException;
}
