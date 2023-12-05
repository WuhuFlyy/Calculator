package modules.equation;

/**
 * @author 吕顺
 * @Description: 解一元方程，次数为2-4次
 * @date 2023/12/5 14:15
 */
public class Equation {
    private double[] coefficient;

    /**
     * @Description 构造方法
     * @param coefficient 系数，按次数从大到小排列
     * @author 吕顺
     * @date 2023/12/5 14:39
     **/
    public Equation(double[] coefficient) {
        if (coefficient[0] == 0.0) {
            throw new ArithmeticException("最高次项的系数不能为0");
        }
        this.coefficient = coefficient;
    }

    /**
     * @Description 计算一元二次方程
     * @return 方程的两根x1、x2的字符串表示形式
     * @author 吕顺
     * @date 2023/12/5 14:43
     **/
    public String calBinaryEquation() {
        double a = coefficient[0], b = coefficient[1], c = coefficient[2];
        double delta = Math.pow(b, 2) - 4 * a * c;

        // 有两个不等实根
        if (delta > 0.0) {
            double re1 = (-1 * b + Math.sqrt(delta)) / (2 * a);
            double re2 = (-1 * b - Math.sqrt(delta)) / (2 * a);
            Complex complex1 = new Complex(re1, 0);
            Complex complex2 = new Complex(re2, 0);
            return "x1 = " + complex1.toString() + "\n" +
                   "x2 = " + complex2.toString();
        }
        // 有两个相等实根
        else if (delta == 0.0) {
            double re = (-1 * b) / (2 * a);
            Complex complex = new Complex(re, 0);
            return "x1 = " + complex.toString() + "\n" +
                   "x2 = " + complex.toString();
        }
        // 有两个共轭复根
        else {
            double re = (-b) / (2 * a);
            double im = Math.sqrt(-delta) / (2 * a);
            Complex complex1 = new Complex(re, im);
            Complex complex2 = new Complex(re, -im);
            return "x1 = " + complex1.toString() + "\n" +
                   "x2 = " + complex2.toString();
        }
    }

    /**
     * @Description 计算一元三次方程
     * @return 方程的三个根x1、x2、x3的字符串表示形式
     * @author 吕顺
     * @date 2023/12/5 21:38
    **/
    public String calCubicEquation(){
        double a = coefficient[0], b = coefficient[1], c = coefficient[2], d = coefficient[3];
        double B = b / a;
        double C = c / a;
        double D = d / a;
        double P = (3 * C - Math.pow(B, 2)) / 9;
        double Q = (9 * B * C - 27 * D - 2 * Math.pow(B, 3)) / 54;
        double delta = Math.pow(P, 3) + Math.pow(Q, 2);

        // 一个实根和两个复根
        if(delta > 0.0){
            double S = Math.cbrt(Q + Math.sqrt(delta));
            double T = Math.cbrt(Q - Math.sqrt(delta));
            Complex complex1 = new Complex(-B / 3 + (S + T), 0);
            Complex complex2 = new Complex(-B / 3 - (S + T) / 2, (S - T) * Math.sqrt(3) / 2);
            Complex complex3 = new Complex(-B / 3 - (S + T) / 2, -(S - T) * Math.sqrt(3) / 2);
            return "x1 = " + complex1.toString() + "\n" +
                   "x2 = " + complex2.toString() + "\n" +
                   "x3 = " + complex3.toString();
        }
        // 三个实根，其中两个肯定相等
        else if(delta == 0.0){
            double root = Math.cbrt(Q);
            Complex complex1 = new Complex(-B / 3 + 2 * root, 0);
            Complex complex2 = new Complex(-B / 3 - root, 0);
            Complex complex3 = new Complex(-B / 3 - root, 0);
            return "x1 = " + complex1.toString() + "\n" +
                   "x2 = " + complex2.toString() + "\n" +
                   "x3 = " + complex3.toString();
        }
        // 三个不等实根
        else{
            double theta = Math.acos(Q / Math.sqrt(-Math.pow(P, 3)));
            double re1 = 2 * Math.sqrt(-P) * Math.cos(theta / 3) - B / 3;
            double re2 = 2 * Math.sqrt(-P) * Math.cos((theta + 2 * Math.PI) / 3) - B / 3;
            double re3 = 2 * Math.sqrt(-P) * Math.cos((theta + 4 * Math.PI) / 3) - B / 3;
            Complex complex1 = new Complex(re1, 0);
            Complex complex2 = new Complex(re2, 0);
            Complex complex3 = new Complex(re3, 0);
            return "x1 = " + complex1.toString() + "\n" +
                   "x2 = " + complex2.toString() + "\n" +
                   "x3 = " + complex3.toString();
        }
    }
}

/**
 * @author 吕顺
 * @Description: 复数类
 * @date 2023/12/5 20:11
**/
class Complex {
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
}
