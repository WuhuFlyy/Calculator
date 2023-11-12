package modules.matrix;

/**
 * @author 岳宗翰
 * @Description:基本矩阵类
 * @date 2023/11/12 13:45
 */
public class Matrix {
    public int[][] matrix;
    public int row;
    public int column;
    public Matrix(int[][] m){
        super();
        this.matrix=m;
    }
    public Matrix(){
        super();
    }

    /**
     * @Description 求矩阵的秩
     * @return int
     * @author 岳宗翰
     * @date 2023/11/12 13:55
     **/
    public int rank(){

        int r=0;
        /*

         */
        return r;
    }

    /**
     * @Description 判断矩阵是否可逆
     * @return boolean
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
