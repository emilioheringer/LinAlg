package com.heringer;

import com.heringer.linalg.ComplexMatrix;
import com.heringer.linalg.Matrix;

public class Main {
    public static void main(String[] args) {
        Complex number = new Complex("2 - 9i");
        number.showRec();
        Complex number2 = new Complex("127<30");
        number2.showRec();
        number2.showPolar();
        Complex complex1 = new Complex("5.0<45.0"); // Exemplo de número complexo em forma polar
        Complex complex2 = new Complex("2.0<30.0");
        Complex div = Complex.divide(complex1, complex2);
        div.showPolar();
        double[][] data = {
                { 4.0, 4.0, 5, 20, 3.45, 3.23,},
                { 3.0, 3.0, 3.0, 3.0, 3.45, 244 },
                { 4.56, 4.56, 3.0, 3.0, 3.45, 100 }
        };

        Matrix m = new Matrix(data);
        m.showMatrix();

        Complex[][] complexMatrix = new Complex[7][7];

        // Preenchendo a matriz com números complexos no formato polar "r<θ"
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // Exemplo de números complexos com magnitudes e ângulos variados
                String polarNotation = (i + 1) + "<" + (30 * (j + 1)); // Ex: "1<30", "2<60", etc.
                complexMatrix[i][j] = new Complex(polarNotation);  // Cria o número complexo diretamente
            }
        }

        // Criando a ComplexMatrix
        ComplexMatrix matrix = new ComplexMatrix(complexMatrix);

        // Exibindo a matriz
        matrix.showMatrix();
    }
    }

