package com.yin.juc.day3.day3_001;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/20.
 */
public class ReentrantLock1 {

    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            ThreadHelper.sleep(1, TimeUnit.SECONDS);
            System.out.println(i);
            if (i==2) m2();
        }
    }

    synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        ReentrantLock1 reentrantLock1 = new ReentrantLock1();
        new Thread(reentrantLock1::m1).start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);
//        new Thread(reentrantLock1::m2).start();
    }

}
