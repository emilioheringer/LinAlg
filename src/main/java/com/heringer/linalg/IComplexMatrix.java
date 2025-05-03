package com.heringer.linalg;

import com.heringer.Complex;

/**
 * Interface representing a complex matrix with various operations and utilities.
 * This interface provides methods for accessing and manipulating complex matrices,
 * including basic arithmetic operations, matrix transformations, and utility functions.
 */
public interface IComplexMatrix {

    /**
     * Retrieves the real part of the matrix as a 2D array of doubles.
     *
     * @return A 2D array representing the real part of the matrix.
     */
    double[][] getReal();

    /**
     * Retrieves the imaginary part of the matrix as a 2D array of doubles.
     *
     * @return A 2D array representing the imaginary part of the matrix.
     */
    double[][] getImag();

    /**
     * Retrieves the entire matrix as a 2D array of Complex numbers.
     *
     * @return A 2D array of Complex objects representing the matrix.
     */
    Complex[][] getMatrix();

    /**
     * Gets the number of rows in the matrix.
     *
     * @return The number of rows in the matrix.
     */
    int getRows();

    /**
     * Gets the number of columns in the matrix.
     *
     * @return The number of columns in the matrix.
     */
    int getColumns();

    /**
     * Retrieves the Complex value at the specified position in the matrix.
     *
     * @param i The row index (0-based).
     * @param j The column index (0-based).
     * @return The Complex value at the specified position.
     */
    Complex get(int i, int j);

    /**
     * Displays the matrix in a human-readable format.
     */
    void showMatrix();

    /**
     * Adds another complex matrix to this matrix.
     *
     * @param other The matrix to be added.
     * @return A new ComplexMatrix representing the result of the addition.
     */
    ComplexMatrix add(ComplexMatrix other);

    /**
     * Subtracts another complex matrix from this matrix.
     *
     * @param other The matrix to be subtracted.
     * @return A new ComplexMatrix representing the result of the subtraction.
     */
    ComplexMatrix subtract(ComplexMatrix other);

    /**
     * Multiplies this matrix by another complex matrix.
     *
     * @param other The matrix to be multiplied.
     * @return A new ComplexMatrix representing the result of the multiplication.
     */
    ComplexMatrix multiply(ComplexMatrix other);

    /**
     * Computes the inverse of this matrix.
     *
     * @return A new ComplexMatrix representing the inverse of this matrix.
     */
    ComplexMatrix inverse();

    /**
     * Computes the transpose of this matrix.
     *
     * @return A new ComplexMatrix representing the transpose of this matrix.
     */
    ComplexMatrix transpose();

    /**
     * Converts this matrix into a symmetrical matrix.
     */
    void symetricalMatrix();

    /**
     * Computes the rank of this matrix.
     *
     * @return The rank of the matrix.
     */
    int rank();

    /**
     * Adds two complex matrices.
     *
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new ComplexMatrix representing the result of the addition.
     */
    static ComplexMatrix sum(ComplexMatrix a, ComplexMatrix b) {
        return null;
    }

    /**
     * Subtracts one complex matrix from another.
     *
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new ComplexMatrix representing the result of the subtraction.
     */
    static ComplexMatrix subtraction(ComplexMatrix a, ComplexMatrix b) {
        return null;
    }

    /**
     * Multiplies two complex matrices.
     *
     * @param a The first matrix.
     * @param b The second matrix.
     * @return A new ComplexMatrix representing the result of the multiplication.
     */
    static ComplexMatrix multiply(ComplexMatrix a, ComplexMatrix b) {
        return null;
    }

    /**
     * Creates an identity matrix with the specified dimensions.
     *
     * @param rows    The number of rows.
     * @param columns The number of columns.
     * @return A new ComplexMatrix representing the identity matrix.
     */
    static ComplexMatrix I(int rows, int columns) {
        return null;
    }

    /**
     * Computes the inverse of a given complex matrix.
     *
     * @param matrix The matrix to be inverted.
     * @return A new ComplexMatrix representing the inverse of the given matrix.
     */
    static ComplexMatrix inverse(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Computes the transpose of a given complex matrix.
     *
     * @param matrix The matrix to be transposed.
     * @return A new ComplexMatrix representing the transpose of the given matrix.
     */
    static ComplexMatrix transpose(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Converts a given complex matrix into a symmetrical matrix.
     *
     * @param matrix The matrix to be converted.
     * @return A new ComplexMatrix representing the symmetrical matrix.
     */
    static ComplexMatrix symetricalMatrix(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Converts a given complex matrix into a diagonal matrix.
     *
     * @param matrix The matrix to be converted.
     * @return A new ComplexMatrix representing the diagonal matrix.
     */
    static ComplexMatrix diagonalMatrix(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Converts a given complex matrix into an orthogonal matrix.
     *
     * @param matrix The matrix to be converted.
     * @return A new ComplexMatrix representing the orthogonal matrix.
     */
    static ComplexMatrix ortogonalMatrix(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Computes the cofactor matrix of a given complex matrix.
     *
     * @param matrix The matrix for which the cofactor matrix is to be computed.
     * @return A new ComplexMatrix representing the cofactor matrix.
     */
    static ComplexMatrix cofatorMatrix(ComplexMatrix matrix) {
        return null;
    }

    /**
     * Multiplies a given complex matrix by a scalar value.
     *
     * @param matrix The matrix to be multiplied.
     * @param scalar The scalar value.
     * @return A new ComplexMatrix representing the result of the multiplication.
     */
    static ComplexMatrix multiplyByScalar(ComplexMatrix matrix, double scalar) {
        return null;
    }

    /**
     * Computes the determinant of a given complex matrix.
     *
     * @param matrix The matrix for which the determinant is to be computed.
     * @return A Complex number representing the determinant of the matrix.
     */
    static Complex determinant(ComplexMatrix matrix) {
        return null;
    }
}