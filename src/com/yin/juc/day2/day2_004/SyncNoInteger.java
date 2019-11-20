package com.yin.juc.day2.day2_004;

import com.yin.helper.ThreadHelper;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class SyncNoInteger {

    /*
    * Integer常量不要作为锁, 可以使用new Integer对象
    * */
    Integer integer = 3;

    Integer integer1 = 3;

    void m() {
        synchronized (integer) {
            System.out.println("m begin to sleep");
            ThreadHelper.sleep(5, TimeUnit.SECONDS);
            System.out.println("m");
            System.out.println("m end to sleep");
        }
    }

    void m1() {
        System.out.println("m1 was executed");
        synchronized (integer1) {
            System.out.println("m1");
        }
    }

    public static void main(String[] args) {
        SyncNoInteger syncNoInteger = new SyncNoInteger();
        new Thread(syncNoInteger::m, "thread1").start();

        System.out.println("main sleep 1 second");
        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        System.out.println("main wake up");
        new Thread(syncNoInteger::m1, "thread2").start();

    }
}
