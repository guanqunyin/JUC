package com.yin.juc.day3;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
/*
* LockSupport, park（）让当前线程停下， unpark(Thread theread)让线程继续往前走
* */

public class TestLockSupport {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    if (i == 5) {
                        LockSupport.park();
                    }
                    ThreadHelper.sleep(1, TimeUnit.SECONDS);
                }
            }
        });

        thread.start();

        ThreadHelper.sleep(8, TimeUnit.SECONDS);
        LockSupport.unpark(thread);
    }
}
