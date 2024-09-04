package org.example;


public class Main {
    public static void heapsort(int arr[]) {
        Heap heap = new Heap(arr);
        int i = 0;
        while (!heap.isEmpty()) {
            arr[i] = heap.extractMin();
            i++;
        }
    }

    public static void main(String[] args) {
        int a[] = {9, 1, 1, 1, 1, 1, 1, 1, 1};
        heapsort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
