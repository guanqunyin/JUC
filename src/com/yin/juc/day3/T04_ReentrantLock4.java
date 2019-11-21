package com.yin.juc.day3;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock.lockInterruptibly()方法
 */
public class T04_ReentrantLock4 {

    final Lock lock = new ReentrantLock();

    void m1(){
        boolean interrupted = false;
        try {
            lock.lockInterruptibly();

        } catch (InterruptedException e) {
            System.out.println("I was catched");
            interrupted = true;
            e.printStackTrace();
        } finally {
            if (!interrupted)
                lock.unlock();
        }
    }


    void m2(){
        try {
            lock.lock();
            ThreadHelper.sleep(60, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T04_ReentrantLock4 t04_reentrantLock4 = new T04_ReentrantLock4();
        //让t2先启动并且获得锁，并持续占有锁60秒
        new Thread(t04_reentrantLock4::m2).start();
        //让当前线程启动， 保证上一个线程先启动
        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        //让m1方法启动，此时它应该处于waitset中，因为锁还在被上面那个线程占有
        Thread thread = new Thread(t04_reentrantLock4::m1);
        thread.start();
        //让当前线程睡眠一秒之后，尝试去中断启动m1的那个线程
        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        thread.interrupt();


    }
}
