import Matrices.*;;

public class Main {
    public static void main(String[] args) {
        int [][] a = { 
                        {9, 6, 3},
                        {2, 4, 8},
                        {7, 1, 5}
                    };

        int [][] b = { 
                       {3, 2, 1},
                         {1, 2, 4},
                         {7, 1, 5}
                    };

        //~ Object Created for matrix class
        Matrix m = new Matrix();
        //!Display Matrix by one function
        System.out.println(m.matrixToString(a));
        
        //! Addition in matrix
        int [][] addition = m.addMatrix(a,b);
        System.out.println("Addition");
        System.out.println(m.matrixToString(addition));

          //! Subtraction in matrix
        int [][] subMatrix = m.subMatrix(a,b);
        System.out.println("Subtraction");
        System.out.println(m.matrixToString(subMatrix));

           //! Multiplication in matrix
        int [][] mulMatrix = m.mulMatrix(a,b);
        System.out.println("Multiplication");
        System.out.println(m.matrixToString(mulMatrix));

            //! Division in matrix
        double [][] divMatrix = m.divideMatrix(a,b);
        System.out.println("Divide");
        System.out.println(m.matrixToString(divMatrix));
          
        //! Adjoint Matrix
        int [][] AdjointMatrix = m.adjointMatrix(a);
        System.out.println("Adjoint Matrix");
        System.out.println(m.matrixToString(AdjointMatrix));

        //! Determinant Matrix
        int  Determinant = m.determinant(a);
        System.out.println("Determinant of Matrix");
        System.out.println(Determinant);

        //! Inverse Matrix
        double [][] inverseMatrix = m.inverseMatrix(a);
        System.out.println("Inverse Matrix");
        System.out.println(m.matrixToString(inverseMatrix));
    }
}
