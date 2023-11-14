package modules.basic;

import modules.GlobalVariable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 岳宗翰
 * @Description: 基本加减乘除，乘方阶乘的高精度运算
 * @date 2023/11/13 16:14
 */
public class Operation {

    /**
     * @Description  小数化分数
     * @param decimal 小数参数
     * @return Fraction
     * @author 岳宗翰
     * @date 2023/11/14 14:18
    **/
    public static Fraction toFraction(String decimal){
        int l=1;
        int len=decimal.length();
        for(;l<=len&&decimal.charAt(l-1)!='.';l++);
        if(l==len+1)l--;
        l=len-l;
        BigDecimal denominator=new BigDecimal(10);
        denominator=denominator.pow(l);
        StringBuilder s= new StringBuilder();
        for(int i=0;i<len;i++){
            if(decimal.charAt(i)!='.'){
                s.append(decimal.charAt(i));
            }
        }
        BigDecimal numerator=new BigDecimal(s.toString());
        Fraction newFraction=new Fraction(numerator.toString(),denominator.toString());
        return newFraction.reduce();
    }

    /**
     * @Description 高精度加法运算
     * @param a 加数
     * @param b 加数
     * @return 字符串
     * @author 岳宗翰
     * @date 2023/11/13 16:19
    **/
    public static String add(String a,String b){
        BigDecimal A=new BigDecimal(a);
        BigDecimal B=new BigDecimal(b);
        return A.add(B).toString();
    }

    /**
     * @Description 高精度减法
     * @param a 被减数
     * @param b 减数
     * @return 字符串
     * @author 岳宗翰
     * @date 2023/11/13 16:23
    **/
    public static String subtract(String a,String b){
        BigDecimal A=new BigDecimal(a);
        BigDecimal B=new BigDecimal(b);
        return A.subtract(B).toString();
    }

    /**
     * @Description 高精度乘法
     * @param a 乘数
     * @param b 乘数
     * @return 字符串
     * @author 岳宗翰
     * @date 2023/11/13 16:39
    **/
    public static String multiply(String a,String b){
        BigDecimal A=new BigDecimal(a);
        BigDecimal B=new BigDecimal(b);
        return A.multiply(B).toString();
    }

    /**
     * @Description 高精度除法
     * @param a 被除数
     * @param b 除数
     * @return 字符串
     * @author 岳宗翰
     * @date 2023/11/13 16:40
    **/
    public static String divide(String a, String b){
        BigDecimal A=new BigDecimal(a);
        BigDecimal B=new BigDecimal(b);
        return A.divide(B, GlobalVariable.decimalScale,RoundingMode.HALF_UP).toString();
    }

    /**
     * @Description 整数指数的高精度运算
     * @param base 底数
     * @param exp 整数型指数
     * @return BigDecimal
     * @author 岳宗翰
     * @date 2023/11/14 15:20
    **/
    private static BigDecimal powInt(BigDecimal base,BigDecimal exp){
        if(base.doubleValue()==0&&exp.doubleValue()==0){
            throw new ArithmeticException("底数和指数不能同时为0");
        }
        BigDecimal A=new BigDecimal(base.toString());
        String B=exp.toString();
        BigDecimal result=new BigDecimal(1);
        BigDecimal one=new BigDecimal(1);
        BigDecimal two=new BigDecimal(2);
        while(!B.equals("0")){
            BigDecimal e=new BigDecimal(B);
            if(e.divideAndRemainder(two)[1].compareTo(one)==0){
                result=result.multiply(A);
                e=e.subtract(one);
            }
            e=e.divide(two,0,RoundingMode.HALF_UP);
            A=A.pow(2);
            B=e.toString();
        }
        return result;
    }
    /**
     * @Description  高精度指数求值
     * @param base 底数
     * @param exp 指数
     * @return String
     * @author 岳宗翰
     * @date 2023/11/14 16:25
    **/
    public static String pow(String base,String exp){

        BigDecimal A=new BigDecimal(base);
        BigDecimal result;
        boolean flag;
        BigDecimal zero=new BigDecimal(0);
        BigDecimal one=new BigDecimal(1);
        BigDecimal two=new BigDecimal(2);
        Fraction F=toFraction(exp);
        BigDecimal numerator=new BigDecimal(F.numerator);
        BigDecimal denominator=new BigDecimal(F.denominator);
        BigDecimal Base=powInt(A,numerator);

        if(F.denominator.equals("1")){
            return Base.toString();
        }
        if(Base.compareTo(one)>0){
            flag=true;
        }
        else if(Base.compareTo(one)<0){
            flag=false;
        }
        else{
            return "1";
        }

        BigDecimal left=new BigDecimal(0);
        BigDecimal right=new BigDecimal(Base.toString());
        BigDecimal mid;
        BigDecimal esp= BigDecimal.valueOf(Math.pow(10, -1 * GlobalVariable.decimalScale));

        while(right.subtract(left).compareTo(esp)>0){
            mid=right.add(left).divide(two,GlobalVariable.decimalScale,RoundingMode.HALF_UP);
            result=powInt(mid,denominator);
            if(flag){
                if(result.compareTo(Base)>0){
                    right=mid.add(zero);
                }
                else{
                    left=mid.add(zero);
                }
            }
            else{
                if(result.compareTo(Base)<0){
                    right=mid.add(zero);
                }
                else{
                    left=mid.add(zero);
                }
            }
        }
        return left.toString();
    }

}
