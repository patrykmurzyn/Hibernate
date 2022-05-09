package com.hello.entity;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "test_id", updatable = false, nullable = false)
    private long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "second_name")
    private String second_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Test(String first_name, String second_name) {
        this.first_name = first_name;
        this.second_name = second_name;
    }

    public Test() {
    }
}
