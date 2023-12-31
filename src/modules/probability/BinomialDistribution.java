package modules.probability;

import modules.basic.Fraction;
import modules.basic.Operation;

/**
 * @author 吕顺
 * @Description: 二项分布
 * @date 2023/11/23 17:52
 */
public class BinomialDistribution {
    private int n;
    private Fraction p;
    private int k;
    private Fraction probabilityEX;
    private Fraction probabilityDX;
    private Fraction probabilityXk;

    /**
     * @param n X ~ B(n, p)中的 n
     * @param p X ~ B(n, p)中的 p
     * @Description 不带k的构造方法
     * @author 吕顺
     * @date 2023/11/23 18:02
     **/
    public BinomialDistribution(int n, Fraction p) {
        if (n <= 0) {
            throw new ArithmeticException("n的大小不合法");
        }
        double pp = Double.parseDouble(p.toDecimal());
        if (pp < 0.0 || pp > 1.0) {
            throw new ArithmeticException("p的大小不合法");
        }
        this.n = n;
        this.p = p;
        this.k = -1;
    }

    /**
     * @param n X ~ B(n, p)中的 n
     * @param p X ~ B(n, p)中的 n
     * @param k 计算P(X=k)
     * @Description 带k的构造方法，可以计算P(X=k)
     * @author 吕顺
     * @date 2023/11/23 18:04
     **/
    public BinomialDistribution(int n, Fraction p, int k) {
        if (n <= 0) {
            throw new ArithmeticException("n的大小不合法");
        }
        double pp = Double.parseDouble(p.toDecimal());
        if (pp < 0.0 || pp > 1.0) {
            throw new ArithmeticException("p的大小不合法");
        }
        if (k > n || k < 0) {
            throw new ArithmeticException("k的大小不合法");
        }
        this.n = n;
        this.p = p;
        this.k = k;
    }

    /**
     * @return java.lang.String
     * @Description 计算数学期望EX
     * @author 吕顺
     * @date 2023/11/23 18:08
     **/
    public String calEX() {
        Fraction n = new Fraction(this.n + "", "1");
        probabilityEX = n.multiply(p);
        return probabilityEX.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算方差DX
     * @author 吕顺
     * @date 2023/11/23 18:10
     **/
    public String calDX() {
        Fraction n = new Fraction(this.n + "", "1");
        Fraction one = new Fraction("1", "1");
        probabilityDX = n.multiply(p).multiply(one.subtract(p));
        return probabilityDX.toString();
    }

    /**
     * @return java.lang.String
     * @Description 计算P(X = K)
     * @author 吕顺
     * @date 2023/11/25 10:02
     **/
    public String calXk() {
        if (k == -1) {
            throw new ArithmeticException("未输入k，不可计算P(X=k)");
        }
        String combination = Operation.calCombination(k + "", n + "");
        Fraction com = new Fraction(combination, "1");
        Fraction one = new Fraction("1", "1");
        Fraction pk = one;
        for (int i = 0; i < k; i++) {
            pk = pk.multiply(p);
        }
        Fraction q = one.subtract(p);
        Fraction qnk = one;
        for (int i = 0; i < n - k; i++) {
            qnk = qnk.multiply(q);
        }
        probabilityXk = com.multiply(pk).multiply(qnk);
        return probabilityXk.toString();
    }
}