package com.coke.problem4;

class Thinking {

    void think() {
        System.out.println("Thinking...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
