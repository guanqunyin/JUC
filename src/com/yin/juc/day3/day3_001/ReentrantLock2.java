package com.yin.juc.day3.day3_001;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/11/20.
 */
public class ReentrantLock2 {

    private final Lock lock = new ReentrantLock();

    void m1() {
        try{
            lock.lock();
            for (int i = 0; i < 10; i++) {
                ThreadHelper.sleep(1, TimeUnit.SECONDS);
                System.out.println(i);
                if (i==2) m2();
            }
        } finally {
            lock.unlock();
        }

    }

     void m2() {
        try{
          lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLock1 reentrantLock1 = new ReentrantLock1();
        new Thread(reentrantLock1::m1).start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);
//        new Thread(reentrantLock1::m2).start();
    }

}
