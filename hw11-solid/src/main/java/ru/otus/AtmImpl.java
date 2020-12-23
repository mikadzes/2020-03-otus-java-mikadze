package ru.otus;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmImpl implements Atm {

    private TreeMap<Banknotes, Integer> state;

    public AtmImpl(Map<Banknotes, Integer> cash) {
        state = new TreeMap<>();
        state.putAll(cash);
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
                        Integer::sum,
                        TreeMap::new));
    }

    public Map<Banknotes, Integer> cashWithdrawal(int amount) throws IllegalArgumentException, IllegalStateException {
        if (amount > getBalance()) {
            throw new IllegalStateException("В банкомате недостаточно средств");
        }
        Banknotes smallestBanknote = getSmallestBanknote();
        if (amount % smallestBanknote.getValue() > 0) {
            throw new IllegalArgumentException("Введите сумму кратную " + smallestBanknote);
        }

        Map<Banknotes, Integer> tray = new TreeMap<>();
        for (Map.Entry<Banknotes, Integer> entry : state.entrySet()) {
            int count = getBanknotesCount(amount, entry);
            amount -= entry.getKey().getValue() * count;
            if (count > 0) {
                tray.put(entry.getKey(), count);
            }
        }

        this.state = Stream.concat(state.entrySet().stream(), tray.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1 - v2,
                        TreeMap::new));
        return tray;
    }

    private int getBanknotesCount(int amount, Map.Entry<Banknotes, Integer> entry) {
        int neededCount = amount / entry.getKey().getValue();
        return entry.getValue() >= neededCount ? neededCount : entry.getValue();
    }

    private Banknotes getSmallestBanknote() {
        return state.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .min(Comparator.comparingInt(o -> o.getKey().getValue()))
                .get()
                .getKey();
    }
}
