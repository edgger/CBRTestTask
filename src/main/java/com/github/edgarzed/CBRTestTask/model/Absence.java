package com.github.edgarzed.CBRTestTask.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "absences")
public class Absence extends AbstractBaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_minutes", nullable = false)
    private short timeMinutes;

    @Column(name = "reason", nullable = false)
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    public Absence() {
    }

    public Absence(LocalDate date, short timeMinutes, String reason, Employee employee) {
        this.date = date;
        this.timeMinutes = timeMinutes;
        this.reason = reason;
        this.employee = employee;
    }
}
