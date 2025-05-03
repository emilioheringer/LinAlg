package com.heringer.linalg;

import org.ejml.data.Complex_F64;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;





class EigenvaluesTest {

    @Test
    void testEigenvalues() {
        double[][] matrix = {
            {4, -2},
            {1, 1}
        };

        Complex_F64[] eigenvalues = Eigenvalues.eigenvalues(matrix);

        assertEquals(2, eigenvalues.length);
        assertTrue(eigenvalues[0].isReal());
        assertTrue(eigenvalues[1].isReal());
        assertEquals(3.0, eigenvalues[0].getReal(), 1e-6);
        assertEquals(2.0, eigenvalues[1].getReal(), 1e-6);
    }

    @Test
    void testGetEigenvectors() {
        double[][] matrix = {
            {4, -2},
            {1, 1}
        };

        SimpleMatrix[] eigenvectors = Eigenvalues.eig(matrix);

        assertEquals(2, eigenvectors.length);
        assertNotNull(eigenvectors[0]);
        assertNotNull(eigenvectors[1]);

        // Verify the first eigenvector corresponds to the first eigenvalue
        SimpleMatrix matrixSM = new SimpleMatrix(matrix);
        SimpleMatrix eigenvector1 = eigenvectors[0];
        SimpleMatrix result1 = matrixSM.mult(eigenvector1);
        SimpleMatrix scaledEigenvector1 = eigenvector1.scale(Eigenvalues.eigenvalues(matrix)[0].getReal());
        assertTrue(result1.isIdentical(scaledEigenvector1, 1e-6));

        // Verify the second eigenvector corresponds to the second eigenvalue
        SimpleMatrix eigenvector2 = eigenvectors[1];
        SimpleMatrix result2 = matrixSM.mult(eigenvector2);
        SimpleMatrix scaledEigenvector2 = eigenvector2.scale(Eigenvalues.eigenvalues(matrix)[1].getReal());
        assertTrue(result2.isIdentical(scaledEigenvector2, 1e-6));
    }
}