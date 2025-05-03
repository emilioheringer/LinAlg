package com.heringer.linalg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.heringer.Complex;

public class ComplexMatrixTest {

        @Test
        void testConstructorInitializesCorrectly() {
                Complex[][] input = {
                                { new Complex(1, 2), new Complex(3, 4) },
                                { new Complex(5, 6), new Complex(7, 8) }
                };

                ComplexMatrix matrix = new ComplexMatrix(input);

                // Verificar as dimensões
                assertEquals(2, matrix.getRows());
                assertEquals(2, matrix.getColumns());

                // Verificar os valores diretamente no array `matrix`
                assertEquals(new Complex(1, 2), matrix.get(0, 0));
                assertEquals(new Complex(3, 4), matrix.get(0, 1));
                assertEquals(new Complex(5, 6), matrix.get(1, 0));
                assertEquals(new Complex(7, 8), matrix.get(1, 1));
        }

        @Test
        void testSum() {
                Complex[][] m1 = {
                                { new Complex(1, 1), new Complex(2, 2) },
                                { new Complex(3, 3), new Complex(4, 4) }
                };

                Complex[][] m2 = {
                                { new Complex(5, -1), new Complex(6, -2) },
                                { new Complex(7, -3), new Complex(8, -4) }
                };

                ComplexMatrix matrix1 = new ComplexMatrix(m1);
                ComplexMatrix matrix2 = new ComplexMatrix(m2);
                ComplexMatrix result = ComplexMatrix.sum(matrix1, matrix2);

                assertEquals(new Complex(6, 0), result.get(0, 0));
                assertEquals(new Complex(8, 0), result.get(0, 1));
                assertEquals(new Complex(10, 0), result.get(1, 0));
                assertEquals(new Complex(12, 0), result.get(1, 1));
        }

        @Test
        void testSumWithNonIntegerValues() {
                // Matriz 1 com números não inteiros
                Complex[][] m1 = {
                                { new Complex(1.5, 2.5), new Complex(3.2, 4.1) },
                                { new Complex(5.3, 6.4), new Complex(7.5, 8.6) }
                };

                // Matriz 2 com números não inteiros
                Complex[][] m2 = {
                                { new Complex(9.1, -1.1), new Complex(0.5, -2.3) },
                                { new Complex(2.7, -3.8), new Complex(4.9, -5.1) }
                };

                // Criando as instâncias de ComplexMatrix
                ComplexMatrix matrix1 = new ComplexMatrix(m1);
                ComplexMatrix matrix2 = new ComplexMatrix(m2);

                // Somando as matrizes
                ComplexMatrix result = ComplexMatrix.sum(matrix1, matrix2);

                // Verificando os resultados
                assertEquals(new Complex(10.6, 1.4), result.get(0, 0));
                assertEquals(new Complex(3.7, 1.8), result.get(0, 1));
                assertEquals(new Complex(8.0, 2.6), result.get(1, 0));
                assertEquals(new Complex(12.4, 3.5), result.get(1, 1));
        }

        @Test
        void testAddBasicPositiveValues() {
                Complex[][] a = {
                                { new Complex(1, 2), new Complex(3, 4) }
                };
                Complex[][] b = {
                                { new Complex(5, 6), new Complex(7, 8) }
                };
                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.add(m2);

                assertEquals(new Complex(6, 8), result.get(0, 0));
                assertEquals(new Complex(10, 12), result.get(0, 1));
        }

        @Test
        void testAddNegativeAndPositive() {
                Complex[][] a = {
                                { new Complex(-1, 3), new Complex(4, -5) }
                };
                Complex[][] b = {
                                { new Complex(2, -3), new Complex(-4, 6) }
                };
                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.add(m2);

                assertEquals(new Complex(1, 0), result.get(0, 0));
                assertEquals(new Complex(0, 1), result.get(0, 1));
        }

        @Test
        void testAddWithDecimalValues() {
                Complex[][] a = {
                                { new Complex(1.5, 2.5) }
                };
                Complex[][] b = {
                                { new Complex(0.5, 1.5) }
                };
                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.add(m2);

                assertEquals(new Complex(2.0, 4.0), result.get(0, 0));
        }

        @Test
        void testAddWithZeros() {
                Complex[][] a = {
                                { new Complex(0, 0), new Complex(1, 1) }
                };
                Complex[][] b = {
                                { new Complex(0, 0), new Complex(2, 3) }
                };
                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.add(m2);

                assertEquals(new Complex(0, 0), result.get(0, 0));
                assertEquals(new Complex(3, 4), result.get(0, 1));
        }

        @Test
        void testAddThrowsOnDifferentSize() {
                Complex[][] a = {
                                { new Complex(1, 1) }
                };
                Complex[][] b = {
                                { new Complex(1, 1), new Complex(1, 1) }
                };
                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                assertThrows(IllegalArgumentException.class, () -> m1.add(m2));
        }

        @Test
        void testStaticSubtractionBasic() {
                Complex[][] a = {
                                { new Complex(5, 5), new Complex(7, 8) }
                };
                Complex[][] b = {
                                { new Complex(2, 1), new Complex(4, 2) }
                };

                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = ComplexMatrix.subtraction(m1, m2);

                assertEquals(new Complex(3, 4), result.get(0, 0));
                assertEquals(new Complex(3, 6), result.get(0, 1));
        }

        @Test
        void testInstanceSubtractNegativeValues() {
                Complex[][] a = {
                                { new Complex(-2, -3), new Complex(0, -1) }
                };
                Complex[][] b = {
                                { new Complex(-1, -1), new Complex(1, 1) }
                };

                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.subtract(m2);

                assertEquals(new Complex(-1, -2), result.get(0, 0));
                assertEquals(new Complex(-1, -2), result.get(0, 1));
        }

        @Test
        void testSubtractWithDecimals() {
                Complex[][] a = {
                                { new Complex(2.5, 3.5) }
                };
                Complex[][] b = {
                                { new Complex(1.2, 1.3) }
                };

                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.subtract(m2);

                assertEquals(new Complex(1.3, 2.2), result.get(0, 0));
        }

        @Test
        void testSubtractWithZeroMatrix() {
                Complex[][] a = {
                                { new Complex(3, 4), new Complex(5, 6) }
                };
                Complex[][] b = {
                                { new Complex(0, 0), new Complex(0, 0) }
                };

                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                ComplexMatrix result = m1.subtract(m2);

                assertEquals(new Complex(3, 4), result.get(0, 0));
                assertEquals(new Complex(5, 6), result.get(0, 1));
        }

        @Test
        void testSubtractionThrowsOnMismatchedSize() {
                Complex[][] a = {
                                { new Complex(1, 1) }
                };
                Complex[][] b = {
                                { new Complex(1, 1), new Complex(1, 1) }
                };

                ComplexMatrix m1 = new ComplexMatrix(a);
                ComplexMatrix m2 = new ComplexMatrix(b);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.subtraction(m1, m2));
                assertThrows(IllegalArgumentException.class, () -> m1.subtract(m2));
        }

        @Test
        void testStaticMultiply_2x2() {
                Complex[][] dataA = {
                                { new Complex(1, 1), new Complex(2, 0) },
                                { new Complex(0, -1), new Complex(3, 2) }
                };

                Complex[][] dataB = {
                                { new Complex(2, 0), new Complex(1, 1) },
                                { new Complex(0, 1), new Complex(2, -1) }
                };

                ComplexMatrix A = new ComplexMatrix(dataA);
                ComplexMatrix B = new ComplexMatrix(dataB);
                ComplexMatrix result = ComplexMatrix.multiply(A, B);

                Complex[][] expected = {
                                {
                                                Complex.sum(Complex.multiply(dataA[0][0], dataB[0][0]),
                                                                Complex.multiply(dataA[0][1], dataB[1][0])),
                                                Complex.sum(Complex.multiply(dataA[0][0], dataB[0][1]),
                                                                Complex.multiply(dataA[0][1], dataB[1][1]))
                                },
                                {
                                                Complex.sum(Complex.multiply(dataA[1][0], dataB[0][0]),
                                                                Complex.multiply(dataA[1][1], dataB[1][0])),
                                                Complex.sum(Complex.multiply(dataA[1][0], dataB[0][1]),
                                                                Complex.multiply(dataA[1][1], dataB[1][1]))
                                }
                };

                for (int i = 0; i < expected.length; i++) {
                        for (int j = 0; j < expected[0].length; j++) {
                                assertEquals(expected[i][j], result.get(i, j),
                                                "Mismatch at position (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        void testInstanceMultiply_2x1_by_1x2() {
                ComplexMatrix A = new ComplexMatrix(new Complex[][] {
                                { new Complex(1, 1) },
                                { new Complex(2, -1) }
                });

                ComplexMatrix B = new ComplexMatrix(new Complex[][] {
                                { new Complex(3, 0), new Complex(0, 1) }
                });

                ComplexMatrix result = A.multiply(B);

                Complex[][] expected = new Complex[][] {
                                { Complex.multiply(new Complex(1, 1), new Complex(3, 0)),
                                                Complex.multiply(new Complex(1, 1), new Complex(0, 1)) },
                                { Complex.multiply(new Complex(2, -1), new Complex(3, 0)),
                                                Complex.multiply(new Complex(2, -1), new Complex(0, 1)) }
                };

                for (int i = 0; i < expected.length; i++) {
                        for (int j = 0; j < expected[0].length; j++) {
                                assertEquals(expected[i][j], result.get(i, j),
                                                "Mismatch at (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        void testMultiplyDimensionMismatch_throwsException() {
                ComplexMatrix A = new ComplexMatrix(new Complex[][] {
                                { new Complex(1, 0), new Complex(2, 0) }
                });

                ComplexMatrix B = new ComplexMatrix(new Complex[][] {
                                { new Complex(1, 0), new Complex(2, 0) }
                });

                Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.multiply(A, B));
                Exception exception2 = assertThrows(IllegalArgumentException.class, () -> A.multiply(B));

                assertEquals("Number of columns of A must match number of rows of B.", exception1.getMessage());
                assertEquals("Number of columns of A must match number of rows of B.", exception2.getMessage());
        }

        @Test
        void testIdentityMatrix() {
                // Teste para uma matriz identidade 2x2
                ComplexMatrix identityMatrix2x2 = ComplexMatrix.I(2, 2);

                // Verificar se é uma matriz quadrada
                assertEquals(2, identityMatrix2x2.getRows());
                assertEquals(2, identityMatrix2x2.getColumns());

                // Verificar os valores na diagonal principal e fora dela
                assertEquals(new Complex(1, 0), identityMatrix2x2.get(0, 0));
                assertEquals(new Complex(0, 0), identityMatrix2x2.get(0, 1));
                assertEquals(new Complex(0, 0), identityMatrix2x2.get(1, 0));
                assertEquals(new Complex(1, 0), identityMatrix2x2.get(1, 1));
        }

        @Test
        void testIdentityMatrix3x3() {
                // Teste para uma matriz identidade 3x3
                ComplexMatrix identityMatrix3x3 = ComplexMatrix.I(3, 3);

                // Verificar se é uma matriz quadrada
                assertEquals(3, identityMatrix3x3.getRows());
                assertEquals(3, identityMatrix3x3.getColumns());

                // Verificar os valores na diagonal principal e fora dela
                assertEquals(new Complex(1, 0), identityMatrix3x3.get(0, 0));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(0, 1));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(0, 2));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(1, 0));
                assertEquals(new Complex(1, 0), identityMatrix3x3.get(1, 1));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(1, 2));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(2, 0));
                assertEquals(new Complex(0, 0), identityMatrix3x3.get(2, 1));
                assertEquals(new Complex(1, 0), identityMatrix3x3.get(2, 2));
        }

        @Test
        void testInverseOfIdentityMatrix() {
                ComplexMatrix identityMatrix = ComplexMatrix.I(3, 3);
                ComplexMatrix inverseMatrix = identityMatrix.inverse();

                for (int i = 0; i < identityMatrix.getRows(); i++) {
                        for (int j = 0; j < identityMatrix.getColumns(); j++) {
                                assertEquals(identityMatrix.get(i, j), inverseMatrix.get(i, j),
                                                "Mismatch at position (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        void testInverseOfSimpleMatrix() {
                Complex[][] data = {
                                { new Complex(4, 0), new Complex(7, 0) },
                                { new Complex(2, 0), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix inverseMatrix = matrix.inverse();

                Complex[][] expectedData = {
                                { new Complex(0.6, 0), new Complex(-0.7, 0) },
                                { new Complex(-0.2, 0), new Complex(0.4, 0) }
                };

                ComplexMatrix expectedMatrix = new ComplexMatrix(expectedData);

                for (int i = 0; i < expectedMatrix.getRows(); i++) {
                        for (int j = 0; j < expectedMatrix.getColumns(); j++) {
                                assertEquals(expectedMatrix.get(i, j), inverseMatrix.get(i, j),
                                                "Mismatch at position (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        public void testNonSquareMatrixThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0) },
                                { new Complex(3, 0), new Complex(4, 0) },
                                { new Complex(5, 0), new Complex(6, 0) }
                };
                ComplexMatrix nonSquare = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, nonSquare::inverse);
        }

        @Test
        public void testSingularMatrixThrowsException() {
                Complex[][] data = {
                                { new Complex(2, 0), new Complex(4, 0) },
                                { new Complex(1, 0), new Complex(2, 0) }
                };
                ComplexMatrix singular = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, singular::inverse);
        }

        @Test
        void testTransposeSquareMatrix() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4) },
                                { new Complex(5, 6), new Complex(7, 8) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = matrix.transpose();

                assertEquals(new Complex(1, 2), transposed.get(0, 0));
                assertEquals(new Complex(5, 6), transposed.get(0, 1));
                assertEquals(new Complex(3, 4), transposed.get(1, 0));
                assertEquals(new Complex(7, 8), transposed.get(1, 1));
        }

        @Test
        void testTransposeNonSquareMatrix() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4), new Complex(5, 6) },
                                { new Complex(7, 8), new Complex(9, 10), new Complex(11, 12) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = matrix.transpose();

                assertEquals(new Complex(1, 2), transposed.get(0, 0));
                assertEquals(new Complex(7, 8), transposed.get(0, 1));
                assertEquals(new Complex(3, 4), transposed.get(1, 0));
                assertEquals(new Complex(9, 10), transposed.get(1, 1));
                assertEquals(new Complex(5, 6), transposed.get(2, 0));
                assertEquals(new Complex(11, 12), transposed.get(2, 1));
        }

        @Test
        void testTransposeEmptyMatrix() {
                Complex[][] data = new Complex[0][0];
                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = matrix.transpose();

                assertEquals(0, transposed.getRows());
                assertEquals(0, transposed.getColumns());
        }

        @Test
        void testTransposeSingleElementMatrix() {
                Complex[][] data = { { new Complex(1, 1) } };
                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = matrix.transpose();

                assertEquals(1, transposed.getRows());
                assertEquals(1, transposed.getColumns());
                assertEquals(new Complex(1, 1), transposed.get(0, 0));
        }

        @Test
        void testSymmetricalMatrixSquare() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1) },
                                { new Complex(2, -1), new Complex(3, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix symmetrical = ComplexMatrix.symetricalMatrix(matrix);

                assertEquals(new Complex(1, 0), symmetrical.get(0, 0));
                assertEquals(new Complex(2, -1), symmetrical.get(0, 1));
                assertEquals(new Complex(2, 1), symmetrical.get(1, 0));
                assertEquals(new Complex(3, 0), symmetrical.get(1, 1));
        }

        @Test
        void testSymmetricalMatrixNonSquareThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1), new Complex(3, 0) },
                                { new Complex(4, 0), new Complex(5, 1), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.symetricalMatrix(matrix));
        }

        @Test
        void testSymmetricalMatrixSingleElement() {
                Complex[][] data = { { new Complex(1, 1) } };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix symmetrical = ComplexMatrix.symetricalMatrix(matrix);

                assertEquals(1, symmetrical.getRows());
                assertEquals(1, symmetrical.getColumns());
                assertEquals(new Complex(1, 1), symmetrical.get(0, 0));
        }

        @Test
        void testSymmetricalMatrixEmptyMatrix() {
                Complex[][] data = new Complex[0][0];

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix symmetrical = ComplexMatrix.symetricalMatrix(matrix);

                assertEquals(0, symmetrical.getRows());
                assertEquals(0, symmetrical.getColumns());
        }

        @Test
        void testNullMatrix() {
                ComplexMatrix matrix = new ComplexMatrix(null);

                assertEquals(0, matrix.getRows());
                assertEquals(0, matrix.getColumns());
                assertEquals(0, matrix.getMatrix().length);
        }

        @Test
        void testSymmetricalMatrixInstanceMethod() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1) },
                                { new Complex(2, -1), new Complex(3, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                matrix.symetricalMatrix();

                assertEquals(new Complex(1, 0), matrix.get(0, 0));
                assertEquals(new Complex(2, -1), matrix.get(0, 1));
                assertEquals(new Complex(2, 1), matrix.get(1, 0));
                assertEquals(new Complex(3, 0), matrix.get(1, 1));
        }

        @Test
        void testSymmetricalMatrixInstanceMethodNonSquareThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1), new Complex(3, 0) },
                                { new Complex(4, 0), new Complex(5, 1), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, matrix::symetricalMatrix);
        }

        @Test
        void testDiagonalMatrixSquare() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1) },
                                { new Complex(3, -1), new Complex(4, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix diagonal = ComplexMatrix.diagonalMatrix(matrix);

                assertEquals(new Complex(1, 0), diagonal.get(0, 0));
                assertEquals(new Complex(0, 0), diagonal.get(0, 1));
                assertEquals(new Complex(0, 0), diagonal.get(1, 0));
                assertEquals(new Complex(4, 0), diagonal.get(1, 1));
        }

        @Test
        void testDiagonalMatrixNonSquareThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 1), new Complex(3, 0) },
                                { new Complex(4, 0), new Complex(5, 1), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.diagonalMatrix(matrix));
        }

        @Test
        void testDiagonalMatrixSingleElement() {
                Complex[][] data = { { new Complex(1, 1) } };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix diagonal = ComplexMatrix.diagonalMatrix(matrix);

                assertEquals(1, diagonal.getRows());
                assertEquals(1, diagonal.getColumns());
                assertEquals(new Complex(1, 1), diagonal.get(0, 0));
        }

        @Test
        void testDiagonalMatrixEmptyMatrix() {
                Complex[][] data = new Complex[0][0];

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix diagonal = ComplexMatrix.diagonalMatrix(matrix);

                assertEquals(0, diagonal.getRows());
                assertEquals(0, diagonal.getColumns());
        }

        @Test
        void testShowMatrix() {

        }

        @Test
        void testStaticTransposeSquareMatrix() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4) },
                                { new Complex(5, 6), new Complex(7, 8) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = ComplexMatrix.transpose(matrix);

                assertEquals(new Complex(1, 2), transposed.get(0, 0));
                assertEquals(new Complex(5, 6), transposed.get(0, 1));
                assertEquals(new Complex(3, 4), transposed.get(1, 0));
                assertEquals(new Complex(7, 8), transposed.get(1, 1));
        }

        @Test
        void testStaticTransposeNonSquareMatrix() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4), new Complex(5, 6) },
                                { new Complex(7, 8), new Complex(9, 10), new Complex(11, 12) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = ComplexMatrix.transpose(matrix);

                assertEquals(new Complex(1, 2), transposed.get(0, 0));
                assertEquals(new Complex(7, 8), transposed.get(0, 1));
                assertEquals(new Complex(3, 4), transposed.get(1, 0));
                assertEquals(new Complex(9, 10), transposed.get(1, 1));
                assertEquals(new Complex(5, 6), transposed.get(2, 0));
                assertEquals(new Complex(11, 12), transposed.get(2, 1));
        }

        @Test
        void testStaticTransposeEmptyMatrix() {
                Complex[][] data = new Complex[0][0];
                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = ComplexMatrix.transpose(matrix);

                assertEquals(0, transposed.getRows());
                assertEquals(0, transposed.getColumns());
        }

        @Test
        void testStaticTransposeSingleElementMatrix() {
                Complex[][] data = { { new Complex(1, 1) } };
                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix transposed = ComplexMatrix.transpose(matrix);

                assertEquals(1, transposed.getRows());
                assertEquals(1, transposed.getColumns());
                assertEquals(new Complex(1, 1), transposed.get(0, 0));
        }

        @Test
        void testStaticInverseIdentityMatrix() {
                ComplexMatrix identityMatrix = ComplexMatrix.I(3, 3);
                ComplexMatrix inverseMatrix = ComplexMatrix.inverse(identityMatrix);

                for (int i = 0; i < identityMatrix.getRows(); i++) {
                        for (int j = 0; j < identityMatrix.getColumns(); j++) {
                                assertEquals(identityMatrix.get(i, j), inverseMatrix.get(i, j),
                                                "Mismatch at position (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        void testStaticInverseSimpleMatrix() {
                Complex[][] data = {
                                { new Complex(4, 0), new Complex(7, 0) },
                                { new Complex(2, 0), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix inverseMatrix = ComplexMatrix.inverse(matrix);

                Complex[][] expectedData = {
                                { new Complex(0.6, 0), new Complex(-0.7, 0) },
                                { new Complex(-0.2, 0), new Complex(0.4, 0) }
                };

                ComplexMatrix expectedMatrix = new ComplexMatrix(expectedData);

                for (int i = 0; i < expectedMatrix.getRows(); i++) {
                        for (int j = 0; j < expectedMatrix.getColumns(); j++) {
                                assertEquals(expectedMatrix.get(i, j), inverseMatrix.get(i, j),
                                                "Mismatch at position (" + i + "," + j + ")");
                        }
                }
        }

        @Test
        void testStaticInverseNonSquareMatrixThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0) },
                                { new Complex(3, 0), new Complex(4, 0) },
                                { new Complex(5, 0), new Complex(6, 0) }
                };
                ComplexMatrix nonSquare = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.inverse(nonSquare));
        }

        @Test
        void testStaticInverseSingularMatrixThrowsException() {
                Complex[][] data = {
                                { new Complex(2, 0), new Complex(4, 0) },
                                { new Complex(1, 0), new Complex(2, 0) }
                };
                ComplexMatrix singular = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.inverse(singular));
        }

        @Test
        void testCofactorMatrixSquare() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4) },
                                { new Complex(5, 6), new Complex(7, 8) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix cofactor = ComplexMatrix.cofatorMatrix(matrix);

                assertEquals(new Complex(1, -2), cofactor.get(0, 0));
                assertEquals(new Complex(3, -4), cofactor.get(0, 1));
                assertEquals(new Complex(5, -6), cofactor.get(1, 0));
                assertEquals(new Complex(7, -8), cofactor.get(1, 1));
        }

        @Test
        void testCofactorMatrixNonSquareThrowsException() {
                Complex[][] data = {
                                { new Complex(1, 2), new Complex(3, 4), new Complex(5, 6) },
                                { new Complex(7, 8), new Complex(9, 10), new Complex(11, 12) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.cofatorMatrix(matrix));
        }

        @Test
        void testCofactorMatrixSingleElement() {
                Complex[][] data = { { new Complex(1, 1) } };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix cofactor = ComplexMatrix.cofatorMatrix(matrix);

                assertEquals(1, cofactor.getRows());
                assertEquals(1, cofactor.getColumns());
                assertEquals(new Complex(1, -1), cofactor.get(0, 0));
        }

        @Test
        void testCofactorMatrixEmptyMatrix() {
                Complex[][] data = new Complex[0][0];

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix cofactor = ComplexMatrix.cofatorMatrix(matrix);

                assertEquals(0, cofactor.getRows());
                assertEquals(0, cofactor.getColumns());

        }

        @Test
        void testRankOfFullRankMatrix() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0) },
                                { new Complex(4, 0), new Complex(5, 0), new Complex(6, 0) },
                                { new Complex(7, 0), new Complex(8, 0), new Complex(10, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                assertEquals(3, matrix.rank());
        }

        @Test
        void testRankOfZeroMatrix() {
                Complex[][] data = {
                                { new Complex(0, 0), new Complex(0, 0) },
                                { new Complex(0, 0), new Complex(0, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                assertEquals(0, matrix.rank());
        }

        @Test
        void testRankOfRectangularMatrix() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0) },
                                { new Complex(4, 0), new Complex(5, 0), new Complex(6, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                assertEquals(2, matrix.rank());
        }

        @Test
        void testRankOfSingularMatrix() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0) },
                                { new Complex(2, 0), new Complex(4, 0), new Complex(6, 0) },
                                { new Complex(3, 0), new Complex(6, 0), new Complex(9, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                assertEquals(1, matrix.rank());
        }

        @Test
        void testMultiplyByScalar() {
                Complex[][] data = {
                                { new Complex(1, 1), new Complex(2, 2) },
                                { new Complex(3, 3), new Complex(4, 4) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix result = ComplexMatrix.multiplyByScalar(matrix, 2);

                assertEquals(new Complex(2, 2), result.get(0, 0));
                assertEquals(new Complex(4, 4), result.get(0, 1));
                assertEquals(new Complex(6, 6), result.get(1, 0));
                assertEquals(new Complex(8, 8), result.get(1, 1));
        }

        @Test
        void testMultiplyByScalarWithZero() {
                Complex[][] data = {
                                { new Complex(1, 1), new Complex(2, 2) },
                                { new Complex(3, 3), new Complex(4, 4) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix result = ComplexMatrix.multiplyByScalar(matrix, 0);

                assertEquals(new Complex(0, 0), result.get(0, 0));
                assertEquals(new Complex(0, 0), result.get(0, 1));
                assertEquals(new Complex(0, 0), result.get(1, 0));
                assertEquals(new Complex(0, 0), result.get(1, 1));
        }

        @Test
        void testMultiplyByScalarWithNegativeValue() {
                Complex[][] data = {
                                { new Complex(1, 1), new Complex(2, 2) },
                                { new Complex(3, 3), new Complex(4, 4) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);
                ComplexMatrix result = ComplexMatrix.multiplyByScalar(matrix, -1);

                assertEquals(new Complex(-1, -1), result.get(0, 0));
                assertEquals(new Complex(-2, -2), result.get(0, 1));
                assertEquals(new Complex(-3, -3), result.get(1, 0));
                assertEquals(new Complex(-4, -4), result.get(1, 1));
        }

        @Test
        void testDeterminantOf4x4Matrix() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0) },
                                { new Complex(5, 0), new Complex(6, 0), new Complex(7, 0), new Complex(8, 0) },
                                { new Complex(9, 0), new Complex(10, 0), new Complex(11, 0), new Complex(12, 0) },
                                { new Complex(13, 0), new Complex(14, 0), new Complex(15, 0), new Complex(16, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.determinant(matrix),
                                "Determinant of singular matrix should throw exception.");
        }

        @Test
        void testDeterminantOf5x5Matrix() {
                Complex[][] data = {
                                { new Complex(1, 0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0),
                                                new Complex(5, 0) },
                                { new Complex(6, 0), new Complex(7, 0), new Complex(8, 0), new Complex(9, 0),
                                                new Complex(10, 0) },
                                { new Complex(11, 0), new Complex(12, 0), new Complex(13, 0), new Complex(14, 0),
                                                new Complex(15, 0) },
                                { new Complex(16, 0), new Complex(17, 0), new Complex(18, 0), new Complex(19, 0),
                                                new Complex(20, 0) },
                                { new Complex(21, 0), new Complex(22, 0), new Complex(23, 0), new Complex(24, 0),
                                                new Complex(25, 0) }
                };

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.determinant(matrix),
                                "Determinant of singular matrix should throw exception.");
        }

        @Test
        void testDeterminantOf8x8Matrix() {
                Complex[][] data = new Complex[8][8];
                for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                                data[i][j] = new Complex(i + j + 1, 0);
                        }
                }

                ComplexMatrix matrix = new ComplexMatrix(data);

                assertThrows(IllegalArgumentException.class, () -> ComplexMatrix.determinant(matrix),
                                "Determinant of singular matrix should throw exception.");
        }

}