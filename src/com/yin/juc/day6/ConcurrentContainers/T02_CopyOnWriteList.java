package com.yin.juc.day6.ConcurrentContainers;

import java.util.concurrent.CopyOnWriteArrayList;

/*
* 写时复制。
* 读
* 其实也是读写分离的思想，在要写入的时候，把原容器复制一份，在往复制后的容器加入元素，最终再指回原来的引用
* 所以可以并发的读
* */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

    }

}
