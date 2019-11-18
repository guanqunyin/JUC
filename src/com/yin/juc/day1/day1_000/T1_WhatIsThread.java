package com.yin.juc.day1.day1_000;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 */
public class T1_WhatIsThread {

    private static class T1 extends Thread{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                ThreadHelper.sleep(1, TimeUnit.MICROSECONDS);
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();

        for (int i = 0; i < 10; i++) {
            ThreadHelper.sleep(1, TimeUnit.MICROSECONDS);
            System.out.println("main");
        }
    }

}
