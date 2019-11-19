package com.yin.juc.day2.day2_002;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 锁优化
 * 同步代码块中的语句越少越好
 * 参考m1和m2
 */
public class FineCoarseLock {

    int count = 0;

    synchronized void m1() {
        //do something need not sync

        ThreadHelper.sleep(2, TimeUnit.SECONDS);

        //业务逻辑中只有这一块儿需要加锁
        count++;

        //do something need not sync
        ThreadHelper.sleep(2, TimeUnit.SECONDS);

    }

    void m2() {
        //do something need not sync

        ThreadHelper.sleep(2, TimeUnit.SECONDS);

        //业务逻辑中只有这一块儿需要加锁
        //此时采用细粒度的锁，可以使线程争用时间变短，从而提升效率。
        synchronized (this) {
            count++;
        }

        //do something need not sync
        ThreadHelper.sleep(2, TimeUnit.SECONDS);

    }

}
