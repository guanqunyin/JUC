package com.yin.juc.day4;

import com.yin.helper.ThreadHelper;

import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量的同步容器，拥有put和get方法，以及getCount（）方法，
 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class T02_Container2<E> {

    static final Object object = new Object();

    Node<E> head;

    Node<E> tail;

    int size;

    final int capacity;

    public T02_Container2(int capacity) {
        this.capacity = capacity;
    }


    void put(E e){
        synchronized(object) {
            if (size > capacity) {
                try {
                    object.notify();
                    object.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            Node node = new Node();
            node.item = e;
            if (head == null) {
                head = tail = node;
            } else {
                head.next = node;
                tail = node;
            }
            size++;
            System.out.println(Thread.currentThread().getName() + " produce " + node.item);
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
            System.out.println("head: " + (head==null?"null":head.item));

            if (head == null)
                try {
                    object.notify();
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            Node<E> nodeToBeRemoved = head;
            if (head == tail) {
                head = tail = null;
            } else {
                Node<E> headNext = head.next;
                head = headNext;
            }
            size--;
            System.out.println(Thread.currentThread().getName() + " consume " + nodeToBeRemoved.item);
            return nodeToBeRemoved.item;
        }

    }

    private static class Node<E> {
        Node<E> next;
        E item;

        public Node(Node<E> next, E item){
            this.next = next;
            this.item = item;
        }

        public Node(){
        }

    }

    public static void main(String[] args) {

        T02_Container2<String> t02_container2 = new T02_Container2(10);
        //2个生产者
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()-> {
                for (int j = 0; j < 10; j++) {
                    t02_container2.put(finalI + " - " + j);
                }
            }, "produce-"+i).start();
        }

//        new Thread(()-> t02_container2.put(2), "produce-2").start();

//        ThreadHelper.sleep(1, TimeUnit.SECONDS);
        //10个消费者
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                String s = null;
                while ((s=t02_container2.get()) != null) {

                }
            } , "consumer-"+i).start();
        }

    }
}
