package com.yin.juc.day4;


import com.yin.helper.ThreadHelper;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法， add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 用LockSupport实现
 */
public class T01_Container1_LockSupport<E> {

    private ArrayList<E> list = new ArrayList<>();

    static Thread t1,t2 = null;

    void add(E e) {
        list.add(e);
    }

    int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T01_Container1_LockSupport<Integer> list = new T01_Container1_LockSupport();

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("list size is 5");
                LockSupport.unpark(t1);
            }
        });
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println(i);
                    if (list.size() == 5) {

                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }
}
