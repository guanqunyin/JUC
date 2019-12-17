package com.yin.juc.day6.ConcurrentContainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * 本质上也是使用了synchronized关键字
 */
public class T03_SynchronizedList {

    public static void main(String[] args) {
        List<String> strs = new ArrayList();
        List<String> strsSync = Collections.synchronizedList(strs);
    }
}
