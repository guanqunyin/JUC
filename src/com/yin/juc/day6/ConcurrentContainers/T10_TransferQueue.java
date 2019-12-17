package com.yin.juc.day6.ConcurrentContainers;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/*
* TransferQueue则更进一步，生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费（不仅仅是添加到队列里就完事）。
* 新添加的transfer方法用来实现这种约束。
* 顾名思义，阻塞就是发生在元素从一个线程transfer到另一个线程的过程中，
* 它有效地实现了元素在线程之间的传递（以建立Java内存模型中的happens-before关系的方式）。
* */
public class T10_TransferQueue {

    public static void main(String[] args) throws InterruptedException {

        TransferQueue transferQueue = new LinkedTransferQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        transferQueue.transfer(i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        Object take1 = transferQueue.take();
                        System.out.println(take1);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
