package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 利用贝叶斯公式计算条件概率：输入 n，n组 P(Bi) 和 P(A|Bi)，需要计算的 P(Bj|A) 中的 j，计算 P(Bj|A)
 * @date 2023/11/20 14:40
 */
public class Bayesian {
    private int n;
    private Fraction[] Bi;
    private Fraction[] AunderBi;
    private int j;
    private Fraction BjunderA;

    /**
     * @Description  构造方法
     * @param n 事件组的个数
     * @param Bi 第i个事件Bi发生的概率
     * @param AunderBi 事件A在事件Bi发生条件下的概率
     * @param j 需要计算的 P(Bj|A) 中的 j，范围 1 <= j <= n
     * @author 吕顺
     * @date 2023/11/20 15:27
    **/
    public Bayesian(int n, Fraction[] Bi, Fraction[] AunderBi, int j){
        if(n <= 0){
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if(Bi.length != n){
            throw new ArithmeticException("P(Bi)的个数不合法");
        }
        if(AunderBi.length != n){
            throw new ArithmeticException("P(A|Bi)个数不合法");
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(Bi[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(B" + i + ")大小不合法");
            }
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(AunderBi[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(A|B" + i + ")大小不合法");
            }
        }
        if(j <= 0 || j > n){
            throw new ArithmeticException("P(Bj|A)中下标j不合法");
        }
        this.n = n;
        this.Bi = Bi;
        this.AunderBi = AunderBi;
        this.j = j;
    }

    /**
     * @Description  利用贝叶斯公式计算事件Bj在事件A发生条件下的概率
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:29
    **/
    public String calBjunderA(){
        Fraction P_ABj = Bi[j-1].multiply(AunderBi[j-1]);
        Fraction P_A = Bi[0].multiply(AunderBi[0]);
        for(int i = 1; i < n; i++){
            P_A = P_A.add(Bi[i].multiply(AunderBi[i]));
        }
        BjunderA = P_ABj.divide(P_A);
        return BjunderA.toString();
    }
}
