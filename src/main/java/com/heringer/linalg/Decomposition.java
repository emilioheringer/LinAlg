package com.heringer.linalg;

import java.util.Arrays;

import org.ejml.simple.SimpleEVD;
import org.ejml.simple.SimpleMatrix;

/**
 * A utility class for performing vector decompositions and related operations.
 * This class provides static methods to check orthogonality between vectors.
 *
 * @author Emílio Heringer
 */
public abstract class Decomposition implements IDecomposition {

    private static final double TOLERANCE = 1e-6;

    /**
     * Checks if two vectors are orthogonal (perpendicular).
     * Two vectors are orthogonal if their dot product is zero.
     *
     * @param a The first Vector.
     * @param b The second Vector.
     * @return true if the vectors are orthogonal, false otherwise.
     */
    public static boolean isOrthogonal(Vector a, Vector b) {
        double dotProduct = Vector.dot(a, b);
        return Math.abs(dotProduct) < TOLERANCE;
    }

    /**
     * Applies the Gram-Schmidt process to a set of vectors to produce an orthonormal basis.
     * The Gram-Schmidt process is a method for orthonormalizing a set of vectors in an inner product space.
     *
     * @param vectors An array of Vector objects to orthonormalize.
     * @return A new array of Vector objects representing the orthonormal basis.
     */
    public static Vector[] gramSchmidt(Vector[] vectors) {
        if (vectors == null || vectors.length == 0) {
            return new Vector[0];
        }

        int dimension = vectors[0].getVector().length;
        for (Vector v : vectors) {
            if (v.getVector().length != dimension) {
                throw new IllegalArgumentException("All vectors must have the same dimension.");
            }
        }

        Vector[] orthonormalBasis = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            Vector v = new Vector(Arrays.copyOf(vectors[i].getVector(), dimension)); // Copia o vetor original
            for (int j = 0; j < i; j++) {
                Vector projection = Vector.project(vectors[i], orthonormalBasis[j]);
                v.subtract(projection);
            }
            orthonormalBasis[i] = Vector.normalize(v);
        }

        return orthonormalBasis;
    }

    /**
     * Performs QR decomposition on a given matrix.
     *
     * @param matrix The Matrix to decompose.
     * @return A new Matrix representing the Q matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static Matrix qrDecomposition(Matrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square for QR decomposition.");
        }

        int n = matrix.getRows();
        Vector[] columns = new Vector[n];
        for (int i = 0; i < n; i++) {
            double[] columnData = new double[n];
            for (int j = 0; j < n; j++) {
                columnData[j] = matrix.getMatrix()[j][i];
            }
            columns[i] = new Vector(columnData);
        }

        Vector[] orthonormalBasis = gramSchmidt(columns);

        double[][] qData = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                qData[j][i] = orthonormalBasis[i].getVector()[j];
            }
        }

        return new Matrix(qData);
    }

    /**
     * Performs LU decomposition on a given matrix.
     *
     * @param matrix The Matrix to decompose.
     * @return A new Matrix representing the L matrix.
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public static Matrix luDecomposition(Matrix matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix must be square for LU decomposition.");
        }

        int n = matrix.getRows();
        double[][] a = matrix.getMatrix();
        double[][] l = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < i; k++) {
                l[i][k] = 0;
                for (int j = 0; j < k; j++) {
                    l[i][k] += l[i][j] * a[j][k];
                }
                l[i][k] = (a[i][k] - l[i][k]) / a[k][k];
            }
            l[i][i] = 1;
            for (int k = i; k < n; k++) {
                for (int j = 0; j < i; j++) {
                    a[i][k] -= l[i][j] * a[j][k];
                }
            }
        }

        return new Matrix(l);
    }

    /**
 * Performs spectral decomposition on a given matrix.
 *
 * @param matrix The Matrix to decompose.
 * @return A SimpleEVD object representing the spectral decomposition.
 * @throws IllegalArgumentException if the matrix is not square.
 */
public static SimpleEVD<SimpleMatrix> spectralDecomposition(Matrix matrix) {
    if (matrix.getRows() != matrix.getColumns()) {
        throw new IllegalArgumentException("Matrix must be square for spectral decomposition.");
    }

    SimpleMatrix simpleMatrix = new SimpleMatrix(matrix.getMatrix());
    SimpleEVD<SimpleMatrix> eig = simpleMatrix.eig();

    return eig;
}
}