package LA;

import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(Enclosed.class)
class MatrixTest {

    @Nested
    public class Constructor {
        @Test
        public void givenColumnVectorsOfVaryingDimension_thenThrowException() {
            assertThrows(IllegalArgumentException.class, () -> Matrix.fromColumns(
                    new Vector(
                            ComplexNumber.real(1)
                    ),
                    new Vector(
                            ComplexNumber.real(0),
                            ComplexNumber.real(1))
            ));
        }

        @Test
        public void givenASquareMatrix_thenInstantiate() {
            Matrix a = Matrix.fromColumns(
                            new Vector(
                                    ComplexNumber.real(1),
                                    ComplexNumber.real(0)
                            ),
                            new Vector(
                                    ComplexNumber.real(0),
                                    ComplexNumber.real(1))
                    );

            assertEquals(2, a.m());
            assertEquals(2, a.n());
            assertThrows(IllegalArgumentException.class, () -> a.getRow(0));

            Matrix k = Matrix.fromRealsWithSize(2,2,1,0,0,1);
            System.out.println(k);
        }


        @Test
        public void constructRealMatrixWithIncorrectParameters(){
            assertThrows(IllegalArgumentException.class, () -> Matrix.fromRealsWithSize(1,2,1,3,4,5,6));
            assertThrows(IllegalArgumentException.class, () -> Matrix.fromRealsWithSize(3,3, 1));
            assertThrows(IllegalArgumentException.class, () -> Matrix.fromRealsWithSize(0,0));
        }

        @Test
        public void constructRealMatrixJustAsParameters(){
            Matrix a = Matrix.fromRealsWithSize(2,2,1,2,3,4);
            assertEquals(2, a.n());
            assertEquals(2, a.m());
            assertEquals(Vector.fromReals(1,2), a.getRow(1));
            assertEquals(Vector.fromReals(3,4), a.getRow(2));
            assertThrows(IllegalArgumentException.class, () -> a.getRow(3));

        }
        @Test
        public void givenARectangularMatrix_thenInstantiate() {
            Matrix a = Matrix.fromColumns(
                    new Vector(
                            ComplexNumber.real(1),
                            ComplexNumber.real(0),
                            ComplexNumber.real(0)
                    ),
                    new Vector(
                            ComplexNumber.real(0),
                            ComplexNumber.real(1),
                            ComplexNumber.real(0)
                    )
            );

            assertEquals(3, a.m());
            assertEquals(2, a.n());
            assertThrows(IllegalArgumentException.class, () -> a.getRow(1000));
            assertThrows(IllegalArgumentException.class, () -> a.getRow(-31));
        }
    }


    @Nested
    public class Transpose {
        @Test
        public void givenASquareMatrix_thenReturnItsTranspose() {
            Matrix a = Matrix.fromRows(
                    Vector.fromReals(1, 2, 3),
                    Vector.fromReals(4, 5, 6),
                    Vector.fromReals(7, 8, 9)
            );

            assertEquals(Matrix.fromRows(
                    Vector.fromReals(1, 4, 7),
                    Vector.fromReals(2, 5, 8),
                    Vector.fromReals(3, 6, 9)
                    ), a.transpose());
        }
    }

    @Nested
    public class GetRow {
        @Test
        public void givenAMatrix_thenReturnTheRequestedRow() {
        Matrix a = Matrix.fromColumns(
                Vector.fromReals(1, 2, 3),
                Vector.fromReals(4, 5, 6),
                Vector.fromReals(7, 8, 9)
        );

        assertEquals(
                Vector.fromReals(2, 5, 8),
                a.getRow(2)
                );
        }
    }
}