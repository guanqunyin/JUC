package com.yin.juc.daythinking;
/*
* 死锁就是有一个人一直在获取一把锁，然而这把锁已经被别人在用着了，
* */
public class TestDeadLock {

    public static void main(String[] args) {

        final Object A = new Object();
        final Object B = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (A) {
                        if (i%10 == 0) {
                            synchronized (B) {
                                System.out.println(Thread.currentThread().getName() + "-B-" + i);
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + "-A-" + i);
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (B) {
                        if (i%10 == 0) {
                            synchronized (A) {
                                System.out.println(Thread.currentThread().getName() + "-A-" + i);
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + "-B-" + i);
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
