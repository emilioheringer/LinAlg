package com.heringer.linalg;

import org.ejml.simple.SimpleMatrix;
import org.ejml.data.Complex_F64;
import org.ejml.simple.SimpleEVD;

/**
 * The {@code Eigenvalues} class provides static methods for computing the eigenvalues
 * and eigenvectors of a given matrix. This class is abstract and implements the {@code IEigenvalues} interface.
 * 
 * <p>It uses the SimpleMatrix and SimpleEVD classes from the EJML library to perform
 * the eigen decomposition of matrices.</p>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #eigenvalues(double[][])}: Computes the eigenvalues of a given matrix.</li>
 *   <li>{@link #eig(double[][])}: Computes the eigenvectors of a given matrix.</li>
 * </ul>
 * 
 * @author Emílio Heringer
 */
public abstract class Eigenvalues implements IEigenvalues {


    public static Complex_F64[] eigenvalues(double[][] A) {
        SimpleMatrix matriz = new SimpleMatrix(A);
        SimpleEVD<SimpleMatrix> evd = matriz.eig();
        Complex_F64[] resultados = new Complex_F64[evd.getNumberOfEigenvalues()];

        for (int i = 0; i < evd.getNumberOfEigenvalues(); i++) {
            resultados[i] = evd.getEigenvalue(i);
        }

        return resultados;
    }

    public static SimpleMatrix[] eig(double[][] A) {
        SimpleMatrix matrix = new SimpleMatrix(A);
        SimpleEVD<SimpleMatrix> evd = matrix.eig();
    
        SimpleMatrix[] autovetores = new SimpleMatrix[evd.getNumberOfEigenvalues()];
    
        for (int i = 0; i < autovetores.length; i++) {
            if (evd.getEigenVector(i) != null) {
                autovetores[i] = evd.getEigenVector(i);
            } else {
                autovetores[i] = new SimpleMatrix(matrix.getNumRows(), 1);
            }
        }
    
        return autovetores;
    }
    
}
