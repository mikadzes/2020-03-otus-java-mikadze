package ru.otus.atm;

import lombok.Getter;
import ru.otus.atm.snapshot.AtmSnapshot;
import ru.otus.atm.snapshot.AtmSnapshotImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.atm.Banknotes.*;

@Getter
public class AtmBuilder {

    private Integer count_10 = 0;
    private Integer count_50 = 0;
    private Integer count_100 = 0;
    private Integer count_200 = 0;
    private Integer count_500 = 0;
    private Integer count_1000 = 0;
    private Integer count_2000 = 0;
    private Integer count_5000 = 0;

    private Map<Banknotes, Integer> tray = new HashMap<>();
    private AtmSnapshot initialState;

    public AtmBuilder setCount_10(int count) {
        this.count_10 = count;
        return this;
    }

    public AtmBuilder setCount_50(int count) {
        this.count_50 = count;
        return this;
    }

    public AtmBuilder setCount_100(int count) {
        this.count_100 = count;
        return this;
    }

    public AtmBuilder setCount_200(int count) {
        this.count_200 = count;
        return this;
    }

    public AtmBuilder setCount_500(int count) {
        this.count_500 = count;
        return this;
    }

    public AtmBuilder setCount_1000(int count) {
        this.count_1000 = count;
        return this;
    }

    public AtmBuilder setCount_2000(int count) {
        this.count_2000 = count;
        return this;
    }

    public AtmBuilder setCount_5000(int count) {
        this.count_5000 = count;
        return this;
    }

    public Atm build() {
        return new AtmImpl(this);
    }

}
