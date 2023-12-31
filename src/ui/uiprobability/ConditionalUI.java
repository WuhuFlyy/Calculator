package ui.uiprobability;

import modules.basic.Fraction;
import modules.basic.Operation;
import modules.probability.Conditional;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 条件概率的UI
 * @date 2023/12/3 11:21
 */
public class ConditionalUI {
    public JTextField inputPAB, inputPB, outputPAUnderB, outputPNotAUnderB;
    public JScrollPane panePAB, panePB, panePAUnderB, panePNotAUnderB;
    public JLabel labelPAB, labelPB;
    public JButton btnSolve, btnBack;

    public ConditionalUI() {
        inputPAB = new JTextField("0");
        inputPB = new JTextField("0");
        panePAB = new JScrollPane(inputPAB, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePB = new JScrollPane(inputPB, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPAUnderB = new JTextField("P(A|B) ");
        outputPNotAUnderB = new JTextField("~P(A|B) ");
        panePAUnderB = new JScrollPane(outputPAUnderB, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePNotAUnderB = new JScrollPane(outputPNotAUnderB, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputPAB, panePAB, MARGIN_X, MARGIN_Y + 55);
        initInput(inputPB, panePB, MARGIN_X, MARGIN_Y + 185);
        initOutput(outputPAUnderB, panePAUnderB, MARGIN_X + 500, MARGIN_Y + 55);
        initOutput(outputPNotAUnderB, panePNotAUnderB, MARGIN_X + 500, MARGIN_Y + 185);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelPAB.setVisible(false);
            labelPB.setVisible(false);
            inputPAB.setVisible(false);
            inputPB.setVisible(false);
            outputPAUnderB.setVisible(false);
            outputPNotAUnderB.setVisible(false);
            panePAB.setVisible(false);
            panePB.setVisible(false);
            panePAUnderB.setVisible(false);
            panePNotAUnderB.setVisible(false);
            btnSolve.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化两个输入提示标签
     * @author 罗孝俊
     * @date 2023/12/3 11:22
     **/
    private void initLabel() {
        labelPAB = new JLabel("PAB");
        labelPB = new JLabel("PB");
        labelPAB.setBounds(MARGIN_X, MARGIN_Y, 300, 50);
        labelPAB.setFont(new Font("宋体", Font.PLAIN, 30));
        labelPAB.setVisible(true);
        labelPB.setBounds(MARGIN_X, MARGIN_Y + 130, 200, 50);
        labelPB.setFont(new Font("宋体", Font.PLAIN, 30));
        labelPB.setVisible(true);
        window.add(labelPAB);
        window.add(labelPB);
    }

    /**
     * @Description 求解条件概率，将结果输出在outputText上
     * @author 罗孝俊
     * @date 2023/12/3 12:11
     **/
    public void solve() {
        Fraction PAB, PB;
        try {
            if (Pattern.matches(POSITIVE_FRACTION_REGEX, inputPAB.getText())) {
                String[] stringAB = inputPAB.getText().split("/");
                PAB = new Fraction(stringAB[0], stringAB[1]);
            } else if (Pattern.matches(POSITIVE_NUMBER_REGEX, inputPAB.getText())) {
                PAB = Operation.toFraction(inputPAB.getText());
            } else {
                JOptionPane.showMessageDialog(null, "请输入一个合法的概率值", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Pattern.matches(POSITIVE_FRACTION_REGEX, inputPB.getText())) {
                String[] stringB = inputPB.getText().split("/");
                PB = new Fraction(stringB[0], stringB[1]);
            } else if (Pattern.matches(POSITIVE_NUMBER_REGEX, inputPB.getText())) {
                PB = Operation.toFraction(inputPB.getText());
            } else {
                JOptionPane.showMessageDialog(null, "请输入一个合法的概率值", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conditional conditional = new Conditional(PAB, PB);
            outputPAUnderB.setText("P(A|B) = " + conditional.calAunderB());
            outputPNotAUnderB.setText("~P(A|B) = " + conditional.calNotAunderB());
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
