package com.zack.refactoring.models;

import java.util.List;
import java.util.Objects;

public class Order {

    private Customer customer;
    private List<Drink> drinks;
    private Voucher voucher;

    public Order(Customer customer, List<Drink> drinks, Voucher voucher) {
        this.customer = customer;
        this.drinks = drinks;
        this.voucher = voucher;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(customer, order.customer) && Objects.equals(drinks, order.drinks) && Objects.equals(voucher, order.voucher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, drinks, voucher);
    }
}
