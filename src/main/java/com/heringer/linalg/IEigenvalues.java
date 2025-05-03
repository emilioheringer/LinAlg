package com.heringer.linalg;

import org.ejml.data.Complex_F64;
import org.ejml.simple.SimpleMatrix;

/**
 * Interface for computing eigenvalues and eigenvectors of a matrix.
 * Provides static methods for obtaining eigenvalues and eigenvectors
 * of a given square matrix.
 * 
 * <p>Eigenvalues and eigenvectors are fundamental concepts in linear algebra
 * and are widely used in various fields such as physics, engineering, and
 * computer science.</p>
 * 
 * <p>Note: The methods in this interface are static and do not require
 * an instance of the interface to be invoked.</p>
 * 
 * @author Emílio Heringer
 */
public interface IEigenvalues {
    static Complex_F64[] eigenvalues(double[][] A) {
        return new Complex_F64[0];
    }

    static SimpleMatrix[] eig(double[][] A) {
        return new SimpleMatrix[0];
    }
}
