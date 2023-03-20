package com.yjx;

public class ThreadDemo1 {


    public static void main(String[] args) {

        //创建线程
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是子线程中执行的任务");
                System.out.println("当前线程----是 "+Thread.currentThread().getName());
                System.out.println("-------------------------------");
            }
        },"Pig");
        //启动线程
        subThread.start();

        //多线程的任务是异步执行的


        System.out.println("当前线程是 "+Thread.currentThread().getName());
        System.out.println("xxxxxxxxxxxxxxxxxxxx");


    }



}
