import modules.basic.Fraction;
import modules.basic.Operation;
import modules.matrix.Matrix;

import java.util.Scanner;

import modules.probabilityStatistics.*;
import ui.CalculatorUI;

/**
 * @author 岳宗翰
 * @Description: 测试测试测试测试测试
 * @date 2023/11/10 21:54
 */
public class CaltulatorTest {
    public static void main(String[] args) {
//        System.out.println(new Classical(10, 7).calP_notA());
//
//        System.out.println(new Conditional(new Fraction("3", "5"), new Fraction("4", "5")).calP_notAunderB());
//
//        Fraction[] P_Bi = new Fraction[2];
//        Fraction[] P_AunderBi = new Fraction[2];
//        P_Bi[0] = new Fraction("1", "2");
//        P_Bi[1] = new Fraction("1", "3");
//        P_AunderBi[0] = new Fraction("3","5");
//        P_AunderBi[1] = new Fraction("2","5");
//        System.out.println(new TotalProbability(2, P_Bi, P_AunderBi).calP_A());
//
//        System.out.println(new Bayesian(2, P_Bi, P_AunderBi, 1).calP_BjunderA());
//
//        System.out.println(new Independence(2, P_Bi).calP_mulA());
//        System.out.println(new Independence(2, P_Bi).calP_sumA());
//        System.out.println(new Independence(2, P_Bi).calP_sumnotA());
//
//
//        //===========================//
//        System.out.println(Operation.calFactorial("10"));
//        System.out.println(Operation.calCombination("5", "10"));
//        Fraction f = new Fraction(Operation.calCombination("5", "10"), "3");
//        System.out.println(f);

        Fraction p = new Fraction("1", "3");
        //System.out.println(new BinomialDistribution(3, p).calP_Xequalk());
        System.out.println(new BinomialDistribution(3, p, 0).calP_Xequalk());
        System.out.println(new BinomialDistribution(3, p, 1).calP_Xequalk());
        System.out.println(new BinomialDistribution(3, p, 2).calP_Xequalk());
        System.out.println(new BinomialDistribution(3, p, 3).calP_Xequalk());

    }

}