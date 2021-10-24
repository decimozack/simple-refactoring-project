package com.zack.refactoring.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Voucher {

    private String serialNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal discount;

    public Voucher(String serialNumber, LocalDate startDate, LocalDate endDate, BigDecimal discount) {
        this.serialNumber = serialNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }


    @Override
    public String toString() {
        return "Voucher{" +
                "serialNumber='" + serialNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return Objects.equals(serialNumber, voucher.serialNumber) && Objects.equals(startDate, voucher.startDate) && Objects.equals(endDate, voucher.endDate) && Objects.equals(discount, voucher.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, startDate, endDate, discount);
    }
}
