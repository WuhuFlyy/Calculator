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
        Matrix p=M.transposition();
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                System.out.printf("%s ",p.matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println(M.getDeterminant());
    }
}
