package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.time.LocalDate;
import java.util.Collection;

public interface AbsenceDAO {
    Absence save(Absence absence);

    Collection<Absence> getAll();

    Collection<Absence> getPage(int start, int count);

    Collection<Absence> getPageFiltered(int start, int count, String fname, String mname, String lname, Position position, LocalDate date);

    long getCount();

    long getCountFiltered(String fname, String mname, String lname, Position position, LocalDate date);
}
