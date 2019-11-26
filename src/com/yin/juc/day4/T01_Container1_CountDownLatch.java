package com.yin.juc.day4;

import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法， add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * --TODO 用countdownlatch实现
 */
public class T01_Container1_CountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
