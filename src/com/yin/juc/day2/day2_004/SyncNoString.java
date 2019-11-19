package com.yin.juc.day2.day2_004;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * String常量不要作为锁
 */
public class SyncNoString {

    String string = "abc";
    String string1 = "abc";

    void m() {
        synchronized (string) {
            ThreadHelper.sleep(5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() +" entered m");
        }
    }

    void m1() {
        synchronized (string1) {
            System.out.println(Thread.currentThread().getName() +" entered m1");
        }
    }

    public static void main(String[] args) {
        SyncNoString syncNoString = new SyncNoString();
        new Thread(syncNoString::m, "thread1").start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        new Thread(syncNoString::m1, "thread2").start();

    }
}
