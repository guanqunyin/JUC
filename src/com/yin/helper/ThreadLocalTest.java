package com.yin.helper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2020/3/15.
 */
public class ThreadLocalTest {

    static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantLock reentrantLock = new ReentrantLock();
    ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();



    public void read() {
        Thread currentThread = Thread.currentThread();
        try {
            System.out.println(currentThread.getName() + "开始尝试获取读锁");
            readLock.lock();
            System.out.println(currentThread.getName() + "获取了读锁");
        } finally {
            System.out.println(currentThread.getName() + "释放读锁");
            readLock.unlock();
        }
    }

    public void write() {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(currentThread.getName() + "获取了写锁");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(currentThread.getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        /*stringThreadLocal.set("test");
        stringThreadLocal.set("test1");
        System.out.println(stringThreadLocal.get());
        ReentrantLock lock = new ReentrantLock();
        lock.getHoldCount();
        lock.getQueueLength();*/
//        lock.getWaitQueueLength();
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        new Thread(()->threadLocalTest.read()).start();
        /*new Thread(()->threadLocalTest.read()).start();
        new Thread(()->threadLocalTest.read()).start();
        new Thread(()->threadLocalTest.read()).start();
        new Thread(()->threadLocalTest.read()).start();
        new Thread(()->threadLocalTest.read()).start();
        new Thread(()->threadLocalTest.read()).start();*/
//        new Thread(()->threadLocalTest.write()).start();


    }
}
