package com.yin.juc.day6.ConcurrentContainers;

import java.util.PriorityQueue;

/*
* 优先级队列，根据指定算法进行排序的一个队列。
* 通过排序规则决定谁在前面或者自己实现的comparable接口。
* */
public class T07_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}
