package org.graph;

import java.util.Arrays;

public class Matrix {

    protected double[][] matrix;
    protected int rows = 0;
    protected int columns = 0;
    protected int rowsCapacity;
    protected int columnsCapacity;
    protected double defVal;

    private static int log2(double x) {
        return (int) (Math.log(x) / Math.log(2));
    }

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
        int newRows = (int) Math.pow(2, (double) Matrix.log2(xindex) + 1);
        int newCols = (int) Math.pow(2, (double) Matrix.log2(yindex) + 1);
        boolean needCopy = xindex >= rowsCapacity || yindex >= columnsCapacity;
        if (xindex >= rowsCapacity) {
            rowsCapacity = newRows;
        }
        if (yindex >= columnsCapacity) {
            columnsCapacity = newCols;
        }
        rows++;
        columns++;
        if (needCopy) {
            var newMatrix = new double[rowsCapacity][columnsCapacity];
            Matrix.fill(newMatrix, rowsCapacity, columnsCapacity, defVal);
            for (int i = 0; i < oldRCap; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, oldCCap);
            }
            matrix = newMatrix;
        }

    }

    /**
     * Ensuring that there is enough capacity to add nrow and ncolumns into matrix.
     *
     * @param nrows    Rows number.
     * @param ncolumns Columns number.
     */
    public void extend(int nrows, int ncolumns) {
        int oldRows = rows;
        int oldColumns = columns;
        int newRows = rows + nrows;
        int newCols = columns + ncolumns;
        boolean needCopy = newRows >= rowsCapacity || newCols >= columnsCapacity;
        if (newRows >= rowsCapacity) {
            rowsCapacity = (rows + nrows) * 2;
        }
        if (newCols >= columnsCapacity) {
            columnsCapacity = (columns + ncolumns) * 2;
        }
        rows += nrows;
        columns += ncolumns;
        if (needCopy) {
            var newMatrix = new double[rowsCapacity][columnsCapacity];
            Matrix.fill(newMatrix, rowsCapacity, columnsCapacity, defVal);
            for (int i = 0; i < oldRows; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, oldColumns);
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
