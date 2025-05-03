package com.heringer.linalg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ejml.simple.SimpleEVD;


public class DecompositionTest {

    @Test
    void isOrthogonal_orthogonalVectors_returnsTrue() {
        Vector a = new Vector(new double[]{1, 0});
        Vector b = new Vector(new double[]{0, 1});
        assertTrue(Decomposition.isOrthogonal(a, b));
    }

    @Test
    void isOrthogonal_nonOrthogonalVectors_returnsFalse() {
        Vector a = new Vector(new double[]{1, 1});
        Vector b = new Vector(new double[]{1, 0});
        assertFalse(Decomposition.isOrthogonal(a, b));
    }

    @Test
    void isOrthogonal_zeroVector_returnsTrue() {
        Vector a = new Vector(new double[]{0, 0});
        Vector b = new Vector(new double[]{1, 1});
        assertTrue(Decomposition.isOrthogonal(a, b));
    }

    @Test
    void isOrthogonal_sameVector_returnsFalse() {
        Vector a = new Vector(new double[]{1, 1});
        Vector b = new Vector(new double[]{1, 1});
        assertFalse(Decomposition.isOrthogonal(a, b));
    }

    @Test
    void gramSchmidt_orthonormalizesVectors() {
        Vector[] vectors = {
            new Vector(new double[]{1, 0}),
            new Vector(new double[]{1, 1})
        };
        Vector[] result = Decomposition.gramSchmidt(vectors);
        assertTrue(Decomposition.isOrthogonal(result[0], result[1]));
        assertEquals(1.0, Vector.magnitude(result[0]), 1e-6);
        assertEquals(1.0, Vector.magnitude(result[1]), 1e-6);
    }

    @Test
    void qrDecomposition_squareMatrix_returnsQMatrix() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix qMatrix = Decomposition.qrDecomposition(matrix);
        assertEquals(2, qMatrix.getRows());
        assertEquals(2, qMatrix.getColumns());
    }

    @Test
    void qrDecomposition_nonSquareMatrix_throwsException() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        assertThrows(IllegalArgumentException.class, () -> Decomposition.qrDecomposition(matrix));
    }

    @Test
    void luDecomposition_squareMatrix_returnsLMatrix() {
        Matrix matrix = new Matrix(new double[][]{
            {4, 3},
            {6, 3}
        });
        Matrix lMatrix = Decomposition.luDecomposition(matrix);
        assertEquals(2, lMatrix.getRows());
        assertEquals(2, lMatrix.getColumns());
    }

    @Test
    void luDecomposition_nonSquareMatrix_throwsException() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        assertThrows(IllegalArgumentException.class, () -> Decomposition.luDecomposition(matrix));
    }

    @Test
    void spectralDecomposition_squareMatrix_returnsEigenDecomposition() {
        Matrix matrix = new Matrix(new double[][]{
            {4, -2},
            {1, 1}
        });
        SimpleEVD<?> evd = Decomposition.spectralDecomposition(matrix);
        assertNotNull(evd);
        assertEquals(2, evd.getNumberOfEigenvalues());
    }

    @Test
    void spectralDecomposition_nonSquareMatrix_throwsException() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        assertThrows(IllegalArgumentException.class, () -> Decomposition.spectralDecomposition(matrix));
    }
}
