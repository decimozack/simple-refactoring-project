package com.zack.refactoring.models;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {

    private String name;
    private LocalDate birthDate;
    private boolean isMember;
    private boolean hasCollectedBirthdayVoucher;

    public Customer(String name, LocalDate birthDate, boolean isMember, boolean hasCollectedBirthdayVoucher) {
        this.name = name;
        this.birthDate = birthDate;
        this.isMember = isMember;
        this.hasCollectedBirthdayVoucher = hasCollectedBirthdayVoucher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public boolean isHasCollectedBirthdayVoucher() {
        return hasCollectedBirthdayVoucher;
    }

    public void setHasCollectedBirthdayVoucher(boolean hasCollectedBirthdayVoucher) {
        this.hasCollectedBirthdayVoucher = hasCollectedBirthdayVoucher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isMember == customer.isMember && hasCollectedBirthdayVoucher == customer.hasCollectedBirthdayVoucher && Objects.equals(name, customer.name) && Objects.equals(birthDate, customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, isMember, hasCollectedBirthdayVoucher);
    }
}
