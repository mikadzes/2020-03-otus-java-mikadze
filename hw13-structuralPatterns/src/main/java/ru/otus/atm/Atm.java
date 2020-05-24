package ru.otus.atm;

import ru.otus.atm.snapshot.AtmSnapshot;

import java.util.Map;

public interface Atm {

    Integer getBalance();

    void cashReplenishment(Map<Banknotes, Integer> cash);

    Map<Banknotes, Integer> cashWithdrawal(Integer amount);

    void restoreInitialState();

    Integer getCount_10();

    Integer getCount_50();

    Integer getCount_100();

    Integer getCount_200();

    Integer getCount_500();

    Integer getCount_1000();

    Integer getCount_2000();

    Integer getCount_5000();
}
