package ru.otus;

public enum Banknotes {

    B10(10),
    B50(50),
    B100(100),
    B200(200),
    B500(500),
    B1000(1000),
    B2000(2000),
    B5000(5000);

    private final int value;

    public int getValue() {
        return value;
    }

    Banknotes(int value) {
        this.value = value;
    }
}
