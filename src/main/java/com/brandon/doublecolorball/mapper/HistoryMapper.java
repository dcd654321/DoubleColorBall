package com.brandon.doublecolorball.mapper;

import com.brandon.doublecolorball.domain.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "historyMapper")
public interface HistoryMapper {
    public List<History> getAllHis();

    public History getHisByPhase(Integer phase);

    public void insertHis(History his);

    public void deleteAllHis();

    public int getMaxPhase();

    public List<Integer> getPhaseListByNum(Integer num);
}
