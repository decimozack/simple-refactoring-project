package com.zack.refactoring.services;

import com.zack.refactoring.dao.VoucherDao;
import com.zack.refactoring.models.Customer;
import com.zack.refactoring.models.Voucher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoucherService {

    private VoucherDao voucherDao;

    public VoucherService() {
        voucherDao = VoucherDao.getInstance();
    }

    public List<String> getValidVouchers() {
        return voucherDao.getValidVoucherCodes();
    }

    boolean isBirthdayMonth(Customer customer, LocalDate today1) {
        return today1.getMonth()
                     .equals(customer.getBirthDate()
                                     .getMonth());
    }

    boolean isEligibleForBirthdayVoucher(Customer customer, LocalDate today1) {
        return isBirthdayMonth(customer, today1) &&
                customer.isMember() &&
                !customer.isHasCollectedBirthdayVoucher();
    }

    Voucher getBirthdayVoucher(Customer customer) {
        Voucher voucher = null;
        LocalDate today1 = LocalDate.now();
        if (isEligibleForBirthdayVoucher(customer, today1)) {
            String voucherCode = String.format("VCH%05d", voucherDao.getNextVoucherCode());
            voucherDao.addNewVoucherCode(voucherCode);
            voucher = new Voucher(voucherCode, LocalDate.now(), LocalDate.now().plusMonths(1), new BigDecimal("0.10"));
            customer.setHasCollectedBirthdayVoucher(true);

        } else {
            System.out.println(
                    String.format("Customer - %s is not eligible for birthday voucher", customer));
        }
        return voucher;
    }

}
