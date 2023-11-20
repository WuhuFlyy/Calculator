import modules.basic.Fraction;
import modules.basic.Operation;
import modules.matrix.Matrix;

import java.util.Scanner;
import ui.CalculatorUI;

/**
 * @author 岳宗翰
 * @Description: 测试测试测试测试测试
 * @date 2023/11/10 21:54
 */
public class CaltulatorTest {
    public static void main(String[] args) {

        System.out.println(new Fraction("3.5","4.5").subtract(new Fraction("4","9")));

        new CalculatorUI();

    }

}