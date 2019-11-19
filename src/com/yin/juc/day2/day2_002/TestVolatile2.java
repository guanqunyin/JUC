package com.yin.juc.day2.day2_002;

import java.util.ArrayList;
import java.util.List;

/**
 * number++不是原子性的操作，volatile只能保证number在线程之间的可见性 取值， number++， 赋值
 */
public class TestVolatile2 {

    volatile int number = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            number++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestVolatile2 testVolatile2 = new TestVolatile2();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(testVolatile2::m);
            thread.start();
            threadList.add(thread);
        }

        for (int i = 0; i < threadList.size(); i++) {
            Thread thread = threadList.get(i);
            thread.join();
        }

        System.out.println(testVolatile2.number);
    }
}
