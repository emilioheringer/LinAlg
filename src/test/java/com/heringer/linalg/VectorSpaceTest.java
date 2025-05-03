package com.heringer.linalg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorSpaceTest {

    @Test
    void returnsFalseForNullInput() {
        assertFalse(new VectorSpace().isSubspace(null));
    }

    @Test
    void returnsFalseForEmptyVectorArray() {
        Vector[] vectors = {};
        assertFalse(new VectorSpace().isSubspace(vectors));
    }

    @Test
    void returnsFalseForVectorsOfDifferentDimensions() {
        Vector[] vectors = {
                new Vector(new double[]{1.0}),
                new Vector(new double[]{1.0, 0.0})
        };
        assertFalse(new VectorSpace().isSubspace(vectors));
    }

    @Test
    void returnsFalseWhenZeroVectorIsMissing() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{-1.0, 0.0})
        };
        assertFalse(new VectorSpace().isSubspace(vectors));
    }



    @Test
    void returnsFalseForNonSubspaceDueToAdditionFailure() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        assertFalse(new VectorSpace().isSubspace(vectors));
    }

    @Test
    void returnsFalseForNonSubspaceDueToScalarMultiplicationFailure() {
        Vector[] vectors = {
                new Vector(new double[]{0.0, 0.0}),
                new Vector(new double[]{1.0, 0.0})
        };
        assertFalse(new VectorSpace().isSubspace(vectors));
    }

    @Test
    void throwsExceptionWhenVectorsAndScalarsAreNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new VectorSpace().linearCombination(null, null));
    }

    @Test
    void throwsExceptionWhenVectorsAndScalarsHaveDifferentLengths() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        double[] scalars = {1.0};
        assertThrows(IllegalArgumentException.class, () ->
                new VectorSpace().linearCombination(vectors, scalars));
    }

    @Test
    void throwsExceptionWhenVectorsHaveDifferentDimensions() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{1.0})
        };
        double[] scalars = {1.0, 2.0};
        assertThrows(IllegalArgumentException.class, () ->
                new VectorSpace().linearCombination(vectors, scalars));
    }

    @Test
    void returnsZeroVectorWhenAllScalarsAreZero() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 2.0}),
                new Vector(new double[]{3.0, 4.0})
        };
        double[] scalars = {0.0, 0.0};
        Vector result = new VectorSpace().linearCombination(vectors, scalars);
        assertEquals(new Vector(new double[]{0.0, 0.0}), result);
    }

    @Test
    void computesCorrectLinearCombinationForValidInput() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 2.0}),
                new Vector(new double[]{3.0, 4.0})
        };
        double[] scalars = {2.0, -1.0};
        Vector result = new VectorSpace().linearCombination(vectors, scalars);
        assertEquals(new Vector(new double[]{-1.0, 0.0}), result);
    }

    @Test
    void returnsEmptyArrayWhenInputIsNull() {
        Vector[] result = new VectorSpace().getBase(null);
        assertEquals(0, result.length);
    }

    @Test
    void returnsEmptyArrayWhenInputIsEmpty() {
        Vector[] vectors = {};
        Vector[] result = new VectorSpace().getBase(vectors);
        assertEquals(0, result.length);
    }

    @Test
    void returnsEmptyArrayWhenVectorsHaveDifferentDimensions() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{1.0})
        };
        Vector[] result = new VectorSpace().getBase(vectors);
        assertEquals(0, result.length);
    }

    @Test
    void returnsSameVectorsWhenInputIsAlreadyABase() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        Vector[] result = new VectorSpace().getBase(vectors);
        assertArrayEquals(vectors, result);
    }

    @Test
    void returnsBaseForLinearlyDependentVectors() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{2.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        Vector[] expectedBase = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        Vector[] result = new VectorSpace().getBase(vectors);
        assertArrayEquals(expectedBase, result);
    }

    @Test
    void returnsBaseForVectorsWithFloatingPointValues() {
        Vector[] vectors = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0}),
                new Vector(new double[]{0.000001, 0.000001})
        };
        Vector[] expectedBase = {
                new Vector(new double[]{1.0, 0.0}),
                new Vector(new double[]{0.0, 1.0})
        };
        Vector[] result = new VectorSpace().getBase(vectors);
        assertArrayEquals(expectedBase, result);
    }

public Vector[] getBase(Vector[] vectors) {
    if (vectors == null || vectors.length == 0) return new Vector[0];

    int dimension = vectors[0].getVector().length;
    for (Vector v : vectors)
        if (v.getVector().length != dimension) return new Vector[0];

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

    @Test
    void testIsSubspaceWithNullInput() {
        VectorSpace vectorSpace = new VectorSpace();
        assertFalse(vectorSpace.isSubspace(null));
    }

    @Test
    void testIsSubspaceWithEmptyArray() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {};
        assertFalse(vectorSpace.isSubspace(vectors));
    }

    @Test
    void testIsSubspaceWithDifferentDimensions() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0}),
            new Vector(new double[]{1.0, 0.0})
        };
        assertFalse(vectorSpace.isSubspace(vectors));
    }

    @Test
    void testIsSubspaceWithoutZeroVector() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{-1.0, 0.0})
        };
        assertFalse(vectorSpace.isSubspace(vectors));
    }

    @Test
    void testLinearCombinationWithNullInput() {
        VectorSpace vectorSpace = new VectorSpace();
        assertThrows(IllegalArgumentException.class, () -> vectorSpace.linearCombination(null, null));
    }

    @Test
    void testLinearCombinationWithMismatchedLengths() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        double[] scalars = {1.0};
        assertThrows(IllegalArgumentException.class, () -> vectorSpace.linearCombination(vectors, scalars));
    }

    @Test
    void testLinearCombinationWithZeroScalars() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 2.0}),
            new Vector(new double[]{3.0, 4.0})
        };
        double[] scalars = {0.0, 0.0};
        Vector result = vectorSpace.linearCombination(vectors, scalars);
        assertEquals(new Vector(new double[]{0.0, 0.0}), result);
    }

    @Test
    void testGetBaseWithNullInput() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] result = vectorSpace.getBase(null);
        assertEquals(0, result.length);
    }

    @Test
    void testGetBaseWithEmptyArray() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {};
        Vector[] result = vectorSpace.getBase(vectors);
        assertEquals(0, result.length);
    }

    @Test
    void testGetBaseWithLinearlyDependentVectors() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{2.0, 0.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        Vector[] expectedBase = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        Vector[] result = vectorSpace.getBase(vectors);
        assertArrayEquals(expectedBase, result);
    }

    @Test
    void testGetDimensionWithNullInput() {
        VectorSpace vectorSpace = new VectorSpace();
        assertEquals(0, vectorSpace.getDimension(null));
    }

    @Test
    void testGetDimensionWithEmptyArray() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {};
        assertEquals(0, vectorSpace.getDimension(vectors));
    }

    @Test
    void testGetDimensionWithValidVectors() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        assertEquals(2, vectorSpace.getDimension(vectors));
    }

    @Test
    void testChangeBaseWithNullInput() {
        VectorSpace vectorSpace = new VectorSpace();
        assertThrows(IllegalArgumentException.class, () -> vectorSpace.changeBase(null, null));
    }

    @Test
    void testChangeBaseWithMismatchedDimensions() {
        VectorSpace vectorSpace = new VectorSpace();
        Vector[] vectors = {
            new Vector(new double[]{1.0, 0.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        Vector[] newBase = {
            new Vector(new double[]{1.0}),
            new Vector(new double[]{0.0, 1.0})
        };
        assertThrows(IllegalArgumentException.class, () -> vectorSpace.changeBase(vectors, newBase));
    }

    @Test
    void testRelativeCoordinatesValidInput() {
        Vector[] vectors = {
            new Vector(new double[]{1, 2}),
            new Vector(new double[]{3, 4})
        };
        Vector[] newBase = {
            new Vector(new double[]{2, 0}),
            new Vector(new double[]{0, 2})
        };
        double[] scalars = {1, 0.5};

        VectorSpace vs = new VectorSpace();
        Vector[] result = vs.relativeCoordinates(vectors, newBase, scalars);

        assertArrayEquals(new double[]{2, 0}, result[0].getVector(), 1e-6);
        assertArrayEquals(new double[]{0, 4}, result[1].getVector(), 1e-6);
    }

    @Test
    void testRelativeCoordinatesNullInput() {
        VectorSpace vs = new VectorSpace();
        Vector[] vectors = {new Vector(new double[]{1, 2})};
        Vector[] newBase = {new Vector(new double[]{2, 0})};
        double[] scalars = {1};

        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(null, newBase, scalars));
        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, null, scalars));
        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, newBase, null));
    }

    @Test
    void testRelativeCoordinatesEmptyInput() {
        VectorSpace vs = new VectorSpace();
        Vector[] empty = {};
        Vector[] vectors = {new Vector(new double[]{1, 2})};
        Vector[] newBase = {new Vector(new double[]{2, 0})};
        double[] scalars = {1};

        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(empty, newBase, scalars));
        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, empty, scalars));
        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, newBase, new double[]{}));
    }

    @Test
    void testRelativeCoordinatesMismatchedDimensions() {
        VectorSpace vs = new VectorSpace();
        Vector[] vectors = {new Vector(new double[]{1, 2}), new Vector(new double[]{3, 4, 5})};
        Vector[] newBase = {new Vector(new double[]{2, 0}), new Vector(new double[]{0, 2})};
        double[] scalars = {1, 1};

        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, newBase, scalars));
    }

    @Test
    void testRelativeCoordinatesMismatchedBaseDimensions() {
        VectorSpace vs = new VectorSpace();
        Vector[] vectors = {new Vector(new double[]{1, 2}), new Vector(new double[]{3, 4})};
        Vector[] newBase = {new Vector(new double[]{2, 0, 1}), new Vector(new double[]{0, 2, 1})};
        double[] scalars = {1, 1};

        assertThrows(IllegalArgumentException.class, () -> vs.relativeCoordinates(vectors, newBase, scalars));
    }
}
