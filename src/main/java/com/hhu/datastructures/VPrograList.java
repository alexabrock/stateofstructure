package com.hhu.datastructures;

import java.util.LinkedList;

public class VPrograList {

    private class Node {
        private int data;
        private Node next;

        private Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node head;

    //fügt am Anfang an 
    public void insert(int value) {
        Node newElement = new Node(value, head);
        head = newElement;
    }

    public String toString() {
        String output = "";
        Node current = head;
        while (current != null) {
            output += current.data + " -> ";
            current = current.next;
        }
        return output;
    }

    public int indexOf(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            index++;
            current = current.next; // erstmal vergessen;)
        }
        return -1; // erstmal vergessen;)
    }

    public int at(int index) {
        Node current = head;
        int currentIndex = 0;
        while (current != null) {
            if (index == currentIndex) { // einziger Unterschied zu indexOf
                return current.data;
            }
            current = current.next;
            currentIndex++;
        }
        throw new IndexOutOfBoundsException("index " + index + " does not exist");
    }

    public void delete(int value) {
        Node current = head;

        if (head == null) {
            return;
        }

        if (head.data == value) {
            head = head.next;
            return;
        }

        // current.next != null -> falls value nicht vorhanden
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public int size() {
        int size = 0;
        Node current = this.head;
        while (current!= null) {
            size++;
            current = current.next;
        }
        return size;
    }

}
