package com.heringer.linalg;

/**
 * Represents a vector space and provides methods to perform operations on vectors within that space.
 * This class includes functionalities to check if a set of vectors forms a subspace,
 * compute linear combinations, find a base for a set of vectors, determine the dimension
 * of a vector space, change the base of vectors, and compute relative coordinates.
 *
 * @author Emílio Heringer
 */
public class VectorSpace implements IVectorSpace {

  private static final double TOLERANCE = 1e-6;

  /**
   * Checks if a given set of vectors forms a subspace.
   * A subspace must contain the zero vector and be closed under addition and scalar multiplication.
   *
   * @param vectors An array of Vector objects to check.
   * @return true if the set of vectors forms a subspace; false otherwise.
   */
  public boolean isSubspace(Vector[] vectors) {
    if (vectors == null || vectors.length == 0)
      return false;

    int dimension = vectors[0].getVector().length;
    for (Vector v : vectors)
      if (v.getVector().length != dimension)
        return false;

    if (!containsZeroVector(vectors))
      return false;

    // Fechamento sob adição
    for (Vector v1 : vectors) {
      for (Vector v2 : vectors) {
        Vector sum = Vector.sum(v1.getVector(), v2.getVector());
        if (!containsVector(vectors, sum))
          return false;
      }
    }

    // Fechamento sob multiplicação escalar apenas para -1, 0, 1
    double[] testScalars = { -1.0, 0.0, 1.0 };
    for (Vector v : vectors) {
      for (double k : testScalars) {
        Vector prod = Vector.multiplyByScalar(v.getVector(), k);
        if (!containsVector(vectors, prod))
          return false;
      }
    }

    return true;
  }

  /**
   * Checks if the given set of vectors contains the zero vector.
   *
   * @param vectors An array of Vector objects to check.
   * @return true if the set contains the zero vector; false otherwise.
   */
  private boolean containsZeroVector(Vector[] vectors) {
    for (Vector v : vectors)
      if (isZeroVector(v))
        return true;
    return false;
  }

  /**
   * Checks if a given vector is a zero vector.
   * A zero vector is a vector where all components are zero (within a specified tolerance).
   *
   * @param v The Vector object to check.
   * @return true if the vector is a zero vector; false otherwise.
   */
  private boolean isZeroVector(Vector v) {
    for (double d : v.getVector())
      if (Math.abs(d) > TOLERANCE)
        return false;
    return true;
  }

  /**
   * Checks if a given vector is contained within a set of vectors.
   *
   * @param vectors An array of Vector objects to check.
   * @param target The Vector object to search for.
   * @return true if the target vector is found within the set; false otherwise.
   */
  private boolean containsVector(Vector[] vectors, Vector target) {
    for (Vector v : vectors)
      if (v.equals(target))
        return true;
    return false;
  }

  /**
   * Computes a linear combination of a set of vectors using given scalars.
   *
   * @param vectors An array of Vector objects to combine.
   * @param scalars An array of double values representing the scalars for each vector.
   * @return A new Vector object representing the linear combination.
   * @throws IllegalArgumentException if the input is invalid (null vectors or scalars, or mismatched lengths).
   */
  public Vector linearCombination(Vector[] vectors, double[] scalars) {
    if (vectors == null || scalars == null || vectors.length != scalars.length)
      throw new IllegalArgumentException("Invalid input");

    int dimension = vectors[0].getVector().length;
    for (Vector v : vectors)
      if (v.getVector().length != dimension)
        throw new IllegalArgumentException("Invalid input");

    double[] result = new double[dimension];
    for (int i = 0; i < vectors.length; i++) {
      for (int j = 0; j < dimension; j++) {
        result[j] += scalars[i] * vectors[i].getVector()[j];
      }
    }
    return new Vector(result);
  }

  /**
   * Finds a base (basis) for a given set of vectors.
   * A base is a set of linearly independent vectors that span the same vector space.
   *
   * @param vectors An array of Vector objects to find the base for.
   * @return An array of Vector objects representing the base.
   */
  public Vector[] getBase(Vector[] vectors) {
    if (vectors == null || vectors.length == 0)
      return new Vector[0];

    int dimension = vectors[0].getVector().length;
    for (Vector v : vectors)
      if (v.getVector().length != dimension)
        return new Vector[0];

    Vector[] base = new Vector[vectors.length];
    int baseSize = 0;

    for (Vector v : vectors) {
      Vector[] temp = new Vector[baseSize + 1];
      System.arraycopy(base, 0, temp, 0, baseSize);
      temp[baseSize] = v;
      if (Vector.rank(temp) > baseSize) {
        base[baseSize++] = v;
      }
    }

    Vector[] result = new Vector[baseSize];
    System.arraycopy(base, 0, result, 0, baseSize);
    return result;

  }

  /**
   * Gets the dimension of a vector space spanned by a given set of vectors.
   * The dimension is the number of vectors in a base for the space.
   *
   * @param vectors An array of Vector objects that span the vector space.
   * @return The dimension of the vector space.
   */
  public int getDimension(Vector[] vectors) {
    if (vectors == null || vectors.length == 0)
      return 0;

    int dimension = vectors[0].getVector().length;
    for (Vector v : vectors)
      if (v.getVector().length != dimension)
        return 0;

    return Vector.rank(vectors);
  }

  /**
   * Changes the base of a set of vectors to a new base.
   * This method expresses the given vectors in terms of the new base vectors.
   *
   * @param vectors An array of Vector objects to change the base of.
   * @param newBase An array of Vector objects representing the new base.
   * @return An array of Vector objects representing the vectors in the new base.
   * @throws IllegalArgumentException if the input is invalid (null vectors or newBase, empty vectors or newBase, or mismatched dimensions).
   */
  public Vector[] changeBase(Vector[] vectors, Vector[] newBase) {
    if (vectors == null || newBase == null) {
      throw new IllegalArgumentException("Vectors and newBase cannot be null.");
  }
  if (vectors.length == 0 || newBase.length == 0) {
      throw new IllegalArgumentException("Vectors and newBase cannot be empty.");
  }
  int dimension = vectors[0].getVector().length;
  for (Vector v : vectors) {
      if (v.getVector().length != dimension) {
          throw new IllegalArgumentException("All vectors must have the same dimension.");
      }
  }
  for (Vector b : newBase) {
      if (b.getVector().length != dimension) {
          throw new IllegalArgumentException("All base vectors must have the same dimension as the input vectors.");
      }
  }

    Vector[] result = new Vector[vectors.length];
    for (int i = 0; i < vectors.length; i++) {
      double[] temp = new double[dimension];
      for (int j = 0; j < dimension; j++) {
        temp[j] += vectors[i].getVector()[j] * newBase[i].getVector()[j];
      }
      result[i] = new Vector(temp);
    }
    return result;
  }

  /**
   * Computes the relative coordinates of a set of vectors with respect to a new base.
   * The relative coordinates are the coefficients needed to express the vectors as a linear combination of the new base vectors.
   *
   * @param vectors An array of Vector objects to compute the relative coordinates of.
   * @param newBase An array of Vector objects representing the new base.
   * @return An array of Vector objects representing the relative coordinates.
   * @throws IllegalArgumentException if the input is invalid (null vectors or newBase, empty vectors or newBase, or mismatched dimensions).
   */
  public Vector[] relativeCoordinates(Vector[] vectors, Vector[] newBase) {
    if (vectors == null || newBase == null) {
      throw new IllegalArgumentException("Vectors and newBase cannot be null.");
  }
  if (vectors.length == 0 || newBase.length == 0) {
      throw new IllegalArgumentException("Vectors and newBase cannot be empty.");
  }
  int dimension = vectors[0].getVector().length;
  for (Vector v : vectors) {
      if (v.getVector().length != dimension) {
          throw new IllegalArgumentException("All vectors must have the same dimension.");
      }
  }
  for (Vector b : newBase) {
      if (b.getVector().length != dimension) {
          throw new IllegalArgumentException("All base vectors must have the same dimension as the input vectors.");
      }
  }

    Vector[] result = new Vector[vectors.length];
    for (int i = 0; i < vectors.length; i++) {
      double[] temp = new double[dimension];
      for (int j = 0; j < dimension; j++) {
        temp[j] += vectors[i].getVector()[j] * newBase[i].getVector()[j];
      }
      result[i] = new Vector(temp);
    }
    return result;
  }

  /**
   * Computes the relative coordinates of a set of vectors with respect to a new base, using given scalars.
   * This method is similar to relativeCoordinates(Vector[], Vector[]), but it also takes an array of scalars as input.
   *
   * @param vectors An array of Vector objects to compute the relative coordinates of.
   * @param newBase An array of Vector objects representing the new base.
   * @param scalars An array of double values representing the scalars for each vector.
   * @return An array of Vector objects representing the relative coordinates.
   * @throws IllegalArgumentException if the input is invalid (null vectors, newBase, or scalars; empty vectors, newBase, or scalars; or mismatched dimensions).
   */
  public Vector[] relativeCoordinates(Vector[] vectors, Vector[] newBase, double[] scalars) {
    if (vectors == null || newBase == null || scalars == null) {
      throw new IllegalArgumentException("Vectors, newBase and scalars cannot be null.");
  }
  if (vectors.length == 0 || newBase.length == 0 || scalars.length == 0) {
      throw new IllegalArgumentException("Vectors, newBase and scalars cannot be empty.");
  } 
  int dimension = vectors[0].getVector().length;
  for (Vector v : vectors) {
      if (v.getVector().length != dimension) {
          throw new IllegalArgumentException("All vectors must have the same dimension.");
      }
  }
  for (Vector b : newBase) {
      if (b.getVector().length != dimension) {
          throw new IllegalArgumentException("All base vectors must have the same dimension as the input vectors.");
      }
  }

    Vector[] result = new Vector[vectors.length];
    for (int i = 0; i < vectors.length; i++) {
      double[] temp = new double[dimension];
      for (int j = 0; j < dimension; j++) {
        temp[j] += vectors[i].getVector()[j] * newBase[i].getVector()[j] * scalars[i];
      }
      result[i] = new Vector(temp);
    }
    return result;
  }
  
}