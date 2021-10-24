package com.zack.refactoring.services;

import com.zack.refactoring.models.Customer;
import com.zack.refactoring.models.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VoucherServiceTest {

    private VoucherService uut;

    @BeforeEach
    void setUp() {
        uut = new VoucherService();
    }

//    @Test
//    void eligibleForBirthdayVoucher_returnNull_GivenNonMember() {
//        Customer customer = new Customer("name", LocalDate.now(), false, false);
//        Voucher voucher = uut.getBirthdayVoucher(customer);
//        assertNull(voucher);
//    }
//
//    @Test
//    void eligibleForBirthdayVoucher_returnNull_GivenCollectedVoucher() {
//        Customer customer = new Customer("name", LocalDate.now(), true, true);
//        Voucher voucher = uut.getBirthdayVoucher(customer);
//        assertNull(voucher);
//    }
//
//    @Test
//    void eligibleForBirthdayVoucher_returnNull_GivenNotBirthdayMonth() {
//        Customer customer = new Customer("name", LocalDate.now().minusMonths(1), true, false);
//        Voucher voucher = uut.getBirthdayVoucher(customer);
//        assertNull(voucher);
//    }
//
//    @Test
//    void eligibleForBirthdayVoucher_returnValidVoucher_GivenEligible() {
//        Customer customer = new Customer("name", LocalDate.now(), true, false);
//        Voucher voucher = uut.getBirthdayVoucher(customer);
//        assertNotNull(voucher);
//        assertEquals(voucher, new Voucher("VCH00002", LocalDate.now(), LocalDate.now().plusMonths(1),
//                                          new BigDecimal("0.10")));
//    }

}