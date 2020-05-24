package ru.otus.atm.snapshot;

import ru.otus.atm.Atm;

public class AtmSnapshotImpl implements AtmSnapshot{
    private Integer count_10 = 0;
    private Integer count_50 = 0;
    private Integer count_100 = 0;
    private Integer count_200 = 0;
    private Integer count_500 = 0;
    private Integer count_1000 = 0;
    private Integer count_2000 = 0;
    private Integer count_5000 = 0;

    public AtmSnapshotImpl(Atm atm) {
        this.count_10 = atm.getCount_10();
        this.count_50 = atm.getCount_50();
        this.count_100 = atm.getCount_100();
        this.count_200 = atm.getCount_200();
        this.count_500 = atm.getCount_500();
        this.count_1000 = atm.getCount_1000();
        this.count_2000 = atm.getCount_2000();
        this.count_5000 = atm.getCount_5000();
    }

    public Integer getCount_10() {
        return count_10;
    }

    public Integer getCount_50() {
        return count_50;
    }

    public Integer getCount_100() {
        return count_100;
    }

    public Integer getCount_200() {
        return count_200;
    }

    public Integer getCount_500() {
        return count_500;
    }

    public Integer getCount_1000() {
        return count_1000;
    }

    public Integer getCount_2000() {
        return count_2000;
    }

    public Integer getCount_5000() {
        return count_5000;
    }
}
