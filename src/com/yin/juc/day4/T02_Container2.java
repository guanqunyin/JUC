package com.yin.juc.day4;

import com.yin.helper.ThreadHelper;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount（）方法，
 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class T02_Container2<E> {

    static final Object object = new Object();

    int size;

    final int capacity;

    private LinkedList<E> linkedList = new LinkedList<>();

    public T02_Container2(int capacity) {
        this.capacity = capacity;
    }


    void put(E e){
        synchronized(object) {
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

    E get(){
        synchronized(object) {
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
