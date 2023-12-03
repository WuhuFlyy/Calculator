import modules.basic.Fraction;

import modules.probability.*;
import ui.uigeneral.GeneralUI;

/**
 * @author 岳宗翰
 * @Description: 测试测试测试测试测试
 * @date 2023/11/10 21:54
 */
public class CaltulatorTest {
    public static void main(String[] args) {
        Fraction p = new Fraction("1", "3");
        System.out.println(new BinomialDistribution(3, p, 0).calXk());
        System.out.println(new BinomialDistribution(3, p, 1).calXk());
        System.out.println(new BinomialDistribution(3, p, 2).calXk());
        System.out.println(new BinomialDistribution(3, p, 3).calXk());
        new GeneralUI();
    }
}