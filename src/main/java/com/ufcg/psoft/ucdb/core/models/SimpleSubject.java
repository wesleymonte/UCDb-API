package com.ufcg.psoft.ucdb.core.models;

import java.io.Serializable;

public class SimpleSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public SimpleSubject(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
