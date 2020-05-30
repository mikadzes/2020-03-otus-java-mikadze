package ru.otus;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.otus.Banknotes.B10;

public class AtmImpl implements Atm {

    private Map<Banknotes, Integer> state;

    public AtmImpl(Map<Banknotes, Integer> cash) {
        this.state = cash;
    }

    public Integer getBalance() {
        return state.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .sum();
    }

    public void cashReplenishment(Map<Banknotes, Integer> cash) {
        this.state = Stream.concat(state.entrySet().stream(), cash.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
    }

    public Map<Banknotes, Integer> cashWithdrawal(int amount) throws IllegalArgumentException, IllegalStateException {
        if (amount > getBalance()) {
            throw new IllegalStateException("В банкомате недостаточно средств");
        }
        if (amount % B10.getValue() > 0) {
            throw new IllegalArgumentException("Введите сумму кратную 10");
        }
        final int[] v = new int[1];
        v[0] = amount;
        Map<Banknotes, Integer>
                tray = state.entrySet().stream()
                .sorted(Map.Entry.<Banknotes, Integer>comparingByKey())
                .map(entry -> {
                    int count = getBanknotesCount(v[0], entry);
                    v[0] -= entry.getKey().getValue() * count;
                    return Map.entry(entry.getKey(), count);
                })
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

        this.state = Stream.concat(state.entrySet().stream(), tray.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1 - v2));
        return tray;
    }

    private int getBanknotesCount(int amount, Map.Entry<Banknotes, Integer> entry) {
        int neededCount = amount / entry.getKey().getValue();
        return entry.getValue() >= neededCount ? neededCount : entry.getValue();
    }
}
