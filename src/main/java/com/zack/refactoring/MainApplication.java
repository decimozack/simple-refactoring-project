package com.zack.refactoring;

import com.zack.refactoring.dto.OrderDetail;
import com.zack.refactoring.models.*;
import com.zack.refactoring.services.OrderService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class MainApplication {


    public static void main(String[] args) {

        Order order = takeOrder();

        OrderService orderService = new OrderService();
        OrderDetail orderDetail = orderService.processOrder(order);

        System.out.println(String.format("Total price to be paid: %s", NumberFormat
                .getCurrencyInstance().format(orderDetail.getTotalPrice())));
        System.out.println(String.format("Receipt No: %s", orderDetail.getReceiptNumber()));
        System.out.println(String.format("Birthday Voucher - %s", orderDetail.getVoucher() == null ? "" : orderDetail.getVoucher()));

    }

    private static Order takeOrder() {

        Drink beer1 = new Beer("Lion", new BigDecimal("6.9"));
        Drink bubbleTea1 = new BubbleTea("Milk Tea", new BigDecimal("5.9"));
        Drink coffee1 = new Coffee("Mocha", new BigDecimal("4.5"));

        Customer customer = new Customer("Jack", LocalDate.of(1996, Month.OCTOBER, 25), true, false);
        List<Drink> orderDrinks = List.of(beer1, bubbleTea1, coffee1);

        Voucher voucher = new Voucher("VCH00001", LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), new BigDecimal("0.10"));
        Order order = new Order(customer, orderDrinks, voucher);

        return order;
    }
}
