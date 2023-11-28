package modules.matrix;

import modules.basic.Fraction;
import modules.basic.Operation;

/**
 * @author 岳宗翰
 * @Description: 基本矩阵类
 * @date 2023/11/12 13:45
 */
public class Matrix {
    public Fraction[][] matrix;
    public int row;
    public int column;
    public int rank;

    /**
     * @Description 带参构造
     * @param m 矩阵数组
     * @param row 行数
     * @param column 列数
     * @author 岳宗翰
     * @date 2023/11/12 15:33
    **/
    public Matrix(Fraction[][] m,int row,int column){
        super();
        this.matrix=m;
        this.row=row;
        this.column=column;
        this.rank=rank();
    }
    public Matrix(){
        super();
    }

    /**
     * @Description
     * @param row1 要交换的行1
     * @param row2 要交换的行2
     * @author 岳宗翰
     * @date 2023/11/12 15:05
     **/
    public void rowELT1(int row1, int row2){
        int row=this.row,col=this.column;
        for(int i=1;i<=col;i++){
            Fraction temp=this.matrix[row1][i];
            this.matrix[row1][i]=this.matrix[row2][i];
            this.matrix[row2][i]=temp;
        }
    }

    /**
     * @Description
     * @param row 变换行
     * @param mul 乘数
     * @author 岳宗翰
     * @date 2023/11/12 15:15
     **/
    public void rowELT2(int row, Fraction mul){
        for(int i=1;i<=this.column;i++){
            this.matrix[row][i]=this.matrix[row][i].multiply(mul);
        }
    }

    /**
     * @Description
     * @param row1 行1
     * @param row2 行2
     * @param mul 乘数
     * @author 岳宗翰
     * @date 2023/11/12 15:19
     **/
    public void rowELT3(int row1, int row2, Fraction mul){
        for(int i=1;i<=this.column;i++){
            this.matrix[row2][i]=this.matrix[row1][i].multiply(mul).add(this.matrix[row2][i]);
        }
    }

    /**
     * @Description
     * @param col1 列1
     * @param col2 列2
     * @author 岳宗翰
     * @date 2023/11/12 15:21
     **/
    public void columnELT1(int col1, int col2){
        int row=this.row,col=this.column;
        for(int i=1;i<=row;i++){
            Fraction temp=this.matrix[i][col1];
            this.matrix[i][col1]=this.matrix[i][col2];
            this.matrix[i][col2]=temp;
        }
    }

    /**
     * @Description
     * @param col 变换列
     * @param mul 乘数
     * @author 岳宗翰
     * @date 2023/11/12 15:25
     **/
    public void columnELT2(int col, Fraction mul){
        for(int i=1;i<=this.row;i++){
            this.matrix[i][col]=this.matrix[i][col].multiply(mul);
        }
    }
    /**
     * @Description
     * @param col1 列1
     * @param col2 列2
     * @param mul 乘数
     * @author 岳宗翰
     * @date 2023/11/12 15:25
     **/
    public void columnELT3(int col1, int col2, Fraction mul){
        for(int i=1;i<=this.row;i++){
            this.matrix[i][col2]=this.matrix[i][col1].multiply(mul).add(this.matrix[i][col2]);
        }
    }

    /**
     * @Description 判断两行是否等价
     * @param row1 行1
     * @param row2 行2
     * @return boolean
     * @author 岳宗翰
     * @date 2023/11/12 15:44
    **/
    private boolean equivalentRow(int row1, int row2){
        Fraction[][] matrix=this.matrix;
        Fraction mul1=matrix[row1][1];
        Fraction mul2=matrix[row2][1];
        for(int i=2;i<=this.column;i++){
            if (matrix[row1][i].multiply(mul2).equals(matrix[row2][i].multiply(mul1))) {
                return false;
            }
        }
        return true;
    }
    /**
     * @Description 判断两列是否等价
     * @param col1 列1
     * @param col2 列2
     * @return boolean
     * @author 岳宗翰
     * @date 2023/11/12 15:46
    **/
    private boolean equivalentCol(int col1, int col2){
        Fraction[][] matrix=this.matrix;
        Fraction mul1=matrix[1][col1];
        Fraction mul2=matrix[1][col2];
        for(int i=2;i<=this.row;i++){
            if (!matrix[i][col1].multiply(mul2).equals(matrix[i][col2].multiply(mul1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 求矩阵的秩
     * @return int 该矩阵的秩
     * @author 岳宗翰
     * @date 2023/11/12 13:55
     **/
    private int rank(){
        Fraction[][] m=this.matrix;
        int r=this.row;
        for(int i=2;i<=this.row;i++){
            boolean flag=true;
            for(int j=1;j<i;j++){
                if(equivalentRow(i,j)){
                    flag=false;
                    break;
                }
            }
            if(!flag){
                r--;
            }
        }
        return r;
    }

    /**
     * @Description 判断矩阵是否可逆
     * @return boolean 是否可逆
     * @author 岳宗翰
     * @date 2023/11/12 13:58
    **/
    public boolean invertible(){
        int r=this.rank();
        return this.row == r && this.column == r;
    }

    /**
     * @Description 求逆矩阵
     * @return modules.matrix.Matrix
     * @author 岳宗翰
     * @date 2023/11/25 13:36
    **/
    public Matrix inverse(){
        if(!this.invertible()){
            throw new ArithmeticException("该矩阵不可逆");
        }
        else{
            return this;
        }
    }
}