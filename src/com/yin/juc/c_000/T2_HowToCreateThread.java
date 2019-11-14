package com.yin.juc.c_000;

/**
 * Created by Administrator on 2019/11/14.
 */
public class T2_HowToCreateThread {

    static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("Hello, My thread");
        }
    }

    static class MyRunnable implements  Runnable{
        @Override
        public void run() {
            System.out.println("Hello, My runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(()->{
            System.out.println("Hell0, Lambda");
        }).start();
    }

}
