package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;

import java.time.LocalDate;
import java.util.Collection;

public interface AbsenceDAO {
    Absence save(Absence absence);

    Collection<Absence> getAll();

    Collection<Absence> getPage(int start, int count);

    Collection<Absence> getPageFiltered(int start, int count, Employee employee, LocalDate date);

    long getCount();

    long getCountFiltered(Employee employee, LocalDate date);
}
