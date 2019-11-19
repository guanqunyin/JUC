package com.yin.juc.day1.day1_003;

public class Main {

    private static class myThread extends Thread{

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
                if (i%5==0){
                    try {
                        int i1 = i / 0;
                    }catch (ArithmeticException ar) {
                        System.out.println("发生了算数异常");
                        System.out.println(ar);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new myThread().start();
    }

}
