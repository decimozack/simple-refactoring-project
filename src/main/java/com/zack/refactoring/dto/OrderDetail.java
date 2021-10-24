package com.zack.refactoring.dto;

import com.zack.refactoring.models.Voucher;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderDetail {

    private String receiptNumber;
    private BigDecimal totalPrice;
    private Voucher voucher;


    public OrderDetail(String receiptNumber, BigDecimal totalPrice, Voucher voucher) {
        this.receiptNumber = receiptNumber;
        this.totalPrice = totalPrice;
        this.voucher = voucher;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(receiptNumber, that.receiptNumber) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(voucher, that.voucher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptNumber, totalPrice, voucher);
    }
}
