package com.yin.juc.day2.day2_002;

/*
* volatile修饰的引用类型时， 引用指向具体的内容，是检测不到具体的内容的改变的
* volatile关键字的作用是告诉编译器，凡是被该关键字申明的变量都是易变的、不稳定的，
* 所以不要试图对该变量使用缓存等优化机制，而应当每次都从他的内存地址中去读取值，
* 但volatile并不是每次更改完就要立刻将他写回内存，
* volatile只提供了内存的可见性，并不提供原子性（与锁机制的区别：锁机制即提供了内存的可见性又提供了原子性
* */
public class TestVolatile3 {

    volatile Man man = new Man();

    void setName(){
        man.name = new String("peimeng");
    }

    String getName() {
        System.out.println(man.name);
        return man.name;
    }


    public static void main(String[] args) {
        TestVolatile3 testVolatile3 = new TestVolatile3();

        new Thread(testVolatile3::setName).start();
        new Thread(testVolatile3::getName).start();


    }

    class Man{

        String name = "yin";
        String date = "2222";
    }
}
