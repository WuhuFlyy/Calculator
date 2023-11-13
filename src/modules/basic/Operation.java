package modules.basic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 岳宗翰
 * @Description: 基本加减乘除，乘方阶乘的高精度运算
 * @date 2023/11/13 16:14
 */
public class Operation {
    private static int scale=10;

    /**
     * @Description 设置除法保留小数位数
     * @param scale 要设置的保留小数位数
     * @author 岳宗翰
     * @date 2023/11/13 16:37
    **/
    public static void setScale(int scale){
        Operation.scale=scale;
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
        return A.divide(B,scale,RoundingMode.HALF_UP).toString();
    }
}
