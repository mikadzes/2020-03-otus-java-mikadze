package ru.otus;

import java.util.Map;

public interface Atm {

    Integer getBalance();

    void cashReplenishment(Map<Banknotes, Integer> cash);

    Map<Banknotes, Integer> cashWithdrawal(int amount);
}
