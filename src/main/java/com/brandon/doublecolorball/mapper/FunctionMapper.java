package com.brandon.doublecolorball.mapper;

import com.brandon.doublecolorball.domain.Function;
import com.brandon.doublecolorball.domain.History;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "functionMapper")
public interface FunctionMapper {
    public List<Function> getAllFuncs();

    public void updateProbabilityByCode(Function function);
}
