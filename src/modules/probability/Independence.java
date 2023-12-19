package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 独立条件下一些概率的计算
 * @date 2023/11/23 16:32
 */
public class Independence {
    private Fraction[] probabilityAi;
    private int n;
    private Fraction probabilitymulA;
    private Fraction probabilitysumA;
    private Fraction probabilitysumNotA;

    /**
     * @param n             事件个数
     * @param probabilityAi 每个事件的概率
     * @Description 构造方法
     * @author 吕顺
     * @date 2023/11/23 16:35
     **/
    public Independence(int n, Fraction[] probabilityAi) {
        if (n <= 0) {
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if (probabilityAi.length != n) {
            throw new ArithmeticException("P(probabilityAi)个数不合法");
        }
        for (int i = 0; i < n; i++) {
            double decimal = Double.parseDouble(probabilityAi[i].toDecimal());
            if (decimal > 1.0 || decimal < 0) {
                throw new ArithmeticException("P(A" + i + ")大小不合法");
            }
        }
        this.n = n;
        this.probabilityAi = probabilityAi;
    }

    /**
     * @return java.lang.String
     * @Description 计算P(A1A2...An)
     * @author 吕顺
     * @date 2023/11/23 16:47
     **/
    public String calMulA() {
        probabilitymulA = probabilityAi[0];
        for (int i = 1; i < n; i++) {
            probabilitymulA = probabilitymulA.multiply(probabilityAi[i]);
        }
        return probabilitymulA.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算P(A1 + A2 + ... + An)
     * @author 吕顺
     * @date 2023/11/23 16:49
     **/
    public String calSumA() {
        Fraction one = new Fraction("1", "1");
        Fraction P_mulnotA = one.subtract(probabilityAi[0]);
        for (int i = 1; i < n; i++) {
            P_mulnotA = P_mulnotA.multiply(one.subtract(probabilityAi[i]));
        }
        probabilitysumA = one.subtract(P_mulnotA);
        return probabilitysumA.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算P(notA1 + notA2 + ... + notAn)
     * @author 吕顺
     * @date 2023/11/23 16:53
     **/
    public String calSumNotA() {
        Fraction one = new Fraction("1", "1");
        Fraction P_mulA = probabilityAi[0];
        for (int i = 1; i < n; i++) {
            P_mulA = P_mulA.multiply(probabilityAi[i]);
        }
        probabilitysumNotA = one.subtract(P_mulA);
        return probabilitysumNotA.toString();
    }
}