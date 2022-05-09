package com.hello.entity;

import com.hello.StudentCondition;
import com.hello.services.ClassService;
import com.hello.services.StudentService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "class_table")
public class Class
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "class_id", updatable = false, nullable = false)
    private long id;
    @Column(name = "nazwa_grupy")
    private String nazwa_grupy;
    @Column(name = "maksymalna_ilosc_studentow")
    private int maksymalna_ilosc_studentow;
    @Transient
    private float procentowe_zapelnienie;
    @Transient
    private ArrayList<Student> students = new ArrayList<Student>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa_grupy() {
        return nazwa_grupy;
    }

    public void setNazwa_grupy(String nazwa_grupy) {
        this.nazwa_grupy = nazwa_grupy;
    }

    public int getMaksymalna_ilosc_studentow() {
        return maksymalna_ilosc_studentow;
    }

    public void setMaksymalna_ilosc_studentow(int maksymalna_ilosc_studentow) {
        this.maksymalna_ilosc_studentow = maksymalna_ilosc_studentow;
    }

    public float getProcentowe_zapelnienie() {
        return procentowe_zapelnienie;
    }

    public void setProcentowe_zapelnienie(float procentowe_zapelnienie) {
        this.procentowe_zapelnienie = procentowe_zapelnienie;
    }

    public Class(String nazwa_grupy, int maksymalna_ilosc_studentow) {
        this.nazwa_grupy = nazwa_grupy;
        this.maksymalna_ilosc_studentow = maksymalna_ilosc_studentow;
    }

    public Class() {
    }

    public void CalculateStudentsPercentage() {
        StudentService studentService = new StudentService();
        List<Student> students = studentService.findAllFromClass(id);
        ClassService classService = new ClassService();
        procentowe_zapelnienie = (float)students.size()/(float)maksymalna_ilosc_studentow * 100f;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student s) {
        if(students.size() + 1 > maksymalna_ilosc_studentow) {
            System.out.println("Przekroczono limit studentow");
        } else if(students.contains(s)) {
            System.out.println("Student " + s.getImie() + " " + s.getNazwisko() + " juz istnieje w grupie " + this.nazwa_grupy);
        }
        else students.add(s);
    }

    public void removeStudent(Student s) {
        students.remove(s);
    }

    public void addPoints(Student s, double ocena) {
        if(!this.students.contains(s)){
            System.out.println("Student nie znajduje sie w tej grupie!");
        } else {
            s.setIlosc_punktow(s.getIlosc_punktow() + ocena);
            System.out.println("Dodano " + ocena + " do punktow " + s.getImie() + " " + s.getNazwisko() + ", aktualnie: " + s.getIlosc_punktow());
        }
    }

    public void removePoints(Student s, double ocena) {
        if(!this.students.contains(s)){
            System.out.println("Student nie znajduje sie w tej grupie!");
        } else {
            s.setIlosc_punktow(s.getIlosc_punktow() + ocena);
            System.out.println("Odjeto " + ocena + " od punktow " + s.getImie() + " " + s.getNazwisko() + ", aktualnie: " + s.getIlosc_punktow());
        }
    }

    public void getStudent(Student s) {
        if(s.getIlosc_punktow() == 0) {
            students.remove(s);
            System.out.println("Liczba punktow studenta rowna 0 - usuniety");
        } else if(!this.students.contains(s)){
            System.out.println("Student nie znajduje sie w tej grupie!");
        } else {
            s.print();
        }
    }



    public void changeCondition(Student s, StudentCondition d) {
        if(!this.students.contains(s)){
            System.out.println("Student nie znajduje sie w tej grupie!");
        } else {
            s.setStatus(d);
            System.out.println("Zmieniono status " + s.getImie() + " " + s.getNazwisko() + " na " + s.getStatus());
        }
    }

    public Student search(String naz) {
        boolean isFound = false;
        for(Student s : students) {
            if(s.getNazwisko() == naz) {
                System.out.println("Znaleziono studenta: " + s.getNazwisko() + s.getImie());
                s.print();
                isFound = true;
                return s;
            }
        }
        if(!isFound) System.out.println("Brak studenta");
        return new Student();
    }

    public void searchPatrial(String value) {
        boolean isFound = false;
        for(Student s : students) {
            if(s.getNazwisko().toLowerCase().contains(value.toLowerCase()) || s.getImie().toLowerCase().contains(value.toLowerCase())) {
                System.out.println(s.getImie() + " " + s.getNazwisko());
                isFound = true;
            }
        }
        if(!isFound) System.out.println("Nie znaleziono studenta!");
    }

    public void countByCondition(StudentCondition condition) {
        int counter = 0;
        for(Student s : students) {
            if(s.getStatus() == condition) counter++;
        }
        System.out.println("Liczba studentow o stanie: " + condition + ": " + counter);
    }

    public void summary() {
        System.out.println("Informacje o wszystkich studentach: ");
        int i = 1;
        for(Student s : students) {
            System.out.println(i + ") " + s.getImie() + " " + s.getNazwisko() + ", rok urodzenia: " + s.getRok_urodzenia() + ", status: " + s.getStatus() + ", liczba punktow: " + s.getIlosc_punktow());
            i++;
        }
    }

    public void sortByName() {
        students.sort(Student::compareByName);
        System.out.println("Lista studentow zostala posortowane alfabetycznie po nazwiskach: ");
        this.summary();
    }

    public void sortByPoints() {
        students.sort(Student::compareByPoints);
        System.out.println("Lista studentow zostala posortowane rosnaco po liczbie punktow: ");
        this.summary();
    }

    public void max() {

        System.out.println("Najwiecej uzyskanych punktow: " + Collections.max(students, Student::compareByPoints));
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", nazwa_grupy='" + nazwa_grupy + '\'' +
                ", maksymalna_ilosc_studentow=" + maksymalna_ilosc_studentow +
                '}';
    }
}