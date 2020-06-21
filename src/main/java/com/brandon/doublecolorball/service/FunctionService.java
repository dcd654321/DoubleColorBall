package com.brandon.doublecolorball.service;

import com.brandon.doublecolorball.domain.RecommendationGroup;

import java.util.List;

public interface FunctionService {
    /**
     * 根据算法编号动态执行算法
     * @param functionCode
     * @param phase
     * @param num
     * @return
     * @throws Exception
     */
    public List<RecommendationGroup> executeFunctionByCode(String functionCode, int phase, int num) throws Exception;

    /**
     * 初始化荐号表
     */
    public void initRecommendationGroup() throws Exception;
}
