package com.yin.juc.day2.day2_005_AtomicXxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/*
 * Windows：
 *    ThreadNumber: 2
         *    synchronized 22ms
         *    Atomic       9ms
         *    LongAdder    10ms
     ThreadNumber: 10
        *    synchronized 61ms
        *    Atomic       31ms
        *    LongAdder    15ms
     ThreadNumber: 2000
        *    synchronized 8803ms
        *    Atomic       6528ms
        *    LongAdder    621ms
 */
public class T02_AtomicVsSyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        T02_AtomicVsSyncVsLongAdder atomic = new T02_AtomicVsSyncVsLongAdder();
        final int loopNumber = 100000;
        final int ThreadNumber = 2000;
        Thread[] threads = new Thread[ThreadNumber];
        final Object object = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < loopNumber; j++) {
                        synchronized (object) {
                            count2++;
                        }
                    }
                }


            });
        }

        long start = System.currentTimeMillis();
        System.out.println("start: " + start);
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("end: " + end);
        System.out.println("synchronized Time elapsed: " + (end-start) + "ms");
        System.out.println(count2);
        System.out.println("=======================");

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < loopNumber; j++) {
                        count1.incrementAndGet();
                    }
                }
            });
        }

        long start1 = System.currentTimeMillis();
        System.out.println("start: " + start);
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end1 = System.currentTimeMillis();
        System.out.println("end: " + end);
        System.out.println("AtomicXxx Time elapsed: " + (end1-start1) + "ms");
        System.out.println(count1.get());
        System.out.println("=======================");

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < loopNumber; j++) {
                    count3.increment();
                }
            });
        }

        long start2 = System.currentTimeMillis();
        System.out.println("start: " + start);
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end2 = System.currentTimeMillis();
        System.out.println("end: " + end);
        System.out.println("Long Time elapsed: " + (end2-start2) + "ms");
        System.out.println(count3.longValue());
        System.out.println("=======================");

    }
}
