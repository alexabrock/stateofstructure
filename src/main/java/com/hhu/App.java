package com.hhu;

import java.util.LinkedList;
import java.util.List;

import org.atpfivt.ljv.LJV;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(new LJV().drawGraph(List.of("Hello", "World")));
    }
}
