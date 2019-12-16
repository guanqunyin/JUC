package com.yin.juc.day2.day2_005_AtomicXxx;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*

 * 原子性：原子性是指在一个操作中就是cpu不可以在中途暂停然后再调度，既不被中断操作，要不执行完成，要不就不执行。
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * 原因： 当值为999时，Thread-1 判断atomicInteger.get()<1000时，
 * 线程切换到了另一个Thread-2， Thread-2判断atomicInteger.get()<1000， 并加了1，再回来就会多加一次
 */
public class T02_TestAtomic3NotAtomicity {

    AtomicInteger atomicInteger = new AtomicInteger();

    void m1() {
        for (int i = 0; i < 10000; i++) {
            if (atomicInteger.get()<1000)
                atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T02_TestAtomic3NotAtomicity atomic3NotAtomicity = new T02_TestAtomic3NotAtomicity();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            Thread thread = new Thread(atomic3NotAtomicity::m1, "Thread-"+i);
            threads[i] = thread;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(atomic3NotAtomicity.atomicInteger.get());
    }
}
