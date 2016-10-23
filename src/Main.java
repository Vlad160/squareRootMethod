/**
 * Created by User on 23.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        double[][] matrix1 = {{8.30, 3.62, 4.10, 1.90}, {3.92, 8.45, 6.78, 2.46}, {3.77, 8.21, 8.04, 2.28}, {2.21, 2.65, 1.69, 6.99}};
        double[][] matrix2 = {{-9.65},{12.21},{14.45},{-8.35}};
        SquareRootMethod aMatrix = new SquareRootMethod(matrix1,matrix2);
        aMatrix.print();
        System.out.println();
        aMatrix.symmetricalTransformation();
        aMatrix.findTMatrix();
        aMatrix.print();
    }
}
