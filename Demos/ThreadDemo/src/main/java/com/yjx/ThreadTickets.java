package com.yjx;

public class ThreadTickets {

    static Integer totalTicket = 100;


    //多线程卖票出现一张票多卖的情况, (线程安全问题)

    private static void saleTicket(){
        while (totalTicket>0){
            System.out.println(Thread.currentThread().getName() + " 售出第 "+totalTicket + "张票");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            totalTicket--;
        }
    }

    public static void main(String[] args) {

        //saleTicket();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                saleTicket();
            }
        },"threadA");


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                saleTicket();
            }
        },"threadB");


        threadA.start();
        threadB.start();

    }

}