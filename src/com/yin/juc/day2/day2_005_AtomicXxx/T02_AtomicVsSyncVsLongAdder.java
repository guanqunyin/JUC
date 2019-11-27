package com.yin.juc.day2.day2_005_AtomicXxx;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/* Mac：
 *    synchronized 59ms
 *    Atomic       2154ms
 *    LongAdder    202ms
 */
public class T02_AtomicVsSyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        T02_AtomicVsSyncVsLongAdder atomic = new T02_AtomicVsSyncVsLongAdder();
        Thread[] threads = new Thread[1000];
        final Object object = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                synchronized (object) {
                    for (int j = 0; j < 100000; j++) {
                        count2++;
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
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
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
                for (int j = 0; j < 100000; j++) {
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
        System.out.println("AtomicXxx Time elapsed: " + (end2-start2) + "ms");
        System.out.println(count3.longValue());
        System.out.println("=======================");

    }
}
