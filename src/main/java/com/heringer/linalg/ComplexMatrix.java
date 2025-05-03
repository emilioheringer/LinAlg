package com.heringer.linalg;

import com.heringer.Complex;

/**
 * Represents a matrix with complex number entries.
 * This class provides basic matrix operations such as addition, subtraction,
 * multiplication, transpose, inverse, determinant, and other matrix properties.
 *
 * @author Emílio Heringer
 */
public class ComplexMatrix implements IComplexMatrix {

    private double[][] real;
    private double[][] imag;
    private Complex[][] matrix;
    private int rows;
    private int columns;

    /**
     * Constructs a ComplexMatrix from a 2D array of Complex numbers.
     * The constructor initializes the matrix with the provided Complex numbers.
     * If the input matrix is null or empty, it initializes an empty matrix.
     *
     * @param matrix The 2D array of Complex numbers to initialize the matrix.
     */
    public ComplexMatrix(Complex[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            this.rows = 0;
            this.columns = 0;
            this.matrix = new Complex[0][0];
        } else {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            this.matrix = new Complex[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    this.matrix[i][j] = matrix[i][j];
                }
            }
        }
    }

    /**
     * Gets the real part of the matrix.
     *
     * @return The 2D array representing the real part of the complex matrix.
     */
    public double[][] getReal() {
        return real;
    }

    /**
     * Gets the imaginary part of the matrix.
     *
     * @return The 2D array representing the imaginary part of the complex matrix.
     */
    public double[][] getImag() {
        return imag;
    }

    /**
     * Gets the entire complex matrix.
     *
     * @return The 2D array of Complex numbers representing the matrix.
     */
    public Complex[][] getMatrix() {
        return matrix;
    }

    /**
     * Gets the number of rows in the matrix.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the matrix.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the Complex number at the specified row and column.
     *
     * @param i The row index.
     * @param j The column index.
     * @return The Complex number at the specified position.
     */
    public Complex get(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Displays the complex matrix in a formatted output to the console.
     * Uses ANSI escape codes to color the output for better readability.
     */
    public void showMatrix() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        int cellWidth = 22;

        printHorizontalBorder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ANSI_YELLOW + "|" + ANSI_RESET);

                double realPart = matrix[i][j].getReal();
                double imagPart = matrix[i][j].getImaginary();

                String imagSign = (imagPart >= 0) ? "+" : "-";
                String imagString = String.format("%.3f", Math.abs(imagPart));

                String complexString = String.format("%.3f %s %s i", realPart, imagSign, imagString);

                int paddingLeft = (cellWidth - complexString.length()) / 2;
                int paddingRight = cellWidth - complexString.length() - paddingLeft;

                String paddedComplexString = " ".repeat(paddingLeft) + complexString + " ".repeat(paddingRight);

                System.out.print(paddedComplexString);
            }

            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET);

            if (i < rows - 1) {
                printHorizontalBorder();
            }
        }

        printHorizontalBorder();
    }

    /**
     * Prints a horizontal border for formatting the matrix output.
     * Uses ANSI escape codes to color the border.
     */
    private void printHorizontalBorder() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        StringBuilder border = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            border.append("-".repeat(22));
            border.append(" ");
        }

        System.out.println(ANSI_YELLOW + border.toString() + ANSI_RESET);
    }

    /**
     * Computes the sum of two ComplexMatrix objects.
     * This is a static method that returns a new ComplexMatrix containing the sum.
     *
     * @param a The first ComplexMatrix.
     * @param b The second ComplexMatrix.
     * @return A new ComplexMatrix that is the sum of a and b.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public static ComplexMatrix sum(ComplexMatrix a, ComplexMatrix b) {
        if (a.getRows() != b.getRows() || a.getColumns() != b.getColumns()) {
            throw new IllegalArgumentException("Number of rows and columns must match");
        }

        int rows = a.getRows();
        int columns = a.getColumns();
        Complex[][] aux = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                aux[i][j] = Complex.sum(a.get(i, j), b.get(i, j));
            }
        }

        return new ComplexMatrix(aux);
    }

    /**
     * Adds another ComplexMatrix to this ComplexMatrix.
     * This method returns a new ComplexMatrix containing the sum.
     *
     * @param other The ComplexMatrix to add to this matrix.
     * @return A new ComplexMatrix that is the sum of this matrix and other.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        Complex[][] result = new Complex[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result[i][j] = Complex.sum(this.matrix[i][j], other.matrix[i][j]);
            }
        }

        return new ComplexMatrix(result);
    }

    /**
     * Computes the subtraction of two ComplexMatrix objects.
     * This is a static method that returns a new ComplexMatrix containing the result.
     *
     * @param a The first ComplexMatrix.
     * @param b The second ComplexMatrix.
     * @return A new ComplexMatrix that is the result of a - b.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public static ComplexMatrix subtraction(ComplexMatrix a, ComplexMatrix b) {
        if (a.getRows() != b.getRows() || a.getColumns() != b.getColumns()) {
            throw new IllegalArgumentException("Number of rows and columns must match");
        }

        int rows = a.getRows();
        int columns = a.getColumns();
        Complex[][] aux = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                aux[i][j] = Complex.subtraction(a.get(i, j), b.get(i, j));
            }
        }

        return new ComplexMatrix(aux);
    }

    /**
     * Subtracts another ComplexMatrix from this ComplexMatrix.
     * This method returns a new ComplexMatrix containing the result.
     *
     * @param other The ComplexMatrix to subtract from this matrix.
     * @return A new ComplexMatrix that is the result of this matrix - other.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        Complex[][] result = new Complex[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result[i][j] = Complex.subtraction(this.matrix[i][j], other.matrix[i][j]);
            }
        }

        return new ComplexMatrix(result);
    }

    /**
     * Multiplies two ComplexMatrix objects.
     * This is a static method that returns a new ComplexMatrix containing the result.
     *
     * @param a The first ComplexMatrix.
     * @param b The second ComplexMatrix.
     * @return A new ComplexMatrix that is the result of a * b.
     * @throws IllegalArgumentException if the number of columns of A does not match the number of rows of B.
     */
    public static ComplexMatrix multiply(ComplexMatrix a, ComplexMatrix b) {
        if (a.getColumns() != b.getRows()) {
            throw new IllegalArgumentException("Number of columns of A must match number of rows of B.");
        }

        int rows = a.getRows();
        int cols = b.getColumns();
        int commonDim = a.getColumns();

        Complex[][] result = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < commonDim; k++) {
                    sum = Complex.sum(sum, Complex.multiply(a.get(i, k), b.get(k, j)));
                }
                result[i][j] = sum;
            }
        }

        return new ComplexMatrix(result);
    }

    /**
     * Multiplies this ComplexMatrix with another ComplexMatrix.
     * This method returns a new ComplexMatrix containing the result.
     *
     * @param other The ComplexMatrix to multiply with this matrix.
     * @return A new ComplexMatrix that is the result of this matrix * other.
     * @throws IllegalArgumentException if the number of columns of this matrix does not match the number of rows of the other matrix.
     */
    public ComplexMatrix multiply(ComplexMatrix other) {
        return ComplexMatrix.multiply(this, other);
    }

    /**
     * Creates an identity ComplexMatrix of the specified size.
     * An identity matrix is a square matrix with ones on the main diagonal and zeros elsewhere.
     *
     * @param rows    The number of rows (and columns) in the identity matrix.
     * @param columns The number of columns (and rows) in the identity matrix.
     * @return A new ComplexMatrix representing the identity matrix.
     * @throws IllegalArgumentException if the number of rows is not equal to the number of columns.
     */
    public static ComplexMatrix I(int rows, int columns) {
        if (rows != columns) {
            throw new IllegalArgumentException("Identity matrix must be square (rows = columns).");
        }

        Complex[][] identityMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    identityMatrix[i][j] = new Complex(1, 0);
                } else {
                    identityMatrix[i][j] = new Complex(0, 0);
                }
            }
        }

        return new ComplexMatrix(identityMatrix);
    }

    /**
     * Computes the inverse of a ComplexMatrix.
     * The inverse of a matrix is a matrix that, when multiplied by the original matrix,
     * results in the identity matrix.
     *
     * @param matrix The ComplexMatrix to compute the inverse of.
     * @return A new ComplexMatrix representing the inverse of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square or is singular (non-invertible).
     */
    public static ComplexMatrix inverse(ComplexMatrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square to compute its inverse.");
        }

        int n = matrix.getRows();
        Complex[][] augmentedMatrix = new Complex[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix.get(i, j);
                augmentedMatrix[i][j + n] = (i == j) ? new Complex(1, 0) : new Complex(0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            Complex pivot = augmentedMatrix[i][i];
            if (pivot.equals(new Complex(0, 0))) {
                throw new IllegalArgumentException("Matrix is singular and cannot be inverted.");
            }

            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] = Complex.divide(augmentedMatrix[i][j], pivot);
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    Complex factor = augmentedMatrix[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmentedMatrix[k][j] = Complex.subtraction(augmentedMatrix[k][j],
                                Complex.multiply(factor, augmentedMatrix[i][j]));
                    }
                }
            }
        }

        Complex[][] inverseMatrix = new Complex[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = augmentedMatrix[i][j + n];
            }
        }

        return new ComplexMatrix(inverseMatrix);
    }

    /**
     * Computes the inverse of this ComplexMatrix.
     *
     * @return A new ComplexMatrix representing the inverse of this matrix.
     * @throws IllegalArgumentException if the matrix is not square or is singular (non-invertible).
     */
    public ComplexMatrix inverse() {
        return ComplexMatrix.inverse(this);
    }

    /**
     * Computes the transpose of a ComplexMatrix.
     * The transpose of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The ComplexMatrix to compute the transpose of.
     * @return A new ComplexMatrix representing the transpose of the input matrix.
     */
    public static ComplexMatrix transpose(ComplexMatrix matrix) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        Complex[][] transposedMatrix = new Complex[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedMatrix[j][i] = matrix.get(i, j);
            }
        }
        return new ComplexMatrix(transposedMatrix);
    }

    /**
     * Computes the transpose of this ComplexMatrix.
     *
     * @return A new ComplexMatrix representing the transpose of this matrix.
     */
    public ComplexMatrix transpose() {
        if (rows == 0 || columns == 0) {
            return new ComplexMatrix(new Complex[0][0]);
        }
        Complex[][] transposedData = new Complex[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposedData[j][i] = matrix[i][j];
            }
        }
        return new ComplexMatrix(transposedData);
    }

    /**
     * Computes the symmetrical version of a ComplexMatrix.
     * The symmetrical version of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The ComplexMatrix to compute the symmetrical version of.
     * @return A new ComplexMatrix representing the symmetrical version of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static ComplexMatrix symetricalMatrix(ComplexMatrix matrix) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to compute its symmetrical version.");
        }

        Complex[][] symetricalMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                symetricalMatrix[i][j] = matrix.get(j, i);
            }
        }
        return new ComplexMatrix(symetricalMatrix);
    }

    /**
     * Converts this matrix to its symmetrical version.
     *
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public void symetricalMatrix() {
        int rows = this.getRows();
        int columns = this.getColumns();

        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to compute its symmetrical version.");
        }

        Complex[][] symetricalMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                symetricalMatrix[i][j] = this.get(j, i);
            }
        }
        this.matrix = symetricalMatrix;
    }

    /**
     * Converts the matrix to a diagonal matrix, setting all non-diagonal elements to zero.
     *
     * @param matrix The ComplexMatrix to convert to a diagonal matrix.
     * @return A new ComplexMatrix representing the diagonal version of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static ComplexMatrix diagonalMatrix(ComplexMatrix matrix) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to compute its diagonal version.");
        }

        Complex[][] diagonalMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    diagonalMatrix[i][j] = matrix.get(i, j);
                } else {
                    diagonalMatrix[i][j] = new Complex(0, 0);
                }
            }
        }
        return new ComplexMatrix(diagonalMatrix);
    }

    /**
     * Converts the matrix to an orthogonal matrix, setting all non-diagonal elements to zero.
     *
     * @param matrix The ComplexMatrix to convert to an orthogonal matrix.
     * @return A new ComplexMatrix representing the orthogonal version of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static ComplexMatrix ortogonalMatrix(ComplexMatrix matrix) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to compute its ortogonal version.");
        }

        Complex[][] ortogonalMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == j) {
                    ortogonalMatrix[i][j] = matrix.get(i, j);
                } else {
                    ortogonalMatrix[i][j] = new Complex(0, 0);
                }
            }
        }
        return new ComplexMatrix(ortogonalMatrix);
    }

    /**
     * Computes the cofactor matrix of a ComplexMatrix.
     * The cofactor of an element is the determinant of the submatrix formed by removing the row and column of that element.
     *
     * @param matrix The ComplexMatrix to compute the cofactor matrix of.
     * @return A new ComplexMatrix representing the cofactor matrix of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static ComplexMatrix cofatorMatrix(ComplexMatrix matrix) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        if (rows != columns) {
            throw new IllegalArgumentException("Matrix must be square to compute its cofator version.");
        }

        Complex[][] cofatorMatrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cofatorMatrix[i][j] = new Complex(matrix.get(i, j).getReal(), -matrix.get(i, j).getImaginary());
            }
        }
        return new ComplexMatrix(cofatorMatrix);
    }

    /**
     * Calculates the rank of the complex matrix using Gaussian elimination.
     *
     * @return The rank of the matrix.
     */
    public int rank() {
        int rank = 0;
        Complex[][] matrixCopy = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            int pivotRow = -1;
            for (int j = i; j < rows; j++) {
                if (!matrixCopy[j][i].equals(new Complex(0, 0))) {
                    pivotRow = j;
                    break;
                }
            }

            if (pivotRow == -1) {
                continue;
            }

            Complex[] temp = matrixCopy[i];
            matrixCopy[i] = matrixCopy[pivotRow];
            matrixCopy[pivotRow] = temp;

            for (int j = i + 1; j < rows; j++) {
                Complex factor = matrixCopy[j][i];
                for (int k = i; k < columns; k++) {
                    matrixCopy[j][k] = Complex.subtraction(matrixCopy[j][k],
                            Complex.multiply(factor, matrixCopy[i][k]));
                }
            }

            rank++;
        }

        return rank;
    }

    /**
     * Multiplies a ComplexMatrix by a scalar value.
     *
     * @param matrix The ComplexMatrix to multiply.
     * @param scalar The scalar value to multiply the matrix by.
     * @return A new ComplexMatrix that is the result of the scalar multiplication.
     */
    public static ComplexMatrix multiplyByScalar(ComplexMatrix matrix, double scalar) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        Complex[][] result = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = new Complex(matrix.get(i, j).getReal() * scalar,
                        matrix.get(i, j).getImaginary() * scalar);
            }
        }

        return new ComplexMatrix(result);
    }

    /**
     * Calculates the determinant of a complex matrix.
     *
     * @param matrix The ComplexMatrix to compute the determinant of.
     * @return The determinant of the matrix as a Complex number.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static Complex determinant(ComplexMatrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square to compute its determinant.");
        }

        if (matrix == null || matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square to calculate determinant.");
        }

        int n = matrix.getRows();
        if (n == 1) {
            return matrix.get(0, 0);
        } else if (n == 2) {
            return Complex.subtraction(Complex.multiply(matrix.get(0, 0), matrix.get(1, 1)),
                    Complex.multiply(matrix.get(0, 1), matrix.get(1, 0)));
        } else {
            Complex det = new Complex(0, 0);
            for (int j = 0; j < n; j++) {
                Complex[][] subMatrix = new Complex[n - 1][n - 1];
                for (int k = 1; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (l < j) {
                            subMatrix[k - 1][l] = matrix.get(k, l);
                        } else if (l > j) {
                            subMatrix[k - 1][l - 1] = matrix.get(k, l);
                        }
                    }
                }
                Complex subDet = determinant(new ComplexMatrix(subMatrix));
                det = Complex.sum(det, Complex.multiply(matrix.get(0, j),
                        Complex.multiply(new Complex(Math.pow(-1, j), 0), subDet)));
            }
            if (det.equals(new Complex(0, 0))) {
                throw new IllegalArgumentException("Matrix is singular and cannot have a determinant.");
            }
            return det;
        }
    }

}
