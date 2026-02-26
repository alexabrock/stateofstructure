package com.hhu;

import com.hhu.datastructures.LinkedList;
import java.util.List;

import org.atpfivt.ljv.LJV;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);

    }
}
