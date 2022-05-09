package com.hello;

import com.hello.entity.Class;
import com.hello.entity.Student;
import com.hello.services.ClassService;
import com.hello.services.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.List;

public class SwingGuiHibernate {

    JFrame f;
    JPanel panel;
    DefaultTableModel tableModel1;
    JTable j1;
    DefaultTableModel tableModel2;
    JTable j2;
    boolean selectStudent = true;

    SwingGuiHibernate() {

        f = new JFrame("Dziennik");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //lista grup
        tableModel1 = new DefaultTableModel();
        j1 = new JTable(tableModel1);
        tableModel1.addColumn("Nazwa Grupy");
        tableModel1.addColumn("Zapełnienie");

        List<Class> classList = GetClassList();

        for(int i = 0; i < classList.size(); i++) {
            classList.get(i).CalculateStudentsPercentage();
            tableModel1.insertRow(i, new Object[] {classList.get(i).getNazwa_grupy(), Math.round(classList.get(i).getProcentowe_zapelnienie() * 100.0)/100.0 + "%" });
        }

        j1.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel1 = j1.getSelectionModel();
        j1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel1.addListSelectionListener(e -> {

            if(selectStudent) {
                int selectedRow = j1.getSelectedRow();

                System.out.println("Selected: " + selectedRow);

                ShowStudentsForSelectedGroup(selectedRow);
            }

        });

        JScrollPane sp1 = new JScrollPane(j1);
        panel.add(sp1, BorderLayout.WEST);

        //lista studentow

        tableModel2 = new DefaultTableModel();
        j2 = new JTable(tableModel2);
        tableModel2.addColumn("Imię");
        tableModel2.addColumn("Nazwisko");
        tableModel2.addColumn("Punkty");

        j2.setBounds(30, 40, 200, 300);

        j2.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel2 = j2.getSelectionModel();
        j2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel2.addListSelectionListener(e -> {

            int selectedRow = j2.getSelectedRow();

            System.out.println("Selected: " + selectedRow);
        });


        JScrollPane sp2 = new JScrollPane(j2);

        panel.add(sp2, BorderLayout.CENTER);

        Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());

        Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout());

        JButton b1 = new JButton("Dodaj grupę");
        panel3.add(b1);

        b1.addActionListener(e -> {

            JTextField nameField = new JTextField(15);
            JTextField ammountField = new JTextField(5);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Nazwa grupy:"));
            myPanel.add(nameField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Maksymalny rozmiar:"));
            myPanel.add(ammountField);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Dodawanie grupy", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                //groups.addClass(nameField.getText(), Integer.parseInt(ammountField.getText()));
                Class c = new Class(nameField.getText(), Integer.parseInt(ammountField.getText()));
                ClassService classService = new ClassService();
                classService.persist(c);
                tableModel1.addRow(new Object[] {nameField.getText(), "0.00%"});

            }
        });

        JButton b2 = new JButton("Edytuj grupę");
        panel3.add(b2);

        b2.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                JTextField nameField = new JTextField(15);
                JTextField ammountField = new JTextField(5);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Nazwa grupy:"));
                myPanel.add(nameField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Maksymalny rozmiar:"));
                myPanel.add(ammountField);

                //Class c = groups.groups.get(tableModel1.getValueAt(j1.getSelectedRow(), 0));

                ClassService classService = new ClassService();
                Class c = classService.findByName(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString());

                StudentService studentService = new StudentService();
                List<Student> students = studentService.findAllFromClass(c.getId());

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Edytowanie grupy", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION && c.getMaksymalna_ilosc_studentow() >= students.size()) {

                    c.setNazwa_grupy(nameField.getText());
                    c.setMaksymalna_ilosc_studentow(Integer.parseInt(ammountField.getText()));
                    c.CalculateStudentsPercentage();
                    classService.update(c);

                    tableModel1.setValueAt(c.getNazwa_grupy(), j1.getSelectedRow(), 0);
                    tableModel1.setValueAt(Math.round(c.getProcentowe_zapelnienie() * 100.0)/100.0 + "%", j1.getSelectedRow(), 1);
                }
            }

        });

        JButton b3 = new JButton("Usuń grupę");
        panel3.add(b3);

        b3.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {


                ClassService classService = new ClassService();
                Class c = classService.findByName(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString());
                classService.delete(c.getId());
                selectStudent = false;
                tableModel1.removeRow(j1.getSelectedRow());
                tableModel2.setRowCount(0);

            }
            selectStudent = true;

        });

        JButton b4 = new JButton("Dodaj studenta");
        panel3.add(b4);

        b4.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                JTextField imieField = new JTextField(12);
                JTextField nazwiskoField = new JTextField(12);
                JTextField rokField = new JTextField(3);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Imię:"));
                myPanel.add(imieField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Nazwisko:"));
                myPanel.add(nazwiskoField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Rok urodzenia:"));
                myPanel.add(rokField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer

                String[] condition = {
                        "obecny", "odrabiający", "chory", "nieobecny"
                };

                JList l = new JList(condition);

                myPanel.add(new JLabel("Status:"));
                myPanel.add(l);

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Dodawanie studenta", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {

                    StudentCondition sc = StudentCondition.brak;
                    if(l.getSelectedIndex() == 0) sc = StudentCondition.obecny;
                    else if(l.getSelectedIndex() == 1) sc = StudentCondition.odrabiajacy;
                    else if(l.getSelectedIndex() == 2) sc = StudentCondition.chory;
                    else if(l.getSelectedIndex() == 3) sc = StudentCondition.nieobecny;

                    ClassService classService = new ClassService();
                    Class c = classService.findByName(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString());

                    Student s = new Student(imieField.getText(), nazwiskoField.getText(), Integer.parseInt(rokField.getText()), 0f, sc, c.getId());

                    StudentService studentService = new StudentService();
                    studentService.persist(s);

                    ShowStudentsForSelectedGroup(j1.getSelectedRow());

                    c.CalculateStudentsPercentage();
                    tableModel1.setValueAt(Math.round(c.getProcentowe_zapelnienie() * 100.0)/100.0 + "%", j1.getSelectedRow(), 1);

                }
            }

        });

        JButton b5 = new JButton("Edutuj studenta");
        panel3.add(b5);

        b5.addActionListener(e -> {
            if(!j2.getSelectionModel().isSelectionEmpty()) {

                StudentService studentService = new StudentService();
                Student student = studentService.findByName((tableModel2.getValueAt(j2.getSelectedRow(), 0).toString()), (tableModel2.getValueAt(j2.getSelectedRow(), 1).toString()));

                JTextField imieField = new JTextField(student.getImie());
                JTextField nazwiskoField = new JTextField(student.getNazwisko());
                JTextField rokField = new JTextField(String.valueOf(student.getRok_urodzenia()));
                JTextField punktyField = new JTextField(String.valueOf(student.getIlosc_punktow()));


                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Imię:"));
                myPanel.add(imieField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Nazwisko:"));
                myPanel.add(nazwiskoField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Rok urodzenia:"));
                myPanel.add(rokField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Punkty:"));
                myPanel.add(punktyField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer

                String[] condition = {
                        "obecny", "odrabiający", "chory", "nieobecny"
                };

                JList l = new JList(condition);

                myPanel.add(new JLabel("Status:"));
                myPanel.add(l);

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Edytowanie studenta", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {

                    StudentCondition sc = StudentCondition.brak;
                    if(l.getSelectedIndex() == 0) sc = StudentCondition.obecny;
                    else if(l.getSelectedIndex() == 1) sc = StudentCondition.odrabiajacy;
                    else if(l.getSelectedIndex() == 2) sc = StudentCondition.chory;
                    else if(l.getSelectedIndex() == 3) sc = StudentCondition.nieobecny;

                    student.setImie(imieField.getText());
                    student.setNazwisko(nazwiskoField.getText());
                    student.setRok_urodzenia(Integer.parseInt(rokField.getText()));
                    student.setIlosc_punktow(Double.parseDouble(punktyField.getText()));
                    student.setStatus(sc);

                    studentService.update(student);

                    tableModel2.setValueAt(student.getImie(), j2.getSelectedRow(), 0);
                    tableModel2.setValueAt(student.getNazwisko(), j2.getSelectedRow(), 1);
                    tableModel2.setValueAt(student.getIlosc_punktow(), j2.getSelectedRow(), 2);
                }
            }
        });

        JButton b6 = new JButton("Usuń studenta");
        panel3.add(b6);

        b6.addActionListener(e -> {
            if(!j2.getSelectionModel().isSelectionEmpty()) {

                StudentService studentService = new StudentService();
                Student student = studentService.findByName((tableModel2.getValueAt(j2.getSelectedRow(), 0).toString()), (tableModel2.getValueAt(j2.getSelectedRow(), 1).toString()));

                studentService.delete(student.getId());

                ShowStudentsForSelectedGroup(j1.getSelectedRow());

                ClassService classService = new ClassService();
                Class c = classService.findByName(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString());

                c.CalculateStudentsPercentage();

                tableModel1.setValueAt(Math.round(c.getProcentowe_zapelnienie() * 100.0)/100.0 + "%", j1.getSelectedRow(), 1);

            }
        });

        Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout());

        JButton b7 = new JButton("Sortuj studentów [alfabetycznie]");
        panel4.add(b7);

        b7.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                ClassService classService = new ClassService();
                Class c = classService.findByName(String.valueOf(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString()));
                StudentService studentService = new StudentService();
                List<Student> students = studentService.SortAlphabetical(c.getId());

                tableModel2.setRowCount(0);

                for(int i = 0; i < students.size(); i++) {
                    tableModel2.insertRow(i, new Object[] {students.get(i).getImie(),
                            students.get(i).getNazwisko(),
                            students.get(i).getIlosc_punktow()});
                }
            }
        });

        JButton b8 = new JButton("Sortuj studentów [punkty]");
        panel4.add(b8);

        b8.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                ClassService classService = new ClassService();
                Class c = classService.findByName(String.valueOf(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString()));
                StudentService studentService = new StudentService();
                List<Student> students = studentService.SortByPoints(c.getId());

                tableModel2.setRowCount(0);

                for(int i = 0; i < students.size(); i++) {
                    tableModel2.insertRow(i, new Object[] {students.get(i).getImie(),
                            students.get(i).getNazwisko(),
                            students.get(i).getIlosc_punktow()});
                }
            }
        });

        Panel panel5 = new Panel();
        panel5.setLayout(new GridLayout());

        JTextField tf = new JTextField(15);
        panel5.add(tf);

        JButton b9 = new JButton("Szukaj");
        panel5.add(b9);

        b9.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                StudentService studentService = new StudentService();
                ClassService classService = new ClassService();

                Class c = classService.findByName(String.valueOf(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString()));


                List<Student> students = studentService.SearchPartial(c.getId(), tf.getText());

                for(int i = 0; i < students.size(); i++) {
                    tableModel2.insertRow(i, new Object[] {students.get(i).getImie(),
                            students.get(i).getNazwisko(),
                            students.get(i).getIlosc_punktow()});
                }

            }
        });

        JButton b10 = new JButton("Szukaj");

        String[] condition = {
                "obecny", "odrabiający", "chory", "nieobecny"
        };

        JComboBox cb = new JComboBox(condition);

        b10.addActionListener(e -> {
            if(!j1.getSelectionModel().isSelectionEmpty()) {

                String s = String.valueOf(cb.getSelectedItem());
                StudentCondition sc = StudentCondition.brak;

                if(s == "obecny") sc = StudentCondition.obecny;
                else if(s == "odrabiający") sc = StudentCondition.odrabiajacy;
                else if(s == "chory") sc = StudentCondition.chory;
                else if(s == "nieobecny") sc = StudentCondition.nieobecny;

                StudentService studentService = new StudentService();

                ClassService classService = new ClassService();

                Class c = classService.findByName(String.valueOf(tableModel1.getValueAt(j1.getSelectedRow(), 0).toString()));

                List<Student> students = studentService.SearchByStatus(c.getId(), sc.toString());

                tableModel2.setRowCount(0);

                for(int i = 0; i < students.size(); i++) {
                    tableModel2.insertRow(i, new Object[] {students.get(i).getImie(),
                            students.get(i).getNazwisko(),
                            students.get(i).getIlosc_punktow()});
                }

            }
        });

        panel5.add(cb);
        panel5.add(b10);

        panel2.add(panel4, BorderLayout.CENTER);

        panel2.add(panel3, BorderLayout.NORTH);

        panel2.add(panel5, BorderLayout.SOUTH);

        // panel2.add(panel6, BorderLayout.SOUTH);

        panel.add(panel2, BorderLayout.SOUTH);

        f.add(panel);



        f.setVisible(true);
        f.setSize(905,450);
    }

    private List<Class> GetClassList() {
        ClassService classService = new ClassService();
        List<Class> classList = classService.findAll();
        return classList;
    }

    private void ShowStudentsForSelectedGroup(int index) {
        System.out.println(tableModel1.getValueAt(index, 0));

        tableModel2.setRowCount(0);

        ClassService classService = new ClassService();
        Class c = classService.findByName(tableModel1.getValueAt(index, 0).toString());

        StudentService studentService = new StudentService();
        List<Student> students = studentService.findAllFromClass(c.getId());

        for(int i = 0; i < students.size(); i++) {
            tableModel2.insertRow(i, new Object[] {students.get(i).getImie(),
                    students.get(i).getNazwisko(),
                    students.get(i).getIlosc_punktow()});
        }


    }
}
