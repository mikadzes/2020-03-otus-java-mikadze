package ru.otus.atm;

import java.util.Map;

public interface Atm {

    int getBalance();

    void restoreInitialState();

    void cashReplenishment(Map<Banknotes, Integer> cash);

    Map<Banknotes, Integer> cashWithdrawal(int amount);
}
