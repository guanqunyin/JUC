package com.yin.juc.day6.fromVectorToQueue;

import com.yin.helper.ThreadHelper;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/*有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 答： 超量销售
 */
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size() > 0) {

                    ThreadHelper.sleep(10, TimeUnit.MILLISECONDS);

                    System.out.println("销售了--" + tickets.remove(0));
                }
            }).start();
        }
    }
}
