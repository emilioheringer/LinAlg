package com.heringer.linalg;

/**
 * Represents a mathematical matrix with double-precision floating-point elements.
 * This class provides basic matrix operations such as addition, subtraction,
 * multiplication, transpose, inverse, determinant, and other matrix properties.
 *
 * @author Emílio Heringer
 */
public class Matrix implements IMatrix {

   
    /**
     * The 2D array representing the matrix elements.
     */
    public double[][] mtx;
    /**
     * The number of rows in the matrix.
     */
    private int row;
    /**
     * The number of columns in the matrix.
     */
    private int columns;

    /**
     * Constructs a Matrix from a 2D array of doubles.
     * The constructor initializes the matrix with the provided 2D array.
     * If the input matrix is null or empty, it throws an IllegalArgumentException.
     *
     * @param matrix The 2D array of doubles to initialize the matrix.
     * @throws IllegalArgumentException if the matrix is null or empty.
     */
    public Matrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }
        this.mtx = matrix;
        this.row = matrix.length;
        this.columns = matrix[0].length;
    }

    /**
     * Constructs a Matrix by copying another Matrix.
     * The constructor creates a new matrix with the same dimensions and elements
     * as the provided matrix.
     *
     * @param other The Matrix to copy.
     */
    public Matrix(Matrix other) {
        this.row = other.mtx.length;
        this.columns = other.mtx[0].length;
        this.mtx = new double[row][columns];
        for (int i = 0; i < row; i++) {
            this.mtx[i] = other.mtx[i].clone();
        }
    }

    /**
     * Constructs a Matrix with specified dimensions.
     * The constructor creates a new matrix with the given number of rows and columns,
     * and initializes all elements to zero.
     *
     * @param n The number of rows.
     * @param i The number of columns.
     * @throws IllegalArgumentException if the dimensions are not positive.
     */
    public Matrix(int n, int i) {
        if (n <= 0 || i <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive.");
        }
        this.row = n;
        this.columns = i;
        this.mtx = new double[n][i];
    }

    /**
     * Gets the number of rows in the matrix.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return this.row;
    }

    /**
     * Gets the number of columns in the matrix.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Gets the 2D array representing the matrix elements.
     *
     * @return The 2D array of doubles representing the matrix.
     */
    public double[][] getMatrix() {
        return mtx;
    }

    /**
     * Displays the matrix in a formatted output to the console.
     * Uses ANSI escape codes to color the output for better readability.
     */
    public void showMatrix() {
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        final String BORDER = ANSI_YELLOW + "----------" + ANSI_RESET;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(BORDER + " ");
            }
            System.out.println();

            for (int j = 0; j < columns; j++) {
                System.out.print(ANSI_YELLOW + "|" + ANSI_RESET);
                String number = String.format("%.3f", mtx[i][j]);
                int totalWidth = 10;
                int padding = (totalWidth - number.length()) / 2;
                String paddedNumber = " ".repeat(padding) + number + " ".repeat(totalWidth - padding - number.length());
                System.out.print(paddedNumber);
            }
            System.out.print(ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println();
        }

        for (int j = 0; j < columns; j++) {
            System.out.print(BORDER + " ");
        }
        System.out.println();
    }

    /**
     * Computes the sum of two Matrix objects.
     * This is a static method that returns a new Matrix containing the sum.
     *
     * @param a The first Matrix.
     * @param b The second Matrix.
     * @return A new Matrix that is the sum of a and b.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public static Matrix sum(Matrix a, Matrix b) {
        if (a.getRows() != b.getRows() || a.getColumns() != b.getColumns()) {
            throw new IllegalArgumentException("Matrix must have same number of rows and columns.");
        }

        double[][] c = new double[a.getRows()][a.getColumns()];

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getColumns(); j++) {
                c[i][j] = a.mtx[i][j] + b.mtx[i][j];
            }
        }

        return new Matrix(c);
    }

    /**
     * Adds another Matrix to this Matrix.
     * This method returns a new Matrix containing the sum.
     *
     * @param other The Matrix to add to this matrix.
     * @return A new Matrix that is the sum of this matrix and other.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public Matrix add(Matrix other) {
        if (this.row != other.row || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        double[][] result = new double[this.row][this.columns];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.columns; j++) {
                result[i][j] = this.mtx[i][j] + other.mtx[i][j];
            }
        }

        return new Matrix(result);
    }

    /**
     * Computes the subtraction of two Matrix objects.
     * This is a static method that returns a new Matrix containing the result.
     *
     * @param a The first Matrix.
     * @param b The second Matrix.
     * @return A new Matrix that is the result of a - b.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public static Matrix subtraction(Matrix a, Matrix b) {
        if (a.getRows() != b.getRows() || a.getColumns() != b.getColumns()) {
            throw new IllegalArgumentException("Matrix must have same number of rows and columns.");
        }

        double[][] c = new double[a.getRows()][a.getColumns()];

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getColumns(); j++) {
                c[i][j] = a.mtx[i][j] - b.mtx[i][j];
            }
        }

        return new Matrix(c);
    }

    /**
     * Subtracts another Matrix from this Matrix.
     * This method returns a new Matrix containing the result.
     *
     * @param other The Matrix to subtract from this matrix.
     * @return A new Matrix that is the result of this matrix - other.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public Matrix subtract(Matrix other) {
        if (this.row != other.row || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        double[][] result = new double[this.row][this.columns];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.columns; j++) {
                result[i][j] = this.mtx[i][j] - other.mtx[i][j];
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies two Matrix objects.
     * This is a static method that returns a new Matrix containing the result.
     *
     * @param a The first Matrix.
     * @param b The second Matrix.
     * @return A new Matrix that is the result of a * b.
     * @throws IllegalArgumentException if the number of columns of A does not match the number of rows of B.
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.getColumns() != b.getRows()) {
            throw new IllegalArgumentException(
                    "O número de colunas da matriz A deve ser igual ao número de linhas da matriz B.");
        }

        double[][] result = new double[a.getRows()][b.getColumns()];

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                result[i][j] = 0;
                for (int k = 0; k < a.getColumns(); k++) {
                    result[i][j] += a.mtx[i][k] * b.mtx[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies this Matrix with another Matrix.
     * This method returns a new Matrix containing the result.
     *
     * @param other The Matrix to multiply with this matrix.
     * @return A new Matrix that is the result of this matrix * other.
     * @throws IllegalArgumentException if the number of columns of this matrix does not match the number of rows of the other matrix.
     */
    public Matrix multiply(Matrix other) {
        if (this.columns != other.row) {
            throw new IllegalArgumentException(
                    "O número de colunas desta matriz deve ser igual ao número de linhas da outra matriz.");
        }

        double[][] result = new double[this.row][other.columns];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < other.columns; j++) {
                result[i][j] = 0;
                for (int k = 0; k < this.columns; k++) {
                    result[i][j] += this.mtx[i][k] * other.mtx[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    /**
     * Creates an identity Matrix of the specified size.
     * An identity matrix is a square matrix with ones on the main diagonal and zeros elsewhere.
     *
     * @param rows    The number of rows (and columns) in the identity matrix.
     * @param columns The number of columns (and rows) in the identity matrix.
     * @return A new Matrix representing the identity matrix.
     * @throws IllegalStateException if the number of rows is not equal to the number of columns.
     */
    public static Matrix I(int rows, int columns) {
        if (rows != columns) {
            throw new IllegalStateException("Matrix must be rows=columns");
        }

        double[][] data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            data[i][i] = 1.0;
        }

        return new Matrix(data);
    }

    /**
     * Computes the inverse of a Matrix.
     * The inverse of a matrix is a matrix that, when multiplied by the original matrix,
     * results in the identity matrix.
     *
     * @param a The Matrix to compute the inverse of.
     * @return A new Matrix representing the inverse of the input matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     * @throws IllegalStateException    if the matrix is singular (non-invertible).
     */
    public static Matrix inverse(Matrix a) {
        int n = a.getMatrix().length;
        if (n != a.getMatrix()[0].length) {
            throw new IllegalArgumentException("A matriz deve ser quadrada.");
        }

        Matrix augmented = new Matrix(new double[n][n]);
        Matrix identity = Matrix.I(n, n);

        double[][] aData = a.getMatrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented.getMatrix()[i][j] = aData[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            if (augmented.getMatrix()[i][i] == 0) {
                throw new IllegalStateException("A matriz não é invertível.");
            }

            double pivot = augmented.getMatrix()[i][i];
            for (int j = 0; j < n; j++) {
                augmented.getMatrix()[i][j] /= pivot;
                identity.getMatrix()[i][j] /= pivot;
            }

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double factor = augmented.getMatrix()[j][i];
                    for (int k = 0; k < n; k++) {
                        augmented.getMatrix()[j][k] -= augmented.getMatrix()[i][k] * factor;
                        identity.getMatrix()[j][k] -= identity.getMatrix()[i][k] * factor;
                    }
                }
            }
        }

        return identity;
    }

    /**
     * Computes the inverse of this Matrix.
     *
     * @return A new Matrix representing the inverse of this matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     * @throws IllegalStateException    if the matrix is singular (non-invertible).
     */
    public Matrix inverse() {
        return Matrix.inverse(this);
    }

    /**
     * Computes the transpose of this Matrix.
     * The transpose of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @return A new Matrix representing the transpose of this matrix.
     */
    public Matrix transpose() {
        double[][] transposedData = new double[this.columns][this.row];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.columns; j++) {
                transposedData[j][i] = this.mtx[i][j];
            }
        }
        return new Matrix(transposedData);
    }

    /**
     * Computes the transpose of a 2D array representing a matrix.
     * The transpose of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The 2D array to compute the transpose of.
     * @return A new Matrix representing the transpose of the input matrix.
     */
    public static Matrix transpose(double[][] matrix) {
        double[][] transposedData = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposedData[j][i] = matrix[i][j];
            }
        }
        return new Matrix(transposedData);
    }

    /**
     * Computes the transpose of a Matrix.
     * The transpose of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The Matrix to compute the transpose of.
     * @return A new Matrix representing the transpose of the input matrix.
     */
    public static Matrix transpose(Matrix matrix) {
        double[][] transposedData = new double[matrix.getColumns()][matrix.getRows()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                transposedData[j][i] = matrix.mtx[i][j];
            }
        }
        return new Matrix(transposedData);

    }

    /**
     * Computes the symmetrical version of a Matrix.
     * The symmetrical version of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The Matrix to compute the symmetrical version of.
     * @return A new Matrix representing the symmetrical version of the input matrix.
     */
    public static Matrix symmetricMatrix(Matrix matrix) {
        double[][] transposedData = new double[matrix.getColumns()][matrix.getRows()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                transposedData[j][i] = matrix.mtx[i][j];
            }
        }
        return new Matrix(transposedData);
    }

    /**
     * Computes the symmetrical version of a 2D array representing a matrix.
     * The symmetrical version of a matrix is a new matrix where the rows and columns are swapped.
     *
     * @param matrix The 2D array to compute the symmetrical version of.
     * @return A new Matrix representing the symmetrical version of the input matrix.
     */
    public static Matrix symmetricMatrix(double[][] matrix) {
        double[][] transposedData = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposedData[j][i] = matrix[i][j];
            }
        }
        return new Matrix(transposedData);

    }

    /**
     * Creates a diagonal matrix from a 2D array.
     * The diagonal matrix has the same diagonal elements as the input matrix,
     * and all other elements are zero.
     *
     * @param matrix The 2D array to create the diagonal matrix from.
     * @return A new Matrix representing the diagonal matrix.
     */
    public static Matrix diagonalMatrix(double[][] matrix) {
        double[][] diagonalData = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == j) {
                    diagonalData[i][j] = matrix[i][j];
                } else {
                    diagonalData[i][j] = 0;
                }
            }
        }
        return new Matrix(diagonalData);
    }

    /**
     * Converts this matrix to a diagonal matrix, setting all non-diagonal elements to zero.
     */
    public void diagonalMatrix() {
        double[][] diagonalData = new double[this.mtx.length][this.mtx[0].length];
        for (int i = 0; i < this.mtx.length; i++) {
            for (int j = 0; j < this.mtx[0].length; j++) {
                if (i == j) {
                    diagonalData[i][j] = this.mtx[i][j];
                } else {
                    diagonalData[i][j] = 0;
                }
            }
        }
        this.mtx = diagonalData;
    }

    /**
     * Creates an orthogonal matrix from a Matrix.
     * The orthogonal matrix has the same diagonal elements as the input matrix,
     * and all other elements are zero.
     *
     * @param matrix The Matrix to create the orthogonal matrix from.
     * @return A new Matrix representing the orthogonal matrix.
     * @throws IllegalArgumentException if the matrix is null or empty.
     */
    public static Matrix ortogonalMatrix(Matrix matrix) {
        if (matrix == null || matrix.getRows() == 0 || matrix.getColumns() == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }
        double[][] ortogonalData = new double[matrix.getColumns()][matrix.getRows()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (i == j) {
                    ortogonalData[i][j] = matrix.mtx[i][j];
                } else {
                    ortogonalData[i][j] = 0;
                }
            }
        }
        return new Matrix(ortogonalData);
    }

    /**
     * Calculates the rank of the matrix using Gaussian elimination.
     *
     * @param matrix The Matrix to calculate the rank of.
     * @return The rank of the matrix.
     * @throws IllegalArgumentException if the matrix is null or empty.
     */
    public static int rank(Matrix matrix) {
        if (matrix == null || matrix.getRows() == 0 || matrix.getColumns() == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }

        double[][] data = new double[matrix.getRows()][matrix.getColumns()];
        for (int i = 0; i < matrix.getRows(); i++) {
            data[i] = matrix.getMatrix()[i].clone();
        }

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        int rank = columns;

        for (int row = 0; row < rank; row++) {
            if (data[row][row] != 0) {
                for (int col = 0; col < rows; col++) {
                    if (col != row) {
                        double multiplier = data[col][row] / data[row][row];
                        for (int i = row; i < rank; i++) {
                            data[col][i] -= multiplier * data[row][i];
                        }
                    }
                }
            } else {
                boolean reduce = true;
                for (int i = row + 1; i < rows; i++) {
                    if (data[i][row] != 0) {
                        double[] temp = data[row];
                        data[row] = data[i];
                        data[i] = temp;
                        reduce = false;
                        break;
                    }
                }

                if (reduce) {
                    rank--;
                    for (int i = 0; i < rows; i++) {
                        data[i][row] = data[i][rank];
                    }
                }
                row--;
            }
        }

        // Verificação para matrizes 1xN ou Nx1
        if (rows == 1 || columns == 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (data[i][j] != 0) {
                        return 1;
                    }
                }
            }
            return 0;
        }

        return rank;
    }

    /**
     * Multiplies a Matrix by a scalar value.
     *
     * @param matrix The Matrix to multiply.
     * @param scalar The scalar value to multiply the matrix by.
     * @return A new Matrix that is the result of the scalar multiplication.
     */
    public static Matrix multiplyByScalar(Matrix matrix, double scalar) {
        double[][] result = new double[matrix.getRows()][matrix.getColumns()];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                result[i][j] = matrix.mtx[i][j] * scalar;
            }
        }
        return new Matrix(result);
    }

    /**
     * Calculates the determinant of a Matrix.
     *
     * @param matrix The Matrix to compute the determinant of.
     * @return The determinant of the matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static double determinant(Matrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        int n = matrix.getRows();
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            data[i] = matrix.getMatrix()[i].clone();
        }

        double det = 1.0;

        for (int i = 0; i < n; i++) {
            double pivot = data[i][i];
            if (pivot == 0) {
                boolean swapped = false;
                for (int j = i + 1; j < n; j++) {
                    if (data[j][i] != 0) {
                        double[] temp = data[i];
                        data[i] = data[j];
                        data[j] = temp;
                        det *= -1;
                        swapped = true;
                        pivot = data[i][i];
                        break;
                    }
                }
                if (!swapped) {
                    return 0.0;
                }
            }
            det *= pivot;
            for (int j = i + 1; j < n; j++) {
                double factor = data[j][i] / pivot;
                for (int k = i; k < n; k++) {
                    data[j][k] -= factor * data[i][k];
                }
            }
        }

        return det;
    }


   

}
