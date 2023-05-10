package com.coke.problem4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPoliticians {
    private final int n;
    private final Lock[] forks;
    private final ExecutorService executor;

    public DiningPoliticians(int n) {
        this.n = n;
        this.forks = new Lock[n];
        for (int i = 0; i < n; i++) {
            this.forks[i] = new ReentrantLock();
        }
        this.executor = Executors.newFixedThreadPool(n);
    }

    public void startDinner() {
        for (int i = 0; i < n; i++) {
            final int leftFork = i;
            final int rightFork = (i + 1) % n;
            this.executor.execute(() -> {
                while (true) {
                    think();
                    this.forks[leftFork].lock();
                    try {
                        this.forks[rightFork].lock();
                        try {
                            eat();
                        } finally {
                            this.forks[rightFork].unlock();
                        }
                    } finally {
                        this.forks[leftFork].unlock();
                    }
                }
            });
        }
    }

    private void think() {
        System.out.println("Thinking...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        System.out.println("Eating...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stopDinner() {
        this.executor.shutdownNow();
    }

    public static void main(String[] args) {
        DiningPoliticians dp = new DiningPoliticians(5);
        dp.startDinner();
    }
}
