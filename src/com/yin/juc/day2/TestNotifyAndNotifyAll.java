package com.yin.juc.day2;

import com.yin.helper.ThreadHelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/20.
 */
public class TestNotifyAndNotifyAll {

    synchronized void m()  {
        try {
            System.out.println(Thread.currentThread().getName() + " is wait");
            //wait 会把锁释放
            this.wait();
            System.out.println((Thread.currentThread().getName() + " is notified"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void notifym(){
        this.notify();
    }

    public static void main(String[] args) {
        TestNotifyAndNotifyAll testNotifyAndNotifyAll = new TestNotifyAndNotifyAll();
        List<Thread> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(testNotifyAndNotifyAll::m, new Integer(i).toString()));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }

        ThreadHelper.sleep(3, TimeUnit.SECONDS);

        synchronized (testNotifyAndNotifyAll) {
//            for (int i = 0; i < 10; i++) {
                testNotifyAndNotifyAll.notifyAll();
//            }
        }

    }
}
