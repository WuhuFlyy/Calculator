package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 利用全概率公式计算概率：输入 n，n组 P(Bi) 和 P(A|Bi)，计算P(A)
 * @date 2023/11/20 14:12
 */
public class TotalProbability {
    private int n;
    private Fraction probabilityA;
    private Fraction[] probabilityBi;
    private Fraction[] probabilityAunderBi;

    /**
     * @param n                   事件组的个数
     * @param probabilityBi       第i个事件Bi发生的概率
     * @param probabilityAunderBi 事件A在事件Bi发生条件下的概率
     * @Description
     * @author 吕顺
     * @date 2023/11/20 14:19
     **/
    public TotalProbability(int n, Fraction[] probabilityBi, Fraction[] probabilityAunderBi) {
        if (n <= 0) {
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if (probabilityBi.length != n) {
            throw new ArithmeticException("P(Bi)个数不合法");
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
        this.n = n;
        this.probabilityBi = probabilityBi;
        this.probabilityAunderBi = probabilityAunderBi;
    }

    /**
     * @return java.lang.String
     * @Description 利用全概率公式计算事件A发生的概率
     * @author 吕顺
     * @date 2023/11/20 14:39
     **/
    public String calA() {
        probabilityA = probabilityBi[0].multiply(probabilityAunderBi[0]);
        for (int i = 1; i < n; i++) {
            probabilityA = probabilityA.add(probabilityBi[i].multiply(probabilityAunderBi[i]));
        }
        return probabilityA.toString();
    }
}