package com.heringer.linalg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class SolvelinsysTest {
    @Test
    void testGaussianEliminationValidMatrix() {
        double[][] data = {
            {2, 1, -1, 8},
            {-3, -1, 2, -11},
            {-2, 1, 2, -3}
        };
        Matrix matrix = new Matrix(data);
    
        Matrix result = Solvelinsys.gaussianElimination(matrix);
    
        double[][] expectedData = {
            {1, 0.5, -0.5, 4},
            {0, 1, 1, 2},
            {0, 0, 1, -1}
        };
        Matrix expected = new Matrix(expectedData);
    
        assertArrayEquals(expected.getMatrix(), result.getMatrix());
    }

    @Test
    void testGaussianEliminationSingularMatrix() {
        double[][] data = {
            {1, 2, 3},
            {2, 4, 6},
            {3, 6, 9}
        };
        Matrix matrix = new Matrix(data);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Solvelinsys.gaussianElimination(matrix);
        });

        assertEquals("Matrix is singular and cannot be solved.", exception.getMessage());
    }

    @Test
void testGaussianEliminationNullMatrix() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        Solvelinsys.gaussianElimination(null);
    });
    assertEquals("Matrix cannot be null or empty.", exception.getMessage());
}

@Test
void testClassifySystemPossible() {
    double[][] data = {
        {2, 1, -1, 8},
        {-3, -1, 2, -11},
        {-2, 1, 2, -3}
    };
    Matrix matrix = new Matrix(data);

    String result = Solvelinsys.classifySystem(matrix);

    assertEquals("Possible", result);
}

@Test
void testClassifySystemImpossible() {
    double[][] data = {
        {1, 2, 3, 4},
        {0, 0, 0, 5},
        {0, 0, 0, 0}
    };
    Matrix matrix = new Matrix(data);

    String result = Solvelinsys.classifySystem(matrix);

    assertEquals("Impossible", result);
}

@Test
void testClassifySystemNullMatrix() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        Solvelinsys.classifySystem(null);
    });
    assertEquals("Matrix cannot be null or empty.", exception.getMessage());
}


}