package com.hhu.prograDatastructures.generic;

public class BinarySearchTree<T extends Comparable<T>> {

    private class BinaryNode {
        private T element;
        private BinaryNode left, right;

        private BinaryNode(T element) {
            this.element = element;
        }
    }

    private BinaryNode root;

    public void insert(T newElement) {
        // Sonderfall: leerer Baum
        if (root == null) {
            root = new BinaryNode(newElement);
            return;
        }

        BinaryNode parent = null;
        BinaryNode child = root;
        while (child != null) {
            parent = child;
            if (newElement.equals(child.element)) {
                // bereits im Baum vorhanden
                return;
            } else if (newElement.compareTo(child.element) < 0) {
                child = child.left;
            } else {
                child = child.right;
            }
        }

        if (newElement.compareTo(parent.element) < 0) {
            parent.left = new BinaryNode(newElement);
        } else {
            parent.right = new BinaryNode(newElement);
        }
    }

    public T minimum() {
        if (root == null) {
            throw new java.util.NoSuchElementException("tree is empty");
        }

        BinaryNode current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.element;
    }

    public T minimumRecursive() {
        if (root == null) {
            throw new java.util.NoSuchElementException("tree is empty");
        }

        return minimumRecursive(root);
    }

    private T minimumRecursive(BinaryNode current) {
        if (current.left == null) {
            return current.element;
        }
        return minimumRecursive(current.left);
    }

    public int size() {
        return size(root);
    }

    private int size(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    public void deleteMinimum() {
        root = deleteMinimum(root);
    }

    // returns new root of the subtree
    private BinaryNode deleteMinimum(BinaryNode root) {
        if (root == null) {
            throw new java.util.NoSuchElementException("tree is empty");
        }

        if (root.left == null) {
            return root.right;
        }

        BinaryNode current = root;
        while (current.left.left != null) {
            current = current.left;
        }

        current.left = current.left.right;
        return root;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(BinaryNode current) {
        if (current == null) {
            return "";
        }
        return toString(current.left) + current.element + ", " + toString(current.right);
    }

}
