package com.brandon.doublecolorball.service.impl;

import com.brandon.doublecolorball.domain.History;
import com.brandon.doublecolorball.mapper.HistoryMapper;
import com.brandon.doublecolorball.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource(name = "historyMapper")
    private HistoryMapper hisMapper;

    @Override
    public List<History> getAllHis() {
        return hisMapper.getAllHis();
    }

    @Override
    public History getHisByPhase(int phase) {
        return hisMapper.getHisByPhase(phase);
    }

    @Override
    public void insertHis(History his) {
        hisMapper.insertHis(his);
    }

    @Override
    public void deleteAllHis() {
        hisMapper.deleteAllHis();
    }

    public void iniHis() throws IOException {
        hisMapper.deleteAllHis();

        List<History>  hisList;
        History his;
        for (int i = 0; i < 5; i++){
            String uri = "http://www.17500.cn/ssq/awardlist.php";
            if(i> 0){
                uri = uri + "?p=" + (i + 1);
            }

            hisList = getDoubeColorBallHistoryFromWeb(uri);
            for(int j = 0; j < hisList.size(); j++){
                his = hisList.get(j);
                hisMapper.insertHis(his);
            }
        }
    }

    public List<History> getDoubeColorBallHistoryFromWeb(String uri) throws IOException {
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
