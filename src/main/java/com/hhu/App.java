package com.hhu;

import java.util.LinkedList;

import org.atpfivt.ljv.Direction;
import org.atpfivt.ljv.LJV;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);

        System.out.println(new LJV()
                .setTreatAsPrimitive(Integer.class)
                .setDirection(Direction.LR)
                .drawGraph(list));

        System.out.println("hellooooooo");
    }
}
