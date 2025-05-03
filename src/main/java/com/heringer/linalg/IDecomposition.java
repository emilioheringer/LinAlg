package com.heringer.linalg;

import org.ejml.simple.SimpleEVD;
import org.ejml.simple.SimpleMatrix;

/**
 * Interface for various matrix decomposition methods.
 * Provides static utility methods for performing common linear algebra operations
 * such as orthogonality checks, Gram-Schmidt process, and matrix decompositions.
 * 
 * <p>Decomposition methods include:
 * <ul>
 *   <li>QR Decomposition</li>
 *   <li>LU Decomposition</li>
 *   <li>Spectral Decomposition</li>
 * </ul>
 * </p>
 * 
 * <p>Note: The methods in this interface are currently placeholders and need to be implemented.</p>
 * 
 * @author Emílio Heringer
 */
public interface IDecomposition {
    static boolean isOrthogonal(Vector a, Vector b) {
        return false;
    }

    static Vector[] gramSchmidt(Vector[] vectors) {
        return new Vector[0];
    }

    static Matrix qrDecomposition(Matrix matrix) {
        return null;
    }

    static Matrix luDecomposition(Matrix matrix) {
        return null;
    }

    static SimpleEVD<SimpleMatrix> spectralDecomposition(Matrix matrix) {
        return null;
    }
}
