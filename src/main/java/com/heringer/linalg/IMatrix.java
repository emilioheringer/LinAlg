package com.heringer.linalg;

/**
 * Interface representing a matrix with various operations and utilities.
 * Provides methods for matrix manipulation such as addition, subtraction, 
 * multiplication, transposition, inversion, and more.
 * 
 * <p>This interface also includes static utility methods for common matrix 
 * operations, allowing for easy manipulation of matrices without requiring 
 * an instance.</p>
 * 
 * @author Emílio Heringer
 */
public interface IMatrix {
    int getRows();
    int getColumns();
    double[][] getMatrix();
    void showMatrix();
    Matrix subtract(Matrix other);
    Matrix multiply(Matrix other);
    Matrix inverse();
    Matrix transpose();
    void diagonalMatrix();
    static Matrix sum(Matrix a, Matrix b) {
        return null;
    }
    static Matrix subtraction(Matrix a, Matrix b) {
        return null;
    }
    static Matrix multiply(Matrix a, Matrix b) {
        return null;
    }
    static Matrix I(int rows, int columns) {
        return null;
    }
    static Matrix inverse(Matrix a) {
        return null;
    }
    static Matrix transpose(double[][] matrix) {
        return null;
    }
    static Matrix transpose(Matrix matrix) {
        return null;
    }
    static Matrix symmetricMatrix(Matrix matrix) {
        return null;
    }
    static Matrix symmetricMatrix(double[][] matrix) {
        return null;
    }
    static Matrix diagonalMatrix(double[][] matrix) {
        return null;
    }
    static Matrix ortogonalMatrix(Matrix matrix) {
        return null;
    }
    static int rank(Matrix matrix) {
        return 0;
    }
    static Matrix multiplyByScalar(Matrix matrix, double scalar) {
        return null;
    }
    static double determinant(Matrix matrix) {
        return 0;
    }
}