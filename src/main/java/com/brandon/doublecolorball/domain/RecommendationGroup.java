package com.brandon.doublecolorball.domain;

import org.springframework.stereotype.Component;

@Component
public class RecommendationGroup implements DoubleColorBall {
    private String id;
    private String functionCode;
    private Integer phase;
    private Integer groupNum;
    private Integer red1;
    private Integer red2;
    private Integer red3;
    private Integer red4;
    private Integer red5;
    private Integer red6;
    private Integer blue;
    private Integer grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Integer getRed1() {
        return red1;
    }

    public void setRed1(Integer red1) {
        this.red1 = red1;
    }

    public Integer getRed2() {
        return red2;
    }

    public void setRed2(Integer red2) {
        this.red2 = red2;
    }

    public Integer getRed3() {
        return red3;
    }

    public void setRed3(Integer red3) {
        this.red3 = red3;
    }

    public Integer getRed4() {
        return red4;
    }

    public void setRed4(Integer red4) {
        this.red4 = red4;
    }

    public Integer getRed5() {
        return red5;
    }

    public void setRed5(Integer red5) {
        this.red5 = red5;
    }

    public Integer getRed6() {
        return red6;
    }

    public void setRed6(Integer red6) {
        this.red6 = red6;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
