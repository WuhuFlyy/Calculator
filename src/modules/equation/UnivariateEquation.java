package modules.equation;

import modules.basic.Complex;

/**
 * @author 吕顺
 * @Description: 解一元多次方程，次数为2-4次
 * @date 2023/12/5 14:15
 */
public class UnivariateEquation {
    private final double[] coefficient;

    /**
     * @param coefficient 系数，按次数从大到小排列
     * @Description 构造方法
     * @author 吕顺
     * @date 2023/12/5 14:39
     **/
    public UnivariateEquation(double[] coefficient) {
        if (coefficient[0] == 0.0) {
            throw new ArithmeticException("最高次项的系数不能为0");
        }
        this.coefficient = coefficient;
    }

    /**
     * @return 方程的两根x1、x2的字符串表示形式
     * @Description 计算一元二次方程
     * @author 吕顺
     * @date 2023/12/5 14:43
     **/
    public String solveBinaryEquation() {
        double a = coefficient[0], b = coefficient[1], c = coefficient[2];
        double delta = Math.pow(b, 2) - 4 * a * c;

        // 有两个不等实根
        if (delta > 0.0) {
            double re1 = (-1 * b + Math.sqrt(delta)) / (2 * a);
            double re2 = (-1 * b - Math.sqrt(delta)) / (2 * a);
            Complex complex1 = new Complex(re1, 0);
            Complex complex2 = new Complex(re2, 0);
            return "x1 = " + complex1 + "\n" +
                    "x2 = " + complex2;
        }
        // 有两个相等实根
        else if (delta == 0.0) {
            double re = (-1 * b) / (2 * a);
            Complex complex = new Complex(re, 0);
            return "x1 = " + complex + "\n" +
                    "x2 = " + complex;
        }
        // 有两个共轭复根
        else {
            double re = (-b) / (2 * a);
            double im = Math.sqrt(-delta) / (2 * a);
            Complex complex1 = new Complex(re, im);
            Complex complex2 = new Complex(re, -im);
            return "x1 = " + complex1 + "\n" +
                    "x2 = " + complex2;
        }
    }

    /**
     * @return 方程的三个根x1、x2、x3的字符串表示形式
     * @Description 计算一元三次方程
     * @author 吕顺
     * @date 2023/12/5 21:38
     **/
    public String solveCubicEquation() {
        double a = coefficient[0], b = coefficient[1], c = coefficient[2], d = coefficient[3];
        double B = b / a;
        double C = c / a;
        double D = d / a;
        double P = (3 * C - Math.pow(B, 2)) / 9;
        double Q = (9 * B * C - 27 * D - 2 * Math.pow(B, 3)) / 54;
        double delta = Math.pow(P, 3) + Math.pow(Q, 2);

        // 一个实根和两个复根
        if (delta > 0.0) {
            double S = Math.cbrt(Q + Math.sqrt(delta));
            double T = Math.cbrt(Q - Math.sqrt(delta));
            Complex complex1 = new Complex(-B / 3 + (S + T), 0);
            Complex complex2 = new Complex(-B / 3 - (S + T) / 2, (S - T) * Math.sqrt(3) / 2);
            Complex complex3 = new Complex(-B / 3 - (S + T) / 2, -(S - T) * Math.sqrt(3) / 2);
            return "x1 = " + complex1 + "\n" +
                    "x2 = " + complex2 + "\n" +
                    "x3 = " + complex3;
        }
        // 三个实根，其中两个肯定相等
        else if (delta == 0.0) {
            double root = Math.cbrt(Q);
            Complex complex1 = new Complex(-B / 3 + 2 * root, 0);
            Complex complex2 = new Complex(-B / 3 - root, 0);
            Complex complex3 = new Complex(-B / 3 - root, 0);
            return "x1 = " + complex1 + "\n" +
                    "x2 = " + complex2 + "\n" +
                    "x3 = " + complex3;
        }
        // 三个不等实根
        else {
            double theta = Math.acos(Q / Math.sqrt(-Math.pow(P, 3)));
            double re1 = 2 * Math.sqrt(-P) * Math.cos(theta / 3) - B / 3;
            double re2 = 2 * Math.sqrt(-P) * Math.cos((theta + 2 * Math.PI) / 3) - B / 3;
            double re3 = 2 * Math.sqrt(-P) * Math.cos((theta + 4 * Math.PI) / 3) - B / 3;
            Complex complex1 = new Complex(re1, 0);
            Complex complex2 = new Complex(re2, 0);
            Complex complex3 = new Complex(re3, 0);
            return "x1 = " + complex1 + "\n" +
                    "x2 = " + complex2 + "\n" +
                    "x3 = " + complex3;
        }
    }

    /**
     * @return 方程的四个根x1、x2、x3、x4的字符串表示形式
     * @Description 计算一元四次方程
     * @author 吕顺
     * @date 2023/12/5 21:56
     **/
    public String solveQuarticEquation() {
        double a = coefficient[0], b = coefficient[1], c = coefficient[2], d = coefficient[3], e = coefficient[4];
        Complex A;
        Complex B = new Complex(b / a, 0.0);
        Complex C = new Complex(c / a, 0.0);
        Complex D = new Complex(d / a, 0.0);
        Complex E = new Complex(e / a, 0.0);

        Complex P = new Complex((Math.pow(C.getRe(), 2) + 12 * E.getRe() - 3 * B.getRe() * D.getRe()) / 9, 0.0);
        Complex Q = new Complex((27 * Math.pow(D.getRe(), 2) + 2 * Math.pow(C.getRe(), 3) + 27 * E.getRe() * Math.pow(B.getRe(), 2) - 72 * C.getRe() * E.getRe() - 9 * B.getRe() * C.getRe() * D.getRe()) / 54, 0.0);
        Complex Delta = Complex.sqrtComplex(Q.mul(Q).sub(P.mul(P).mul(P)), 2.0);
        Complex u = Q.add(Delta);
        Complex v = Q.sub(Delta);

        if (Math.pow(v.getRe(), 2) + Math.pow(v.getIm(), 2) > Math.pow(u.getRe(), 2) + Math.pow(u.getIm(), 2)) {
            u = Complex.sqrtComplex(v, 3.0);
        } else {
            u = Complex.sqrtComplex(u, 3.0);
        }

        Complex y;
        if (Math.pow(u.getRe(), 2) + Math.pow(u.getIm(), 2) > 0.0) {
            v = P.div(u);
            Complex o1 = new Complex(-0.5, Math.cos(Math.PI / 6));
            Complex o2 = new Complex(-0.5, -Math.cos(Math.PI / 6));
            double m;
            double mMax = 0.0;
            Complex yMax = null;
            for (int i = 0; i < 3; i++) {
                Complex cst3 = new Complex(3.0, 0.0);
                Complex cst4 = new Complex(4.0, 0.0);
                y = u.add(v).add(C.div(cst3));
                u = u.mul(o1);
                v = v.mul(o2);
                A = B.mul(B).add(cst4.mul(y.sub(C)));
                m = Math.pow(A.getRe(), 2.0) + Math.pow(A.getIm(), 2.0);
                if (i == 0 || mMax < m) {
                    mMax = m;
                    yMax = y;
                }
            }
            y = yMax;
        } else {
            Complex cst3 = new Complex(3.0, 0.0);
            y = C.div(cst3);
        }

        Complex complex1, complex2, complex3, complex4;
        Complex cst4 = new Complex(4.0, 0.0);
        Complex cst2 = new Complex(2.0, 0.0);
        Complex cst8 = new Complex(8.0, 0.0);
        Complex cst1 = new Complex(-1.0, 0.0);
        Complex m = Complex.sqrtComplex(B.mul(B).add(cst4.mul(y.sub(C))), 2.0);
        if (Math.pow(m.getIm(), 2.0) + Math.pow(m.getRe(), 2.0) >= Double.MIN_VALUE) {
            Complex n = B.mul(y).sub(cst2.mul(D)).div(m);
            A = Complex.sqrtComplex(B.add(m).mul(B.add(m)).sub(cst8.mul(y.add(n))), 2.0);
            complex1 = B.add(m).mul(cst1).add(A).div(cst4);
            complex2 = B.add(m).mul(cst1).sub(A).div(cst4);
            A = Complex.sqrtComplex(B.sub(m).mul(B.sub(m)).sub(cst8.mul(y.sub(n))), 2.0);
            complex3 = B.sub(m).mul(cst1).add(A).div(cst4);
            complex4 = B.sub(m).mul(cst1).sub(A).div(cst4);
        } else {
            A = Complex.sqrtComplex(B.mul(B).sub(cst8.mul(y)), 2.0);
            complex1 = complex2 = B.mul(cst1).add(A).div(cst4);
            complex3 = complex4 = B.mul(cst1).sub(A).div(cst4);
        }
        return "x1 = " + complex1 + "\n" +
                "x2 = " + complex2 + "\n" +
                "x3 = " + complex3 + "\n" +
                "x4 = " + complex4;
    }
}

