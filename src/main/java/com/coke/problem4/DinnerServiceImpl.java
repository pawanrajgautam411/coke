package com.coke.problem4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DinnerServiceImpl implements DinnerService {
    private final int n;
    private final List<Lock> forks;
    private final ExecutorService executor;


    public DinnerServiceImpl(int n) {
        this.n = n;
        this.forks = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            this.forks.add(new ReentrantLock());
        }

        this.executor = Executors.newFixedThreadPool(n);
    }

    /**
     * start the dinner ceremony
     */
    @Override
    public void startDinner() {
        Eating eating = new Eating();
        Thinking thinking = new Thinking();

        for (int i = 0; i < n; i++) {
            final int leftFork = i;
            final int rightFork = (i + 1) % n;

            this.executor.execute(() -> {
                while (true) {
                    thinking.think();
                    this.forks.get(leftFork).lock();

                    try {
                        this.forks.get(rightFork).lock();

                        try {
                            eating.eat();

                        } finally {
                            this.forks.get(rightFork).unlock();
                        }

                    } finally {
                        this.forks.get(leftFork).unlock();
                    }
                }
            });
        }
    }

    /**
     * stop the dinner ceremony
     */
    @Override
    public void stopDinner() {
        this.executor.shutdownNow();
    }

}
