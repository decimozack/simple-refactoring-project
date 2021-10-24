package com.zack.refactoring.dao;

import com.zack.refactoring.models.Voucher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VoucherDao {

    private int voucherCount = 2;
    private List<String> validVouchers;
    private static VoucherDao voucherDao;

    public static VoucherDao getInstance() {
        if (voucherDao != null) {
            return voucherDao;
        }

        voucherDao = new VoucherDao();
        return voucherDao;
    }

    private VoucherDao() {
        validVouchers = new ArrayList<>();
        validVouchers.add("VCH00001");
    }

    public List<String> getValidVoucherCodes() {
        return validVouchers;
    }

    public void addNewVoucherCode(String voucherCode) {
        Objects.requireNonNull(voucherCode);
        validVouchers.add(voucherCode);
        voucherCount++;
    }

    public int getNextVoucherCode() {
        return voucherCount;
    }
}
