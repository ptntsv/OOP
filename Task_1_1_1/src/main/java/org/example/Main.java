package org.example;


public class Main {

    public static void heapsort(int[] arr) {
        Heap heap = new Heap(arr);
        int i = 0;
        while (!heap.isEmpty()) {
            arr[i] = heap.extractMin();
            i++;
        }
    }

    public static void main(String[] args) {
    }
}
