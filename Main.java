package com.hello;

import com.hello.entity.Class;
import com.hello.entity.Grade;
import com.hello.entity.Student;
import com.hello.entity.Test;
import com.hello.services.ClassService;
import com.hello.services.GradeService;
import com.hello.services.StudentService;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {

        new SwingGuiHibernate();
        /*
        StudentService studentService = new StudentService();
        ClassService classService = new ClassService();
        GradeService gradeService = new GradeService();

        Student student = new Student("Test", "Test", 2002, 2.2, StudentCondition.chory, 2);

        studentService.persist(student);

        student.setImie("Updated");
        student.setNazwisko("Updated");

        studentService.update(student);

        List<Student> students = studentService.findAll();

        for(Student s : students) {
            System.out.println(s.toString());
        }
*/

        /*
        List<Class> c = classService.findAll();

        for(Class s : c) {
            System.out.println(s.toString());
        }

        List<Grade> grades = gradeService.findAll();

        for(Grade s : grades) {
            System.out.println(s.toString());
        }
         */
        //Student s = new Student("Test", "Test", 2002, 2.2, StudentCondition.chory, 2);
        //studentService.persist(s);
        //studentService.delete(34l);

        //Test t = new Test("Adam", "Nowak");

        //Class c = new Class("5fdg", 233);
        //HibernateRepository.Connection(c);



        /*

        ClassContainer groups = new ClassContainer();
        groups.addClass("1B", 12);
        groups.addClass("3A", 28);

        Student s1 = new Student("Adam",      "Nowak",       StudentCondition.chory,       2003);
        Student s2 = new Student("Jakub",     "Kowalski",    StudentCondition.odrabiajacy, 2004);
        Student s3 = new Student("Adrian",    "Wisniewski",  StudentCondition.obecny,      2003);
        Student s4 = new Student("Paulina",   "Lewandowska", StudentCondition.obecny,      2003);
        Student s5 = new Student("Kinga",     "Majewska",    StudentCondition.obecny,      2003);
        Student s6 = new Student("Artur",     "Kowalski",    StudentCondition.odrabiajacy, 2004);
        Student s7 = new Student("Mateusz",   "Zielinski",   StudentCondition.obecny,      2003);
        Student s8 = new Student("Karol",     "Szymanski",   StudentCondition.chory,       2003);
        Student s9 = new Student("Dawid",     "Kaminski",    StudentCondition.obecny,      2003);
        Student s10 = new Student("Marcin",   "Wozniak",     StudentCondition.odrabiajacy, 2005);
        Student s11 = new Student("Dominika", "Wilk",        StudentCondition.obecny,      2003);
        Student s12 = new Student("Paulina",   "Lewandowska", StudentCondition.obecny,      2003);
        Student s13 = new Student("Kinga",     "Majewska",    StudentCondition.obecny,      2003);
        Student s14 = new Student("Artur",     "Kowalski",    StudentCondition.odrabiajacy, 2004);
        Student s15 = new Student("Mateusz",   "Zielinski",   StudentCondition.obecny,      2003);
        Student s16 = new Student("Karol",     "Szymanski",   StudentCondition.chory,       2003);

        groups.getClass("1B").addStudent(s1);
        groups.getClass("1B").addStudent(s2);
        groups.getClass("1B").addStudent(s3);
        groups.getClass("1B").addStudent(s4);
        groups.getClass("1B").addStudent(s5);
        groups.getClass("1B").addStudent(s6);
        groups.getClass("1B").addStudent(s7);
        groups.getClass("1B").addStudent(s8);
        groups.getClass("1B").addStudent(s9);
        groups.getClass("1B").addStudent(s10);
        groups.getClass("3A").addStudent(s11);
        groups.getClass("3A").addStudent(s12);
        groups.getClass("3A").addStudent(s13);
        groups.getClass("3A").addStudent(s14);
        groups.getClass("3A").addStudent(s15);
        groups.getClass("3A").addStudent(s16);

        groups.getClass("1B").addPoints(s1, 8);
        groups.getClass("1B").addPoints(s2, 4);
        groups.getClass("1B").addPoints(s3, 12);
        groups.getClass("1B").addPoints(s4, 20);
        groups.getClass("1B").addPoints(s5, 1);
        groups.getClass("1B").addPoints(s6, 9);
        groups.getClass("1B").addPoints(s7, 0);
        groups.getClass("1B").addPoints(s8, 33);
        groups.getClass("1B").addPoints(s9, 5);
        groups.getClass("1B").addPoints(s10, 17);
        groups.getClass("3A").addPoints(s11, 19);
        groups.getClass("3A").addPoints(s12, 1);
        groups.getClass("3A").addPoints(s13, 6);
        groups.getClass("3A").addPoints(s14, 13);
        groups.getClass("3A").addPoints(s15, 5);
        groups.getClass("3A").addPoints(s16, 26);


         */

        //groups.summary();

        //groups.getClass("1B").sortByPoints();

        //new SwingGUI(groups);
    }
}

/*
create table student(
    student_id SERIAL PRIMARY KEY,
    imie VARCHAR(50) NOT NULL,
    nazwisko VARCHAR(50) NOT NULL,
    rok_urodzenia INT NOT NULL,
    ilosc_punktow DOUBLE PRECISION NOT NULL,
    status VARCHAR(20),
    class_id SERIAL NOT NULL,
    CONSTRAINT student_class_fk FOREIGN KEY (class_id)
    REFERENCES class(class_id)
);

create table class(
    class_id SERIAL PRIMARY KEY,
    nazwa_grupy VARCHAR(20) UNIQUE NOT NULL,
    maksymalna_ilosc_studentow INT NOT NULL
);

create table grade(
    grade_id SERIAL PRIMARY KEY,
    ocena DOUBLE PRECISION NOT NULL,
    przedmiot VARCHAR(20) NOT NULL,
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    komentarz VARCHAR(50),
    student_id SERIAL NOT NULL,
    CONSTRAINT student_class_fk FOREIGN KEY (student_id)
        REFERENCES student(student_id)
);

DROP table grade;

INSERT INTO class(nazwa_grupy, maksymalna_ilosc_studentow)
VALUES ('3A', 25);

INSERT INTO class(nazwa_grupy, maksymalna_ilosc_studentow)
VALUES ('1B', 32);

INSERT INTO class(nazwa_grupy, maksymalna_ilosc_studentow)
VALUES ('2C', 20);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status)
VALUES ('Adam', 'Nowak', 2003, 0.0, 'chory');

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status)
VALUES ('Jakub', 'Kowalski', 2002, 2.5, 'odrabiajacy');

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Adrian', 'Wisniewski', 2004, 1.25, 'obecny', 2);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Paulina', 'Lewandowska', 2003, 0, 'obecny', 3);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Kinga', 'Majewska', 2002, 5, 'obecny', 1);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Artur', 'Kowalski', 2003, 1, 'odrabiajacy', 2);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Mateusz', 'Zielinski', 2002, 0, 'chory', 2);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Karol', 'Szymanski', 2002, 0, 'obecny', 3);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Norbert', 'Lewandowski', 2002, 5, 'obecny', 1);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Klaudia', 'Kot', 2001, 3.5, 'obecny', 2);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Pawel', 'Korzychowski', 2001, 8, 'obecny', 2);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Krystian', 'Pierzchala', 2003, 0.75, 'obecny', 1);

INSERT INTO student(imie, nazwisko, rok_urodzenia, ilosc_punktow, status, class_id)
VALUES ('Dominik', 'Kowalski', 2003, 2, 'obecny', 1);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (2.5, 'Analiza Matematyczna', '2022-01-21', 'lab1', 2);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (1.25, 'Angielski', '2022-01-25', 'lab1 - brak konspektu', 3);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (0, 'Wzorce projektowe', '2022-01-27', 'kolokwium 1 - niesamodzielna praca', 4);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (2, 'Analiza Matematyczna', '2022-01-21', 'lab1', 5);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (3, 'Angielski', '2022-01-25', 'lab1', 5);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (1, 'Wzorce projektowe', '2022-01-27', 'kolokwium 1', 6);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (2, 'Wzorce projektowe', '2022-01-27', 'kolokwium 1', 9);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (3, 'Analiza Matematyczna', '2022-01-21', 'lab 1', 9);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (1.5, 'Analiza Matematyczna', '2022-01-21', 'lab 1', 10);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (2, 'Angielski', '2022-01-21', 'lab 1', 10);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (3, 'Angielski', '2022-01-25', 'lab 1', 11);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (5, 'Angielski', '2022-01-28', 'olimpiada', 11);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (0.75, 'Analiza Matematyczna', '2022-01-21', 'lab 1 - niescislosci', 12);

INSERT INTO grade(ocena, przedmiot, data, komentarz, student_id)
VALUES (2, 'Analiza Matematyczna', '2022-01-21', 'lab 1', 13);
 */