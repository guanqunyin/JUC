package com.yin.juc.day4;

/*
 * ArrayList 为什么是线程不安全的
 * 多个线程同时add的时候，可能其中一个线程Thread-1往里面丢了一个元素，
 * 还没size++， 另一个线程Thread-2也往里面丢了一个元素，然后也执行了size++，完了size还是1，但是其实元素丢了两个进去了已经
 * 这就是线程不安全的地方
 * TODO 看看ArrayList哪里不安全了
 */

import com.yin.helper.ThreadHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_TestArrayListNotThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> integers = new ArrayList<>();
//        List<Integer> integers = Collections.synchronizedList(arrayList);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                integers.add(i);
            }
        });


        Thread thread1 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                integers.add(i);
            }
        });
        thread.start();
        thread1.start();

        ThreadHelper.sleep(5, TimeUnit.SECONDS);

        System.out.println(integers.size());
    }
}
