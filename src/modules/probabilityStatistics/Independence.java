package modules.probabilityStatistics;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 独立条件下一些概率的计算
 * @date 2023/11/23 16:32
 */
public class Independence {
    private Fraction[] P_Ai;
    private int n;
    private Fraction P_mulA;
    private Fraction P_sumA;
    private Fraction P_sumnotA;

    /**
     * @Description  构造方法
     * @param n 事件个数
     * @param P_Ai 每个事件的概率
     * @author 吕顺
     * @date 2023/11/23 16:35
    **/
    public Independence(int n, Fraction[] P_Ai){
        if(n <= 0){
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if(P_Ai.length != n){
            throw new ArithmeticException("P(Ai)个数不合法");
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(P_Ai[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(A" + i + ")大小不合法");
            }
        }
        this.n = n;
        this.P_Ai = P_Ai;
    }

    /**
     * @Description  计算P(A1A2...An)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:47
    **/
    public String calP_mulA(){
        P_mulA = P_Ai[0];
        for(int i = 1; i < n; i++){
            P_mulA = P_mulA.multiply(P_Ai[i]);
        }
        return P_mulA.toString();
    }

    /**
     * @Description  计算P(A1+A2+...+An)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:49
    **/
    public String calP_sumA(){
        Fraction one = new Fraction("1", "1");
        Fraction P_mulnotA = one.subtract(P_Ai[0]);
        for(int i = 1; i < n; i++){
            P_mulnotA = P_mulnotA.multiply(one.subtract(P_Ai[i]));
        }
        P_sumA = one.subtract(P_mulnotA);
        return P_sumA.toString();
    }

    /**
     * @Description  计算P(notA1+notA2+...+notAn)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:53
    **/
    public String calP_sumnotA(){
        Fraction one = new Fraction("1", "1");
        Fraction P_mulA = P_Ai[0];
        for(int i = 1; i < n; i++){
            P_mulA = P_mulA.multiply(P_Ai[i]);
        }
        P_sumnotA = one.subtract(P_mulA);
        return P_sumnotA.toString();
    }
}
