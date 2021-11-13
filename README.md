# Gaussian Elimination Calculator

A calculator for the gaussian 
elimination algorithm to solve systems of linear equations
with multiple unknown variables, written in Java.

For understanding the maths behind it, the calculator has
a built-in calculation path step trace, and an easy-to-use GUI.
Note that the calulator will only change a given matrix to the
reduced row echelon form, from which the solution vector can
be read.

This calculator currently only works with uniquely solvable matrices.

## Getting started

### Dependencies

* [Apache Maven](https://maven.apache.org/)
* Java Platform ([OpenJDK 17](https://jdk.java.net/17/))

### Installation

1. Download and install [dependencies](#Dependencies)
2. Navigate to the project root directory
3. Build the application jar file with maven:

```bash
$ mvn install
```

The jar file and build information is then written to the
newly created directory ```targets```.

### Usage

To run the application, pass the ```.jar``` file to the
installed java runtime:

```bash
$ java -jar ./target/fpr.gauss-1.0-SNAPSHOT.jar
```

### Documentation

To generate the JavaDoc HTML documentation files, run the maven
executable with the parameter ```javadoc:javadoc```:

```bash
$ mvn javadoc:javadoc
```

The generated files are accessible in the directory ```target/site/apidocs```.

### Tests

To run only the unit tests, call the maven executable with the ```test``` parameter:

```bash
$ mvn test
```
