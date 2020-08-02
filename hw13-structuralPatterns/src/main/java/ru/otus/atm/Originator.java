package ru.otus.atm;

import java.util.ArrayDeque;
import java.util.Deque;

public class Originator {
    private final Deque<Memento> stack = new ArrayDeque<>();

    void saveState(AtmImpl state) {
        stack.push(new Memento(state));
    }

    AtmImpl restoreState() {
        return stack.pop().getState();
    }
}
