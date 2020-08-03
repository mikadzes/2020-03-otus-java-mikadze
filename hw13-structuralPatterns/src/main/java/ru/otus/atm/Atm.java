package ru.otus.atm;

import java.util.Map;

public interface Atm {

    int getBalance();

    void cashReplenishment(Map<Banknotes, Integer> cash);

    Map<Banknotes, Integer> cashWithdrawal(int amount);

    void restoreState();

    void saveState();
}
