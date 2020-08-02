package ru.otus;

import ru.otus.atm.Atm;
import ru.otus.atm.AtmImpl;
import ru.otus.atm.Banknotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AtmDepartment {
    private List<Atm> atms = new ArrayList<>();

    public void addAtm(Map<Banknotes, Integer> cash) {
        atms.add(new AtmImpl(cash));
    }

    public Integer getDepartmentBalance() {
        return atms.stream().mapToInt(Atm::getBalance).sum();
    }

    public void restoreState() {
        atms.forEach(Atm::restoreState);
    }

    public Atm getAtm(int i) {
        return atms.get(i);
    }
}
