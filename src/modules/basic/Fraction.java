package modules.basic;

import modules.GlobalVariable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 岳宗翰
 * @Description: 分数类，支持分数的约分和其他基本运算
 * @date 2023/11/13 16:47
 */
public class Fraction {
    public String denominator;
    public String numerator;

    /**
     * @param numerator 整数小数均可
     * @Description 只通过一个数字作为分子的构造方法
     * @author 岳宗翰
     * @date 2023/11/25 13:40
     **/
    public Fraction(String numerator) {
        super();
        Fraction newFraction = Operation.toFraction(numerator);
        this.numerator = newFraction.numerator;
        this.denominator = newFraction.denominator;
    }

    /**
     * @param numerator   分子
     * @param denominator 分母
     * @Description 只提供带参构造方法（应该不会构造没有分子分母的分数吧）
     * @author 岳宗翰
     * @date 2023/11/13 16:52
     **/
    public Fraction(String numerator, String denominator) {
        super();
        if (Double.parseDouble(denominator) == 0) {
            throw new ArithmeticException("denominator can't be zero");
        } else if (Double.parseDouble(numerator) == 0) {
            this.numerator = "0";
            this.denominator = "1";
        } else {
            int len1 = numerator.length() - 1;
            int len2 = denominator.length() - 1;
            int l = 0;
            BigDecimal n = new BigDecimal(numerator);
            BigDecimal d = new BigDecimal(denominator);
            BigDecimal m = new BigDecimal(10);
            while (len1 >= 0 && numerator.charAt(len1) != '.') len1--;
            while (len2 >= 0 && denominator.charAt(len2) != '.') len2--;
            if (len1 == -1 && len2 == -1) {
                BigDecimal gcd = new BigDecimal(gcd(numerator, denominator));
                this.numerator = new BigDecimal(numerator).divideAndRemainder(gcd)[0].toString();
                this.denominator = new BigDecimal(denominator).divideAndRemainder(gcd)[0].toString();
            } else {
                if (len1 == -1) {
                    l = denominator.length() - len2;
                } else if (len2 == -1) {
                    l = numerator.length() - len1;
                } else {
                    len1 = numerator.length() - len1;
                    len2 = denominator.length() - len2;
                    l = Math.max(len1, len2);
                }
                m = m.pow(l - 1);
                n = n.multiply(m);
                d = d.multiply(m);
                String sn = n.toString();
                String sd = d.toString();
                len1 = sn.length();
                len2 = sd.length();
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                for (int i = 0; i < len1 && sn.charAt(i) != '.'; i++) {
                    s1.append(sn.charAt(i));
                }
                for (int i = 0; i < len2 && sd.charAt(i) != '.'; i++) {
                    s2.append(sd.charAt(i));
                }
                numerator = s1.toString();
                denominator = s2.toString();
                BigDecimal gcd = new BigDecimal(gcd(numerator, denominator));
                this.numerator = new BigDecimal(numerator).divideAndRemainder(gcd)[0].toString();
                this.denominator = new BigDecimal(denominator).divideAndRemainder(gcd)[0].toString();
            }
        }
    }

    /**
     * @param a 可视为分子
     * @param b 可视为分母
     * @return java.lang.String
     * @Description 求最小公约数，可用于约分
     * @author 岳宗翰
     * @date 2023/11/13 17:39
     **/
    public static String gcd(String a, String b) {
        BigDecimal A = new BigDecimal(a);
        BigDecimal B = new BigDecimal(b);
        BigDecimal[] C = A.divideAndRemainder(B);
        while (C[1].compareTo(new BigDecimal(0)) != 0) {
            A = B;
            B = C[1];
            C = A.divideAndRemainder(B);
        }
        return B.toString();
    }

    /**
     * @return java.lang.String
     * @Description 重载toString方法，直接以分数打印
     * @author 岳宗翰
     * @date 2023/11/13 18:44
     **/
    public String toString() {
        this.reduce();
        if (this.denominator.equals("1")) return this.numerator;
        return this.numerator + "/" + this.denominator;
    }

    /**
     * @return java.lang.String
     * @Description 转换为小数
     * @author 岳宗翰
     * @date 2023/11/13 18:45
     **/
    public String toDecimal() {
        BigDecimal numerator = new BigDecimal(this.numerator);
        BigDecimal denominator = new BigDecimal(this.denominator);
        return numerator.divide(denominator, GlobalVariable.decimalScale, RoundingMode.HALF_UP).toString();
    }

    /**
     * @return 约分后的分数值
     * @Description 约分运算
     * @author 岳宗翰
     * @date 2023/11/13 18:21
     **/
    public Fraction reduce() {
        BigDecimal m = new BigDecimal(-1);
        BigDecimal denominator = new BigDecimal(this.denominator);
        BigDecimal numerator = new BigDecimal(this.numerator);
        BigDecimal q = new BigDecimal(gcd(this.numerator, this.denominator));
        denominator = denominator.divideAndRemainder(q)[0];
        numerator = numerator.divideAndRemainder(q)[0];
        if (denominator.toString().charAt(0) == '-') {
            denominator = denominator.multiply(m);
            numerator = numerator.multiply(m);
        }
        this.numerator = numerator.toString();
        this.denominator = denominator.toString();
        return this;
    }

    /**
     * @param n 另一个分数
     * @return boolean
     * @Description 判断两个分数相等
     * @author 岳宗翰
     * @date 2023/11/20 13:31
     **/
    public boolean equals(Fraction n) {
        return this.numerator.equals(n.numerator) && this.denominator.equals(n.denominator);
    }

    /**
     * @param adder 分数加数
     * @return 分数和
     * @Description 分数加法
     * @author 岳宗翰
     * @date 2023/11/13 17:51
     **/
    public Fraction add(Fraction adder) {

        BigDecimal denominator1 = new BigDecimal(this.denominator);
        BigDecimal numerator1 = new BigDecimal(this.numerator);
        BigDecimal denominator2 = new BigDecimal(adder.denominator);
        BigDecimal numerator2 = new BigDecimal(adder.numerator);
        BigDecimal denominator = denominator1.multiply(denominator2);
        BigDecimal numerator3 = denominator1.multiply(numerator2);
        BigDecimal numerator4 = denominator2.multiply(numerator1);
        numerator3 = numerator3.add(numerator4);
        Fraction newFraction = new Fraction(numerator3.toString(), denominator.toString());
        return newFraction.reduce();
    }

    /**
     * @param subtractor 减数
     * @return 分数
     * @Description 分数减法
     * @author 岳宗翰
     * @date 2023/11/20 13:37
     **/
    public Fraction subtract(Fraction subtractor) {
        subtractor = subtractor.multiply(new Fraction("-1", "1"));
        return this.add(subtractor);
    }

    /**
     * @param multiplier 乘数
     * @return 乘法运算后的分数
     * @Description 分数乘法
     * @author 岳宗翰
     * @date 2023/11/13 18:29
     **/
    public Fraction multiply(Fraction multiplier) {
        BigDecimal numerator = new BigDecimal(this.numerator);
        BigDecimal denominator = new BigDecimal(this.denominator);
        numerator = numerator.multiply(new BigDecimal(multiplier.numerator));
        denominator = denominator.multiply(new BigDecimal(multiplier.denominator));
        Fraction newFraction = new Fraction(numerator.toString(), denominator.toString());
        return newFraction.reduce();
    }

    /**
     * @param divisor 除数
     * @return 除法运算后的分数
     * @Description 分数除法
     * @author 岳宗翰
     * @date 2023/11/13 18:30
     **/
    public Fraction divide(Fraction divisor) {
        BigDecimal numerator = new BigDecimal(this.numerator);
        BigDecimal denominator = new BigDecimal(this.denominator);
        numerator = numerator.multiply(new BigDecimal(divisor.denominator));
        denominator = denominator.multiply(new BigDecimal(divisor.numerator));
        Fraction newFraction = new Fraction(numerator.toString(), denominator.toString());
        return newFraction.reduce();
    }
}