package com.yin.juc.day4;

import com.yin.helper.ThreadHelper;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount（）方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class T02_Container2<E> {

    static final Object object = new Object();

    int size;

    final int capacity;

    private LinkedList<E> linkedList = new LinkedList<>();

    public T02_Container2(int capacity) {
        this.capacity = capacity;
    }

    //生产者调用
    void put(E e){
        synchronized(object) {
            /* 需要用while，当生产者生产的数量超过额定值的时候，十个生产者都会阻塞，此时消费者消费了一个，
             * 那么其中一个生产者(Produce-1)会被唤醒，并又生产了一个产品，但是这个生产者（Produce-1）
             * 又调用了object.notify()， 可能会唤醒其中一个生产者线程（Produce-2），
             * 如果加的是if，那么Produce-2就会在往容器里再加入一个产品导致容器超过额定值，所以需要加while，让Produce-2再去判断一次
             */
            while (linkedList.size() >= capacity) {
                try {
                    object.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() +" size:"+linkedList.size()+" ; produce " + e);
            linkedList.addLast(e);
            System.out.println();
            object.notify();
        }
    }

    int getCount() {
        synchronized(object) {
            try {
                if (size < 5) {
                    object.wait();
                }
                object.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return size;
        }
    }

    //消费者调用
    E get(){
        synchronized(object) {
            /*
            * 需要加while，当容器中没有产品时，两个消费者线程(Consumer-1, Consumer-2）都将会等待
            * 直到生产者(Produce-1...Produce-9)往里面丢了产品，其中一个消费者线程（Consumer-1）将会被唤醒，
            * 当Consumer-1消费掉其中一个产品的时候，将会调用object.notify()，
            * 也可能把另一个消费者（Consumer-2）线程唤醒，如果加的是if，那么Consumer-2可能去执行consume就会抛出异常
            * 因为此时里面并没有产品
            */
            while (linkedList.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " ready to wait");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " ready to awaken");

            E e = null;
            try {
                e = linkedList.removeFirst();
            } catch (NoSuchElementException exception) {

            }
            System.out.println(Thread.currentThread().getName() + " consume " + e);
            object.notify();
            return e;
        }
    }


    public static void main(String[] args) {

        T02_Container2<String> t02_container2 = new T02_Container2(10);
        Random random = new Random(100);
        //2个生产者
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                while (true) {
                    t02_container2.put("" + random.nextInt());
                    ThreadHelper.sleep(1, TimeUnit.SECONDS);
                }
            }, "Produce-"+i).start();
        }


//        new Thread(()-> t02_container2.put(2), "produce-2").start();

//        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        //10个消费者
        for (int i = 0; i < 2; i++) {
            new Thread(()-> {
                while (true) {
                    t02_container2.get();
                    ThreadHelper.sleep(1, TimeUnit.SECONDS);
                }
            } , "consumer-"+i).start();
        }

    }
}
