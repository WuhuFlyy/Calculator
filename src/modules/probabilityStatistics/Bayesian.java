package modules.probabilityStatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 利用贝叶斯公式计算条件概率：输入 n，n组 P(Bi) 和 P(A|Bi)，需要计算的 P(Bj|A) 中的 j，计算 P(Bj|A)
 * @date 2023/11/20 14:40
 */
public class Bayesian {
    private int n;
    private Fraction[] P_Bi;
    private Fraction[] P_AunderBi;
    private int j;
    private Fraction P_BjunderA;

    /**
     * @Description  构造方法
     * @param n 事件组的个数
     * @param P_Bi 第i个事件Bi发生的概率
     * @param P_AunderBi 事件A在事件Bi发生条件下的概率
     * @param j 需要计算的 P(Bj|A) 中的 j，范围 1 <= j <= n
     * @author 吕顺
     * @date 2023/11/20 15:27
    **/
    public Bayesian(int n, Fraction[] P_Bi, Fraction[] P_AunderBi, int j){
        if(n <= 0){
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if(P_Bi.length != n){
            throw new ArithmeticException("P(Bi)的个数不合法");
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
        if(j <= 0 || j > n){
            throw new ArithmeticException("P(Bj|A)中下标j不合法");
        }
        this.n = n;
        this.P_Bi = P_Bi;
        this.P_AunderBi = P_AunderBi;
        this.j = j;
    }

    /**
     * @Description  利用贝叶斯公式计算事件Bj在1事件A发生条件下的概率P_BjunderA
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:29
    **/
    public String calP_BjunderA(){
        Fraction P_ABj = P_Bi[j-1].multiply(P_AunderBi[j-1]);
        Fraction P_A = P_Bi[0].multiply(P_AunderBi[0]);
        for(int i = 1; i < n; i++){
            P_A = P_A.add(P_Bi[i].multiply(P_AunderBi[i]));
        }
        P_BjunderA = P_ABj.divide(P_A);
        return P_BjunderA.toString();
    }
}
