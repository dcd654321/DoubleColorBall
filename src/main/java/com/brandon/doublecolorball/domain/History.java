package com.brandon.doublecolorball.domain;

import org.springframework.stereotype.Component;

@Component
public class History {
    private Integer phase;
    private Integer red1;
    private Integer red2;
    private Integer red3;
    private Integer red4;
    private Integer red5;
    private Integer red6;
    private Integer blue;

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public void setRed1(Integer red1) {
        this.red1 = red1;
    }

    public void setRed2(Integer red2) {
        this.red2 = red2;
    }

    public void setRed3(Integer red3) {
        this.red3 = red3;
    }

    public void setRed4(Integer red4) {
        this.red4 = red4;
    }

    public void setRed5(Integer red5) {
        this.red5 = red5;
    }

    public void setRed6(Integer red6) {
        this.red6 = red6;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Integer getPhase() {
        return phase;
    }

    public Integer getRed1() {
        return red1;
    }

    public Integer getRed2() {
        return red2;
    }

    public Integer getRed3() {
        return red3;
    }

    public Integer getRed4() {
        return red4;
    }

    public Integer getRed5() {
        return red5;
    }

    public Integer getRed6() {
        return red6;
    }

    public Integer getBlue() {
        return blue;
    }
}
