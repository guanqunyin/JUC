package com.yin.juc.day4;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法， add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * -- 用countdownlatch实现
 */
public class T01_Container1_CountDownLatch<E> {

    private LinkedList<E> linkedList = new LinkedList<>();

    void add(E e) {
        linkedList.add(e);
    }

    int size() {
        return linkedList.size();
    }

    public static void main(String[] args) {
        T01_Container1_CountDownLatch<Integer> list =  new T01_Container1_CountDownLatch();
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println(i);
                    if (list.size()==5) {
                        countDownLatch1.countDown();
                        try {
                            countDownLatch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch1.await();
                    System.out.println("list size arrive 5, thread1 will end");
                    countDownLatch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread1.start();
    }
}
