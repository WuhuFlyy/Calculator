package ui.uiprobability;

import modules.basic.Fraction;
import modules.probability.BinomialDistribution;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 二项分布UI
 * @date 2023/12/12 13:34
 */
public class BinomialUI {
    public JTextField inputK, inputN, inputP, outputPK, outputE, outputD;
    public JLabel labelK, labelN, labelP;
    public JScrollPane paneK, paneN, paneP, panePK, paneE, paneD;
    public JButton btnSolve, btnBack;

    /**
     * @Description 二项分布UI构造方法
     * @author 罗孝俊
     * @date 2023/12/12 13:39
     **/
    public BinomialUI() {
        inputK = new JTextField("0");
        inputN = new JTextField("0");
        inputP = new JTextField("0");
        paneK = new JScrollPane(inputK, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneN = new JScrollPane(inputN, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneP = new JScrollPane(inputP, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPK = new JTextField("P(X=k) = ");
        outputE = new JTextField("E(X) = ");
        outputD = new JTextField("D(X) = ");
        panePK = new JScrollPane(outputPK, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneE = new JScrollPane(outputE, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneD = new JScrollPane(outputD, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputN, paneN, MARGIN_X, MARGIN_Y + 55);
        initInput(inputP, paneP, MARGIN_X, MARGIN_Y + 185);
        initInput(inputK, paneK, MARGIN_X, MARGIN_Y + 315);
        initOutput(outputPK, panePK, MARGIN_X + 500, MARGIN_Y + 55);
        initOutput(outputE, paneE, MARGIN_X + 500, MARGIN_Y + 185);
        initOutput(outputD, paneD, MARGIN_X + 500, MARGIN_Y + 315);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelK.setVisible(false);
            labelN.setVisible(false);
            labelP.setVisible(false);

            inputK.setVisible(false);
            inputN.setVisible(false);
            inputP.setVisible(false);
            outputPK.setVisible(false);
            outputE.setVisible(false);
            outputD.setVisible(false);

            paneK.setVisible(false);
            paneN.setVisible(false);
            paneP.setVisible(false);
            panePK.setVisible(false);
            paneE.setVisible(false);
            paneD.setVisible(false);

            btnSolve.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/12 13:47
     **/
    private void initLabel() {
        labelN = new JLabel("X~B(n,p), n = ");
        labelP = new JLabel("X~B(n,p), p = ");
        labelK = new JLabel("待求P(X=k)中的k");

        labelN.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelP.setBounds(MARGIN_X, MARGIN_Y + 130, 400, 50);
        labelK.setBounds(MARGIN_X, MARGIN_Y + 260, 400, 50);

        labelN.setFont(new Font("宋体", Font.PLAIN, 30));
        labelP.setFont(new Font("宋体", Font.PLAIN, 30));
        labelK.setFont(new Font("宋体", Font.PLAIN, 30));

        window.add(labelN);
        window.add(labelP);
        window.add(labelK);
    }

    /**
     * @Description 求解二项分布EX, DX和P(X=k)
     * @author 罗孝俊
     * @date 2023/12/12 13:53
     **/
    private void solve() {
        if (!inputK.getText().matches(INTEGER_REGEX)) {
            JOptionPane.showMessageDialog(null, "k应为非负整数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!inputN.getText().matches(POSITIVE_INTEGER_REGEX)) {
            JOptionPane.showMessageDialog(null, "n应为正整数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!inputP.getText().matches(POSITIVE_FRACTION_REGEX) && !inputP.getText().matches(POSITIVE_NUMBER_REGEX)) {
            JOptionPane.showMessageDialog(null, "P应为[0,1]之间的小数或分数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int n = Integer.parseInt(inputN.getText());
        int k = Integer.parseInt(inputK.getText());
        if (k < 0) {
            JOptionPane.showMessageDialog(null, "k应为非负整数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Fraction p = null;
        try {
            if (inputP.getText().matches(POSITIVE_FRACTION_REGEX)) {
                String[] tmp = inputP.getText().split("/");
                p = new Fraction(tmp[0], tmp[1]);
            } else if (inputP.getText().matches(POSITIVE_NUMBER_REGEX)) {
                p = new Fraction(inputP.getText());
            }
            BinomialDistribution binomialDistribution = new BinomialDistribution(n, p, k);
            outputE.setText("E(X) = " + binomialDistribution.calEX());
            outputD.setText("D(X) = " + binomialDistribution.calDX());
            outputPK.setText("P(X=k) = " + binomialDistribution.calXk());
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
