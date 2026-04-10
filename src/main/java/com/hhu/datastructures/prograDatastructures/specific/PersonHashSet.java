package com.hhu.datastructures.prograDatastructures.specific;

import com.hhu.datastructures.prograDatastructures.dependencies.persons.Person;

public class PersonHashSet {

//     private final static int SIZE = 50;
    private final static int SIZE = 10;
    private Person[] persons = new Person[SIZE];
    
    public void insert(Person person) {
        int insertionIndex = person.hashCode() % SIZE;
        while(persons[insertionIndex] != null) {
            // lineares Sondieren
            insertionIndex++;
        }
        persons[insertionIndex] = person;
    }
    
    @Override
    public String toString() {
        String elements = "{";
        for(Person person: persons) {
            if(person != null) {
                elements += person + ", ";
            }
        }
        return elements + "}";
    }
    
    public boolean contains(Person person) {
        int index = person.hashCode() % SIZE;
//         return persons[index] != null;
        while(persons[index] != null && !persons[index].equals(person)) {
            // lineares Sondieren
            index++;
        }
        return persons[index] != null && persons[index].equals(person);
    }
    
    public void delete(Person person) {
        int index = person.hashCode() % SIZE;
        // ignoriert Sondierung …
        if (persons[index] != null && persons[index].equals(person)) {
            persons[index] = null;
        }
    }
    
    public int size() {
        int usedPlaces = 0;
        for(Person person: persons) {
            if(person != null) {
                usedPlaces++;
            }
        }
        return usedPlaces;
    }

}
