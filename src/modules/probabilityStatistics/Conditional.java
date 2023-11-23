package modules.probabilityStatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 条件概率计算：输入 P(AB) 和 P(B)，计算 P(A|B) = P(AB) / P(B) 和 P(notA|B) = 1 - P(A|B)
 * @date 2023/11/20 11:07
 */
public class Conditional {
    private Fraction P_AB;
    private Fraction P_B;
    private Fraction P_AunderB;
    private Fraction P_notAunderB;

    /**
     * @Description  构造方法
     * @param P_AB 事件A和B共同发生的概率
     * @param P_B 事件B发生的概率
     * @author 吕顺
     * @date 2023/11/20 11:10
    **/
    public Conditional(Fraction P_AB, Fraction P_B){
        double decimalP_AB = Double.parseDouble(P_AB.toDecimal());
        double decimalP_B = Double.parseDouble(P_B.toDecimal());
        if(decimalP_AB > 1.0 || decimalP_AB < 0 || decimalP_AB > decimalP_B){
            throw new ArithmeticException("P(AB)大小不合法");
        }
        if(decimalP_B > 1.0 || decimalP_B <= 0){
            throw new ArithmeticException("P(B)大小不合法");
        }
        this.P_AB = P_AB;
        this.P_B = P_B;
    }

    /**
     * @Description  计算事件A在事件B发生条件下的概率P_AunderB
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 11:11
    **/
    public String calP_AunderB(){
        P_AunderB = P_AB.divide(P_B);
        return P_AunderB.toString();
    }

    /**
     * @Description  计算事件notA在事件B发生条件下的概率P_notAunderB
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 12:23
    **/
    public String calP_notAunderB(){
        P_AunderB = P_AB.divide(P_B);
        Fraction one = new Fraction("1", "1");
        P_notAunderB = one.subtract(P_AunderB);
        return P_notAunderB.toString();
    }

}
