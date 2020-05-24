package ru.otus.atm;

import lombok.Getter;
import ru.otus.atm.snapshot.AtmSnapshot;
import ru.otus.atm.snapshot.AtmSnapshotImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.otus.atm.Banknotes.*;

@Getter
public class AtmImpl implements Atm {

    private Integer count_10;
    private Integer count_50;
    private Integer count_100;
    private Integer count_200;
    private Integer count_500;
    private Integer count_1000;
    private Integer count_2000;
    private Integer count_5000;

    private Map<Banknotes, Integer> tray = new HashMap<>();
    private AtmSnapshot initialState;

    public AtmImpl(AtmBuilder builder) {
        count_10 = builder.getCount_10();
        count_50 = builder.getCount_50();
        count_100 = builder.getCount_100();
        count_200 = builder.getCount_200();
        count_500 = builder.getCount_500();
        count_1000 = builder.getCount_1000();
        count_2000 = builder.getCount_2000();
        count_5000 = builder.getCount_5000();
        saveSnapshot();
    }

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
        count_10 += cash.getOrDefault(B10, 0);
        count_50 += cash.getOrDefault(B50, 0);
        count_100 += cash.getOrDefault(B100, 0);
        count_200 += cash.getOrDefault(B200, 0);
        count_500 += cash.getOrDefault(B500, 0);
        count_1000 += cash.getOrDefault(B1000, 0);
        count_2000 += cash.getOrDefault(B2000, 0);
        count_5000 += cash.getOrDefault(B5000, 0);
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

    public void restoreInitialState() {
        this.count_10 = initialState.getCount_10();
        this.count_50 = initialState.getCount_50();
        this.count_100 = initialState.getCount_100();
        this.count_200 = initialState.getCount_200();
        this.count_500 = initialState.getCount_500();
        this.count_1000 = initialState.getCount_1000();
        this.count_2000 = initialState.getCount_2000();
        this.count_5000 = initialState.getCount_5000();
    }

    private void saveSnapshot() {
        initialState = new AtmSnapshotImpl(this);
    }

    private Integer addToTray(Integer amount, Banknotes banknote) {
        Integer count = calculateBanknoteCount(amount, banknote);
        if (count != null && count != 0) {
            tray.put(banknote, count);
            setBanknoteCount(banknote, count);
        }
        return calculateAmount(amount, banknote, count);
    }

    private void setBanknoteCount(Banknotes banknote, Integer count) {
        switch (banknote) {
            case B10:
                this.count_10 -= count;
                break;
            case B50:
                this.count_50 -= count;
                break;
            case B100:
                this.count_100 -= count;
                break;
            case B200:
                this.count_200 -= count;
                break;
            case B500:
                this.count_500 -= count;
                break;
            case B1000:
                this.count_1000 -= count;
                break;
            case B2000:
                this.count_2000 -= count;
                break;
            case B5000:
                this.count_5000 -= count;
                break;
        }
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
