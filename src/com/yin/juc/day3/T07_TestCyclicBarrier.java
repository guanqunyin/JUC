package com.yin.juc.day3;

import org.omg.IOP.TAG_JAVA_CODEBASE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO 模拟一个栅栏的使用场景， 20个线程到达之后，20个线程可以同时执行某个方法。。。。
 * 比如游乐场的海盗船，当满了20个人之后，那个门才会打开，这20个人在一起走进去做海盗船。
 */
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("门开了");
            }
        });

        List<Thread> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "去坐上了海盗船");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }
    }
}
