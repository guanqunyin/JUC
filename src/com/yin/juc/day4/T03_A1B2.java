package com.yin.juc.day4;

import com.yin.helper.ThreadHelper;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
要求用线程交替打印A1B2C3...Z26
*
*
* */
public class T03_A1B2 {

    static boolean Thread1ExecutedFlag = false;

    public static void main(String[] args) {
        final Object object = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (char i = 'A'; i <= 'Z'; i++) {
                        if (!Thread1ExecutedFlag) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(i);
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 1; i <= 26; i++) {
                        try {
                            if (!Thread1ExecutedFlag) {
                                Thread1ExecutedFlag = true;
                                object.notify();
                            }
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                        object.notify();
                    }
                }

            }
        });
        thread1.start();
//        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        thread.start();
    }
}
