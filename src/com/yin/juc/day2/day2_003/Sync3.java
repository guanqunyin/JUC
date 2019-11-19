package com.yin.juc.day2.day2_003;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，当对象的属性发生改变的时候，不影响锁的使用，
 * 但是如果o变成另一个对象，则锁对象会发生改变
 * 应该避免将锁定对象的引用变成另一个对象, 通常定义为final
 */
public class Sync3 {

    final Object o = new Object();

    void m() {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + ", I will sleep 10 s");
            ThreadHelper.sleep(10, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) {
        Sync3 sync3 = new Sync3();

        new Thread(sync3::m, "Thread1").start();
        new Thread(sync3::m, "Thread2").start();
        new Thread(sync3::m, "Thread3").start();
        new Thread(sync3::m, "Thread4").start();

        ThreadHelper.sleep(5, TimeUnit.SECONDS);
        System.out.println("after 5 seconds");
//        sync3.o = new Object();
        new Thread(sync3::m, "Thread5").start();
        new Thread(sync3::m, "Thread6").start();
        new Thread(sync3::m, "Thread7").start();
        new Thread(sync3::m, "Thread8").start();
        System.out.println("the end");

    }
}
