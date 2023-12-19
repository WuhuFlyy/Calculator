package ui.uiprobability;

import modules.probability.Classical;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 古典概型
 * @date 2023/11/26 12:13
 */
public class ClassicalUI {
    public JTextField inputK, inputN, outputPA, outputPNotA;
    public JScrollPane paneK, paneN, panePA, panePNotA;
    public JLabel labelK, labelN;
    public JButton btnSolve, btnBack;

    /**
     * @Description 古典概率UI设置
     * @author 罗孝俊
     * @date 2023/12/3 11:26
     **/
    public ClassicalUI() {
        inputK = new JTextField("0");
        inputN = new JTextField("0");
        paneK = new JScrollPane(inputK, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneN = new JScrollPane(inputN, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPA = new JTextField("P(A) ");
        outputPNotA = new JTextField("~P(A) ");
        panePA = new JScrollPane(outputPA, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePNotA = new JScrollPane(outputPNotA, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputK, paneK, MARGIN_X, MARGIN_Y + 55);
        initInput(inputN, paneN, MARGIN_X, MARGIN_Y + 185);
        initOutput(outputPA, panePA, MARGIN_X + 500, MARGIN_Y + 55);
        initOutput(outputPNotA, panePNotA, MARGIN_X + 500, MARGIN_Y + 185);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelK.setVisible(false);
            labelN.setVisible(false);
            inputK.setVisible(false);
            inputN.setVisible(false);
            outputPA.setVisible(false);
            outputPNotA.setVisible(false);
            paneK.setVisible(false);
            paneN.setVisible(false);
            panePA.setVisible(false);
            panePNotA.setVisible(false);
            btnSolve.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description solve按钮的点击事件
     * @author 罗孝俊
     * @date 2023/12/3 11:22
     **/
    private void solve() {
        if (!inputK.getText().matches(POSITIVE_INTEGER_REGEX) || !inputN.getText().matches(POSITIVE_INTEGER_REGEX)) {
            JOptionPane.showMessageDialog(null, "请输入一个正整数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int n = Integer.parseInt(inputN.getText());
        int k = Integer.parseInt(inputK.getText());
        try {
            Classical classical = new Classical(n, k);
            outputPA.setText("P(A) = " + classical.calA());
            outputPNotA.setText("~P(A) = " + classical.calNotA());
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @Description 初始化两个输入提示标签
     * @author 罗孝俊
     * @date 2023/12/3 11:22
     **/
    private void initLabel() {
        labelK = new JLabel("A包含的基本事件数k");
        labelN = new JLabel("样本空间事件总数N");
        labelK.setBounds(MARGIN_X, MARGIN_Y, 300, 50);
        labelK.setFont(new Font("宋体", Font.PLAIN, 30));
        labelN.setBounds(MARGIN_X, MARGIN_Y + 130, 300, 50);
        labelN.setFont(new Font("宋体", Font.PLAIN, 30));
        window.add(labelK);
        window.add(labelN);
    }
}