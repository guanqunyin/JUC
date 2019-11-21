package com.yin.juc.day4;

/**
 * 实现一个容器，提供两个方法， add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class T01_Container1<E> {

    static final Object object = new Object();

    Node<E> head;

    Node<E> tail;

    int size;

    void add(E e)  {
        synchronized(object) {
            Node node = new Node();
            node.item = e;
            if (head == null) {
                head = tail = node;
            } else {
                head.next = node;
                tail = node;
            }
            size++;
            if(size==5) {
                try {
                    object.notify();
                    object.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    int size() {
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
        T01_Container1<Integer> container1 = new T01_Container1();
        Thread addThread = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    System.out.println("add "+ i );
                    container1.add(i);
                }
            }

        );

        Thread readThread = new Thread(() -> {
            System.out.println(container1.size());
        });

        addThread.start();
        readThread.start();

    }


}
