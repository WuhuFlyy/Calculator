import modules.matrix.Matrix;

import java.util.Scanner;

/**
 * @author 岳宗翰
 * @Description:测试测试测试测试测试
 * @date 2023/11/10 21:54
 */
public class CaltulatorTest {
    public static void main(String[] args) {
        double[][] m=new double[4][4];
        Scanner in=new Scanner(System.in);
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                m[i][j]=in.nextDouble();
            }
        }
        Matrix matrix=new Matrix(m,3,3);
        System.out.println(matrix.rank());
    }

}