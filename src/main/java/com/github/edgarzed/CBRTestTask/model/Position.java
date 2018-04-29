package com.github.edgarzed.CBRTestTask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Position() {
    }

    public String getName() {
        return name;
    }
}
