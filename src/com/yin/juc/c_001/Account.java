package com.yin.juc.c_001;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/11/14.
 * 模拟银行账户
   设置值加锁
   读不加锁
   这样是否可行
 */
public class Account {

    private String name;

    //余额
    double balance;

    public Account(String name) {
        this.name = name;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Account account = new Account("yin");
        new Thread(()->{
            ThreadHelper.sleep(2, TimeUnit.SECONDS);
            account.setBalance(100);
        }).start();

        ThreadHelper.sleep(1, TimeUnit.SECONDS);

        new Thread(()->{
            System.out.println(account.getBalance());
        }).start();


    }
}
