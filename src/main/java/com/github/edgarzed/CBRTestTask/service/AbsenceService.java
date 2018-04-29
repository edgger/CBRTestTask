package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.time.LocalDate;
import java.util.Collection;

public interface AbsenceService {
    Absence save(Employee employee, Absence absence);
    Collection<Absence> getAll();
    Collection<Absence> getFiltered(Collection<Employee> employee, Position position, LocalDate date);
}
