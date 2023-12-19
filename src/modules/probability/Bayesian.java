package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 利用贝叶斯公式计算条件概率：输入 n，n组 P(Bi) 和 P(A|Bi)，需要计算的 P(Bj|A) 中的 j，计算 P(Bj|A)
 * @date 2023/11/20 14:40
 */
public class Bayesian {
    private int n;
    private Fraction[] probabilityBi;
    private Fraction[] probabilityAunderBi;
    private int j;
    private Fraction probabilityBjunderA;

    /**
     * @param n                   事件组的个数
     * @param probabilityBi       第i个事件Bi发生的概率
     * @param probabilityAunderBi 事件A在事件Bi发生条件下的概率
     * @param j                   需要计算的 P(Bj|A) 中的 j，范围 1 <= j <= n
     * @Description 构造方法
     * @author 吕顺
     * @date 2023/11/20 15:27
     **/
    public Bayesian(int n, Fraction[] probabilityBi, Fraction[] probabilityAunderBi, int j) {
        if (n <= 0) {
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if (probabilityBi.length != n) {
            throw new ArithmeticException("P(Bi)的个数不合法");
        }
        if (probabilityAunderBi.length != n) {
            throw new ArithmeticException("P(A|Bi)个数不合法");
        }
        for (int i = 0; i < n; i++) {
            double decimal = Double.parseDouble(probabilityBi[i].toDecimal());
            if (decimal > 1.0 || decimal < 0) {
                throw new ArithmeticException("P(B" + i + ")大小不合法");
            }
        }
        for (int i = 0; i < n; i++) {
            double decimal = Double.parseDouble(probabilityAunderBi[i].toDecimal());
            if (decimal > 1.0 || decimal < 0) {
                throw new ArithmeticException("P(A|B" + i + ")大小不合法");
            }
        }
        if (j <= 0 || j > n) {
            throw new ArithmeticException("P(Bj|A)中下标j不合法");
        }
        this.n = n;
        this.probabilityBi = probabilityBi;
        this.probabilityAunderBi = probabilityAunderBi;
        this.j = j;
    }

    /**
     * @return java.lang.String
     * @Description 利用贝叶斯公式计算事件Bj在事件A发生条件下的概率
     * @author 吕顺
     * @date 2023/11/23 16:29
     **/
    public String calBjunderA() {
        Fraction P_ABj = probabilityBi[j - 1].multiply(probabilityAunderBi[j - 1]);
        Fraction P_A = probabilityBi[0].multiply(probabilityAunderBi[0]);
        for (int i = 1; i < n; i++) {
            P_A = P_A.add(probabilityBi[i].multiply(probabilityAunderBi[i]));
        }
        probabilityBjunderA = P_ABj.divide(P_A);
        return probabilityBjunderA.toString();
    }
}