package org.example;


public class Main {

    /**
     * Heapsort algorithm.
     *
     * @param Array to sort.
     */
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
