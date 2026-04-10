package com.hhu.datastructures.prograDatastructures.dependencies.persons;

public class Student extends Person {

    private final int studentNumber;
    
    public Student(String name, String mail, int studentNumber) {
        super(name, mail);
        this.studentNumber = studentNumber;
    }
    
    public Student(String name, String mail) {
        this(name, mail, -1);
    }
    
    public int getStudentNumber() {
        return studentNumber;
    }
    
    public String toString() {
        return super.toString() + ", " + studentNumber;
    }

}
