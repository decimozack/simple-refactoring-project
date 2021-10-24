package com.zack.refactoring.models;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Drink {

    private String name;
    private BigDecimal price;

    public Drink(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name) && Objects.equals(price, drink.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
