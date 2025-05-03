# Álgebra Linear

Este projeto é uma biblioteca de utilitários de engenharia, com foco em álgebra linear. Ele fornece classes e métodos para realizar operações com matrizes, vetores e números complexos.

## Visão Geral

A biblioteca é estruturada em torno dos seguintes pacotes principais:

-   `com.heringer`: Contém a classe [`Main`](src/main/java/com/heringer/Main.java) para demonstração e testes rápidos.
-   `com.heringer.linalg`: Inclui classes para manipulação de matrizes ([`Matrix`](src/main/java/com/heringer/linalg/Matrix.java), [`ComplexMatrix`](src/main/java/com/heringer/linalg/ComplexMatrix.java)), vetores ([`Vector`](src/main/java/com/heringer/linalg/Vector.java)) e espaços vetoriais ([`VectorSpace`](src/main/java/com/heringer/linalg/VectorSpace.java)), bem como implementações de algoritmos de decomposição ([`Decomposition`](src/main/java/com/heringer/linalg/Decomposition.java)) e resolução de sistemas lineares ([`Solvelinsys`](src/main/java/com/heringer/linalg/Solvelinsys.java)).

## Funcionalidades

### Álgebra Linear

-   **Matrizes:**
    -   Operações básicas: adição, subtração, multiplicação.
    -   Transposição e inversão de matrizes.
    -   Cálculo do determinante e do rank.
    -   Decomposições: LU, QR, Espectral.
    -   Matrizes complexas.
-   **Vetores:**
    -   Operações básicas: adição, subtração, produto escalar.
    -   Produto vetorial.
    -   Normalização e projeção.
    -   Cálculo de ângulo entre vetores.
-   **Espaços Vetoriais:**
    -   Verificação de subespaços.
    -   Combinações lineares.
    -   Base e dimensão de um espaço vetorial.
    -   Mudança de base e coordenadas relativas.
-   **Sistemas Lineares:**
    -   Resolução via Eliminação de Gauss.
    -   Classificação de sistemas.

### Números Complexos

-   A biblioteca utiliza a biblioteca [ComplexNumber](https://github.com/emilioheringer/ComplexNumber) para representar e manipular números complexos.

## Estrutura do Projeto


EngineeringUtils/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── heringer/
│   │               ├── Main.java
│   │               └── linalg/
│   │                   ├── ComplexMatrix.java
│   │                   ├── Decomposition.java
│   │                   ├── Eigenvalues.java
│   │                   ├── IComplexMatrix.java
│   │                   ├── IDecomposition.java
│   │                   ├── IEigenvalues.java
│   │                   ├── IMatrix.java
│   │                   ├── ISolvelinsys.java
│   │                   ├── IVector.java
│   │                   ├── IVectorSpace.java
│   │                   ├── Matrix.java
│   │                   ├── Solvelinsys.java
│   │                   ├── Vector.java
│   │                   └── VectorSpace.java
│   └── test/
│       └── java/
│           └── com/
│               └── heringer/
│                   └── linalg/
│                       ├── ComplexMatrixTest.java
│                       ├── EigenvaluesTest.java
│                       └── MatrixTest.java
└── target/



## Dependências

## Maven
```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

    <dependency>
	    <groupId>com.github.emilioheringer</groupId>
	    <artifactId>LinAlg</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

## Gradle
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

    dependencies {
	        implementation 'com.github.emilioheringer:LinAlg:Tag'
	}
```


## Exemplos

```java
// Exemplo de uso da classe Matrix
double[][] data = {
    {1, 2},
    {3, 4}
};
Matrix matrix = new Matrix(data);
matrix.showMatrix();

// Exemplo de uso da classe ComplexMatrix
Complex[][] complexData = {
    {new Complex(1, 0), new Complex(0, 1)},
    {new Complex(0, -1), new Complex(1, 0)}
};
ComplexMatrix complexMatrix = new ComplexMatrix(complexData);
complexMatrix.showMatrix();
```
Testes

Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

Autor
Emílio Heringer

Licença
Este projeto está licenciado sob a MIT License.
Este README fornece uma visão geral do projeto, suas funcionalidades, estrutura, como usar e como contribuir. Ele também inclui exemplos de uso e informações sobre os testes.Este README fornece uma visão geral do projeto, suas funcionalidades, estrutura, como usar e como contribuir. Ele também inclui exemplos de uso e informações sobre os testes.

# EngineeringUtils

This project is a utility library focused on **linear algebra**, providing classes and methods for working with matrices, vectors, and complex numbers.

## Overview

The library is organized into the following main packages:

- `com.heringer`: Contains the [`Main`](src/main/java/com/heringer/Main.java) class for demonstration and quick tests.
- `com.heringer.linalg`: Includes classes for working with matrices ([`Matrix`](src/main/java/com/heringer/linalg/Matrix.java), [`ComplexMatrix`](src/main/java/com/heringer/linalg/ComplexMatrix.java)), vectors ([`Vector`](src/main/java/com/heringer/linalg/Vector.java)), and vector spaces ([`VectorSpace`](src/main/java/com/heringer/linalg/VectorSpace.java)), as well as implementations of decomposition algorithms ([`Decomposition`](src/main/java/com/heringer/linalg/Decomposition.java)) and linear system solvers ([`Solvelinsys`](src/main/java/com/heringer/linalg/Solvelinsys.java)).

## Features

### Linear Algebra

- **Matrices:**
  - Basic operations: addition, subtraction, multiplication
  - Matrix transposition and inversion
  - Determinant and rank calculation
  - Decompositions: LU, QR, Spectral
  - Complex matrices

- **Vectors:**
  - Basic operations: addition, subtraction, dot product
  - Cross product
  - Normalization and projection
  - Angle calculation between vectors

- **Vector Spaces:**
  - Subspace verification
  - Linear combinations
  - Basis and dimension
  - Change of basis and relative coordinates

- **Linear Systems:**
  - Solving via Gaussian elimination
  - System classification

### Complex Numbers

This library integrates with the [ComplexNumber](https://github.com/emilioheringer/ComplexNumber) library to handle complex number operations.

## Project Structure

EngineeringUtils/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── heringer/
│   │               ├── Main.java
│   │               └── linalg/
│   │                   ├── ComplexMatrix.java
│   │                   ├── Decomposition.java
│   │                   ├── Eigenvalues.java
│   │                   ├── IComplexMatrix.java
│   │                   ├── IDecomposition.java
│   │                   ├── IEigenvalues.java
│   │                   ├── IMatrix.java
│   │                   ├── ISolvelinsys.java
│   │                   ├── IVector.java
│   │                   ├── IVectorSpace.java
│   │                   ├── Matrix.java
│   │                   ├── Solvelinsys.java
│   │                   ├── Vector.java
│   │                   └── VectorSpace.java
│   └── test/
│       └── java/
│           └── com/
│               └── heringer/
│                   └── linalg/
│                       ├── ComplexMatrixTest.java
│                       ├── EigenvaluesTest.java
│                       └── MatrixTest.java
└── target/


## Dependencies

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.emilioheringer</groupId>
    <artifactId>LinAlg</artifactId>
    <version>1.0.0</version>
</dependency>
```
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.emilioheringer:LinAlg:Tag'
}
```
```
// Example using Matrix class
double[][] data = {
    {1, 2},
    {3, 4}
};
Matrix matrix = new Matrix(data);
matrix.showMatrix();

// Example using ComplexMatrix class
Complex[][] complexData = {
    {new Complex(1, 0), new Complex(0, 1)},
    {new Complex(0, -1), new Complex(1, 0)}
};
ComplexMatrix complexMatrix = new ComplexMatrix(complexData);
complexMatrix.showMatrix();
```
Tests
Unit tests are included under the src/test directory to ensure the correctness of matrix operations, decompositions, and more.

Contribution
Contributions are welcome! Feel free to open issues or submit pull requests.

Author
Emílio Heringer

License
This project is licensed under the MIT License.

[![](https://jitpack.io/v/emilioheringer/LinAlg.svg)]https://jitpack.io/#emilioheringer/LinAlg)
