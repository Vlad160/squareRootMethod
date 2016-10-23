package Matrix;
public class SquareRootMethod {
    private Matrix aMatrix;
    private Matrix tMatrix;
    private Matrix transposedTMatrix;
    private Matrix fVector;
    private Matrix xVector;
    private Matrix yVector;

    public SquareRootMethod(double[][] matrix, double[][] fVect) {
        aMatrix = new Matrix(matrix);
        fVector = new Matrix(fVect);
        transposedTMatrix = new Matrix(aMatrix.getRows(), aMatrix.getColumns());
        tMatrix = new Matrix(aMatrix.getRows(), aMatrix.getColumns());
        xVector = new Matrix(fVector.getRows(), 1);
        yVector = new Matrix(fVector.getRows(), 1);
    }

    public void symmetricalTransformation() {
        Matrix transposedAMatrix = new Matrix(Matrix.transpone(aMatrix));
        aMatrix = transposedAMatrix.multiply(aMatrix);
        fVector = transposedAMatrix.multiply(fVector);
    }

    public void print() {
        aMatrix.print();
        System.out.println();
        fVector.print();
        System.out.println();
        yVector.print();
        System.out.println();
        xVector.print();
        Matrix residualVector = new Matrix(aMatrix.multiply(xVector));
        residualVector.substract(fVector);
        residualVector.print();
    }

    public void findTMatrix() {
        tMatrix.set(0, 0, Math.sqrt(aMatrix.get(0, 0)));
        for (int j = 1; j < aMatrix.getColumns(); ++j) {
            tMatrix.set(0, j, aMatrix.get(0, j) / tMatrix.get(0, 0));
        }
        for (int i = 1; i < aMatrix.getRows(); ++i) {
            for (int j = 0; j < aMatrix.getColumns(); ++j) {
                if (i == j) {
                    double sum = 0;
                    for (int k = 0; k <= i - 1; ++k) {
                        sum += tMatrix.get(k, j) * tMatrix.get(k, j);
                    }
                    double difference = aMatrix.get(i, i) - sum;
                    tMatrix.set(i, i, Math.sqrt(difference));
                }
                if (j > i) {
                    double sum = 0;
                    for (int k = 0; k <= i - 1; ++k) {
                        sum += tMatrix.get(k, i) * tMatrix.get(k, j);
                    }
                    double difference = aMatrix.get(i, j) - sum;
                    tMatrix.set(i, j, difference / tMatrix.get(i, i));
                }
            }
        }
        transposedTMatrix = Matrix.transpone(tMatrix);
    }

    public void findYVector() {
        yVector.set(0, 0, fVector.get(0, 0) / tMatrix.get(0, 0));
        for (int i = 1; i < aMatrix.getRows(); ++i) {
            double sum = 0;
            for (int k = 0; k <= i - 1; ++k) {
                sum += tMatrix.get(k, i) * yVector.get(k, 0);
            }
            double difference = fVector.get(i, 0) - sum;
            yVector.set(i, 0, difference / tMatrix.get(i, i));
        }
    }

    public void findSolution() {
        xVector.set(xVector.getRows() - 1, 0, yVector.get(yVector.getRows() - 1, 0) / tMatrix.get(yVector.getRows() - 1, yVector.getRows() - 1));
        for (int i = aMatrix.getRows() - 1; i >= 0; --i) {
            double sum = 0;
            for (int k = i + 1; k <= xVector.getRows() - 1; ++k) {
                sum += tMatrix.get(i, k) * xVector.get(k, 0);
            }
            double difference = yVector.get(i, 0) - sum;
            xVector.set(i, 0, difference / tMatrix.get(i, i));
        }
    }
}
