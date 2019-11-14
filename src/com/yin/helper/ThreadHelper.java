package com.yin.helper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 */
public class ThreadHelper {

    private ThreadHelper(){};

    public static void sleep(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
