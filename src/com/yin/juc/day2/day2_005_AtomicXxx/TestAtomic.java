package com.yin.juc.day2.day2_005_AtomicXxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序，证明AtomXXX类比synchronized更高效.
 *     ThreadNumber: 2
 *          sync: 24ms
 *          Atomic: 15ms
 *     ThreadNumber: 10
*  *        sync: 51ms
 *  *       Atomic: 31ms
 *  ThreadNumber: 100
 *          sync: 485ms
 *          Atomic: 210ms
 *   ThreadNumber: 1000
 * *  *        sync: 4500ms
 *  *  *       Atomic: 1968ms
 */
public class TestAtomic {

    AtomicInteger number = new AtomicInteger();

    final Object object = new Object();

    int j = 0;

    //1871 ms
    void m(){
        for (int i = 0; i < 100000; i++) {
            number.incrementAndGet();
        }
    }

    //58 ms
    void m1() {
        for (int i = 0; i < 100000; i++) {
            synchronized(object) {
                j++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestAtomic atomic = new TestAtomic();
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(atomic::m1);
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
        System.out.println("Sync Time elapsed: " + (end-start) + "ms");
        System.out.println(atomic.j);

//        Integer[] integers = new Integer[10];
        //foreach 不能赋值，相当于 int i = intger[0], i=1;
        /*for (int i = 0; i < 10; i++) {
            integers[i] = i;
        }
        for (Integer i : integers) {
            System.out.println(i);
        }*/
        System.out.println("======================");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(atomic::m);
        }

        long start1 = System.currentTimeMillis();
        System.out.println("start: " + start1);
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end1 = System.currentTimeMillis();
        System.out.println("end: " + end);
        System.out.println("Atomic Time elapsed: " + (end1-start1) + "ms");
        System.out.println(atomic.j);
    }
}
