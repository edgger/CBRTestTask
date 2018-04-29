package com.github.edgarzed.CBRTestTask.dto;

import java.time.LocalDate;

public class AbsenceData {
    private int id;
    private String employee;
    private String employeePosition;
    private LocalDate date;
    private short timeMinutes;
    private String reason;

    public AbsenceData(int id, String employee, String employeePosition, LocalDate date, short timeMinutes, String reason) {
        this.id = id;
        this.employee = employee;
        this.employeePosition = employeePosition;
        this.date = date;
        this.timeMinutes = timeMinutes;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public LocalDate getDate() {
        return date;
    }

    public short getTimeMinutes() {
        return timeMinutes;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "AbsenceData{" +
                "id=" + id +
                ", employee='" + employee + '\'' +
                ", employeePosition='" + employeePosition + '\'' +
                ", date=" + date +
                ", timeMinutes=" + timeMinutes +
                ", reason='" + reason + '\'' +
                '}';
    }
}
