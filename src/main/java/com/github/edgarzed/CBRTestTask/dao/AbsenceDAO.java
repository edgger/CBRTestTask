package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;

import java.time.LocalDate;
import java.util.Collection;

public interface AbsenceDAO {
    Absence save(Absence absence);
    Collection<Absence> getAll();
    Collection<Absence> getFiltered(Collection<Employee> employee, LocalDate date);
}
