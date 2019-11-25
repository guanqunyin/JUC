package com.yin.juc.day2.day2_005_AtomicXxx;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 写一个程序，证明AtomXXX类比synchronized更高效.
 */
public class TestAtomic {

    AtomicInteger number;

    void m(){
        for (int i = 0; i < 10000; i++) {
            number.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        TestAtomic atomic = new TestAtomic();
        new Thread(atomic::m);
    }
}
