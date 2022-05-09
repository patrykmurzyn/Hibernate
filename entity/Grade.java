package com.hello.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "grade_id", updatable = false, nullable = false)
    private long id;

    @Column(name = "ocena")
    private int ocena;

    @Column(name = "przedmiot")
    private String przedmiot;

    @Column(name = "data")
    private Date data;

    @Column(name = "komentarz")
    private String komentarz;

    @Column(name = "student_id")
    private Long student_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(String komentarz) {
        this.komentarz = komentarz;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Grade(int ocena, String przedmiot, Date data, String komentarz, Long student_id) {
        this.ocena = ocena;
        this.przedmiot = przedmiot;
        this.data = data;
        this.komentarz = komentarz;
        this.student_id = student_id;
    }

    public Grade() {
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", ocena=" + ocena +
                ", przedmiot='" + przedmiot + '\'' +
                ", data=" + data +
                ", komentarz='" + komentarz + '\'' +
                ", student_id=" + student_id +
                '}';
    }
}
