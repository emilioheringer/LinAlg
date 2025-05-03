package com.heringer.linalg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class VectorTest {

    @Test
    void testSum() {
        double[] vector1 = {1.0, 2.0, 3.0};
        double[] vector2 = {4.0, 5.0, 6.0};
        Vector result = Vector.sum(vector1, vector2);
        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, result.getVector());
    }

    @Test
    void testAdd() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        vector1.add(vector2);
        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, vector1.getVector());
    }

    @Test
    void testSubtraction() {
        double[] vector1 = {5.0, 7.0, 9.0};
        double[] vector2 = {1.0, 2.0, 3.0};
        Vector result = Vector.subtraction(vector1, vector2);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0}, result.getVector());
    }

    @Test
    void testSubtract() {
        Vector vector1 = new Vector(new double[]{5.0, 7.0, 9.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        vector1.subtract(vector2);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0}, vector1.getVector());
    }

    @Test
    void testMultiplyByScalarArray() {
        double[] vector = {1.0, 2.0, 3.0};
        double scalar = 2.0;
        Vector result = Vector.multiplyByScalar(vector, scalar);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, result.getVector());
    }

    @Test
    void testMultiplyByScalarVector() {
        Vector vector = new Vector(new double[]{1.0, 2.0, 3.0});
        double scalar = 2.0;
        Vector result = Vector.multiplyByScalar(vector, scalar);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, result.getVector());
    }

    @Test
    void testMultiplyByScalarElementWise() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = vector1.multiplyByScalar(vector2);
        assertArrayEquals(new double[]{4.0, 10.0, 18.0}, result.getVector());
    }

    @Test
    void testSumWithEmptyInput() {
        Vector result = Vector.sum();
        assertArrayEquals(new double[]{}, result.getVector());
    }

    @Test
    void testSubtractionWithEmptyInput() {
        Vector result = Vector.subtraction();
        assertArrayEquals(new double[]{}, result.getVector());
    }

    @Test
    void testSumWithDifferentLengths() {
        double[] vector1 = {1.0, 2.0};
        double[] vector2 = {1.0, 2.0, 3.0};
        assertThrows(IllegalArgumentException.class, () -> Vector.sum(vector1, vector2));
    }

    @Test
    void testSubtractionWithDifferentLengths() {
        double[] vector1 = {1.0, 2.0};
        double[] vector2 = {1.0, 2.0, 3.0};
        assertThrows(IllegalArgumentException.class, () -> Vector.subtraction(vector1, vector2));
    }

    @Test
    void testAddWithDifferentLengths() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.add(vector2));
    }

    @Test
    void testSubtractWithDifferentLengths() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.subtract(vector2));
    }

    @Test
    void testDotStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        double result = Vector.dot(vector1, vector2);
        assertEquals(32.0, result);
    }

    @Test
    void testDotInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        double result = vector1.dot(vector2);
        assertEquals(32.0, result);
    }

    @Test
    void testDotWithDifferentLengthsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertThrows(IllegalArgumentException.class, () -> Vector.dot(vector1, vector2));
    }

    @Test
    void testDotWithDifferentLengthsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.dot(vector2));
    }

    @Test
    void testCrossStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{-3.0, 6.0, -3.0}, result.getVector());
    }

    @Test
    void testCrossInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = vector1.cross(vector2);
        assertArrayEquals(new double[]{-3.0, 6.0, -3.0}, result.getVector());
    }

    @Test
    void testCrossWithNon3DVectorsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        assertThrows(IllegalArgumentException.class, () -> Vector.cross(vector1, vector2));
    }

    @Test
    void testCrossWithNon3DVectorsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.cross(vector2));
    }

    @Test
    void testNormStatic() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        double result = Vector.norm(vector);
        assertEquals(5.0, result);
    }

    @Test
    void testNormInstance() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        double result = vector.getNorm();
        assertEquals(5.0, result);
    }

    @Test
    void testMagnitudeStatic() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        double result = Vector.magnitude(vector);
        assertEquals(5.0, result);
    }

    @Test
    void testMagnitudeInstance() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        double result = vector.getMagnitude();
        assertEquals(5.0, result);
    }

    @Test
    void testEmptyVectorNorm() {
        Vector vector = new Vector(new double[]{});
        double result = vector.getNorm();
        assertEquals(0.0, result);
    }

    @Test
    void testEmptyVectorMagnitude() {
        Vector vector = new Vector(new double[]{});
        double result = vector.getMagnitude();
        assertEquals(0.0, result);
    }

    @Test
    void testCrossWith3DVectorsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0, 0.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 1.0}, result.getVector());
    }

    @Test
    void testCrossWith3DVectorsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0, 0.0});
        Vector result = vector1.cross(vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 1.0}, result.getVector());
    }

    @Test
    void testCrossWithZeroVectorStatic() {
        Vector vector1 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testCrossWithZeroVectorInstance() {
        Vector vector1 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector result = vector1.cross(vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testNormalizeStatic() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        Vector result = Vector.normalize(vector);
        assertArrayEquals(new double[]{0.6, 0.8}, result.getVector(), 1e-9);
    }

    @Test
    void testNormalizeInstance() {
        Vector vector = new Vector(new double[]{3.0, 4.0});
        Vector result = vector.normalize();
        assertArrayEquals(new double[]{0.6, 0.8}, result.getVector(), 1e-9);
    }

    @Test
    void testNormalizeZeroVectorStatic() {
        Vector vector = new Vector(new double[]{0.0, 0.0, 0.0});
        assertThrows(IllegalArgumentException.class, () -> Vector.normalize(vector));
    }

    @Test
    void testNormalizeZeroVectorInstance() {
        Vector vector = new Vector(new double[]{0.0, 0.0, 0.0});
        assertThrows(IllegalArgumentException.class, vector::normalize);
    }

    @Test
    void testEmptyVectorSum() {
        Vector vector1 = new Vector(new double[]{});
        Vector vector2 = new Vector(new double[]{});
        Vector result = Vector.sum(vector1.getVector(), vector2.getVector());
        assertArrayEquals(new double[]{}, result.getVector());
    }

    @Test
    void testEmptyVectorSubtraction() {
        Vector vector1 = new Vector(new double[]{});
        Vector vector2 = new Vector(new double[]{});
        Vector result = Vector.subtraction(vector1.getVector(), vector2.getVector());
        assertArrayEquals(new double[]{}, result.getVector());
    }

    @Test
    void testEmptyVectorDotProduct() {
        Vector vector1 = new Vector(new double[]{});
        Vector vector2 = new Vector(new double[]{});
        double result = Vector.dot(vector1, vector2);
        assertEquals(0.0, result);
    }

    @Test
    void testEmptyVectorCrossProduct() {
        Vector vector1 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testNormalizeWithNegativeValues() {
        Vector vector = new Vector(new double[]{-3.0, -4.0});
        Vector result = vector.normalize();
        assertArrayEquals(new double[]{-0.6, -0.8}, result.getVector(), 1e-9);
    }

    @Test
    void testMagnitudeWithNegativeValues() {
        Vector vector = new Vector(new double[]{-3.0, -4.0});
        double result = vector.getMagnitude();
        assertEquals(5.0, result);
    }

    @Test
    void testNormWithNegativeValues() {
        Vector vector = new Vector(new double[]{-3.0, -4.0});
        double result = vector.getNorm();
        assertEquals(5.0, result);
    }

    @Test
    void testCrossProductWithParallelVectors() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{2.0, 4.0, 6.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testCrossProductWithAntiParallelVectors() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{-2.0, -4.0, -6.0});
        Vector result = Vector.cross(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testProjectStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = Vector.project(vector1, vector2);
        assertArrayEquals(new double[]{1.662337662337662, 2.077922077922078, 2.4935064935064934}, result.getVector(), 1e-9);
    }

    @Test
    void testProjectInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = vector1.project(vector2);
        assertArrayEquals(new double[]{1.662337662337662, 2.077922077922078, 2.4935064935064934}, result.getVector(), 1e-9);
    }

    @Test
    void testProjectOntoZeroVectorStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{0.0, 0.0, 0.0});
        assertThrows(IllegalArgumentException.class, () -> Vector.project(vector1, vector2));
    }

    @Test
    void testProjectOntoZeroVectorInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{0.0, 0.0, 0.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.project(vector2));
    }

    @Test
    void testProjectWithZeroVectorStatic() {
        Vector vector1 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = Vector.project(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }

    @Test
    void testProjectWithZeroVectorInstance() {
        Vector vector1 = new Vector(new double[]{0.0, 0.0, 0.0});
        Vector vector2 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector result = vector1.project(vector2);
        assertArrayEquals(new double[]{0.0, 0.0, 0.0}, result.getVector());
    }
    @Test
    void testAngleStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0});
        double result = Vector.angle(vector1, vector2);
        assertEquals(Math.PI / 2, result, 1e-9);
    }

    @Test
    void testAngleInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0});
        double result = vector1.angle(vector2);
        assertEquals(Math.PI / 2, result, 1e-9);
    }

    @Test
    void testAngleWithZeroVectorStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 0.0});
        assertThrows(IllegalArgumentException.class, () -> Vector.angle(vector1, vector2));
    }

    @Test
    void testAngleWithZeroVectorInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 0.0});
        assertThrows(IllegalArgumentException.class, () -> vector1.angle(vector2));
    }

    @Test
    void testAngleWithParallelVectorsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{2.0, 4.0, 6.0});
        double result = Vector.angle(vector1, vector2);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testAngleWithParallelVectorsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{2.0, 4.0, 6.0});
        double result = vector1.angle(vector2);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testAngleWithAntiParallelVectorsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{-1.0, -2.0, -3.0});
        double result = Vector.angle(vector1, vector2);
        assertEquals(Math.PI, result, 1e-9);
    }

    @Test
    void testAngleWithAntiParallelVectorsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector2 = new Vector(new double[]{-1.0, -2.0, -3.0});
        double result = vector1.angle(vector2);
        assertEquals(Math.PI, result, 1e-9);
    }

    @Test
    void testNormalizeWithLargeValues() {
        Vector vector = new Vector(new double[]{1e9, 1e9});
        Vector result = vector.normalize();
        assertArrayEquals(new double[]{Math.sqrt(0.5), Math.sqrt(0.5)}, result.getVector(), 1e-9);
    }

    @Test
    void testProjectWithOrthogonalVectorsStatic() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0});
        Vector result = Vector.project(vector1, vector2);
        assertArrayEquals(new double[]{0.0, 0.0}, result.getVector(), 1e-9);
    }

    @Test
    void testProjectWithOrthogonalVectorsInstance() {
        Vector vector1 = new Vector(new double[]{1.0, 0.0});
        Vector vector2 = new Vector(new double[]{0.0, 1.0});
        Vector result = vector1.project(vector2);
        assertArrayEquals(new double[]{0.0, 0.0}, result.getVector(), 1e-9);

    }

    @Test
    void testStaticAngleBetweenOrthogonalVectors() {
        Vector v1 = new Vector(new double[]{1, 0});
        Vector v2 = new Vector(new double[]{0, 1});
        double angle = Vector.angle(v1, v2);
        assertEquals(Math.PI / 2, angle, 1e-9);
    }

    @Test
    void testStaticAngleBetweenSameVectors() {
        Vector v1 = new Vector(new double[]{1, 1});
        double angle = Vector.angle(v1, v1);
        assertEquals(0.0, angle, 1e-7);
    }

    @Test
    void testStaticAngleWithZeroVectorThrowsException() {
        Vector v1 = new Vector(new double[]{0, 0});
        Vector v2 = new Vector(new double[]{1, 0});
        assertThrows(IllegalArgumentException.class, () -> Vector.angle(v1, v2));
    }

    @Test
    void testInstanceAngleBetweenOrthogonalVectors() {
        Vector v1 = new Vector(new double[]{1, 0});
        Vector v2 = new Vector(new double[]{0, 1});
        double angle = v1.angle(v2);
        assertEquals(Math.PI / 2, angle, 1e-9);
    }

    @Test
    void testInstanceAngleBetweenSameVectors() {
        Vector v1 = new Vector(new double[]{2, 2});
        double angle = v1.angle(v1);
        assertEquals(0.0, angle, 1e-7);
    }

    @Test
    void testInstanceAngleWithZeroVectorThrowsException() {
        Vector v1 = new Vector(new double[]{1, 0});
        Vector v2 = new Vector(new double[]{0, 0});
        assertThrows(IllegalArgumentException.class, () -> v1.angle(v2));
    }

    @Test
    void testIsLinearlyIndependentWithEmptyArray() {
        Vector[] vectors = {};
        assertTrue(Vector.isLinearlyIndependent(vectors));
    }

    @Test
    void testIsLinearlyIndependentWithSingleVector() {
        Vector[] vectors = {new Vector(new double[]{1.0, 2.0, 3.0})};
        assertTrue(Vector.isLinearlyIndependent(vectors));
    }

    @Test
    void testIsLinearlyIndependentWithLinearlyIndependentVectors() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0, 0.0}),
            new Vector(new double[]{0.0, 1.0, 0.0}),
            new Vector(new double[]{0.0, 0.0, 1.0})
        };
        assertTrue(Vector.isLinearlyIndependent(vectors));
    }

    @Test
    void testIsLinearlyIndependentWithLinearlyDependentVectors() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 2.0, 3.0}),
            new Vector(new double[]{2.0, 4.0, 6.0}),
            new Vector(new double[]{3.0, 6.0, 9.0})
        };
        assertFalse(Vector.isLinearlyIndependent(vectors));
    }

    @Test
    void testIsLinearlyIndependentWithDifferentLengths() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 2.0}),
            new Vector(new double[]{1.0, 2.0, 3.0})
        };
        assertThrows(IllegalArgumentException.class, () -> Vector.isLinearlyIndependent(vectors));
    }

    @Test
    void testIsLinearlyDependentWithEmptyArray() {
        Vector[] vectors = {};
        assertFalse(Vector.isLinearlyDependent(vectors));
    }

    @Test
    void testIsLinearlyDependentWithSingleVector() {
        Vector[] vectors = {new Vector(new double[]{1.0, 2.0, 3.0})};
        assertTrue(Vector.isLinearlyIndependent(vectors));
        assertFalse(Vector.isLinearlyDependent(vectors));
    }

    @Test
    void testIsLinearlyDependentWithLinearlyIndependentVectors() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0, 0.0}),
            new Vector(new double[]{0.0, 1.0, 0.0}),
            new Vector(new double[]{0.0, 0.0, 1.0})
        };
        assertFalse(Vector.isLinearlyDependent(vectors));
    }

    @Test
    void testIsLinearlyDependentWithLinearlyDependentVectors() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 2.0, 3.0}),
            new Vector(new double[]{2.0, 4.0, 6.0}),
            new Vector(new double[]{3.0, 6.0, 9.0})
        };
        assertTrue(Vector.isLinearlyDependent(vectors));
    }

    @Test
    void testIsLinearlyDependentWithDifferentLengths() {
        Vector[] vectors = {
            new Vector(new double[]{1.0, 2.0}),
            new Vector(new double[]{1.0, 2.0, 3.0})
        };
        assertThrows(IllegalArgumentException.class, () -> Vector.isLinearlyDependent(vectors));
    }




}





