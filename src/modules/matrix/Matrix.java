package modules.matrix;

import modules.basic.Fraction;

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
     * @param m      矩阵数组
     * @param row    行数
     * @param column 列数
     * @Description 带参构造
     * @author 岳宗翰
     * @date 2023/11/12 15:33
     **/
    public Matrix(Fraction[][] m, int row, int column) {
        super();
        this.matrix = new Fraction[row + 1][column + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                this.matrix[i][j] = new Fraction(m[i][j].numerator, m[i][j].denominator);
            }
        }
        this.row = row;
        this.column = column;
    }

    public Matrix() {
        super();
    }

    /**
     * @param row1 要交换的行1
     * @param row2 要交换的行2
     * @Description
     * @author 岳宗翰
     * @date 2023/11/12 15:05
     **/
    private void rowELT1(int row1, int row2) {
        int row = this.row, col = this.column;
        for (int i = 1; i <= col; i++) {
            Fraction temp = this.matrix[row1][i];
            this.matrix[row1][i] = this.matrix[row2][i];
            this.matrix[row2][i] = temp;
        }
    }

    /**
     * @param row 变换行
     * @param mul 乘数
     * @Description
     * @author 岳宗翰
     * @date 2023/11/12 15:15
     **/
    private void rowELT2(int row, Fraction mul) {
        for (int i = 1; i <= this.column; i++) {
            this.matrix[row][i] = this.matrix[row][i].multiply(mul);
        }
    }

    /**
     * @param row1 行1
     * @param row2 行2
     * @param mul  乘数
     * @Description row2=row2+row1*mul
     * @author 岳宗翰
     * @date 2023/11/12 15:19
     **/
    private void rowELT3(int row1, Fraction mul, int row2) {
        for (int i = 1; i <= this.column; i++) {
            this.matrix[row2][i] = this.matrix[row1][i].multiply(mul).add(this.matrix[row2][i]);
        }
    }

    /**
     * @param row1 行1
     * @param mul  乘数
     * @param row2 行2
     * @Description row2=row2-row1*mul
     * @author 岳宗翰
     * @date 2023/12/4 13:04
     **/
    private void rowELT4(int row1, Fraction mul, int row2) {
        for (int i = 1; i <= this.column; i++) {
            this.matrix[row2][i] = this.matrix[row2][i].subtract(this.matrix[row1][i].multiply(mul));
        }
    }

    /**
     * @param col1 列1
     * @param col2 列2
     * @Description
     * @author 岳宗翰
     * @date 2023/11/12 15:21
     **/
    private void columnELT1(int col1, int col2) {
        int row = this.row, col = this.column;
        for (int i = 1; i <= row; i++) {
            Fraction temp = this.matrix[i][col1];
            this.matrix[i][col1] = this.matrix[i][col2];
            this.matrix[i][col2] = temp;
        }
    }

    /**
     * @param col 变换列
     * @param mul 乘数
     * @Description
     * @author 岳宗翰
     * @date 2023/11/12 15:25
     **/
    private void columnELT2(int col, Fraction mul) {
        for (int i = 1; i <= this.row; i++) {
            this.matrix[i][col] = this.matrix[i][col].multiply(mul);
        }
    }

    /**
     * @param col1 列1
     * @param col2 列2
     * @param mul  乘数
     * @Description
     * @author 岳宗翰
     * @date 2023/11/12 15:25
     **/
    private void columnELT3(int col1, Fraction mul, int col2) {
        for (int i = 1; i <= this.row; i++) {
            this.matrix[i][col2] = this.matrix[i][col1].multiply(mul).add(this.matrix[i][col2]);
        }
    }

    /**
     * @param row1 行1
     * @param row2 行2
     * @return boolean
     * @Description 判断两行是否等价
     * @author 岳宗翰
     * @date 2023/11/12 15:44
     **/
    private boolean equivalentRow(int row1, int row2) {
        Fraction[][] matrix = this.matrix;
        Fraction mul1 = matrix[row1][1];
        Fraction mul2 = matrix[row2][1];
        for (int i = 2; i <= this.column; i++) {
            if (!matrix[row1][i].multiply(mul2).equals(matrix[row2][i].multiply(mul1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param col1 列1
     * @param col2 列2
     * @return boolean
     * @Description 判断两列是否等价
     * @author 岳宗翰
     * @date 2023/11/12 15:46
     **/
    private boolean equivalentCol(int col1, int col2) {
        Fraction[][] matrix = this.matrix;
        Fraction mul1 = matrix[1][col1];
        Fraction mul2 = matrix[1][col2];
        for (int i = 2; i <= this.row; i++) {
            if (!matrix[i][col1].multiply(mul2).equals(matrix[i][col2].multiply(mul1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return int 该矩阵的秩
     * @Description 求矩阵的秩
     * @author 岳宗翰
     * @date 2023/11/12 13:55
     **/
    public int getRank() {
        int l = Math.max(this.row, this.column);
        int r = l;
        Fraction zero = new Fraction("0", "1");
        Fraction[][] m = new Fraction[l + 1][l + 1];
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= l; j++) {
                m[i][j] = zero;
            }
        }
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= this.column; j++) {
                m[i][j] = this.matrix[i][j];
            }
        }
        Matrix newMatrix = new Matrix(m, l, l);
        if (this.row < this.column) {
            newMatrix = newMatrix.transposition();
        }

        for (int i = 1; i <= l; i++) {
            int tag = 0;
            for (int j = i; j <= l; j++) {
                if (!newMatrix.matrix[j][i].equals(zero)) {
                    newMatrix.rowELT1(i, j);
                    tag = 1;
                    break;
                }
            }
            if (tag == 0) {
                r--;
                continue;
            }
            newMatrix.rowELT2(i, new Fraction(newMatrix.matrix[i][i].denominator, newMatrix.matrix[i][i].numerator));
            for (int j = i + 1; j <= l; j++) {
                newMatrix.rowELT4(i, newMatrix.matrix[j][i], j);
            }
        }
        this.rank = r;
        return r;
    }

    /**
     * @return boolean 是否可逆
     * @Description 判断矩阵是否可逆
     * @author 岳宗翰
     * @date 2023/11/12 13:58
     **/
    public boolean invertible() {
        int r = this.getRank();
        return this.row == r && this.column == r;
    }

    /**
     * @return modules.matrix.Matrix
     * @Description 求逆矩阵, 返回新矩阵而不改变原有矩阵
     * @author 岳宗翰
     * @date 2023/11/25 13:36
     **/
    public Matrix inverse() {
        Fraction zero = new Fraction("0", "1");
        if (!this.invertible()) {
            throw new ArithmeticException("Uninvertible");
        } else {
            Fraction[][] newMatrix = new Fraction[rank + 1][rank + 1];
            for (int i = 1; i <= rank; i++) {
                for (int j = 1; j <= rank; j++) newMatrix[i][j] = new Fraction("0");
            }
            for (int i = 1; i <= rank; i++) newMatrix[i][i] = new Fraction("1");
            Matrix thisMatrix = new Matrix(matrix, rank, rank);
            Matrix inversedMatrix = new Matrix(newMatrix, rank, rank);
            for (int i = 1; i <= rank; i++) {
                for (int j = i; j <= row; j++) {
                    if (!thisMatrix.matrix[j][i].equals(zero)) {
                        thisMatrix.rowELT1(i, j);
                        inversedMatrix.rowELT1(i, j);
                        break;
                    }
                }
                inversedMatrix.rowELT2(i, new Fraction(thisMatrix.matrix[i][i].denominator, thisMatrix.matrix[i][i].numerator));
                thisMatrix.rowELT2(i, new Fraction(thisMatrix.matrix[i][i].denominator, thisMatrix.matrix[i][i].numerator));
                for (int j = i + 1; j <= rank; j++) {
                    inversedMatrix.rowELT4(i, thisMatrix.matrix[j][i], j);
                    thisMatrix.rowELT4(i, thisMatrix.matrix[j][i], j);
                }

            }
            for (int i = rank; i > 1; i--) {
                for (int j = 1; j < i; j++) {
                    inversedMatrix.rowELT4(i, thisMatrix.matrix[j][i], j);
                    thisMatrix.rowELT4(i, thisMatrix.matrix[j][i], j);
                }
            }

            return inversedMatrix;
        }
    }

    /**
     * @param multiplier 所乘矩阵
     * @return modules.matrix.Matrix
     * @Description 矩阵乘法
     * @author 岳宗翰
     * @date 2023/12/5 14:14
     **/
    public Matrix multiply(Matrix multiplier) {
        if (!(this.column == multiplier.row)) {
            throw new ArithmeticException("Can't be multiplied");
        }
        Fraction[][] newMatrix = new Fraction[this.row + 1][multiplier.column + 1];
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= multiplier.column; j++) {
                newMatrix[i][j] = new Fraction("0");
            }
        }
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= multiplier.column; j++) {
                for (int k = 1; k <= this.column; k++) {
                    newMatrix[i][j] = newMatrix[i][j].add(this.matrix[i][k].multiply(multiplier.matrix[k][j]));
                }
            }
        }
        return new Matrix(newMatrix, this.row, multiplier.column);
    }

    /**
     * @param n 指数
     * @return modules.matrix.Matrix
     * @Description 矩阵幂函数
     * @author 岳宗翰
     * @date 2023/12/5 14:16
     **/
    public Matrix pow(long n) {
        if (this.column != this.row) {
            throw new ArithmeticException("must be square matrix");
        }
        Matrix newMatrix = new Matrix(this.matrix, this.row, this.column);
        Fraction[][] m = new Fraction[row + 1][row + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= row; j++) {
                m[i][j] = new Fraction("0", "1");
            }
        }
        for (int i = 1; i <= row; i++) m[i][i] = new Fraction("1", "1");
        Matrix thisMatrix = new Matrix(m, row, row);
        if (n < 0) {
            n = -n;
            newMatrix = newMatrix.inverse();
        }
        while (n > 0) {
            if (n % 2 == 1) {
                thisMatrix = thisMatrix.multiply(newMatrix);
            }
            newMatrix = newMatrix.multiply(newMatrix);
            n /= 2;
        }
        return thisMatrix;
    }

    /**
     * @return modules.basic.Fraction
     * @Description 求矩阵行列式
     * @author 岳宗翰
     * @date 2023/12/5 16:13
     **/
    public Fraction getDeterminant() {
        int tag = 0;
        Fraction zero = new Fraction("0", "1");
        if (row != column) {
            throw new ArithmeticException("must be square matrix");
        } else if (!this.invertible()) {
            return new Fraction("0", "1");
        } else {
            Fraction d = new Fraction("1");

            Matrix thisMatrix = new Matrix(matrix, rank, rank);
            for (int i = 1; i <= rank; i++) {
                for (int j = i; j <= row; j++) {
                    if (!thisMatrix.matrix[j][i].equals(zero)) {
                        thisMatrix.rowELT1(i, j);
                        if (i != j) {
                            tag++;
                        }
                        break;
                    }
                }
                d = d.multiply(thisMatrix.matrix[i][i]);
                thisMatrix.rowELT2(i, new Fraction(thisMatrix.matrix[i][i].denominator, thisMatrix.matrix[i][i].numerator));
                for (int j = i + 1; j <= rank; j++) {
                    thisMatrix.rowELT4(i, thisMatrix.matrix[j][i], j);
                }
            }
            if (tag % 2 == 1) {
                d = d.multiply(new Fraction("-1"));
            }
            return d;
        }
    }

    /**
     * @return modules.matrix.Matrix
     * @Description 求矩阵转置
     * @author 岳宗翰
     * @date 2023/12/5 16:14
     **/
    public Matrix transposition() {
        Fraction[][] newMatrix = new Fraction[column + 1][row + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                newMatrix[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(newMatrix, column, row);
    }
}