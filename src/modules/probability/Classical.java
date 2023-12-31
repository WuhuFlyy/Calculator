package modules.probability;

import modules.basic.Fraction;

/**
 * @author 吕顺
 * @Description: 古典概型的概率计算：输入 n 和 k，计算 P(A) = n / k
 * @date 2023/11/20 10:14
 */

public class Classical {
    private int n;
    private int k;
    private Fraction probabilityA;
    private Fraction probabilitynotA;

    /**
     * @param n 样本空间中包含的基本事件总数
     * @param k 事件A发生包含的基本事件数
     * @Description 构造方法
     * @author 吕顺
     * @date 2023/11/20 10:31
     **/
    public Classical(int n, int k) {
        if (n <= 0) {
            throw new ArithmeticException("样本空间中包含的基本事件总数n不合法");
        }
        if (k < 0 || k > n) {
            throw new ArithmeticException("事件A发生包含的基本事件数k不合法");
        }
        this.n = n;
        this.k = k;
    }

    /**
     * @return java.lang.String
     * @Description 计算事件A发生的概率
     * @author 吕顺
     * @date 2023/11/20 10:57
     **/
    public String calA() {
        String numerator = Integer.toString(k);
        String denominator = Integer.toString(n);
        probabilityA = new Fraction(numerator, denominator);
        return probabilityA.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算事件notA发生的概率
     * @author 吕顺
     * @date 2023/11/20 11:05
     **/
    public String calNotA() {
        String numerator = Integer.toString(n - k);
        String denominator = Integer.toString(n);
        probabilitynotA = new Fraction(numerator, denominator);
        return probabilitynotA.toString();
    }
}