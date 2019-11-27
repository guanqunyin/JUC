package com.yin.juc.day3;

import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁和排它锁 TODO 自己测试一下怎么用
 */
public class T09_TestReadWriteLock<E> {

    ReadWriteLock lock = new ReentrantReadWriteLock();

    LinkedList<E> linkedList = new LinkedList();

    void add(E e) {

        linkedList.addLast(e);
    }

    void get() {
        linkedList.getFirst();
    }

}


