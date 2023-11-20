package modules.probabilityStatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 条件概率计算
 * @date 2023/11/20 11:07
 */
public class Conditional {
    public Fraction P_AB;
    public Fraction P_B;
    public Fraction P_AunderB;
    public Fraction P_notAunderB;
    public Fraction P_AundernotB;
    public Fraction P_notAundernotB;

    /**
     * @Description  构造方法
     * @param P_AB 事件A和B共同发生的概率
     * @param P_B 事件B发生的概率
     * @author 吕顺
     * @date 2023/11/20 11:10
    **/
    public Conditional(Fraction P_AB, Fraction P_B){
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
     * @Description   计算事件notA在事件B发生条件下的概率P_notAunderB
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 12:23
    **/
    public String calP_notAunderB(){

        return P_notAunderB.toString();
    }

    /**
     * @Description   计算事件A在事件notB发生条件下的概率P_AundernotB
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 12:23
    **/
    public String calP_AundernotB(){

        return P_AundernotB.toString();
    }

    /**
     * @Description   计算事件notA在事件notB发生条件下的概率P_notAundernotB
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 12:23
    **/
    public String calP_notAundernotB(){

        return P_notAundernotB.toString();
    }
}
