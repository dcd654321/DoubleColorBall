package com.brandon.doublecolorball.service.impl;

import com.brandon.doublecolorball.domain.Function;
import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.domain.RecommendationGroup;
import com.brandon.doublecolorball.mapper.FunctionMapper;
import com.brandon.doublecolorball.mapper.HistoryMapper;
import com.brandon.doublecolorball.mapper.RecommendationGroupMapper;
import com.brandon.doublecolorball.service.FunctionService;
import com.brandon.doublecolorball.service.HistoryService;
import com.brandon.doublecolorball.utils.FunctionGroup;
import com.brandon.doublecolorball.utils.PubUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Resource(name = "functionGroup")
    private FunctionGroup functionGroup;
    @Resource(name = "historyMapper")
    private HistoryMapper historyMapper;
    @Resource(name = "functionMapper")
    private FunctionMapper functionMapper;
    @Resource(name = "recommendationGroupMapper")
    private RecommendationGroupMapper recommendationGroupMapper;

    /**
     * 根据算法编号动态执行算法
     *
     * @param functionCode
     * @param phase
     * @param num
     * @return
     * @throws Exception
     */
    public List<RecommendationGroup> executeFunctionByCode(String functionCode, int phase, int num) throws Exception {
        Class s = functionGroup.getClass();
        Method method = s.getDeclaredMethod(functionCode, Integer.class, Integer.class);
        List<RecommendationGroup> list = (List<RecommendationGroup>) method.invoke(functionGroup, phase, num);

        return list;
    }

    /**
     * 初始化荐号表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initRecommendationGroup() throws Exception {
        List<Integer> listPhase = historyMapper.getPhaseListByNum(500);
        listPhase.add(0, listPhase.get(0) + 1);

        List<Function> listFunc = functionMapper.getAllFuncs();
        for (Function func : listFunc) {
            for (int phase : listPhase) {
                List<RecommendationGroup> list = executeFunctionByCode(func.getCode(), phase, 5);
                for(RecommendationGroup recommendationGroup:list){
                    recommendationGroupMapper.insertRecommendationGroup(recommendationGroup);
                }
            }
        }
    }
}
