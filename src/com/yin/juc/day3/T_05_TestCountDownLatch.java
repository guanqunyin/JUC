package com.yin.juc.day3;

import com.yin.helper.ThreadHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/20.
 */
public class T_05_TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        final Object object = new Object();
        List<Thread> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName());
                        ThreadHelper.sleep(1, TimeUnit.SECONDS);
                        countDownLatch.countDown();
                    }
                }
            }));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }

        System.out.println("begin to latch");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end the latch");

    }
}
