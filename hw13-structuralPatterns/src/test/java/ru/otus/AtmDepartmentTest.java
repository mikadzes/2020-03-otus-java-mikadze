package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmBuilder;

import static org.junit.jupiter.api.Assertions.*;

class AtmDepartmentTest {
    AtmDepartment atmDepartment;

    @BeforeEach
    void setUp() {
        atmDepartment = new AtmDepartment();
        atmDepartment.addAtm(
                new AtmBuilder()
                        .setCount_5000(10)
                        .build());
        atmDepartment.addAtm(
                new AtmBuilder()
                        .setCount_2000(10)
                        .build());
        atmDepartment.addAtm(
                new AtmBuilder()
                        .setCount_1000(10)
                        .build());

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

    }
}