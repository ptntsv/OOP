package org.example;

import java.util.Arrays;

public class Heap {
    private int[] heap;
    private int heapLen;

    /**
     * Binary heap should maintain an invariant
     * A[i] <= A[i * 2 + 1] and A[i] <= A[i * 2 + 2]
     *
     * @param i Index of heap element
     * @return Does i-th element in heap maintains an invariant
     */
    private boolean checkInvariant(int i) {
        if (heapLen / 2 <= i) return true;
        return heap[i] <= heap[2 * i + 1] && (2 * i + 2 >= heapLen || heap[i] <= heap[2 * i + 2]);
    }


    /**
     * Swaps two heap elements
     *
     * @param i Index of first element
     * @param j Index of second element
     */
    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    private void siftDown(int i) {
        while (i < heapLen / 2) {
            int min_child_i = 2 * i + 1;
            if (2 * i + 2 < heapLen && heap[2 * i + 1] > heap[2 * i + 2]) min_child_i = 2 * i + 2;
            if (heap[i] > heap[min_child_i]) {
                swap(i, min_child_i);
            }
            i++;
        }
    }

    public int peekMin() {
        return heap[0];
    }

    /**
     * Method that turns an array into binary heap.
     * Loop starts with first 'non-crown' element in reverse order.
     */
    private void heapify() {
        for (int i = heapLen / 2 - 1; i >= 0; i--) {
            if (!checkInvariant(i)) {
                siftDown(i);
            }
        }
    }

    public int extractMin() {
        int ret = peekMin();
        swap(0, heapLen - 1);
        heapLen--;
        siftDown(0);
        return ret;
    }

    /**
     * Method checks whether array is binary heap or not.
     *
     * @return
     */
    public boolean isHeap() {
        for (int i = 0; i < heapLen; i++) {
            if (!checkInvariant(i)) return false;
        }
        return true;
    }
    public boolean isEmpty() {
        return heapLen == 0;
    }

    public Heap(int arr[]) {
        heap = arr.clone();
        heapLen = arr.length;
        heapify();
        assert isHeap();
    }
}
