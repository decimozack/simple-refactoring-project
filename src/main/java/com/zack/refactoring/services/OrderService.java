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
    VoucherDao voucherDao = VoucherDao.getInstance();
    private int receiptSerialNumber = 1;

    public OrderDetail processOrder(Order order) {

        if (order.getDrinks().size() > 0) {

            // Calculate total price for all the drinks
            BigDecimal total = BigDecimal.ZERO;
            for (Drink drink : order.getDrinks()) {
                total = total.add(drink.getPrice());
            }

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

            Voucher voucher = null;
            LocalDate today1 = LocalDate.now();
            if (today1.getMonth().equals(order.getCustomer().getBirthDate().getMonth()) &&
                    order.getCustomer().isMember() &&
                    !order.getCustomer().isHasCollectedBirthdayVoucher()) {
                String s = String.format("VCH%05d", voucherDao.getNextVoucherCode());
                voucherDao.addNewVoucherCode(s);
                voucher = new Voucher(s, LocalDate.now(), LocalDate.now().plusMonths(1), new BigDecimal("0.10"));
                order.getCustomer().setHasCollectedBirthdayVoucher(true);

            } else {
                System.out.println(
                        String.format("Customer - %s is not eligible for birthday voucher", order.getCustomer()));
            }

            return new OrderDetail(String.format("SN%05d", receiptSerialNumber++), total, voucher);
        }

        return null;

    }
}
