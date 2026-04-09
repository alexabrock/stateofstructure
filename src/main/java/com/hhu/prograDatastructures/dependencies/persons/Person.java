package com.hhu.prograDatastructures.dependencies.persons;

public class Person {

    private final String name;
    private final String mailAddress;
    
    public Person(String name, String mailAddress) {
        this.name = name;
        this.mailAddress = mailAddress;
    }
    
    public String getName() {
        return name;
    }
    
    public String getMailAddress() {
        return mailAddress;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        for(char character: name.toCharArray()) {
            hash += character - 'A';
        }
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(other == null || getClass() != other.getClass())
            return false;
        return ((Person) other).name.equals(this.name) && ((Person) other).mailAddress.equals(this.mailAddress);
    }
    
    @Override
    public String toString() {
        return name + ", " + mailAddress;
    }

} 
