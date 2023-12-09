package modules.basic;

/**
 * @author 吕顺
 * @Description:  复数类
 * @date 2023/12/7 16:59
 */
public class Complex {
    private double re;
    private double im;

    /**
     * @Description 构造方法
     * @param re 实部
     * @param im 虚部
     * @author 吕顺
     * @date 2023/12/5 20:13
     **/
    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    /**
     * @Description 获得复数的实部
     * @return 实部
     * @author 吕顺
     * @date 2023/12/7 16:50
     **/
    public double getRe(){
        return re;
    }

    /**
     * @Description 获得复数的虚部
     * @return 虚部
     * @author 吕顺
     * @date 2023/12/7 16:51
     **/
    public double getIm(){
        return im;
    }

    /**
     * @Description
     * @return 复数类的toString()方法重载，保留double的九位小数
     * @author 吕顺
     * @date 2023/12/5 21:05
     **/
    @Override
    public String toString() {
        if(Math.abs(re) < 1e-10 && Math.abs(im) > 1e-10){
            return (im >= 0 ? " + " : " - ") + String.format("%.9f", Math.abs(im)) + "i";
        }
        else if(Math.abs(re) > 1e-10 && Math.abs(im) < 1e-10){
            return String.format("%.9f", re);
        }
        else if(Math.abs(re) < 1e-10 && Math.abs(im) < 1e-10){
            return "0";
        }
        return String.format("%.9f", re) + (im >= 0 ? " + " : " - ") + String.format("%.9f",Math.abs(im)) + "i";
    }

    /**
     * @Description 对一个复数开n次方, n需要大于零
     * @param complex 被开方的复数
     * @param n 开n次方
     * @return 开n次方后的复数
     * @author 吕顺
     * @date 2023/12/7 16:18
     **/
    public static Complex sqrtComplex(Complex complex, double n){
        // 模
        double r = Math.hypot(complex.re, complex.im);
        if(r > 0.0){
            // 辐角
            double a = Math.atan2(complex.im, complex.re);
            n = 1.0 / n;
            r = Math.pow(r, n);
            a *= n;
            return new Complex(r * Math.cos(a), r * Math.sin(a));
        }
        return new Complex(0, 0);
    }

    /**
     * @Description 复数加法
     * @param num 加数
     * @return 复数相加的结果
     * @author 吕顺
     * @date 2023/12/7 16:51
     **/
    public Complex add(Complex num){
        double re = this.re + num.getRe();
        double im = this.im + num.getIm();
        return new Complex(re, im);
    }

    /**
     * @Description 复数减法
     * @param num 减数
     * @return 复数相减的结果
     * @author 吕顺
     * @date 2023/12/7 16:53
     **/
    public Complex sub(Complex num){
        double re = this.re - num.getRe();
        double im = this.im - num.getIm();
        return new Complex(re, im);
    }

    /**
     * @Description 复数乘法
     * @param num 乘数
     * @return 复数相乘的结果
     * @author 吕顺
     * @date 2023/12/7 16:55
     **/
    public Complex mul(Complex num){
        double re = this.re * num.getRe() - this.im * num.getIm();
        double im = this.re * num.getIm() + this.im * num.getRe();
        return new Complex(re, im);
    }

    /**
     * @Description 复数除法
     * @param num 除数
     * @return 复数相除的结果
     * @author 吕顺
     * @date 2023/12/7 16:58
     **/
    public Complex div(Complex num){
        double denominator = Math.pow(num.getRe(), 2) + Math.pow(num.getIm(), 2);
        double re = (this.re * num.getRe() + this.im * num.getIm()) / denominator;
        double im = (this.im * num.getRe() - this.re * num.getIm()) / denominator;
        return new Complex(re, im);
    }
}
