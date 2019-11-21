package com.yin.juc.day3;

import com.yin.helper.ThreadHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 *        -(true)则为公平锁，如果有另一个线程持有锁 或者有其他线程在等待队列中等待这个锁，
 *                          那么新发出的请求的线程将被放入到队列中
 *        -false则为非公平锁,只有当锁被某个线程持有时，新发出请求的线程才会被放入队列中（此时和公平锁是一样的）
 *
 *  区别： 它们的差别在于非公平锁会有更多的机会去抢占锁。非公平锁有一个抢占锁的过程。
 */
public class T05_ReentrantLock5 extends Thread{

    final static Lock lock = new ReentrantLock(true);

    void m1() {
        try {
            lock.lock();
            ThreadHelper.sleep(5, TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
        } finally {
            lock.unlock();
        }
    }

    //from mashibing
    public void run() {
        for(int i=0; i<1000; i++) {
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        //这个例子不够贴切
        /*T05_ReentrantLock5 t05_reentrantLock5 = new T05_ReentrantLock5();
        new Thread(t05_reentrantLock5::m1, "Thread-1").start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        List<Thread> list = new ArrayList();
        for (int i = 2; i < 10; i++) {
            System.out.println(i + " is in the wait set");
            list.add(new Thread(t05_reentrantLock5::m2, "Thread-"+i));
//            ThreadHelper.sleep(1, TimeUnit.MICROSECONDS);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }
        System.out.println("========================");*/

        //这个例子更加贴切一些
        /*
        * 比如， 线程1获取锁之后，此时线程2阻塞在获取锁，线程1释放锁之后，当线程1去和2争锁的情况发生时，
        * 如果是公平锁，那么先等待的会先获取锁，即线程2获得锁，即会有1，2交替输出的情况，
        * 如果是非公平锁，则会发生抢占锁的可能，即线程1还有可能再次获得锁
        * */
        T05_ReentrantLock5 rl=new T05_ReentrantLock5();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}
