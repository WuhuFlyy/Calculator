import modules.equation.MultivariateEquation;
import modules.equation.UnivariateEquation;

/**
 * @author 岳宗翰
 * @Description: 测试测试测试测试测试
 * @date 2023/11/10 21:54
 */
public class CaltulatorTest {
    public static void main(String[] args) {
//        Fraction p = new Fraction("1", "3");
//        System.out.println(new BinomialDistribution(3, p, 0).calXk());
//        System.out.println(new BinomialDistribution(3, p, 1).calXk());
//        System.out.println(new BinomialDistribution(3, p, 2).calXk());
//        System.out.println(new BinomialDistribution(3, p, 3).calXk());
//        new GeneralUI();

//        double[] co = {1, 2, 2};
//        System.out.println(new Equation(co).calBinaryEquation());
//
//        double[] cc = {8, 12, -(8*-5-2*2*-4), 5*(4+12)-16};
//        System.out.println(new Equation(cc).calCubicEquation());


        double[] cd = {-1,-2,-3,-4,5};
        System.out.println(new UnivariateEquation(cd).solveQuarticEquation());


//        double[] cd = {-1,-2,-3,-4,5};
//        System.out.println(new UnivariateEquation(cd).solveQuarticEquation());

        double co1[] = {1,1,1}, co2[] = {2,2,2}, co3[] = {-3,-3,-3} , cons[] = {8, -2, 6};
        System.out.println(new MultivariateEquation(co1, co2, co3, cons).solveCubicEquation());
    }
}