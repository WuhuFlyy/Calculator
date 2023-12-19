package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 条件概率计算：输入 P(AB) 和 P(B)，计算 P(A|B) = P(AB) / P(B) 和 P(notA|B) = 1 - P(A|B)
 * @date 2023/11/20 11:07
 */
public class Conditional {
    private final Fraction probabilityAB;
    private final Fraction probabilityB;
    private Fraction probabilityAunderB;
    private Fraction probabilitynotAunderB;

    /**
     * @param probabilityAB 事件A和B共同发生的概率
     * @param probabilityB  事件B发生的概率
     * @Description 构造方法
     * @author 吕顺
     * @date 2023/11/20 11:10
     **/
    public Conditional(Fraction probabilityAB, Fraction probabilityB) {
        double decimalP_AB = Double.parseDouble(probabilityAB.toDecimal());
        double decimalP_B = Double.parseDouble(probabilityB.toDecimal());
        if (decimalP_AB > 1.0 || decimalP_AB < 0 || decimalP_AB > decimalP_B) {
            throw new ArithmeticException("P(AB)大小不合法");
        }
        if (decimalP_B > 1.0 || decimalP_B <= 0) {
            throw new ArithmeticException("P(B)大小不合法");
        }
        this.probabilityAB = probabilityAB;
        this.probabilityB = probabilityB;
    }

    /**
     * @return java.lang.String
     * @Description 计算事件A在事件B发生条件下的概率
     * @author 吕顺
     * @date 2023/11/20 11:11
     **/
    public String calAunderB() {
        probabilityAunderB = probabilityAB.divide(probabilityB);
        return probabilityAunderB.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算事件notA在事件B发生条件下的概率
     * @author 吕顺
     * @date 2023/11/20 12:23
     **/
    public String calNotAunderB() {
        probabilityAunderB = probabilityAB.divide(probabilityB);
        Fraction one = new Fraction("1", "1");
        probabilitynotAunderB = one.subtract(probabilityAunderB);
        return probabilitynotAunderB.toString();
    }
}