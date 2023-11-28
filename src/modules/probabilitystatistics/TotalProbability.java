package modules.probabilitystatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 利用全概率公式计算概率：输入 n，n组 P(Bi) 和 P(A|Bi)，计算P(A)
 * @date 2023/11/20 14:12
 */
public class TotalProbability {
    private int n;
    private Fraction P_A;
    private Fraction[] P_Bi;
    private Fraction[] P_AunderBi;

    /**
     * @Description
     * @param n 事件组的个数
     * @param P_Bi 第i个事件Bi发生的概率
     * @param P_AunderBi 事件A在事件Bi发生条件下的概率
     * @author 吕顺
     * @date 2023/11/20 14:19
    **/
    public TotalProbability(int n, Fraction[] P_Bi, Fraction[] P_AunderBi){
        if(n <= 0){
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if(P_Bi.length != n){
            throw new ArithmeticException("P(Bi)个数不合法");
        }
        if(P_AunderBi.length != n){
            throw new ArithmeticException("P(A|Bi)个数不合法");
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(P_Bi[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(B" + i + ")大小不合法");
            }
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(P_AunderBi[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(A|B" + i + ")大小不合法");
            }
        }
        this.n = n;
        this.P_Bi = P_Bi;
        this.P_AunderBi = P_AunderBi;
    }

    /**
     * @Description  利用全概率公式计算事件A发生的概率P_A
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/20 14:39
    **/
    public String calP_A(){
        P_A = P_Bi[0].multiply(P_AunderBi[0]);
        for(int i = 1; i < n; i++){
            P_A = P_A.add(P_Bi[i].multiply(P_AunderBi[i]));
        }
        return P_A.toString();
    }
}
