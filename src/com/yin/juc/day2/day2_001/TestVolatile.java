package com.yin.juc.day2.day2_001;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/18.
 */
public class TestVolatile {

    volatile boolean flag = true;

    void m(){
        System.out.println("begin");
        while (flag) {
//            System.out.println("I am running!!!");
        }
        System.out.println("end");
        System.out.println(flag);
    }

    public static void main(String[] args) {
        TestVolatile  testVolatile = new TestVolatile();
        new Thread(testVolatile::m, "t1").start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        System.out.println("I am here");
        testVolatile.flag = false;
        System.out.println("I was setted");
    }
}
