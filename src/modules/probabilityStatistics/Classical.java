package modules.probabilityStatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 古典概型的概率计算
 * @date 2023/11/20 10:14
 */

public class Classical {
    public int n;
    public int k;
    public Fraction P_A;
    public Fraction P_notA;

    /**
     * @Description 构造方法
     * @param n 样本空间中包含的基本事件总数
     * @param k 事件A发生包含的基本事件数
     * @author 吕顺
     * @date 2023/11/20 10:31
    **/
    public Classical(int n, int k){
        this.n = n;
        this.k = k;
    }

    /**
     * @Description 计算事件A发生的概率P_A
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 10:57
    **/
    public String calP_A(){
        String numerator = Integer.toString(k);
        String denominator = Integer.toString(n);
        P_A = new Fraction(numerator, denominator);
        return P_A.toString();
    }

    /**
     * @Description 计算事件notA发生的概率P_notA
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 11:05
    **/
    public String calP_notA(){
        String numerator = Integer.toString(n-k);
        String denominator = Integer.toString(n);
        P_notA = new Fraction(numerator, denominator);
        return P_notA.toString();
    }
}
