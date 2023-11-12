package modules.matrix;

/**
 * @author 岳宗翰
 * @Description: 基本矩阵类
 * @date 2023/11/12 13:45
 */
public class Matrix {
    public double[][] matrix;
    public int row;
    public int column;
    /**
     * @Description 带参构造
     * @param m 矩阵数组
     * @param row 行数
     * @param column 列数
     * @author 岳宗翰
     * @date 2023/11/12 15:33
    **/
    public Matrix(double[][] m,int row,int column){
        super();
        this.matrix=m;
        this.row=row;
        this.column=column;
    }
    public Matrix(){
        super();
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
        double[][] matrix=this.matrix;
        double mul1=matrix[row1][1];
        double mul2=matrix[row2][1];
        for(int i=2;i<=this.column;i++){
            if (matrix[row1][i] * mul2 != matrix[row2][i] * mul1) {
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
        double[][] matrix=this.matrix;
        double mul1=matrix[1][col1];
        double mul2=matrix[1][col2];
        for(int i=2;i<=this.row;i++){
            if (matrix[i][col1] * mul2 != matrix[i][col2] * mul1) {
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
    public int rank(){
        double[][] m=this.matrix;
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
        if(this.row==r&&this.column==r)return true;
        return false;
    }
    /*
    其他矩阵运算类似这样写
     */
}
