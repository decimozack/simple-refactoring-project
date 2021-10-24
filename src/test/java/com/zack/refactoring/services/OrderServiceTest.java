package com.zack.refactoring.services;

import com.zack.refactoring.dto.OrderDetail;
import com.zack.refactoring.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService uut;

    @BeforeEach
    void setUp() {
        uut = new OrderService();
    }

    @Test
    void processOrder_ReturnNull_GivenNoDrinks() {
        OrderDetail actual = uut.processOrder(new Order(null, Collections.emptyList(), null));
        assertNull(actual);
    }

    @Test
    void processOrder_ReturnTotalValueWithNoDiscount_GivenNoVoucher() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        OrderDetail actual = uut.processOrder(new Order(customer, List.of(new Beer("Leopard", expectedTotalCost)), null));
        assertNotNull(actual);
        assertEquals(actual.getTotalPrice(), expectedTotalCost);
        assertEquals("SN00001", actual.getReceiptNumber());
    }


    @Test
    void processOrder_ReturnOrderDetailWithTotalSum_GivenNoVoucher() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", expectedTotalCost),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, null));
        assertNotNull(actual);
        assertEquals(actual.getTotalPrice(), new BigDecimal("11"));
        assertEquals("SN00001", actual.getReceiptNumber());
    }

    @Test
    void processOrder_ReturnOrderDetailWithTotalSum_GivenExpired() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", expectedTotalCost),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        Voucher expectedVoucher = new Voucher("VCH00002",
                                              LocalDate.now().minusMonths(2),
                                              LocalDate.now().minusMonths(1),
                                              new BigDecimal("0.10"));


        OrderDetail actual = uut.processOrder(new Order(customer, drinks, expectedVoucher));
        assertNotNull(actual);
        assertEquals(actual.getTotalPrice(), new BigDecimal("11"));
        assertEquals("SN00001", actual.getReceiptNumber());
    }

    @Test
    void processOrder_ReturnOrderDetailWithTotalSum_GivenInvalidVoucher() {

        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", BigDecimal.ONE),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        Voucher voucher = new Voucher("VCH00002",
                                              LocalDate.now(),
                                              LocalDate.now().plusMonths(1),
                                              new BigDecimal("0.10"));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, voucher));
        assertNotNull(actual);
        assertEquals("SN00001", actual.getReceiptNumber());
        assertEquals(new BigDecimal("11"), actual.getTotalPrice());
    }

    @Test
    void processOrder_ReturnOrderDetailWithDiscountedTotalSum_GivenVoucher() {

        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", BigDecimal.ONE),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        Voucher voucher = new Voucher("VCH00001",
                                      LocalDate.now(),
                                      LocalDate.now().plusMonths(1),
                                      new BigDecimal("0.10"));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, voucher));
        assertNotNull(actual);
        assertEquals("SN00001", actual.getReceiptNumber());
        assertEquals(new BigDecimal("9.90"), actual.getTotalPrice());
    }

    @Test
    void processOrder_ReturnOrderDetailWithIncreasedReceiptNumber() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", expectedTotalCost),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, null));
        assertNotNull(actual);
        assertEquals("SN00001", actual.getReceiptNumber());
        actual = uut.processOrder(new Order(customer, drinks, null));
        assertNotNull(actual);
        assertEquals("SN00002", actual.getReceiptNumber());
    }

    @Test
    void processOrder_ReturnOrderDetailWithoutBirthdayVoucher_GivenCollected() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         true);
        List<Drink> drinks = List.of(new Beer("Leopard", expectedTotalCost),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, null));
        assertNotNull(actual);
        assertEquals("SN00001", actual.getReceiptNumber());
        assertNull(actual.getVoucher());
    }

    @Test
    void processOrder_ReturnOrderDetailBirthdayVoucher() {

        BigDecimal expectedTotalCost = BigDecimal.ONE;
        Customer customer = new Customer("Jack",
                                         LocalDate.of(1996, LocalDate.now().getMonth(), 20),
                                         true,
                                         false);
        List<Drink> drinks = List.of(new Beer("Leopard", expectedTotalCost),
                                     new BubbleTea("Milk Chocolate Tea", BigDecimal.TEN));

        OrderDetail actual = uut.processOrder(new Order(customer, drinks, null));
        assertNotNull(actual);
        assertEquals("SN00001", actual.getReceiptNumber());
        assertNotNull(actual.getVoucher());
        Voucher expectedVoucher = new Voucher("VCH00002",
                                              LocalDate.now(),
                                              LocalDate.now().plusMonths(1),
                                              new BigDecimal("0.10"));

        assertEquals(expectedVoucher, actual.getVoucher());
        assertTrue(customer.isHasCollectedBirthdayVoucher());
    }

}