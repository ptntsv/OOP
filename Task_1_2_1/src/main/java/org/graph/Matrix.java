package org.graph;

/**
 * Matrix class.
 */
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
        int oldRowCap = rowsCapacity;
        int oldColCap = columnsCapacity;
        int newRowCap = xindex + 1;
        int newColCap = yindex + 1;
        boolean enoughRows = xindex < rowsCapacity;
        boolean enoughCols = yindex < columnsCapacity;
        if (!enoughRows) {
            rowsCapacity = newRowCap;
        }
        if (!enoughCols) {
            columnsCapacity = newColCap;
        }
        rows = xindex + 1;
        columns = yindex + 1;
        boolean needCopy = !(enoughRows && enoughCols);
        if (needCopy) {
            var newMatrix = new double[rowsCapacity][columnsCapacity];
            Matrix.fill(newMatrix, rowsCapacity, columnsCapacity, defVal);
            for (int i = 0; i < oldRowCap; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, oldColCap);
            }
            matrix = newMatrix;
        }

    }

    /**
     * Get matrix[i][j].
     *
     * @param i Index #1.
     * @param j Index #2.
     * @return matrix[i][j]
     */
    public double get(int i, int j) {
        try {
            return matrix[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MatrixOutOfBoundsException(
                "Values " + i + ", " + j + " are out of boundaries");
        }
    }

    /**
     * Set matrix[i][j] with provided value.
     *
     * @param i   Index #1.
     * @param j   Index #2.
     * @param val Value.
     */
    public void set(int i, int j, double val) {
        try {
            matrix[i][j] = val;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MatrixOutOfBoundsException(
                "Values " + i + ", " + j + " are out of boundaries");
        }
    }

    /**
     * Constructor.
     *
     * @param rowCap       Row capacity.
     * @param colCap       Col capacity.
     * @param defaultValue Default value.
     */
    public Matrix(int rowCap, int colCap, double defaultValue) {
        this.defVal = defaultValue;
        rowsCapacity = rowCap;
        columnsCapacity = colCap;
        matrix = new double[rowsCapacity][columnsCapacity];
        Matrix.fill(matrix, rowsCapacity, columnsCapacity, defVal);
    }
}
