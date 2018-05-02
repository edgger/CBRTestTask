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

    @ManyToOne(fetch = FetchType.LAZY)
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public short getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(short timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "date=" + date +
                ", timeMinutes=" + timeMinutes +
                ", reason='" + reason + '\'' +
                ", employee=" + employee +
                ", id=" + id +
                '}';
    }
}
