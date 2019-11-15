package com.yin.juc.c_001;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 * 模拟银行账户
   设置值加锁
   读不加锁
   这样是否可行?
    Answer: 可能导致脏读
 */
public class Account {

    private String name;

    //余额
    double balance;

    public Account(String name) {
        this.name = name;
    }

    public synchronized void setBalance(double balance) {
        ThreadHelper.sleep(2, TimeUnit.SECONDS);
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance() {

        System.out.println("I am getting balance");
        return balance;
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account("yin");
        new Thread(()->{
                account.setBalance(100);
        }).start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        new Thread(()->{
                System.out.println(account.getBalance());
        }).start();

        account.wait();

    }
}
