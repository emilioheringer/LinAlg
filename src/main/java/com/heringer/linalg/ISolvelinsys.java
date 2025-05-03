package com.heringer.linalg;

/**
 * Interface for solving linear systems of equations.
 */
public interface ISolvelinsys {

    /**
     * Solves a linear system of equations using Gaussian Elimination.
     *
     * @param augmentedMatrix The augmented matrix representing the linear system.
     * @return The resulting matrix after applying Gaussian Elimination.
     */
    static Matrix gaussianElimination(Matrix augmentedMatrix) {
        return null;
    }

    /**
     * Classifies the type of a linear system based on its augmented matrix.
     *
     * @param augmentedMatrix The augmented matrix representing the linear system.
     * @return A string describing the classification of the system (e.g., consistent, inconsistent, etc.).
     */
    static String classifySystem(Matrix augmentedMatrix) {
        return null;
    }
}
