package org.graph;

public class Matrix {

    protected double[][] matrix;
    protected int rows = 0;
    protected int columns = 0;
    protected int rowsCapacity;
    protected int columnsCapacity;
    protected double defVal;

    /**
     * Fill matrix with provided value.
     *
     * @param arr   Matrix to fill.
     * @param n     Rows N.
     * @param m     Columns N.
     * @param value Provided values.
     */
    public static void fill(double[][] arr, int n, int m, double value) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = value;
            }
        }
    }

    /**
     * Checks either enough capacity for new value on provided index.
     *
     * @param xindex Rows index.
     * @param yindex Columns index.
     */
    public void ensureCapacity(int xindex, int yindex) {
        int oldRCap = rowsCapacity;
        int oldCCap = columnsCapacity;
        int newRCap = xindex + 1;
        int newCCap = yindex + 1;
        boolean enoughRows = xindex < rowsCapacity;
        boolean enoughCols = yindex < columnsCapacity;
        if (!enoughRows) {
            rowsCapacity = newRCap;
        }
        if (!enoughCols) {
            columnsCapacity = newCCap;
        }
        rows = xindex + 1;
        columns = yindex + 1;
        boolean needCopy = !(enoughRows && enoughCols);
        if (needCopy) {
            var newMatrix = new double[rowsCapacity][columnsCapacity];
            Matrix.fill(newMatrix, rowsCapacity, columnsCapacity, defVal);
            for (int i = 0; i < oldRCap; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, oldCCap);
            }
            matrix = newMatrix;
        }

    }

    public double get(int i, int j) {
        try {
            return matrix[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MatrixOutOfBoundsException(
                    "Values " + i + ", " + j + " are out of boundaries");
        }
    }

    public void set(int i, int j, double val) {
        try {
            matrix[i][j] = val;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MatrixOutOfBoundsException(
                    "Values " + i + ", " + j + " are out of boundaries");
        }
    }

    public Matrix(int rowCap, int colCap, double defaultValue) {
        this.defVal = defaultValue;
        rowsCapacity = rowCap;
        columnsCapacity = colCap;
        matrix = new double[rowsCapacity][columnsCapacity];
        Matrix.fill(matrix, rowsCapacity, columnsCapacity, defVal);
    }
}
