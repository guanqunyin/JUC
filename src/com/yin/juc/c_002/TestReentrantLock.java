package com.yin.juc.c_002;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某一个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 *
 * 重入锁实现可重入性原理或机制是：
 *  每一个锁关联一个线程持有者和计数器，当计数器为 0 时表示该锁没有被任何线程持有，
 *  那么任何线程都可能获得该锁而调用相应的方法；当某一线程请求成功后，JVM会记下锁的持有线程，
 *  并且将计数器置为 1；此时其它线程请求该锁，则必须等待；而该持有锁的线程如果再次请求这个锁，
 *  就可以再次拿到这个锁，同时计数器会递增；当线程退出同步代码块时，计数器会递减，如果计数器为 0，
 *  则释放该锁。
 */
public class TestReentrantLock {

    public synchronized void A() {
        System.out.println("A");
        B();
    }

    public synchronized void B() {
        System.out.println("B");
    }

    /*
     *对于方法A和方法B，都被synchronized修饰，即他们的锁对象都是 TestReentrantLock的一个实例对象， 如果不允许可重入的话，
     * 当在A方法中调用B方法的时候，B方法不然任何一个对象进去，就会死锁
     *
     */
    public static void main(String[] args) {
        new TestReentrantLock().A();
        new B().print();
        new B().print1();
    }


}

//synchronized为什么是可重入的？ 答: 如果不让重入的话， 那么就会导致死锁的发生。
class A{

    protected synchronized void print(){
        System.out.println(this);
        System.out.println("This is parent A");
    }

    //子类调用的时候为什么打印的是子类对象
    protected void print1() {
        System.out.println(this);
    }
}

class B extends A{

    protected synchronized void print(){
        System.out.println(this);
        System.out.println("This is child A");
        super.print();
    }

    @Override
    protected void print1() {
        System.out.println(this);
        super.print1();
    }
}
