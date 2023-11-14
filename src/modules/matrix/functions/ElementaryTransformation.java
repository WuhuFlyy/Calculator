package modules.matrix.functions;

import modules.basic.Fraction;
import modules.basic.Operation;
import modules.matrix.Matrix;

/**
 * @author 岳宗翰
 * @Description: 矩阵初等变换
 * @date 2023/11/12 14:46
 */
public class ElementaryTransformation {
    /**
     * @Description
     * @param m 传入的矩阵
     * @param row1 要交换的行1
     * @param row2 要交换的行2
     * @return double[][] 初等变换后的矩阵数组
     * @author 岳宗翰
     * @date 2023/11/12 15:05
    **/
    public Fraction[][] rowELT1(Matrix m, int row1, int row2){
        int row=m.row,col=m.column;
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=col;i++){
            Fraction temp=newM[row1][i];
            newM[row1][i]=newM[row2][i];
            newM[row2][i]=temp;
        }
        return newM;
    }
    /**
     * @Description
     * @param m 传入的矩阵
     * @param row 变换行
     * @param mul 乘数
     * @return double[][] 变换后的矩阵
     * @author 岳宗翰
     * @date 2023/11/12 15:15
    **/
    public Fraction[][] rowELT2(Matrix m, int row, double mul){
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=m.column;i++){
            newM[row][i]=newM[row][i].multiply(Operation.toFraction(String.valueOf(mul)));
        }
        return newM;
    }
    /**
     * @Description
     * @param m 传入矩阵
     * @param row1 行1
     * @param row2 行2
     * @param mul 乘数
     * @return double[][]
     * @author 岳宗翰
     * @date 2023/11/12 15:19
    **/
    public Fraction[][] rowELT3(Matrix m, int row1, int row2, double mul){
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=m.column;i++){
            newM[row2][i]=newM[row1][i].multiply(Operation.toFraction(String.valueOf(mul))).add(newM[row2][i]);
        }
        return newM;
    }
    /**
     * @Description
     * @param m 传入矩阵
     * @param col1 列1
     * @param col2 列2
     * @return double[][]
     * @author 岳宗翰
     * @date 2023/11/12 15:21
    **/
    public Fraction[][] columnELT1(Matrix m, int col1, int col2){
        int row=m.row,col=m.column;
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=row;i++){
            Fraction temp=newM[i][col1];
            newM[i][col1]=newM[i][col2];
            newM[i][col2]=temp;
        }
        return newM;
    }
    /**
     * @Description
     * @param m 传入矩阵
     * @param col 变换列
     * @param mul 乘数
     * @return double[][]
     * @author 岳宗翰
     * @date 2023/11/12 15:25
    **/
    public Fraction[][] columnELT2(Matrix m, int col, int mul){
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=m.row;i++){
            newM[i][col]=newM[i][col].multiply(Operation.toFraction(String.valueOf(mul)));
        }
        return newM;
    }
    /**
     * @Description
     * @param m 传入矩阵
     * @param col1 列1
     * @param col2 列2
     * @param mul 乘数
     * @return double[][]
     * @author 岳宗翰
     * @date 2023/11/12 15:25
    **/
    public Fraction[][] columnELT3(Matrix m, int col1, int col2, double mul){
        Fraction[][] newM=m.matrix;
        for(int i=1;i<=m.row;i++){
            newM[i][col2]=newM[i][col1].multiply(Operation.toFraction(String.valueOf(mul))).add(newM[i][col2]);
        }
        return newM;
    }
}
