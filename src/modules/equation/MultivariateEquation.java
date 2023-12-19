package modules.equation;

/**
 * @author 吕顺
 * @Description: 解多元一次方程，2-3元
 * @date 2023/12/9 10:55
 */
public class MultivariateEquation {
    private double[] coefficient1;
    private double[] coefficient2;
    private double[] coefficient3;
    private double[] constant;

    /**
     * @param coefficient1 第一个方程的系数 a and b
     * @param coefficient2 第二个方程的系数 c and d
     * @param constant     常量 e and f
     * @Description 二元一次方程组构造方法
     * 形如：ax + by = e   cx + dy = f
     * @author 吕顺
     * @date 2023/12/10 23:30
     **/
    public MultivariateEquation(double[] coefficient1, double[] coefficient2, double[] constant) {
        this.coefficient1 = coefficient1;
        this.coefficient2 = coefficient2;
        this.constant = constant;
    }

    /**
     * @param coefficient1 第一个方程的系数 a, b and c
     * @param coefficient2 第二个方程的系数 d, e and f
     * @param coefficient3 第三个方程的系数 g, h and i
     * @param constant     常量 u, v and w
     * @Description 三元一次方程组构造方法
     * 形如：ax + by + cz = u   dx + ey + fz = v   gx + hy + iz = w
     * @author 吕顺
     * @date 2023/12/10 23:21
     **/
    public MultivariateEquation(double[] coefficient1, double[] coefficient2, double[] coefficient3, double[] constant) {
        this.coefficient1 = coefficient1;
        this.coefficient2 = coefficient2;
        this.coefficient3 = coefficient3;
        this.constant = constant;
    }

    /**
     * @return 方程组的解，如果无解则返回 "方程组无解"
     * @Description 求解二元一次方程组
     * @author 吕顺
     * @date 2023/12/10 23:33
     **/
    public String solveBinaryEquation() {
        double a = coefficient1[0];
        double b = coefficient1[1];
        double c = coefficient2[0];
        double d = coefficient2[1];
        double e = constant[0];
        double f = constant[1];
        double denominator = a * d - b * c;
        if (Math.abs(denominator) < 1e-9) {
            throw new ArithmeticException("方程组无解");
        }
        double x, y;
        x = (d * e - b * f) / denominator;
        y = (a * f - c * e) / denominator;
        return "x = " + x + "\n" +
                "y = " + y;
    }

    /**
     * @return 方程组的解，如果无解则返回 "方程组无解"
     * @Description 求解三元一次方程组
     * @author 吕顺
     * @date 2023/12/11 0:25
     **/
    public String solveCubicEquation() {
        double p1 = coefficient1[0] * coefficient2[1] * coefficient3[2];
        double p2 = coefficient1[1] * coefficient2[2] * coefficient3[0];
        double p3 = coefficient1[2] * coefficient2[0] * coefficient3[1];
        double q1 = coefficient3[0] * coefficient2[1] * coefficient1[2];
        double q2 = coefficient3[1] * coefficient2[2] * coefficient1[0];
        double q3 = coefficient3[2] * coefficient2[0] * coefficient1[1];
        if (Math.abs(p1 + p2 + p3 - q1 - q2 - q3) < 1e-9) {
            throw new ArithmeticException("方程组无解");
        }
        // 构建增广矩阵
        double[][] augmentedMatrix = new double[3][4];
        for (int i = 0; i < 3; i++) {
            augmentedMatrix[0][i] = coefficient1[i];
            augmentedMatrix[1][i] = coefficient2[i];
            augmentedMatrix[2][i] = coefficient3[i];
        }
        for (int j = 0; j < 3; j++) {
            augmentedMatrix[j][3] = constant[j];
        }

        for (int i = 0; i < 3; i++) {
            if (augmentedMatrix[i][i] == 0) {
                for (int k = i + 1; k < 3; k++) {
                    if (augmentedMatrix[k][i] != 0) {
                        double[] temp = augmentedMatrix[i];
                        augmentedMatrix[i] = augmentedMatrix[k];
                        augmentedMatrix[k] = temp;
                        break;
                    }
                }
            }

            double pivot = augmentedMatrix[i][i];
            for (int j = i; j <= 3; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            for (int k = 0; k < 3; k++) {
                if (k != i) {
                    double factor = augmentedMatrix[k][i];
                    for (int j = i; j <= 3; j++) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                    }
                }
            }
        }

        double x, y, z;
        x = augmentedMatrix[0][augmentedMatrix[0].length - 1];
        y = augmentedMatrix[1][augmentedMatrix[1].length - 1];
        z = augmentedMatrix[2][augmentedMatrix[2].length - 1];
        return "x = " + x + "\n" +
                "y = " + y + "\n" +
                "z = " + z + "\n";
    }
}
