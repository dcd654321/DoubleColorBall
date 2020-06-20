package com.brandon.doublecolorball.mapper;

import com.brandon.doublecolorball.domain.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryMapper {
    public List<History> getAllHis();

    public History getHisByPhase(int phase);

    public void insertHis(History his);

    public void deleteAllHis();
}
