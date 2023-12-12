package ui.uiequation;

import modules.basic.Fraction;
import modules.equation.UnivariateEquation;

import static ui.UIValues.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 多项式方程UI
 * @date 2023/12/8 23:54
 */
public class PolynomialUI {
    public JTextField inputTimes;
    public JTextArea inputCoefficient, outputAns;
    public JScrollPane paneTimes, paneCoefficient, paneAns;
    public JLabel labelTimes, labelCoefficient, labelAns;
    public JButton btnSolve, btnBack;

    /**
     * @Description 界面UI构造
     * @author 罗孝俊
     * @date 2023/12/9 21:32
    **/
    public PolynomialUI(){
        inputTimes = new JTextField("3");
        inputCoefficient = new JTextArea("1, 2, 3");
        outputAns = new JTextArea();
        paneTimes = new JScrollPane(inputTimes, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneCoefficient = new JScrollPane(inputCoefficient, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAns = new JScrollPane(outputAns, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputTimes, paneTimes, MARGIN_X, MARGIN_Y + 55);
        initInput(inputCoefficient, paneCoefficient, MARGIN_X, MARGIN_Y + 185);
        paneCoefficient.setBounds(MARGIN_X, MARGIN_Y + 185, 300, 400);
        initOutput(outputAns, paneAns, MARGIN_X + 500, MARGIN_Y + 55);
        paneAns.setBounds(MARGIN_X + 500, MARGIN_Y + 55, 300, 600);
        outputAns.setLineWrap(true);
//        outputAns.setWrapStyleWord(true);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelTimes.setVisible(false);
            labelCoefficient.setVisible(false);
            labelAns.setVisible(false);

            inputTimes.setVisible(false);
            inputCoefficient.setVisible(false);
            outputAns.setVisible(false);

            paneTimes.setVisible(false);
            paneCoefficient.setVisible(false);
            paneAns.setVisible(false);

            btnSolve.setVisible(false);
            new EquationGeneralUI();
            btnBack.setVisible(false);
        });

    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/9 21:32
    **/
    private void initLabel(){
        labelTimes = new JLabel("方程次数(2~4)");
        labelCoefficient = new JLabel("方程系数(从高次到低次)");
        labelAns = new JLabel("方程解");

        labelTimes.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelCoefficient.setBounds(MARGIN_X, MARGIN_Y + 130, 500, 50);
        labelAns.setBounds(MARGIN_X + 500, MARGIN_Y, 400, 50);

        labelTimes.setFont(new Font("宋体", Font.PLAIN, 24));
        labelCoefficient.setFont(new Font("宋体", Font.PLAIN, 24));
        labelAns.setFont(new Font("宋体", Font.PLAIN, 24));

        window.add(labelTimes);
        window.add(labelCoefficient);
        window.add(labelAns);
    }

    /**
     * @Description 解方程
     * @author 罗孝俊
     * @date 2023/12/9 21:32
    **/
    public void solve() {
        int num;
        if (inputTimes.getText().matches(POSITIVE_INTEGER_REGEX)) {
            num = Integer.parseInt(inputTimes.getText());
            if (num < 2 || num > 4) {
                JOptionPane.showMessageDialog(null, "方程次数应为2~4", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "方程次数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] coefficientStr = inputCoefficient.getText().split("(\\s)*,(\\s)*");
        if (coefficientStr.length != num + 1) {
            JOptionPane.showMessageDialog(null, "方程系数数量与次数不匹配", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double[] coefficient = new double[num + 1];
        try {
            for (int i = 0; i <= num; i++) {
                String str = coefficientStr[i];
                if (str.matches(FRACTION_REGEX)) {
                    String[] tmp = str.split("/");
                    coefficient[i] = Double.parseDouble(new Fraction(tmp[0], tmp[1]).toDecimal());
                } else if (str.matches(NUMBER_REGEX)) {
                    coefficient[i] = Double.parseDouble(str);
                } else {
                    JOptionPane.showMessageDialog(null, num - i + "次项输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            UnivariateEquation univariateEquation = new UnivariateEquation(coefficient);
            switch (num) {
                case 2:
                    outputAns.setText(univariateEquation.solveBinaryEquation());
                    break;
                case 3:
                    outputAns.setText(univariateEquation.solveCubicEquation());
                    break;
                case 4:
                    outputAns.setText(univariateEquation.solveQuarticEquation());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Unexpected Error", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
}
