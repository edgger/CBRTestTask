package com.github.edgarzed.CBRTestTask.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employees")
public class Employee extends AbstractBaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Collection<Absence> absences;

    public Employee() {
    }
}
