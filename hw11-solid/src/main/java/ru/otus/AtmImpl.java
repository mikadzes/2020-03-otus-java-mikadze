package ru.otus;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.Banknotes.*;

public class AtmImpl implements Atm {

    private Integer count_10 = 0;
    private Integer count_50 = 0;
    private Integer count_100 = 0;
    private Integer count_200 = 0;
    private Integer count_500 = 0;
    private Integer count_1000 = 0;
    private Integer count_2000 = 0;
    private Integer count_5000 = 0;

    private Map<Banknotes, Integer> tray = new HashMap<>();

    public Integer getBalance() {
        return B10.getValue() * count_10
                + B50.getValue() * count_50
                + B100.getValue() * count_100
                + B200.getValue() * count_200
                + B500.getValue() * count_500
                + B1000.getValue() * count_1000
                + B2000.getValue() * count_2000
                + B5000.getValue() * count_5000;
    }

    public void cashReplenishment(Map<Banknotes, Integer> cash) {
        count_10 += cash.get(B10);
        count_50 += cash.get(B50);
        count_100 += cash.get(B100);
        count_200 += cash.get(B200);
        count_500 += cash.get(B500);
        count_1000 += cash.get(B1000);
        count_2000 += cash.get(B2000);
        count_5000 += cash.get(B5000);
    }

    public Map<Banknotes, Integer> cashWithdrawal(Integer amount) throws IllegalArgumentException, IllegalStateException {
        if (amount > getBalance()) {
            throw new IllegalStateException("В банкомате недостаточно средств");
        }
        if (amount % B10.getValue() > 0) {
            throw new IllegalArgumentException("Введите сумму кратную 10");
        }
        tray.clear();
        amount = addToTray(amount, B5000);
        amount = addToTray(amount, B2000);
        amount = addToTray(amount, B1000);
        amount = addToTray(amount, B500);
        amount = addToTray(amount, B200);
        amount = addToTray(amount, B100);
        amount = addToTray(amount, B50);
        amount = addToTray(amount, B10);
        if (amount != 0) {
            throw new IllegalArgumentException("Внутренняя ошибка банкомата");
        }
        return tray;
    }

    private Integer addToTray(Integer amount, Banknotes banknote) {
        Integer count = calculateBanknoteCount(amount, banknote);
        if (count != null && count != 0) {
            tray.put(banknote, count);
        }
        return calculateAmount(amount, banknote, count);
    }

    private Integer calculateAmount(Integer amount, Banknotes banknote, Integer count) {
        if (count != null) {
            return amount - banknote.getValue() * count;
        }
        return amount;
    }

    private Integer calculateBanknoteCount(Integer amount, Banknotes banknote) {
        int neededCount = amount / banknote.getValue();
        switch (banknote) {
            case B10:
                return count_10 < neededCount ? count_10 : neededCount;
            case B50:
                return count_50 < neededCount ? count_50 : neededCount;
            case B100:
                return count_100 < neededCount ? count_100 : neededCount;
            case B200:
                return count_200 < neededCount ? count_200 : neededCount;
            case B500:
                return count_500 < neededCount ? count_500 : neededCount;
            case B1000:
                return count_1000 < neededCount ? count_1000 : neededCount;
            case B2000:
                return count_2000 < neededCount ? count_2000 : neededCount;
            case B5000:
                return count_5000 < neededCount ? count_5000 : neededCount;
        }
        return null;
    }

}
