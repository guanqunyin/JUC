package com.yin.juc.day2.day2_005_AtomicXxx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 写一个程序，证明AtomXXX类比synchronized更高效.
 *     答： Mac下synchronized比AtomicXxx快很多。
 */
public class TestAtomic {

    AtomicInteger number = new AtomicInteger();

    int j = 0;

    //1871 ms
    void m(){
        for (int i = 0; i < 100000; i++) {
            number.incrementAndGet();
        }
    }

    //58 ms
    synchronized void m1() {
        for (int i = 0; i < 100000; i++) {
            j++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestAtomic atomic = new TestAtomic();
        Thread[] threads = new Thread[1];

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
        System.out.println("Time elapsed: " + (end-start) + "ms");
        System.out.println(atomic.j);

//        Integer[] integers = new Integer[10];
        //foreach 不能赋值，相当于 int i = intger[0], i=1;
        /*for (int i = 0; i < 10; i++) {
            integers[i] = i;
        }
        for (Integer i : integers) {
            System.out.println(i);
        }*/
    }
}
