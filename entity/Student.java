package com.hello.entity;
import com.hello.Comparable;
import com.hello.StudentCondition;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Student implements Comparable<Student>
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id", updatable = false, nullable = false)
    private long id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "rok_urodzenia")
    private int rok_urodzenia;
    @Column(name = "ilosc_punktow")
    private double ilosc_punktow;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StudentCondition status;
    @Column(name = "class_id")
    private long class_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(int rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public double getIlosc_punktow() {
        return ilosc_punktow;
    }

    public void setIlosc_punktow(double ilosc_punktow) {
        this.ilosc_punktow = ilosc_punktow;
    }

    public StudentCondition getStatus() {
        return status;
    }

    public void setStatus(StudentCondition status) {
        this.status = status;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public Student(String imie, String nazwisko, int rok_urodzenia, double ilosc_punktow, StudentCondition status, long class_id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_urodzenia = rok_urodzenia;
        this.ilosc_punktow = ilosc_punktow;
        this.status = status;
        this.class_id = class_id;
    }

    public Student() {
    }

    public Student(String imie, String nazwisko, StudentCondition status, int rok_urodzenia) {
        this.id = this.id;
        this.id++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.status = status;
        this.rok_urodzenia = rok_urodzenia;
        this.ilosc_punktow = 0;
    }

    public String print() {
        return "Student{" +
                "Imie='" + imie + '\'' +
                ", Nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +
                ", ilosc_punktow=" + ilosc_punktow +
                '}';
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +
                ", ilosc_punktow=" + ilosc_punktow +
                ", status=" + status +
                ", class_id=" + class_id +
                '}';
    }

    @Override
    public int compareByName(Student s) {
        return this.nazwisko.compareTo(s.nazwisko);
    }

    @Override
    public int compareByPoints(Student s) {
        return Double.compare(this.ilosc_punktow, s.ilosc_punktow);
    }
}