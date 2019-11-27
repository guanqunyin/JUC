package com.yin.juc.day4;


import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法， add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * -- 看能否用semaphore实现, 暂时没有想到好的思路
 */
public class T01_Container1_Semaphore<E> {

    private ArrayList<E> list = new ArrayList<>();

    static Thread t1,t2 = null;

    volatile boolean thread2Executed = false;

    void add(E e) {
        list.add(e);
    }

    int size() {
        return list.size();
    }

    public static void main(String[] args) {
        /*T01_Container1_Semaphore list = new T01_Container1_Semaphore();
        Semaphore semaphore = new Semaphore(1);

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("list size is 5");

            }
        });
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println(i);
                    if (list.size() == 5) {
                        semaphore.release();
                    }
                }
            }
        });
        t1.start();
        t2.start();*/
    }
}
