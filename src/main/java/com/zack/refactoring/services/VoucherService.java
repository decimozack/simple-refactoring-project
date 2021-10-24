package com.zack.refactoring.services;

import com.zack.refactoring.dao.VoucherDao;
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

}
