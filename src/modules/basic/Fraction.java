package modules.basic;

import java.math.BigDecimal;

/**
 * @author 岳宗翰
 * @Description: 分数类，支持分数的约分和其他基本运算
 * @date 2023/11/13 16:47
 */
public class Fraction {
    private String denominator;
    private String numerator;

    /**
     * @Description 只提供带参构造方法（应该不会构造没有分子分母的分数吧）
     * @param numerator 分子
     * @param denominator 分母
     * @author 岳宗翰
     * @date 2023/11/13 16:52
    **/
    public Fraction(String numerator,String denominator){
        super();
        this.denominator=denominator;
        this.numerator=numerator;
    }

    public String toString(){
        return this.numerator+"/"+this.denominator;
    }

    /**
     * @Description 求最小公约数，可用于约分
     * @param a 可视为分子
     * @param b 可视为分母
     * @return java.lang.String
     * @author 岳宗翰
     * @date 2023/11/13 17:39
    **/
    public static String gcd(String a,String b){
        BigDecimal A=new BigDecimal(a);
        BigDecimal B=new BigDecimal(b);
        BigDecimal[] C=A.divideAndRemainder(B);
        while(C[1].compareTo(new BigDecimal(0))!=0){
            A=B;
            B=C[1];
            C=A.divideAndRemainder(B);
        }
        return B.toString();
    }

    /**
     * @Description 约分运算
     * @return 约分后的分数值
     * @author 岳宗翰
     * @date 2023/11/13 18:21
    **/
    public Fraction reduce(){
        BigDecimal m=new BigDecimal(-1);
        BigDecimal denominator=new BigDecimal(this.denominator);
        BigDecimal numerator=new BigDecimal(this.numerator);
        BigDecimal q=new BigDecimal(gcd(this.denominator,this.numerator));
        denominator=denominator.divideAndRemainder(q)[0];
        numerator=numerator.divideAndRemainder(q)[0];
        if(denominator.toString().charAt(0)=='-'){
            denominator=denominator.multiply(m);
            numerator=numerator.multiply(m);
        }
        this.numerator=numerator.toString();
        this.denominator=denominator.toString();
        return this;
    }

    /**
     * @Description 分数加法，可以当减法用
     * @param adder 分数加数
     * @return 分数和
     * @author 岳宗翰
     * @date 2023/11/13 17:51
    **/
    public Fraction add(Fraction adder){

        BigDecimal denominator1=new BigDecimal(this.denominator);
        BigDecimal numerator1=new BigDecimal(this.numerator);
        BigDecimal denominator2=new BigDecimal(adder.denominator);
        BigDecimal numerator2=new BigDecimal(adder.numerator);
        BigDecimal denominator=denominator1.multiply(denominator2);
        BigDecimal numerator3=denominator1.multiply(numerator2);
        BigDecimal numerator4=denominator2.multiply(numerator1);
        numerator3=numerator3.add(numerator4);
        Fraction newFraction=new Fraction(numerator3.toString(),denominator.toString());
        return newFraction.reduce();
    }

    /**
     * @Description 分数乘法
     * @param multiplier 乘数
     * @return 乘法运算后的分数
     * @author 岳宗翰
     * @date 2023/11/13 18:29
    **/
    public Fraction multiply(Fraction multiplier){
        BigDecimal numerator=new BigDecimal(this.numerator);
        BigDecimal denominator=new BigDecimal(this.denominator);
        numerator=numerator.multiply(new BigDecimal(multiplier.numerator));
        denominator=denominator.multiply(new BigDecimal(multiplier.denominator));
        Fraction newFraction=new Fraction(numerator.toString(),denominator.toString());
        return newFraction.reduce();
    }

    /**
     * @Description 分数除法
     * @param divisor 除数
     * @return 除法运算后的分数
     * @author 岳宗翰
     * @date 2023/11/13 18:30
    **/
    public Fraction divide(Fraction divisor){
        BigDecimal numerator=new BigDecimal(this.numerator);
        BigDecimal denominator=new BigDecimal(this.denominator);
        numerator=numerator.multiply(new BigDecimal(divisor.denominator));
        denominator=denominator.multiply(new BigDecimal(divisor.numerator));
        Fraction newFraction=new Fraction(numerator.toString(),denominator.toString());
        return newFraction.reduce();
    }
}
