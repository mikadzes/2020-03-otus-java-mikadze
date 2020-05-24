package ru.otus;

import ru.otus.atm.Atm;

import java.util.ArrayList;
import java.util.List;


public class AtmDepartment {
    private List<Atm> atms = new ArrayList<>();

    public void addAtm(Atm atm) {
        atms.add(atm);
    }

    public Integer getDepartmentBalance() {
        return atms.stream().mapToInt(Atm::getBalance).sum();
    }

    public void restoreState() {
        atms.forEach(Atm::restoreInitialState);
    }

    public Atm getAtm(int i) {
        return atms.get(i);
    }

    public void setAtm(int i, Atm atm) {
        atms.set(i, atm);
    }
}
