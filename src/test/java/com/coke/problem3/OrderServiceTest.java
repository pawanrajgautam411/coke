package com.coke.problem3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private OrderService orderService;

    private static String JSON_STRING_FROM_FILE;

    @BeforeAll
    static void beforeAll() throws IOException {
        String filePath = Objects.requireNonNull(
                        OrderServiceTest.class.getClassLoader().getResource("order_request.json")
                )
                .getFile();

        Path path = Paths.get(filePath);

        byte[] bytes = Files.readAllBytes(path);
        JSON_STRING_FROM_FILE = new String(bytes);
    }

    @BeforeEach
    void beforeEach() {
        OrderRepository orderRepository = new OrderRepository();
        OrderProcessor orderProcessor = new OrderProcessor(orderRepository);
        orderService = new OrderService(orderProcessor);
    }

    @Test
    void processOrder_givenRequestJson_thenReturnOrderId() {
        LoggedInUser.switchUser("steve@gmail.com");

        // Order 1: Fresh Order
        OrderOutput orderOutput = orderService.processOrder(JSON_STRING_FROM_FILE);
        assertNotNull(orderOutput);

        // Order 2: Duplicate Order from same email_id
        DuplicateOrderException exception = assertThrows(DuplicateOrderException.class,
                () -> orderService.processOrder(JSON_STRING_FROM_FILE));
        exception.printStackTrace();

        assertNotNull(orderOutput.getOrderId());

        // Order 3: Fresh Order from different email_id
        LoggedInUser.switchUser("mark@gmail.com");
        orderService.processOrder(JSON_STRING_FROM_FILE);

        // Order 4: Duplicate Order from same email_id
        LoggedInUser.switchUser("steve@gmail.com");
        exception = assertThrows(DuplicateOrderException.class,
                () -> orderService.processOrder(JSON_STRING_FROM_FILE));
        exception.printStackTrace();
    }
}