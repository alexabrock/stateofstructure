package com.hhu.prograDatastructures.generic;

public class HashSet<T> {

    private final static int SIZE = 100_000_000;
    private Object[] objects = new Object[SIZE];
    // man kann leider keine Arrays mit generischen Typ anlegen
    // (https://www.tothenew.com/blog/why-is-generic-array-creation-not-allowed-in-java/)
    // Dass wir "nur" ein Object-Array haben, ist hier aber nicht schlimm,
    // da unsere öffentlichen Methoden sicherstellen, dass nur T-Objekte im Array sind,
    // und sich das HashSet selbst nicht dafür interessiert, welche Objekte gespeichert werden.
    
    public void insert(T object) {
        int insertionIndex = Math.abs(object.hashCode() % SIZE);
        while(objects[insertionIndex] != null) {
            // lineares Sondieren
            insertionIndex++;
        }
        objects[insertionIndex] = object;
    }
    
    @Override
    public String toString() {
        String elements = "{";
        for(Object object: objects) {
            if(object != null) {
                elements += object + ", ";
            }
        }
        return elements + "}";
    }
    
    public boolean contains(T object) {
        int index = Math.abs(object.hashCode() % SIZE);
//         return objects[index] != null;
        while(objects[index] != null && !objects[index].equals(object)) {
            // lineares Sondieren
            index++;
        }
        return objects[index] != null && objects[index].equals(object);
    }
    
    public int size() {
        int usedPlaces = 0;
        for(Object object: objects) {
            if(object != null) {
                usedPlaces++;
            }
        }
        return usedPlaces;
    }

}
