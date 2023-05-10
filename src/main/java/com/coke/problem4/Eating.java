package com.coke.problem4;

class Eating {

    void eat() {
        System.out.println("Eating...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
