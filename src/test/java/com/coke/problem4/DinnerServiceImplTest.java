package com.coke.problem4;

import org.junit.jupiter.api.Test;

class DinnerServiceImplTest {

    @Test
    void testSuccessScenario() throws InterruptedException {
        DinnerServiceImpl dp = new DinnerServiceImpl(5);
        dp.startDinner();

        Thread.sleep(10 * 1000);
        dp.stopDinner();

    }

}