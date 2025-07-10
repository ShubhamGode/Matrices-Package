package Matrices;
public class Matrix {

    //*To convert 2D Array (Matrix) to String */
 public  String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public  String matrixToString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            for (double val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //* To Add Multiple Matrix */
     public  int[][] addMatrix(int[][]... matrices) {
        if (matrices.length == 0) {
            throw new MatrixException("No matrices provided.");
        }

        int rows = matrices[0].length;
        int cols = matrices[0][0].length;

        for (int[][] matrix : matrices) {
            if (matrix.length != rows || matrix[0].length != cols) {
                throw new MatrixException("All matrices must have the same dimensions.");
            }
        }

        int[][] result = new int[rows][cols];

        for (int[][] matrix : matrices) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] += matrix[i][j];
                }
            }
        }

        return result;
    }

     //* To Multiply Multiple Matrix */
   public  int[][] mulMatrix(int[][]... matrices) {
    if (matrices.length < 2) {
        throw new MatrixException("At least two matrices required for multiplication.");
    }

    int[][] result = matrices[0];

    for (int m = 1; m < matrices.length; m++) {
        int[][] next = matrices[m];

        int col1 = result[0].length;
        int row2 = next.length;

        if (col1 != row2) {
           throw new MatrixException("Multiplication failed between result matrix (from matrix 1 to matrix " + m +") and matrix " + (m + 1) + " due to incompatible dimensions.");

        }

        int[][] temp = new int[result.length][next[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < next[0].length; j++) {
                for (int k = 0; k < result[0].length; k++) {
                    temp[i][j] += result[i][k] * next[k][j];
                }
            }
        }

        result = temp;
    }

    return result;
}


     //* To Subtract Multiple Matrix */
    public  int[][] subMatrix(int[][]... matrices) {
    if (matrices.length == 0) {
        throw new MatrixException("No matrices provided.");
    }

    int rows = matrices[0].length;
    int cols = matrices[0][0].length;

    for (int[][] matrix : matrices) {
        if (matrix.length != rows || matrix[0].length != cols) {
            throw new MatrixException("All matrices must have the same dimensions.");
        }
    }

    int[][] result = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            result[i][j] = matrices[0][i][j];
        }
    }

    for (int k = 1; k < matrices.length; k++) {
        int[][] matrix = matrices[k];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] -= matrix[i][j];
            }
        }
    }

    return result;
}

     //* To find Adjoint Matrix */
public  int[][] adjointMatrix(int[][] matrix) {
    int n = matrix.length;
    int[][] cofactorMatrix = new int[n][n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int[][] sub = getSubMatrix(matrix, i, j); 
            int minor = determinant(sub);
            int sign = ((i + j) % 2 == 0) ? 1 : -1;
            cofactorMatrix[i][j] = sign * minor;
        }
    }

    int[][] adjoint = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            adjoint[i][j] = cofactorMatrix[j][i]; 
        }
    }

    return adjoint;
}


 //* To find Minor  Matrix */
public  int[][] getSubMatrix(int[][] matrix, int rowToRemove, int colToRemove) {
    int n = matrix.length;
    int[][] sub = new int[n - 1][n - 1];
    int r = 0;
    for (int i = 0; i < n; i++) {
        if (i == rowToRemove) continue;
        int c = 0;
        for (int j = 0; j < n; j++) {
            if (j == colToRemove) continue;
            sub[r][c++] = matrix[i][j];
        }
        r++;
    }
    return sub;
}

 //* To find Determinant */
public  int determinant(int[][] matrix) {
    int n = matrix.length;
    if (n == 1) return matrix[0][0];
    if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

    int det = 0;
    for (int j = 0; j < n; j++) {
        int[][] sub = getSubMatrix(matrix, 0, j);
        det += Math.pow(-1, j) * matrix[0][j] * determinant(sub);
    }
    return det;
}

 //* To find Inverse Matrix */
  public  double[][] inverseMatrix(int[][] matrix) {
    int row = matrix.length;
    int col = matrix[0].length;
    double[][] result = new double[row][col];
    int Determinant = determinant(matrix);

    if (Determinant == 0) {
        throw new MatrixException("Matrix is not invertible");
    }

    int[][] AdjointMatrix = adjointMatrix(matrix); 
 
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            result[i][j] = (double) AdjointMatrix[i][j] / Determinant;
        }
    }

    return result;
}

    public  double[][] mulMatrix(double[][] matrix1, double[][] matrix2) {
    int rows1 = matrix1.length;
    int cols1 = matrix1[0].length;
    int rows2 = matrix2.length;
    int cols2 = matrix2[0].length;

    if (cols1 != rows2) {
        throw new MatrixException("Matrix multiplication error: Columns of first matrix must match rows of second matrix.");
    }

    double[][] result = new double[rows1][cols2];

    for (int i = 0; i < rows1; i++) {
        for (int j = 0; j < cols2; j++) {
            for (int k = 0; k < cols1; k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }

    return result;
}


    public  double[][] divideMatrix (int [][] matrix1, int[][] matrix2){
        int row = matrix1.length;
        int col = matrix1[0].length;
        double[][] result = new double[row][col];
         int Determinant = determinant(matrix2);

    if (Determinant == 0) {
        throw new MatrixException("Matrix is not divisible");
    }
        double [][] inverse = inverseMatrix(matrix2);
        double[][] matrix1Double = convertToDouble(matrix1);
        result = mulMatrix(matrix1Double,inverse);
        return result;
    }

    public  double[][] convertToDouble(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    double[][] result = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            result[i][j] = (double) matrix[i][j];
        }
    }

    return result;
}

}
