package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;

import java.time.LocalDate;
import java.util.Collection;

public interface AbsenceService {
    Absence save(Absence absence);

    Collection<Absence> getAll();

    Collection<Absence> getFiltered(Collection<Employee> employee, LocalDate date);
}
