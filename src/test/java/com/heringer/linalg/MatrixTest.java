package com.heringer.linalg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void testConstructorAndGetters() {
        double[][] data = {
                { 1.0, 2.0 },
                { 3.0, 4.0 }
        };
        Matrix m = new Matrix(data);

        assertEquals(2, m.getRows());
        assertEquals(2, m.getColumns());
    }

    @Test
    void testCopyConstructor() {
        double[][] data = {
                { 1.0, 2.0 },
                { 3.0, 4.0 }
        };
        Matrix original = new Matrix(data);
        Matrix copy = new Matrix(original);

        assertEquals(original.getRows(), copy.getRows());
        assertEquals(original.getColumns(), copy.getColumns());
        assertNotSame(original, copy); // Objetos diferentes
        assertNotSame(original.mtx, copy.mtx); // Arrays diferentes
    }

    @Test
    void testStaticSum() {
        double[][] aData = {
                { 1.0, 2.0 },
                { 3.0, 4.0 }
        };
        double[][] bData = {
                { 5.0, 6.0 },
                { 7.0, 8.0 }
        };
        Matrix a = new Matrix(aData);
        Matrix b = new Matrix(bData);

        Matrix result = Matrix.sum(a, b);

        assertEquals(6.0, result.mtx[0][0]);
        assertEquals(8.0, result.mtx[0][1]);
        assertEquals(10.0, result.mtx[1][0]);
        assertEquals(12.0, result.mtx[1][1]);
    }

    @Test
    void testAddChained() {
        Matrix a = new Matrix(new double[][] {
                { 1, 1 },
                { 1, 1 }
        });
        Matrix b = new Matrix(new double[][] {
                { 2, 2 },
                { 2, 2 }
        });
        Matrix c = new Matrix(new double[][] {
                { 3, 3 },
                { 3, 3 }
        });

        Matrix result = a.add(b).add(c);

        assertArrayEquals(new double[] { 6, 6 }, result.mtx[0]);
        assertArrayEquals(new double[] { 6, 6 }, result.mtx[1]);
    }

    @Test
    void testSumDimensionMismatch() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2 }
        });

        Matrix b = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });

        Exception exception = assertThrows(IllegalArgumentException.class, () -> Matrix.sum(a, b));

        assertTrue(exception.getMessage().contains("same number of rows and columns"));
    }

    @Test
    void testStaticSubtraction() {
        Matrix a = new Matrix(new double[][] {
                { 5, 6 },
                { 7, 8 }
        });
        Matrix b = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });

        Matrix result = Matrix.subtraction(a, b);

        assertArrayEquals(new double[] { 4, 4 }, result.mtx[0]);
        assertArrayEquals(new double[] { 4, 4 }, result.mtx[1]);
    }

    @Test
    void testChainedSubtract() {
        Matrix a = new Matrix(new double[][] {
                { 10, 10 },
                { 10, 10 }
        });
        Matrix b = new Matrix(new double[][] {
                { 3, 3 },
                { 3, 3 }
        });
        Matrix c = new Matrix(new double[][] {
                { 2, 2 },
                { 2, 2 }
        });

        Matrix result = a.subtract(b).subtract(c);

        assertArrayEquals(new double[] { 5, 5 }, result.mtx[0]);
        assertArrayEquals(new double[] { 5, 5 }, result.mtx[1]);
    }

    @Test
    void testSubtractionDimensionMismatch() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2 }
        });
        Matrix b = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });

        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> Matrix.subtraction(a, b));

        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> a.subtract(b));

        assertTrue(ex1.getMessage().contains("same number of rows and columns"));
        assertTrue(ex2.getMessage().contains("same dimensions"));
    }

    @Test
    public void testStaticMultiply2x2() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });
        Matrix b = new Matrix(new double[][] {
                { 5, 6 },
                { 7, 8 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 19, 22 },
                { 43, 50 }
        });

        Matrix result = Matrix.multiply(a, b);
        assertMatrixEquals(expected, result);
    }

    @Test
    public void testInstanceMultiply2x2() {
        Matrix a = new Matrix(new double[][] {
                { 2, 0 },
                { 1, 3 }
        });
        Matrix b = new Matrix(new double[][] {
                { 1, 4 },
                { 2, 5 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 2, 8 },
                { 7, 19 }
        });

        Matrix result = a.multiply(b);
        assertMatrixEquals(expected, result);
    }

    @Test
    public void testMultiplyDifferentSizes() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2, 3 },
                { 4, 5, 6 }
        });

        Matrix b = new Matrix(new double[][] {
                { 7, 8 },
                { 9, 10 },
                { 11, 12 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 58, 64 },
                { 139, 154 }
        });

        Matrix result = a.multiply(b);
        assertMatrixEquals(expected, result);
    }

    @Test
    public void testMultiplyIncompatibleDimensions() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });

        Matrix b = new Matrix(new double[][] {
                { 5, 6 }
        });

        assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(a, b));

        assertThrows(IllegalArgumentException.class, () -> a.multiply(b));
    }

    // Helper para comparar matrizes com tolerância
    private void assertMatrixEquals(Matrix expected, Matrix actual) {
        assertEquals(expected.getRows(), actual.getRows(), "Número de linhas incompatível");
        assertEquals(expected.getColumns(), actual.getColumns(), "Número de colunas incompatível");

        for (int i = 0; i < expected.getRows(); i++) {
            for (int j = 0; j < expected.getColumns(); j++) {
                assertEquals(expected.mtx[i][j], actual.mtx[i][j], 1e-9,
                        String.format("Valor diferente em [%d][%d]", i, j));
            }
        }
    }

    @Test
    void testIdentityMatrix3x3() {
        Matrix identity = Matrix.I(3, 3);
        double[][] data = identity.getMatrix();

        double[][] expected = {
                { 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 }
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], data[i][j], 1e-9,
                        "Mismatch at (" + i + "," + j + ")");
            }
        }
    }

    @Test
    void testIdentityMatrixNotSquare_throwsException() {
        assertThrows(IllegalStateException.class, () -> Matrix.I(3, 2));
        assertThrows(IllegalStateException.class, () -> Matrix.I(2, 3));
    }

    @Test
    void testInverse() {
        // Matriz 2x2 invertível
        double[][] data = {
                { 4, 7 },
                { 2, 6 }
        };
        Matrix a = new Matrix(data);
        Matrix expected = new Matrix(new double[][] {
                { 0.6, -0.7 },
                { -0.2, 0.4 }
        });

        Matrix result = Matrix.inverse(a);

        assertTrue(areMatricesEqual(result, expected), "A matriz inversa está incorreta.");
    }

    @Test
    void testInverseNonInvertible() {
        // Matriz 2x2 não invertível (linha dependente)
        double[][] data = {
                { 1, 2 },
                { 2, 4 }
        };
        Matrix a = new Matrix(data);

        // Esperamos uma exceção
        assertThrows(IllegalStateException.class, () -> Matrix.inverse(a), "Deveria lançar uma exceção, pois a matriz não é invertível.");
    }

    @Test
    void testInverseThis() {
        // Matriz 2x2 invertível
        double[][] data = {
                { 4, 7 },
                { 2, 6 }
        };
        Matrix a = new Matrix(data);
        Matrix expected = new Matrix(new double[][] {
                { 0.6, -0.7 },
                { -0.2, 0.4 }
        });

        Matrix result = Matrix.inverse(a);

        assertTrue(areMatricesEqual(result, expected), "A matriz inversa (this) está incorreta.");
    }

    @Test
    void testInverseThisNonInvertible() {
        // Matriz 2x2 não invertível (linha dependente)
        double[][] data = {
                { 1, 2 },
                { 2, 4 }
        };
        Matrix a = new Matrix(data);

        // Esperamos uma exceção
        assertThrows(IllegalStateException.class, a::inverse, "Deveria lançar uma exceção, pois a matriz não é invertível.");
    }

    @Test
    void testTranspose() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2, 3 },
                { 4, 5, 6 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 1, 4 },
                { 2, 5 },
                { 3, 6 }
        });

        Matrix result = a.transpose();

        assertMatrixEquals(expected, result);
    }

    @Test
    void testStaticTranspose() {
        double[][] data = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        Matrix expected = new Matrix(new double[][] {
                { 1, 4 },
                { 2, 5 },
                { 3, 6 }
        });

        Matrix result = Matrix.transpose(data);

        assertMatrixEquals(expected, result);
    }

    @Test
    void testShowMatrix() {
        Matrix a = new Matrix(new double[][] {
                { 1.123, 2.456 },
                { 3.789, 4.012 }
        });

        // This test ensures no exceptions are thrown during execution
        assertDoesNotThrow(a::showMatrix);
    }

    @Test
    void testStaticIdentityMatrix() {
        Matrix identity = Matrix.I(4, 4);
        double[][] expected = {
                { 1.0, 0.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0, 0.0 },
                { 0.0, 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 0.0, 1.0 }
        };

        assertMatrixEquals(new Matrix(expected), identity);
    }

    @Test
    void testTransposeSquareMatrix() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2 },
                { 3, 4 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 1, 3 },
                { 2, 4 }
        });

        Matrix result = a.transpose();

        assertMatrixEquals(expected, result);
    }

    @Test
    void testTransposeSingleRowMatrix() {
        Matrix a = new Matrix(new double[][] {
                { 1, 2, 3 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 1 },
                { 2 },
                { 3 }
        });

        Matrix result = a.transpose();

        assertMatrixEquals(expected, result);
    }

    @Test
    void testTransposeSingleColumnMatrix() {
        Matrix a = new Matrix(new double[][] {
                { 1 },
                { 2 },
                { 3 }
        });

        Matrix expected = new Matrix(new double[][] {
                { 1, 2, 3 }
        });

        Matrix result = a.transpose();

        assertMatrixEquals(expected, result);
    }

@Test
void testDiagonalMatrixStatic() {
        double[][] data = {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
        };

        Matrix result = Matrix.diagonalMatrix(data);

        double[][] expected = {
                        { 1, 0, 0 },
                        { 0, 5, 0 },
                        { 0, 0, 9 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testDiagonalMatrixInstance() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
        });

        matrix.diagonalMatrix();

        double[][] expected = {
                        { 1, 0, 0 },
                        { 0, 5, 0 },
                        { 0, 0, 9 }
        };

        assertMatrixEquals(new Matrix(expected), matrix);
}

@Test
void testRank() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
        });

        int rank = Matrix.rank(matrix);

        assertEquals(2, rank, "The rank of the matrix is incorrect.");
}

@Test
void testRankZeroMatrix() {
        Matrix matrix = new Matrix(new double[][] {
                        { 0, 0, 0 },
                        { 0, 0, 0 },
                        { 0, 0, 0 }
        });

        int rank = Matrix.rank(matrix);

        assertEquals(0, rank, "The rank of a zero matrix should be 0.");
}

@Test
void testOrthogonalMatrix() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 0, 0 },
                        { 0, 1, 0 },
                        { 0, 0, 1 }
        });

        Matrix result = Matrix.ortogonalMatrix(matrix);

        double[][] expected = {
                        { 1, 0, 0 },
                        { 0, 1, 0 },
                        { 0, 0, 1 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testOrthogonalMatrixInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> Matrix.ortogonalMatrix(null));

        assertThrows(IllegalArgumentException.class, () -> Matrix.ortogonalMatrix(new Matrix(new double[][] {})));
}

@Test
void testSymmetricMatrixStatic() {
        double[][] data = {
                        { 1, 2, 3 },
                        { 4, 5, 6 }
        };

        Matrix result = Matrix.symmetricMatrix(data);

        double[][] expected = {
                        { 1, 4 },
                        { 2, 5 },
                        { 3, 6 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testSymmetricMatrixInstance() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 }
        });

        Matrix result = Matrix.symmetricMatrix(matrix);

        double[][] expected = {
                        { 1, 4 },
                        { 2, 5 },
                        { 3, 6 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testMultiplyByScalar() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
        });

        double scalar = 2.0;

        Matrix result = Matrix.multiplyByScalar(matrix, scalar);

        double[][] expected = {
                        { 2, 4, 6 },
                        { 8, 10, 12 },
                        { 14, 16, 18 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testMultiplyByScalarZero() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
        });

        double scalar = 0.0;

        Matrix result = Matrix.multiplyByScalar(matrix, scalar);

        double[][] expected = {
                        { 0, 0, 0 },
                        { 0, 0, 0 },
                        { 0, 0, 0 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testMultiplyByScalarNegative() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, -2, 3 },
                        { -4, 5, -6 },
                        { 7, -8, 9 }
        });

        double scalar = -1.0;

        Matrix result = Matrix.multiplyByScalar(matrix, scalar);

        double[][] expected = {
                        { -1, 2, -3 },
                        { 4, -5, 6 },
                        { -7, 8, -9 }
        };

        assertMatrixEquals(new Matrix(expected), result);
}

@Test
void testDeterminant5x5Matrix() {
        Matrix matrix = new Matrix(new double[][] {
                        { 2, 1, 3, 4, 5 },
                        { 1, 2, 3, 4, 5 },
                        { 3, 4, 5, 6, 7 },
                        { 4, 5, 6, 7, 8 },
                        { 5, 6, 7, 8, 9 }
        });

        double determinant = Matrix.determinant(matrix);

        assertEquals(0.0, determinant, 1e-9, "The determinant of the 5x5 matrix is incorrect.");
}

@Test
void testDeterminant7x7Matrix() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3, 4, 5, 6, 7 },
                        { 2, 3, 4, 5, 6, 7, 8 },
                        { 3, 4, 5, 6, 7, 8, 9 },
                        { 4, 5, 6, 7, 8, 9, 10 },
                        { 5, 6, 7, 8, 9, 10, 11 },
                        { 6, 7, 8, 9, 10, 11, 12 },
                        { 7, 8, 9, 10, 11, 12, 13 }
        });

        double determinant = Matrix.determinant(matrix);

        assertEquals(0.0, determinant, 1e-9, "The determinant of the 7x7 matrix is incorrect.");
}

@Test
void testDeterminant10x10Matrix() {
        Matrix matrix = new Matrix(new double[][] {
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                        { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
                        { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 },
                        { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 },
                        { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 },
                        { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
                        { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 },
                        { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 },
                        { 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 },
                        { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 }
        });

        double determinant = Matrix.determinant(matrix);

        assertEquals(0.0, determinant, 1e-9, "The determinant of the 10x10 matrix is incorrect.");
}













    // Método auxiliar para comparar duas matrizes
    private boolean areMatricesEqual(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            return false;
        }

        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getColumns(); j++) {
                if (Math.abs(m1.getMatrix()[i][j] - m2.getMatrix()[i][j]) > 1e-6) {
                    return false;
                }
            }
        }
        return true;
    }
}
