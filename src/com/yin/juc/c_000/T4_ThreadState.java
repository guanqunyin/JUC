package com.yin.juc.c_000;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 */
public class T4_ThreadState {

    public static void main(String[] args) {

        Thread myThread = new Thread(()->{
            System.out.println(Thread.currentThread().getState());
            System.out.println("This is my Thread");
        });
        System.out.println(myThread.getState());

        myThread.start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        System.out.println(myThread.getState());

    }
}
