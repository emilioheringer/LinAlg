package com.heringer.linalg;

/**
 * Interface representing a mathematical vector and providing various operations
 * for vector manipulation and computation.
 * 
 * <p>This interface defines methods for basic vector operations such as addition,
 * subtraction, scalar multiplication, dot product, cross product, normalization,
 * projection, and angle calculation. It also includes static utility methods for
 * performing these operations on arrays or other vector instances.</p>
 * 
 * <p>Additionally, it provides methods for determining linear independence,
 * dependency, and rank of a set of vectors.</p>
 * 
 * <p>Implementations of this interface should define how vectors are represented
 * and how these operations are performed.</p>
 * 
 * @author Emílio Heringer
 */
public interface IVector {
    double[] getVector();

    void add(Vector other);

    void subtract(Vector other);

    Vector multiplyByScalar(Vector other);

    double dot(Vector other);

    Vector cross(Vector other);

    double getNorm();

    double getMagnitude();

    Vector normalize();

    Vector project(Vector other);

    double angle(Vector other);

    static Vector sum(double[]... vectors) {
        return null;
    }

    static Vector subtraction(double[]... vectors) {
        return null;
    }

    static Vector multiplyByScalar(double[] vector, double scalar) {
        return null;
    }

    static Vector multiplyByScalar(Vector vector, double scalar) {
        return null;
    }

    static double dot(Vector vector1, Vector vector2) {
        return 0;
    }

    static Vector cross(Vector vector1, Vector vector2) {
        return null;
    }

    static double norm(Vector vector) {
        return 0;
    }

    static double magnitude(Vector vector) {
        return 0;
    }

    static Vector normalize(Vector vector) {
        return null;
    }

    static Vector project(Vector vector1, Vector vector2) {
        return null;
    }

    static double angle(Vector vector1, Vector vector2) {
        return 0;
    }

    static boolean isLinearlyIndependent(Vector[] vectors) {
        return false;
    }

    static boolean isLinearlyDependent(Vector[] vectors) {
        return false;
    }

    static int rank(Vector[] vectors) {
        return 0;
    }
}
