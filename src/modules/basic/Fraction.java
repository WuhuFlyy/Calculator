package modules.basic;

import modules.GlobalVariable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.DoublePredicate;

/**
 * @author 岳宗翰
 * @Description: 分数类，支持分数的约分和其他基本运算
 * @date 2023/11/13 16:47
 */
public class Fraction {
    public String denominator;
    public String numerator;

    /**
     * @Description 只提供带参构造方法（应该不会构造没有分子分母的分数吧）
     * @param numerator 分子
     * @param denominator 分母
     * @author 岳宗翰
     * @date 2023/11/13 16:52
    **/
    public Fraction(String numerator,String denominator){
        super();
        if(Double.parseDouble(denominator)==0){
            throw new ArithmeticException("分母不能为0");
        }
        else if(Double.parseDouble(numerator)==0){
            this.numerator="0";
            this.denominator="1";
        }
        else{
            int len1=numerator.length()-1;
            int len2=denominator.length()-1;
            int l=0;
            BigDecimal n=new BigDecimal(numerator);
            BigDecimal d=new BigDecimal(denominator);
            BigDecimal m=new BigDecimal(10);
            while(len1>=0&&numerator.charAt(len1)!='.')len1--;
            while(len2>=0&&denominator.charAt(len2)!='.')len2--;
            if(len1==-1&&len2==-1){
                this.numerator=numerator;
                this.denominator=denominator;
            }
            else {
                if (len1 == -1) {
                    l = denominator.length() - len2;
                } else if (len2 == -1) {
                    l = numerator.length() - len1;
                } else {
                    len1 = numerator.length() - len1;
                    len2 = denominator.length() - len2;
                    l = Math.max(len1, len2);
                }
                m=m.pow(l-1);
                n=n.multiply(m);
                d=d.multiply(m);
                String sn=n.toString();
                String sd=d.toString();
                len1=sn.length();
                len2=sd.length();
                StringBuilder s1= new StringBuilder();
                StringBuilder s2= new StringBuilder();
                for(int i=0;i<len1&&sn.charAt(i)!='.';i++){
                    s1.append(sn.charAt(i));
                }
                for(int i=0;i<len2&&sd.charAt(i)!='.';i++){
                    s2.append(sd.charAt(i));
                }
                this.numerator=s1.toString();
                this.denominator=s2.toString();
            }
        }
    }

    /**
     * @Description 重载toString方法，直接以分数打印
     * @return java.lang.String
     * @author 岳宗翰
     * @date 2023/11/13 18:44
    **/
    public String toString(){
        this.reduce();
        if(this.denominator.equals("1"))return this.numerator;
        return this.numerator+"/"+this.denominator;
    }

    /**
     * @Description 转换为小数
     * @return java.lang.String
     * @author 岳宗翰
     * @date 2023/11/13 18:45
    **/
    public String toDecimal(){
        BigDecimal numerator=new BigDecimal(this.numerator);
        BigDecimal denominator=new BigDecimal(this.denominator);
        return numerator.divide(denominator,GlobalVariable.decimalScale, RoundingMode.HALF_UP).toString();
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
        BigDecimal q=new BigDecimal(gcd(this.numerator,this.denominator));
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

    public boolean equals(Fraction n){
        return this.numerator.equals(n.numerator)&&this.denominator.equals(n.denominator);
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
