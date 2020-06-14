package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.atm.Banknotes.*;

class AtmDepartmentTest {
    AtmDepartment atmDepartment;

    @BeforeEach
    void setUp() {
        atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(Map.of(B5000, 10));
        atmDepartment.addAtm(Map.of(B2000, 10));
        atmDepartment.addAtm(Map.of(B1000, 10));
    }

    @Test
    void getDepartmentBalance() {
        assertEquals(80000, atmDepartment.getDepartmentBalance());
    }

    @Test
    void restoreState() {
        atmDepartment.getAtm(0).cashWithdrawal(5000);
        atmDepartment.getAtm(1).cashWithdrawal(2000);
        atmDepartment.getAtm(2).cashWithdrawal(1000);
        assertEquals(72000, atmDepartment.getDepartmentBalance());
        atmDepartment.restoreState();
        assertEquals(80000, atmDepartment.getDepartmentBalance());
    }
}