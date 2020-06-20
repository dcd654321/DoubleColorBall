package com.brandon.doublecolorball.utils;

import java.util.UUID;

public class PubUtils {
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
