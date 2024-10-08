package org.example;


import java.util.Arrays;

/**
 * Class that implements binary heap data structure.
 */
public class Heap {

    private int[] heap;

    public int[] getHeap() {
        return Arrays.copyOf(heap, heapLen);
    }

    private int heapLen;

    public int getHeapLen() {
        return heapLen;
    }

    /**
     * Binary heap should maintain an invariant A[i] &lt;= A[i * 2 + 1] and A[i] &lt;= A[i * 2 +
     * 2].
     *
     * @param i Index of heap element
     * @return Does i-th element in heap maintains an invariant
     */
    public boolean checkInvariant(int i) {
        if (heapLen / 2 <= i) {
            return true;
        }
        return heap[i] <= heap[2 * i + 1] && (2 * i + 2 >= heapLen || heap[i] <= heap[2 * i + 2]);
    }


    /**
     * Swaps two heap elements.
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
            int minChildIndex = 2 * i + 1;
            if (2 * i + 2 < heapLen && heap[2 * i + 1] > heap[2 * i + 2]) {
                minChildIndex = 2 * i + 2;
            }
            if (heap[i] > heap[minChildIndex]) {
                swap(i, minChildIndex);
            }
            i = minChildIndex;
        }
    }

    public int peekMin() {
        return heap[0];
    }

    /**
     * Method that turns an array into binary heap. Loop starts with first 'non-crown' element in
     * reverse order.
     */
    private void heapify() {
        for (int i = heapLen / 2 - 1; i >= 0; i--) {
            if (!checkInvariant(i)) {
                siftDown(i);
            }
        }
    }

    /**
     * Extract minimum value of heap.
     *
     * @return Current minimum.
     */
    public int extractMin() {
        final int ret = peekMin();
        swap(0, heapLen - 1);
        heapLen--;
        siftDown(0);
        return ret;
    }


    /**
     * Whether heap empty or not.
     *
     * @return True if heapLen == 0, false otherwise
     */
    public boolean isEmpty() {
        return heapLen == 0;
    }

    /**
     * Constructor for Heap class.
     *
     * @param arr Array that needs to be "heapified"
     */
    public Heap(int[] arr) {
        heap = arr.clone();
        heapLen = arr.length;
        heapify();
    }
}
