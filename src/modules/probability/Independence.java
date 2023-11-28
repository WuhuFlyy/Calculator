<<<<<<<< HEAD:src/modules/probabilitystatistics/Independence.java
package modules.probabilitystatistics;
========
package modules.probability;
>>>>>>>> 44979bd461e40833c695cc715e595d57adef151f:src/modules/probability/Independence.java

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 独立条件下一些概率的计算
 * @date 2023/11/23 16:32
 */
public class Independence {
    private Fraction[] Ai;
    private int n;
    private Fraction mulA;
    private Fraction sumA;
    private Fraction sumNotA;

    /**
     * @Description  构造方法
     * @param n 事件个数
     * @param Ai 每个事件的概率
     * @author 吕顺
     * @date 2023/11/23 16:35
    **/
    public Independence(int n, Fraction[] Ai){
        if(n <= 0){
            throw new ArithmeticException("事件组的个数n不合法");
        }
        if(Ai.length != n){
            throw new ArithmeticException("P(Ai)个数不合法");
        }
        for(int i = 0; i < n; i++){
            double decimal = Double.parseDouble(Ai[i].toDecimal());
            if(decimal > 1.0 || decimal < 0){
                throw new ArithmeticException("P(A" + i + ")大小不合法");
            }
        }
        this.n = n;
        this.Ai = Ai;
    }

    /**
     * @Description  计算P(A1A2...An)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:47
    **/
    public String calMulA(){
        mulA = Ai[0];
        for(int i = 1; i < n; i++){
            mulA = mulA.multiply(Ai[i]);
        }
        return mulA.toString();
    }

    /**
     * @Description  计算P(A1+A2+...+An)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:49
    **/
    public String calSumA(){
        Fraction one = new Fraction("1", "1");
        Fraction P_mulnotA = one.subtract(Ai[0]);
        for(int i = 1; i < n; i++){
            P_mulnotA = P_mulnotA.multiply(one.subtract(Ai[i]));
        }
        sumA = one.subtract(P_mulnotA);
        return sumA.toString();
    }

    /**
     * @Description  计算P(notA1+notA2+...+notAn)
     * @return java.lang.String
     * @author 吕顺
     * @date 2023/11/23 16:53
    **/
    public String calSumNotA(){
        Fraction one = new Fraction("1", "1");
        Fraction P_mulA = Ai[0];
        for(int i = 1; i < n; i++){
            P_mulA = P_mulA.multiply(Ai[i]);
        }
        sumNotA = one.subtract(P_mulA);
        return sumNotA.toString();
    }
}
