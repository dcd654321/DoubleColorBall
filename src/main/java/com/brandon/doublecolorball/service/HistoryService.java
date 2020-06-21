package com.brandon.doublecolorball.service;

import com.brandon.doublecolorball.domain.History;

import java.io.IOException;
import java.util.List;

public interface HistoryService {
    public List<History> getAllHis();

    public History getHisByPhase(Integer phase);

    public void insertHis(History his);

    public void deleteAllHis();

    public void initHis() throws IOException;

}
