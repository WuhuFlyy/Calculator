package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 条件概率计算：输入 P(AB) 和 P(B)，计算 P(A|B) = P(AB) / P(B) 和 P(notA|B) = 1 - P(A|B)
 * @date 2023/11/20 11:07
 */
public class Conditional {
    private Fraction AB;
    private Fraction B;
    private Fraction AunderB;
    private Fraction notAunderB;

    /**
     * @Description  构造方法
     * @param AB 事件A和B共同发生的概率
     * @param B 事件B发生的概率
     * @author 吕顺
     * @date 2023/11/20 11:10
    **/
    public Conditional(Fraction AB, Fraction B){
        double decimalP_AB = Double.parseDouble(AB.toDecimal());
        double decimalP_B = Double.parseDouble(B.toDecimal());
        if(decimalP_AB > 1.0 || decimalP_AB < 0 || decimalP_AB > decimalP_B){
            throw new ArithmeticException("P(AB)大小不合法");
        }
        if(decimalP_B > 1.0 || decimalP_B <= 0){
            throw new ArithmeticException("P(B)大小不合法");
        }
        this.AB = AB;
        this.B = B;
    }

    /**
     * @Description  计算事件A在事件B发生条件下的概率
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 11:11
    **/
    public String calAunderB(){
        AunderB = AB.divide(B);
        return AunderB.toString();
    }

    /**
     * @Description  计算事件notA在事件B发生条件下的概率
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 12:23
    **/
    public String calNotAunderB(){
        AunderB = AB.divide(B);
        Fraction one = new Fraction("1", "1");
        notAunderB = one.subtract(AunderB);
        return notAunderB.toString();
    }
}
