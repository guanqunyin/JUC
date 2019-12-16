package com.yin.juc.day4;

/*
 * ArrayList 为什么是线程不安全的
 * 多个线程同时add的时候，可能其中一个线程Thread-1往里面丢了一个元素，
 * 还没size++， 另一个线程Thread-2也往里面丢了一个元素，
 * 那么总共应该是两个元素，size也应该是2.
 * 然后也执行了size++，完了size还是1，也只存储了一个元素。但是其实元素丢了两个进去了已经
 * 这就是线程不安全的地方
 * 看看ArrayList哪里不安全了
 *  ArrayList的add方法源码
 *  ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
 * A: 会出现三种情况
 * 1. ArrayList中出现null值
 *                         0                      size++之后是1
 *   Thread-1 elementData[size] = e  ----------->size++并把值写回主存
 *                        0                                          线程的值从主存中复制到工作内存此时size值为1再执行size++则为2，当下一个线程来的时候直接往elemenData[2]上面加数据了。则elementData[1]为null
 *   Thread-2 elementData[size] = e  -------------------------------->size++
 *
 * 2. ArrayList中size不是预想的值，更小
 *                          0                     size变为1并写入主存
 *    Thread-1 elementData[size] = e  ----------->size++
 *                          0                     size变为1也写入主存把上面写入的值更新掉，自然size就少了一次。
 *    Thread-2 elementData[size] = e  ----------->size++
 *
 * 3. 数组越界异常（发生在当需要扩容的时候， 比如size为9。 elementData.length=10）
 *                                     9                                           9                      size变为10并写入主存
 *    Thread-1 ensureCapacityInternal(size + 1)---------------------->elementData[size] = e  ----------->size++ ----
 *                                     9                                                                            \    elementData[10]抛出数组越界异常
 *    Thread-2 ensureCapacityInternal(size + 1)                                                                      --->elementData[size] = e  ----------->size++
 */

import com.yin.helper.ThreadHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_TestArrayListNotThreadSafe {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> integers = new ArrayList<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
//                System.out.println(i);
                integers.add(i);
            }
        });


        Thread thread1 = new Thread(() -> {
            for (int i = 100000; i < 200000; i++) {
//                System.out.println(i);
                integers.add(i);
            }
        });
        thread.start();
        thread1.start();

        ThreadHelper.sleep(5, TimeUnit.SECONDS);

        System.out.println(integers.size());
        System.out.println(Arrays.toString(integers.toArray()));
    }
}
