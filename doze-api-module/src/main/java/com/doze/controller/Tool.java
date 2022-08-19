package com.doze.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Tool {

    //48,57,65,90,97,122
    public static String getFileName(){
        Date date = new Date();
        Random rd =new Random();
        char[] str= {97,98,99,100,101,102,103,104,105,106,107};//乱序前的数组
        for (int i = 0; i < str.length; i++) {
            int c = rd.nextInt(str.length - i);//接收每次产生的随机数
            char tem = str[str.length - i - 1];
            str[str.length-1-i] =str[c];
            str[c] = tem;
        }
        return String.valueOf(str)+date.getTime();
    }
}
