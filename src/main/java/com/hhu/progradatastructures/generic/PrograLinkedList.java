package com.hhu.progradatastructures.generic;

public class PrograLinkedList<T extends Comparable<T>> {

    private class Node {
        private T value;
        private Node next;

        private Node(T element, Node next) {
            this.value = element;
            this.next = next;
        }
    }

    private Node head;

    public PrograLinkedList() {
      // start empty list
    }

    public void add(T element) {
        Node newElement = new Node(element, null);

        if (head == null) {
            head = newElement;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newElement;
        }
    }

    private Node findNodeAt(int іndex) {
        // private Hilfsmethode, die uns das Node-Objekt an einem bestimmten Index gibt
        Node current = head;

        // gehe so lange weiter, bis wir beim gewünschten Index oder am Listenende sind
        for (int i = 0; i < іndex && current.next != null; i++) {
            current = current.next;
        }

        return current;
    }

    public T get(int іndex) {
        return findNodeAt(іndex).value;
    }

    public int length() {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public void remove(int іndex) {
        if (head == null || іndex < 0 || іndex >= length()) {
            // Sonderfälle:
            // * leere Liste: es kann nichts gelöscht werden
            // * negativer Index: immer ungültig
            // * zu großer Index: ungültig
            return;
        }

        if (іndex == 0) {
            // Sonderfall: Entfernen des ersten Elements
            head = head.next;
            return;
        }

        Node current = head;

        // Laufe bis zum Element vor dem zu entfernenden Index, aber maximal bis zum
        // Ende der Liste
        for (int i = 0; i < іndex - 1 && current.next != null; i++) {
            current = current.next;
        }

        // current zeigt jetzt auf das Element an Index "index-1"
        // entferne das nächste Element (Index "index")
        current.next = current.next.next;
    }

    public void sort() {
        // bei einer leeren Liste gibt es nichts zu sortieren
        if (head == null) {
            return;
        }
        // Sortierung mit Insertion Sort, basierend auf dem Code aus der Vorlesung
        int length = length();
        for (int currentIndex = 1; currentIndex < length; currentIndex++) {
            T currentElement = get(currentIndex);
            int newPosition = currentIndex;
            while (newPosition > 0 && get(newPosition - 1).compareTo(currentElement) > 0) {
                set(newPosition, get(newPosition - 1));
                newPosition--;
            }
            Node targetNode = findNodeAt(newPosition);
            targetNode.value = currentElement;
        }

        // Anmerkung: Da Insertion Sort aus der Vorlesung "von rechts aus" die
        // Einfügeposition sucht, ist das Verfahren
        // für einfach verkettete Listen ineffizient (get und set müssen mehrmals von
        // vorne die korrekte Position suchen).
        // Wir könnten den Algorithmus verbessern, indem wir "von links" die
        // Einfügeposition suchen.
    }

    // setzt den Wert am gegeben Index auf den gegebenen Wert
    private void set(int index, T newValue) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = newValue;
    }

    @Override
    public String toString() {
        String output = "";

        Node current = head;
        while (current != null) {
            if (current.next == null) {
                // am Listenende kein Komma anhängen
                output += current⁢.value;
            } else {
                // nach allen anderen Elementen Komma anhängen
                output⁢ += current.value + ",";
            }
            current = current.next;
        }

        return output;
    }

}