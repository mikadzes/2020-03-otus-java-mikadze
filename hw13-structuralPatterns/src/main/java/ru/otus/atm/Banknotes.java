package ru.otus.atm;

public enum Banknotes {

    B5000(5000),
    B2000(2000),
    B1000(1000),
    B500(500),
    B200(200),
    B100(100),
    B50(50),
    B10(10);

    private final int value;

    public int getValue() {
        return value;
    }

    Banknotes(int value) {
        this.value = value;
    }
}
