package com.yin.juc.day6.ConcurrentContainers;

import java.util.concurrent.SynchronousQueue;

/*
*
* 容量为0的同步队列
*
* SynchronousQueue是一个内部只能包含一个元素的队列。
* 插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素。
* 同样，如果线程尝试获取元素并且当前不存在任何元素，则该线程将被阻塞，直到线程将元素插入队列。
* 将这个类称为队列有点夸大其词。这更像是一个点。
* */
public class T09_SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
//        synchronousQueue.add(1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object take = synchronousQueue.take();
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        synchronousQueue.put(1);
    }

}
