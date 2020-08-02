package ru.otus.atm;

class Memento {
    private final AtmImpl state;

    Memento(AtmImpl atm) {
        this.state = new AtmImpl(atm);
    }

    AtmImpl getState() {
        return state;
    }
}