package org.graph;

import java.util.Arrays;

public class Matrix {

    protected double[][] matrix;
    protected int rows = 0;
    protected int columns = 0;
    protected int rowsCapacity;
    protected int columnsCapacity;
    protected double defVal;

    public static void fill(double[][] arr, int n, int m, double value) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = value;
            }
        }
    }

    public void extend(int nrows, int ncolumns) {
        int oldRows = rows;
        int oldColumns = columns;
        boolean needCopy = rows + nrows >= rowsCapacity || columns + ncolumns >= columnsCapacity;
        if (rows + nrows >= rowsCapacity) {
            rowsCapacity = (rows + nrows) * 2;
        }
        if (columns + ncolumns >= columnsCapacity) {
            columnsCapacity = (columns + ncolumns) * 2;
        }
        rows += nrows;
        columns += ncolumns;
        if (needCopy) {
            var newMatrix = new double[rowsCapacity][columnsCapacity];
            Matrix.fill(newMatrix, rowsCapacity, columnsCapacity, defVal);
            for (int i = 0; i < oldRows; i++) {
                System.arraycopy(newMatrix[i], 0, matrix[i], 0, oldColumns);
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
