package com.brandon.doublecolorball.utils;

import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.domain.RecommendationGroup;
import com.brandon.doublecolorball.mapper.HistoryMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 荐号算法类
 */
@Component
public class FunctionGroup {
    @Resource(name = "historyMapper")
    private HistoryMapper historyMapper;

    /**
     * 根据传入的期号和组数随机生成n组双色球
     */
    public List<RecommendationGroup> genBallsWithRandomNum(Integer phase, Integer num) throws Exception {
        List<RecommendationGroup> list = new ArrayList<>();
        RecommendationGroup recommendationGroup;

        for (int i = 0; i < num; i++) {
            // 通过反射实现动态调用java方法
            recommendationGroup = new RecommendationGroup();
            Class aClass = recommendationGroup.getClass();

            // 1.设置已知信息
            recommendationGroup.setId(PubUtils.getId());
            recommendationGroup.setFunctionCode("genBallsWithRandomNum");
            recommendationGroup.setPhase(phase);
            recommendationGroup.setGroupNum(i + 1);

            // 2.生成红球
            Map redBalls = new HashMap();
            for (int j = 1; j <= 6; j++) {
                int red = 0;
                while (true) {
                    red = PubUtils.genRandom(1, 33);
                    if (!redBalls.containsValue(red)) {
                        redBalls.put("red" + j, red);
                        break;
                    }
                }

                // 动态执行setRed+n方法
                Method setRed = aClass.getDeclaredMethod("setRed" + j, Integer.class);
                setRed.invoke(recommendationGroup, red);
            }

            // 3.生成篮球
            int blue = PubUtils.genRandom(1, 16);
            recommendationGroup.setBlue(blue);

            // 4.判断中奖等级
            Map<String, Integer> ballsMap = PubUtils.getBallMapByDoubleColorBall(recommendationGroup);
            int grade = calGradeByBallsMap(ballsMap);
            recommendationGroup.setGrade(grade);

            // 5.组装
            list.add(recommendationGroup);
        }

        return list;
    }

    /**
     * 根据双色球号码计算双色球中奖等级
     *
     * @param ballsMap
     * @return
     */
    public int calGradeByBallsMap(Map<String, Integer> ballsMap) throws Exception {
        if (ballsMap == null) {
            throw new Exception("com.brandon.doublecolorball.utils.PubUtils.calGrade:" +
                    "传入的ballsMap为空！");
        }

        int grade = 7; // 未开奖
        int phase = ballsMap.get("phase");

        // 获取对应期次的双色球开奖历史
        History his = historyMapper.getHisByPhase(phase);
        if (his != null) {
            // 将开奖历史中双色球号码提取出来
            Map<String, Integer> mapHis = PubUtils.getBallMapByDoubleColorBall(his);

            // 判断具体中了几个号码
            int redRightNum = 0;
            int blueRightNum = 0;
            for (int i = 1; i <= 6; i++) {
                int redHis = mapHis.get("red" + i);
                for (int j = 1; j <= 6; j++) {
                    int redRecom = ballsMap.get("red" + j);
                    if (redHis == redRecom) {
                        redRightNum++;
                    }
                }
            }

            if (mapHis.get("blue") == ballsMap.get("blue")) {
                blueRightNum++;
            }

            // 计算中奖等级
            grade = PubUtils.calGradeByRightNum(redRightNum, blueRightNum);
        }

        return grade;
    }

}
