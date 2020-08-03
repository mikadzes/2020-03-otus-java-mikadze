package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.atm.Atm;
import ru.otus.atm.AtmImpl;
import ru.otus.atm.Banknotes;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.otus.atm.Banknotes.*;

class AtmTest {

    Atm atm;
    Map<Banknotes, Integer> cash;

    @BeforeEach
    void setUp() {
        cash = Map.of(
                B5000, 10,
                B2000, 10,
                B1000, 10,
                B500, 10,
                B200, 10,
                B100, 10,
                B50, 10,
                B10, 10
        );
        atm = AtmImpl.builder()
                .add(B5000, 10)
                .add(B2000, 10)
                .add(B1000, 10)
                .add(B500, 10)
                .add(B200, 10)
                .add(B100, 10)
                .add(B50, 10)
                .add(B10, 10)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBalance() {
        assertEquals(88600, atm.getBalance());
    }

    @Test
    void cashReplenishment() {
        atm.cashReplenishment(cash);
        assertEquals(177200, atm.getBalance());
    }

    @Test
    void cashWithdrawal1() {
        assertThrows(IllegalStateException.class, () -> atm.cashWithdrawal(88700));
        assertThrows(IllegalArgumentException.class, () -> atm.cashWithdrawal(101));
        assertEquals(cash, atm.cashWithdrawal(88600));
    }

    @Test
    void cashWithdrawal2() {
        assertEquals(Map.of(B5000, 1), atm.cashWithdrawal(5000));
        assertEquals(83600, atm.getBalance());
        assertEquals(Map.of(B10, 1), atm.cashWithdrawal(10));
        assertEquals(83590, atm.getBalance());
        assertEquals(Map.of(B2000, 1, B1000, 1, B10, 2), atm.cashWithdrawal(3020));
        assertEquals(80570, atm.getBalance());
    }
}