package com.heringer.linalg;

/**
 * A utility class for solving linear systems of equations using Gaussian elimination.
 * This class provides static methods to perform Gaussian elimination and classify
 * the system of equations as possible or impossible.
 *
 * @author Emílio Heringer
 */
public abstract class Solvelinsys implements ISolvelinsys {

    /**
     * Solves a system of linear equations using Gaussian elimination.
     * This method takes an augmented matrix representing the system of equations
     * and performs Gaussian elimination to transform it into row-echelon form.
     *
     * @param augmentedMatrix The augmented matrix representing the system of equations.
     * @return A new Matrix representing the row-echelon form of the augmented matrix.
     * @throws IllegalArgumentException if the input matrix is null or empty.
     * @throws IllegalStateException    if the matrix is singular and cannot be solved.
     */
    public static Matrix gaussianElimination(Matrix augmentedMatrix) {
        if (augmentedMatrix == null || augmentedMatrix.getRows() == 0 || augmentedMatrix.getColumns() == 0 || augmentedMatrix.getMatrix() == null) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }

        int rows = augmentedMatrix.getRows();
        int columns = augmentedMatrix.getColumns();
        double[][] data = augmentedMatrix.getMatrix();

        for (int pivot = 0; pivot < rows; pivot++) {
            if (Math.abs(data[pivot][pivot]) < 1e-10) {
                boolean swapped = false;
                for (int i = pivot + 1; i < rows; i++) {
                    if (Math.abs(data[i][pivot]) > 1e-10) {
                        double[] temp = data[pivot];
                        data[pivot] = data[i];
                        data[i] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    throw new IllegalStateException("Matrix is singular and cannot be solved.");
                }
            }

            double pivotValue = data[pivot][pivot];
            for (int col = pivot; col < columns; col++) {
                data[pivot][col] /= pivotValue;
            }

            for (int row = pivot + 1; row < rows; row++) {
                double factor = data[row][pivot];
                for (int col = pivot; col < columns; col++) {
                    data[row][col] -= factor * data[pivot][col];
                }
            }
        }

        return new Matrix(data);
    }

    /**
     * Classifies a system of linear equations as possible or impossible.
     * This method analyzes the augmented matrix representing the system of equations
     * to determine if the system has a solution or if it is inconsistent.
     *
     * @param augmentedMatrix The augmented matrix representing the system of equations.
     * @return "Possible" if the system has a solution, "Impossible" if the system is inconsistent.
     * @throws IllegalArgumentException if the input matrix is null or empty.
     */
    public static String classifySystem(Matrix augmentedMatrix) {
        if (augmentedMatrix == null || augmentedMatrix.getRows() == 0 || augmentedMatrix.getColumns() == 0 || augmentedMatrix.getMatrix() == null) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }

        int rows = augmentedMatrix.getRows();
        int columns = augmentedMatrix.getColumns();
        double[][] data = augmentedMatrix.getMatrix();

        for (int i = 0; i < rows; i++) {
            boolean allZero = true;
            for (int j = 0; j < columns - 1; j++) {
                if (Math.abs(data[i][j]) > 1e-10) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && Math.abs(data[i][columns - 1]) > 1e-10) {
                return "Impossible";
            }
        }

        return "Possible";
    }
}
