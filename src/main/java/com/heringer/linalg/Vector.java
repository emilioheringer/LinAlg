package com.heringer.linalg;

import java.util.Arrays;

/**
 * Represents a mathematical vector with double-precision floating-point elements.
 * This class provides basic vector operations such as addition, subtraction,
 * multiplication, dot product, cross product, norm, normalization, projection,
 * angle calculation, and linear dependency checks.
 *
 * @author Emílio Heringer
 */
public class Vector implements IVector {
    private static final double TOLERANCE = 1e-6;

    double[] vector;

    /**
     * Constructs an empty Vector.
     * Initializes a new vector with no elements.
     */
    public Vector() {

    }

    /**
     * Constructs a Vector from an array of doubles.
     * Initializes a new vector with the provided array of double-precision
     * floating-point elements.
     *
     * @param vector The array of doubles to initialize the vector.
     */
    public Vector(double[] vector) {
        this.vector = vector;
    }

    /**
     * Gets the array representing the vector elements.
     *
     * @return The array of doubles representing the vector.
     */
    public double[] getVector() {
        return vector;
    }

    /**
     * Computes the sum of multiple vectors.
     * This is a static method that returns a new Vector containing the sum of the
     * input vectors.
     *
     * @param vectors An array of double arrays representing the vectors to be summed.
     * @return A new Vector that is the sum of the input vectors.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public static Vector sum(double[]... vectors) {
        if (vectors.length == 0) {
            return new Vector(new double[0]);
        }

        int length = vectors[0].length;
        for (double[] vector : vectors) {
            if (vector.length != length) {
                throw new IllegalArgumentException("All vectors must have the same length.");
            }
        }

        double[] result = new double[length];
        for (double[] vector : vectors) {
            for (int i = 0; i < length; i++) {
                result[i] += vector[i];
            }
        }
        return new Vector(result);
    }

    /**
     * Adds another Vector to this Vector.
     * This method modifies the current Vector by adding the elements of the other
     * Vector.
     *
     * @param other The Vector to add to this vector.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public void add(Vector other) {
        if (this.vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] += other.vector[i];
        }
    }

    /**
     * Computes the subtraction of multiple vectors.
     * This is a static method that returns a new Vector containing the result of
     * subtracting the input vectors.
     *
     * @param vectors An array of double arrays representing the vectors to be
     *                subtracted.
     * @return A new Vector that is the result of subtracting the input vectors.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public static Vector subtraction(double[]... vectors) {
        if (vectors.length == 0) {
            return new Vector(new double[0]);
        }

        int length = vectors[0].length;
        for (double[] vector : vectors) {
            if (vector.length != length) {
                throw new IllegalArgumentException("All vectors must have the same length.");
            }
        }

        double[] result = vectors[0].clone();
        for (int j = 1; j < vectors.length; j++) {
            for (int i = 0; i < length; i++) {
                result[i] -= vectors[j][i];
            }
        }
        return new Vector(result);

    }

    /**
     * Subtracts another Vector from this Vector.
     * This method modifies the current Vector by subtracting the elements of the
     * other Vector.
     *
     * @param other The Vector to subtract from this vector.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public void subtract(Vector other) {
        if (this.vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] -= other.vector[i];
        }

    }

    /**
     * Multiplies a vector by a scalar value.
     * This is a static method that returns a new Vector containing the result of
     * multiplying the input vector by the scalar.
     *
     * @param vector The array of doubles representing the vector to be multiplied.
     * @param scalar The scalar value to multiply the vector by.
     * @return A new Vector that is the result of the scalar multiplication.
     */
    public static Vector multiplyByScalar(double[] vector, double scalar) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }
        return new Vector(result);

    }

    /**
     * Multiplies a Vector by a scalar value.
     * This is a static method that returns a new Vector containing the result of
     * multiplying the input vector by the scalar.
     *
     * @param vector The Vector to be multiplied.
     * @param scalar The scalar value to multiply the vector by.
     * @return A new Vector that is the result of the scalar multiplication.
     */
    public static Vector multiplyByScalar(Vector vector, double scalar) {
        double[] vectorArray = vector.getVector();
        double[] result = new double[vectorArray.length];
        for (int i = 0; i < vectorArray.length; i++) {
            result[i] = vectorArray[i] * scalar;
        }
        return new Vector(result);
    }

    /**
     * Multiplies this Vector by another Vector element-wise.
     * This method returns a new Vector containing the result of multiplying the
     * elements of this Vector by the elements of the other Vector.
     *
     * @param other The Vector to multiply with this vector.
     * @return A new Vector that is the result of the element-wise multiplication.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public Vector multiplyByScalar(Vector other) {
        if (this.vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        double[] result = new double[this.vector.length];
        for (int i = 0; i < this.vector.length; i++) {
            result[i] = this.vector[i] * other.vector[i];
        }
        return new Vector(result);

    }

    /**
     * Computes the dot product of two vectors.
     * This is a static method that returns the dot product of the input vectors.
     *
     * @param vector1 The first Vector.
     * @param vector2 The second Vector.
     * @return The dot product of the two vectors.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public static double dot(Vector vector1, Vector vector2) {
        if (vector1.vector.length != vector2.vector.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        double result = 0.0;
        for (int i = 0; i < vector1.vector.length; i++) {
            result += vector1.vector[i] * vector2.vector[i];
        }
        return result;
    }

    /**
     * Computes the dot product of this Vector with another Vector.
     * This method returns the dot product of this Vector with the other Vector.
     *
     * @param other The other Vector.
     * @return The dot product of this vector with the other vector.
     * @throws IllegalArgumentException if the vectors have different lengths.
     */
    public double dot(Vector other) {
        if (this.vector.length != other.vector.length) {
            throw new IllegalArgumentException("Vectors must have the same length.");
        }
        double result = 0.0;
        for (int i = 0; i < this.vector.length; i++) {
            result += this.vector[i] * other.vector[i];
        }
        return result;
    }

    /**
     * Computes the cross product of two 3D vectors.
     * This is a static method that returns a new Vector containing the cross product
     * of the input vectors.
     *
     * @param vector1 The first Vector.
     * @param vector2 The second Vector.
     * @return A new Vector that is the cross product of the two vectors.
     * @throws IllegalArgumentException if the vectors are not 3D vectors.
     */
    public static Vector cross(Vector vector1, Vector vector2) {
        if (vector1.vector.length != 3 || vector2.vector.length != 3) {
            throw new IllegalArgumentException("Cross product is only defined for 3D vectors.");
        }
        double[] result = new double[3];
        result[0] = vector1.vector[1] * vector2.vector[2] - vector1.vector[2] * vector2.vector[1];
        result[1] = vector1.vector[2] * vector2.vector[0] - vector1.vector[0] * vector2.vector[2];
        result[2] = vector1.vector[0] * vector2.vector[1] - vector1.vector[1] * vector2.vector[0];
        return new Vector(result);
    }

    /**
     * Computes the cross product of this Vector with another Vector.
     * This method returns a new Vector containing the cross product of this Vector
     * with the other Vector.
     *
     * @param other The other Vector.
     * @return A new Vector that is the cross product of this vector with the other
     * vector.
     * @throws IllegalArgumentException if the vectors are not 3D vectors.
     */
    public Vector cross(Vector other) {
        if (this.vector.length != 3 || other.vector.length != 3) {
            throw new IllegalArgumentException("Cross product is only defined for 3D vectors.");
        }
        double[] result = new double[3];
        result[0] = this.vector[1] * other.vector[2] - this.vector[2] * other.vector[1];
        result[1] = this.vector[2] * other.vector[0] - this.vector[0] * other.vector[2];
        result[2] = this.vector[0] * other.vector[1] - this.vector[1] * other.vector[0];
        return new Vector(result);
    }

    /**
     * Computes the norm (magnitude) of a vector.
     * This is a static method that returns the norm of the input vector.
     *
     * @param vector The Vector to compute the norm of.
     * @return The norm (magnitude) of the vector.
     */
    public static double norm(Vector vector) {
        double sum = 0.0;
        for (double v : vector.vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    /**
     * Computes the magnitude of a vector.
     * This is a static method that returns the magnitude of the input vector.
     *
     * @param vector The Vector to compute the magnitude of.
     * @return The magnitude of the vector.
     */
    public static double magnitude(Vector vector) {
        double sum = 0.0;
        for (double v : vector.vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    /**
     * Gets the norm (magnitude) of this vector.
     *
     * @return The norm (magnitude) of this vector.
     */
    public double getNorm() {
        double sum = 0.0;
        for (double v : this.vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    /**
     * Gets the magnitude of this vector.
     *
     * @return The magnitude of this vector.
     */
    public double getMagnitude() {
        double sum = 0.0;
        for (double v : this.vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    /**
     * Normalizes a vector to unit length.
     * This is a static method that returns a new Vector that is the normalized
     * version of the input vector.
     *
     * @param vector The Vector to normalize.
     * @return A new Vector that is the normalized version of the input vector.
     * @throws IllegalArgumentException if the vector is a zero vector.
     */
    public static Vector normalize(Vector vector) {
        double norm = norm(vector);
        if (norm == 0) {
            throw new IllegalArgumentException("Cannot normalize a zero vector.");
        }
        double[] result = new double[vector.vector.length];
        for (int i = 0; i < vector.vector.length; i++) {
            result[i] = vector.vector[i] / norm;
        }
        return new Vector(result);
    }

    /**
     * Normalizes this vector to unit length.
     * This method returns a new Vector that is the normalized version of this
     * vector.
     *
     * @return A new Vector that is the normalized version of this vector.
     * @throws IllegalArgumentException if the vector is a zero vector.
     */
    public Vector normalize() {
        double norm = getNorm();
        if (norm == 0) {
            throw new IllegalArgumentException("Cannot normalize a zero vector.");
        }
        double[] result = new double[this.vector.length];
        for (int i = 0; i < this.vector.length; i++) {
            result[i] = this.vector[i] / norm;
        }
        return new Vector(result);
    }

    /**
     * Projects one vector onto another.
     * This is a static method that returns a new Vector that is the projection of
     * vector1 onto vector2.
     *
     * @param vector1 The Vector to be projected.
     * @param vector2 The Vector onto which the projection is performed.
     * @return A new Vector that is the projection of vector1 onto vector2.
     * @throws IllegalArgumentException if the vector onto which the projection is
     *                                  performed is a zero vector.
     */
    public static Vector project(Vector vector1, Vector vector2) {
        double dotProduct = dot(vector1, vector2);
        double normSquared = dot(vector2, vector2);
        if (normSquared == 0) {
            throw new IllegalArgumentException("Cannot project onto a zero vector.");
        }
        double scalar = dotProduct / normSquared;
        return multiplyByScalar(vector2, scalar);
    }

    /**
     * Projects this vector onto another vector.
     * This method returns a new Vector that is the projection of this vector onto
     * the other vector.
     *
     * @param other The Vector onto which the projection is performed.
     * @return A new Vector that is the projection of this vector onto the other
     * vector.
     * @throws IllegalArgumentException if the vector onto which the projection is
     *                                  performed is a zero vector.
     */
    public Vector project(Vector other) {
        double dotProduct = dot(this, other);
        double normSquared = dot(other, other);
        if (normSquared == 0) {
            throw new IllegalArgumentException("Cannot project onto a zero vector.");
        }
        double scalar = dotProduct / normSquared;
        return multiplyByScalar(other, scalar);
    }

    /**
     * Calculates the angle between two vectors in radians.
     * This is a static method that returns the angle between the input vectors.
     *
     * @param vector1 The first Vector.
     * @param vector2 The second Vector.
     * @return The angle between the two vectors in radians.
     * @throws IllegalArgumentException if either vector is a zero vector.
     */
    public static double angle(Vector vector1, Vector vector2) {
        double dotProduct = dot(vector1, vector2);
        double norm1 = norm(vector1);
        double norm2 = norm(vector2);
        if (norm1 == 0 || norm2 == 0) {
            throw new IllegalArgumentException("Cannot calculate angle with a zero vector.");
        }
        return Math.acos(dotProduct / (norm1 * norm2));
    }

    /**
     * Calculates the angle between this vector and another vector in radians.
     * This method returns the angle between this vector and the other vector.
     *
     * @param other The other Vector.
     * @return The angle between this vector and the other vector in radians.
     * @throws IllegalArgumentException if either vector is a zero vector.
     */
    public double angle(Vector other) {
        double dotProduct = dot(this, other);
        double norm1 = getNorm();
        double norm2 = other.getNorm();
        if (norm1 == 0 || norm2 == 0) {
            throw new IllegalArgumentException("Cannot calculate angle with a zero vector.");
        }
        return Math.acos(dotProduct / (norm1 * norm2));
    }

    /**
     * Checks if a set of vectors is linearly independent.
     * This is a static method that returns true if the input vectors are linearly
     * independent, and false otherwise.
     *
     * @param vectors An array of Vectors to check for linear independence.
     * @return true if the vectors are linearly independent, false otherwise.
     * @throws IllegalArgumentException if the vectors have different lengths or if
     *                                  the vectors have zero length.
     */
    public static boolean isLinearlyIndependent(Vector[] vectors) {
        if (vectors == null || vectors.length == 0) {
            return true; // Um conjunto vazio de vetores é linearmente independente.
        }

        int length = vectors[0].getVector().length;
        if (length == 0) {
            throw new IllegalArgumentException("Vectors must have non-zero length.");
        }

        for (Vector vector : vectors) {
            if (vector.getVector().length != length) {
                throw new IllegalArgumentException("All vectors must have the same length.");
            }
        }

        int rank = rank(vectors);
        return rank == Math.min(vectors.length, length);
    }

    /**
     * Checks if a set of vectors is linearly dependent.
     * This is a static method that returns true if the input vectors are linearly
     * dependent, and false otherwise.
     *
     * @param vectors An array of Vectors to check for linear dependence.
     * @return true if the vectors are linearly dependent, false otherwise.
     */
    public static boolean isLinearlyDependent(Vector[] vectors) {
        return !isLinearlyIndependent(vectors);
    }

    /**
     * Calculates the rank of a set of vectors.
     * This is a static method that returns the rank of the input vectors.
     *
     * @param vectors An array of Vectors to calculate the rank of.
     * @return The rank of the vectors.
     * @throws IllegalArgumentException if the vectors have different lengths or if
     *                                  the vectors have zero length.
     */
    public static int rank(Vector[] vectors) {
        if (vectors == null || vectors.length == 0) {
            return 0; // O rank de um conjunto vazio de vetores é 0.
        }

        int length = vectors[0].getVector().length;
        if (length == 0) {
            throw new IllegalArgumentException("Vectors must have non-zero length.");
        }

        for (Vector vector : vectors) {
            if (vector.getVector().length != length) {
                throw new IllegalArgumentException("All vectors must have the same length.");
            }
        }


        double[][] matrixData = new double[vectors.length][length];
        for (int i = 0; i < vectors.length; i++) {
            matrixData[i] = vectors[i].getVector();
        }


        int rows = matrixData.length;
        int columns = matrixData[0].length;
        int rank = 0;

        boolean[] rowUsed = new boolean[rows];

        for (int col = 0; col < columns; col++) {
            int pivotRow = -1;

            // Encontre a linha pivô
            for (int row = 0; row < rows; row++) {
                if (!rowUsed[row] && matrixData[row][col] != 0) {
                    pivotRow = row;
                    break;
                }
            }

            if (pivotRow == -1) {
                continue;
            }

            rowUsed[pivotRow] = true;
            rank++;


            double pivotValue = matrixData[pivotRow][col];
            for (int j = col; j < columns; j++) {
                matrixData[pivotRow][j] /= pivotValue;
            }


            for (int row = 0; row < rows; row++) {
                if (row != pivotRow && matrixData[row][col] != 0) {
                    double factor = matrixData[row][col];
                    for (int j = col; j < columns; j++) {
                        matrixData[row][j] -= factor * matrixData[pivotRow][j];
                    }
                }
            }
        }

        return rank;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relation on non-null object
     * references.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector1 = (Vector) o;
        return Arrays.equals(vector, vector1.vector);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those
     * provided by java.util.HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
    }
}