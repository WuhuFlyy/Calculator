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


        Fraction[][] m=new Fraction[5][5];
        for(int i=1;i<=2;i++){
            for(int j=1;j<=3;j++){
                int a=in.nextInt();
                m[i][j]=new Fraction(Integer.toString(a));
            }
        }
        Matrix M=new Matrix(m,2,3);
        System.out.println(M.getRank());
    }
}
