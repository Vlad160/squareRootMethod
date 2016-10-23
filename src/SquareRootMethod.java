import Matrix.Matrix;

/**
 * Created by User on 23.10.2016.
 */
public class SquareRootMethod {
    private Matrix aMatrix;
    private Matrix tMatrix;
    private Matrix transposedTMatrix;
    private Matrix fVector;
    private Matrix xVector;

    public SquareRootMethod(double[][] matrix, double[][] fVect) {
        aMatrix = new Matrix(matrix);
        fVector = new Matrix(fVect);
        transposedTMatrix = new Matrix(aMatrix.getRows(), aMatrix.getColumns());
        tMatrix = new Matrix(aMatrix.getRows(), aMatrix.getColumns());
        xVector = new Matrix(fVector.getRows(), 1);
    }

    public void symmetricalTransformation() {
        Matrix transposedAMatrix = new Matrix(Matrix.transpone(aMatrix));
        aMatrix = transposedAMatrix.multiply(aMatrix);
        fVector = transposedAMatrix.multiply(fVector);
    }

    public void print() {
        aMatrix.print();
        System.out.println();
        tMatrix.print();
        System.out.println();
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
                if (j > i){
                    double sum = 0;
                    for (int k = 0; k <= i - 1; ++k) {
                        sum += tMatrix.get(k, i) * tMatrix.get(k, j);
                    }
                    double difference = aMatrix.get(i,j) - sum;
                    tMatrix.set(i,j,difference/tMatrix.get(i,i));
                }
            }
        }
    }
}
