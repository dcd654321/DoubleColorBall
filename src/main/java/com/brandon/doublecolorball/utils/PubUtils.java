package com.brandon.doublecolorball.utils;

import com.brandon.doublecolorball.domain.DoubleColorBall;
import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.domain.RecommendationGroup;
import com.brandon.doublecolorball.service.HistoryService;
import com.brandon.doublecolorball.service.impl.HistoryServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Component
public class PubUtils {
    /**
     * 获取主键UUID
     *
     * @return
     */
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据输入的最大最小值生成随机数
     *
     * @param min
     * @param max
     * @return
     * @throws Exception
     */
    public static int genRandom(int min, int max) throws Exception {
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;

        return num;
    }

    /***
     * 将推荐号类对象中的双色球提取成Map
     * @param doubleColorBall
     * @return
     */
    public static Map<String, Integer> getBallMapByDoubleColorBall(DoubleColorBall doubleColorBall) throws Exception {
        if (doubleColorBall == null) {
            throw new Exception("com.brandon.doublecolorball.utils.PubUtils.getBallMapByRecommendationGroup:" +
                    "doubleColorBall！");
        }

        Map<String, Integer> ballsMap = new HashMap<>();
        Class s = doubleColorBall.getClass();

        for (int i = 1; i <= 6; i++) {
            Method getRedMethod = s.getDeclaredMethod("getRed" + i);
            int redBall = (int) getRedMethod.invoke(doubleColorBall);
            ballsMap.put("red" + i, redBall);
        }

        ballsMap.put("phase", doubleColorBall.getPhase());
        ballsMap.put("blue", doubleColorBall.getBlue());

        return ballsMap;
    }

    /**
     * 根据红球篮球中奖数计算中奖等级
     * 一等奖：6 + 1
     * 二等奖：6 + 0
     * 三等奖：5 + 1
     * 四等奖：5 + 0 或 4 + 1
     * 五等奖：4 + 0 或 3 + 1
     * 六等奖：2 + 1 或 1 + 1 或 0 + 1
     *
     * @param redRightNum
     * @param blueRightNum
     * @return
     */
    public static int calGradeByRightNum(int redRightNum, int blueRightNum) {
        int grade = 0;
        if (redRightNum == 6 && blueRightNum == 1) {
            // 一等奖：6 + 1
            grade = 1;
        } else if (redRightNum == 6 && blueRightNum == 0) {
            // 二等奖：6 + 0
            grade = 2;
        } else if (redRightNum == 5 && blueRightNum == 1) {
            // 三等奖：5 + 1
            grade = 3;
        } else if ((redRightNum == 5 && blueRightNum == 0)
                || (redRightNum == 4 && blueRightNum == 1)) {
            // 四等奖：5 + 0 或 4 + 1
            grade = 4;
        } else if ((redRightNum == 4 && blueRightNum == 0)
                || (redRightNum == 3 && blueRightNum == 1)) {
            // 五等奖：4 + 0 或 3 + 1
            grade = 5;
        } else if ((redRightNum == 2 && blueRightNum == 1)
                || (redRightNum == 1 && blueRightNum == 1)
                || (redRightNum == 0 && blueRightNum == 1)) {
            // 六等奖：2 + 1 或 1 + 1 或 0 + 1
            grade = 6;
        }

        return grade;
    }

    /**
     * 从指定URI获取双色球开奖历史
     * @param uri
     * @return
     * @throws IOException
     */
    public static List<History> getDoubeColorBallHistoryFromWeb(String uri) throws IOException {
        List<History> hisList = new ArrayList<History>();

        //读取目的网页URL地址，获取网页源码
        URL url = new URL(uri);
        HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"gb2312"));
        StringBuilder sb = new StringBuilder();
        String line;
        int tbodyNum = 0;
        List<String[]> dataList = new ArrayList<String[]>();
        String[] trArr;
        while ((line = br.readLine()) != null) {

            if(line.indexOf("tbody")> -1){
                tbodyNum ++;
            }

            if(tbodyNum == 1){
                if(line.indexOf("<td>") > -1){
                    line = line.replaceAll("<tr>", "");
                    line = line.replaceAll("</tr>", "");
                    line = line.replaceAll("<td>", "");
                    trArr = line.split("</td>");
                    dataList.add(trArr);
                }
            }

        }
        is.close();
        br.close();

        // 取球号
        String[] data;
        String[] ballNum;
        History his;
        for(int i=0;i<dataList.size();i++){
            data = dataList.get(i);
            String ballStr = data[3];
            ballStr = ballStr.replaceAll("\\+", " ");
            ballNum = ballStr.split(" ");

            if(ballNum.length>0){
                his = new History();
                his.setPhase(Integer.parseInt(data[0]));
                his.setRed1(Integer.parseInt(ballNum[0]));
                his.setRed2(Integer.parseInt(ballNum[1]));
                his.setRed3(Integer.parseInt(ballNum[2]));
                his.setRed4(Integer.parseInt(ballNum[3]));
                his.setRed5(Integer.parseInt(ballNum[4]));
                his.setRed6(Integer.parseInt(ballNum[5]));
                his.setBlue(Integer.parseInt(ballNum[6]));

                hisList.add(his);
            }
        }

        return hisList;
    }

}
