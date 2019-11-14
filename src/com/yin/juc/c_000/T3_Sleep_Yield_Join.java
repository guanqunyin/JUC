package com.yin.juc.c_000;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 */
public class T3_Sleep_Yield_Join {

    public static void main(String[] args) {
        //testSleep();
        //testYield();
        testJoin();
    }


    public static void testSleep(){
        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
                System.out.println("A - " + "begin");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("A - " + i + "end");
//            }
        }).start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("B - " + "begin");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B - " + i + "end");
            }
        }).start();
    }

    public static void testYield() {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": A - " + i);
                if(i%10==0) Thread.yield();
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": B -" + i);
                if(i%10==0) Thread.yield();
            }

        }).start();
    }

    public static void testJoin() {

        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                ThreadHelper.sleep(1, TimeUnit.SECONDS);
            }
        });
        thread1.start();

        new Thread(()->{
            System.out.println("t2 is running");
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
