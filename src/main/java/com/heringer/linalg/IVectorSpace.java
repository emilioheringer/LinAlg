package com.heringer.linalg;

/**
 * Interface representing a vector space with operations for subspace checking,
 * linear combinations, base transformations, and relative coordinates.
 * 
 * @author Emílio Heringer
 */
public interface IVectorSpace {
    boolean isSubspace(Vector[] vectors);

    Vector linearCombination(Vector[] vectors, double[] scalars);

    Vector[] getBase(Vector[] vectors);

    int getDimension(Vector[] vectors);

    Vector[] changeBase(Vector[] vectors, Vector[] newBase);

    Vector[] relativeCoordinates(Vector[] vectors, Vector[] newBase);

    Vector[] relativeCoordinates(Vector[] vectors, Vector[] newBase, double[] scalars);
}
