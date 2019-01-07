package com.example.springboottest.task;


public class PrintTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(30000);
            String name = Thread.currentThread().getName();
            System.out.println("hello-------" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
