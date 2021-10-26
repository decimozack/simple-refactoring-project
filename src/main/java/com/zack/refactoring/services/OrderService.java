package com.zack.refactoring.services;

import com.zack.refactoring.dao.VoucherDao;
import com.zack.refactoring.models.Drink;
import com.zack.refactoring.models.Order;
import com.zack.refactoring.dto.OrderDetail;
import com.zack.refactoring.models.Voucher;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderService {

    private VoucherService voucherService = new VoucherService();
    private int receiptSerialNumber = 1;

    public OrderDetail processOrder(Order order) {

        if (order.getDrinks()
                 .size() <= 0) {
            return null;
        }

        // Calculate total price for all the drinks
        BigDecimal total = BigDecimal.ZERO;
        for (Drink drink : order.getDrinks()) {
            total = total.add(drink.getPrice());
        }

        // TODO: refactor this
        // Calculate price after applying voucher
        LocalDate today = LocalDate.now();
        if (order.getVoucher() != null) {
            if (order.getVoucher().getStartDate().isEqual(today) ||
                    order.getVoucher().getEndDate().equals(today) ||
                    (order.getVoucher().getStartDate().isBefore(today) && order.getVoucher().getEndDate().isAfter(today))) {
                if (voucherService.getValidVouchers().contains(order.getVoucher().getSerialNumber())) {
                    total = total.multiply(BigDecimal.ONE.subtract(order.getVoucher().getDiscount()));
                }
            }
        }

        Voucher voucher = voucherService.getBirthdayVoucher(order.getCustomer());

        return new OrderDetail(String.format("SN%05d", receiptSerialNumber++), total, voucher);

    }

}
