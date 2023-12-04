import modules.basic.Fraction;
import modules.matrix.Matrix;

import java.util.Scanner;

/**
 * @author 岳宗翰
 * @Description:
 * @date 2023/11/25 13:43
 */
public class MatrixTest {
    public static Scanner in=new Scanner(System.in);
    public static void main(String[] args) {

        Fraction[][] m=new Fraction[4][4];
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                int a=in.nextInt();
                m[i][j]=new Fraction(Integer.toString(a));
            }
        }
        Matrix M=new Matrix(m,3,3);
        System.out.println(M.getRank());
        M.inverse();
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(M.matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        Matrix M2=M.multiply(M.inverse());
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.print(M2.matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
