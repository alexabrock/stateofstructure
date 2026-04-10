package com.hhu.datastructures.prograDatastructures.dependencies.persons;

public class Employee extends Person {

    private final double salary;
    private static final String CURRENCY = "€";
    
    public Employee(String name, String mail, double salary) {
        super(name, mail);
        System.out.println("Konstruktor Employee(String, String, double)");
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String toString() {
        return super.toString() + ", " + salary + CURRENCY; 
    }

}
