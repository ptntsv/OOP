package org.example;

import java.util.Arrays;

public class Heap {
    private int[] heap;

    private boolean checkInvariant(int i) {
        if (heap.length / 2 <= i) return true;
        return heap[i] <= heap[2 * i + 1] && (2 * i + 2 >= heap.length || heap[i] <= heap[2 * i + 2]);
    }


    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    private void siftDown(int i) {
        while (i < heap.length / 2) {
            int min_child_i = 2 * i + 1;
            if (2 * i + 2 < heap.length && heap[2 * i + 1] > heap[2 * i + 2]) min_child_i = 2 * i + 2;
            if (heap[i] > heap[min_child_i]) {
                swap(i, min_child_i);
            }
            i++;
        }
    }

    public int peekMin() {
        return heap[0];
    }

    private void heapify() {
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            if (!checkInvariant(i)) {
                siftDown(i);
            }
        }
    }

    public void print() {
        for (int i = 0; i < heap.length; i++) {
            System.out.println(heap[i]);
        }
    }

    public int extractMin() {
        int ret = peekMin();
        swap(0, heap.length - 1);
        heap = Arrays.copyOf(heap, heap.length - 1);
        siftDown(0);
        return ret;
    }

    public boolean isHeap() {
        for (int i = 0; i < heap.length; i++) {
            if (!checkInvariant(i)) return false;
        }
        return true;
    }

    public Heap(int arr[]) {
        heap = arr.clone();
        heapify();
        assert isHeap();
    }
}
