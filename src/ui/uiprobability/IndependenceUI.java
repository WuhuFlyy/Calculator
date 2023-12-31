package ui.uiprobability;

import modules.basic.Fraction;
import modules.basic.Operation;
import modules.probability.Independence;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 独立事件的相关计算UI
 * @date 2023/12/3 12:47
 */
public class IndependenceUI {
    public JTextField inputN, outputPMul, outputPSum, outPutPSumNot;
    public JTextArea inputPEvents;
    public JScrollPane paneN, panePEvents, panePMul, panePSum, panePSumNot;
    public JLabel labelN, labelPEvents, labelPMul, labelPSum, labelPSumNot;
    public JButton btnSolve, btnBack;

    public IndependenceUI() {
        inputN = new JTextField("3");
        inputPEvents = new JTextArea("0.1 0.3 1/7");
        paneN = new JScrollPane(inputN, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePEvents = new JScrollPane(inputPEvents, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPMul = new JTextField("0");
        outputPSum = new JTextField("0");
        outPutPSumNot = new JTextField("0");
        panePMul = new JScrollPane(outputPMul, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePSum = new JScrollPane(outputPSum, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePSumNot = new JScrollPane(outPutPSumNot, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputN, paneN, MARGIN_X, MARGIN_Y + 55);
        initInput(inputPEvents, panePEvents, MARGIN_X, MARGIN_Y + 185);
        panePEvents.setBounds(MARGIN_X, MARGIN_Y + 185, 300, 100);
        inputPEvents.setLineWrap(true);
        initOutput(outputPMul, panePMul, MARGIN_X + 500, MARGIN_Y + 55);
        initOutput(outputPSum, panePSum, MARGIN_X + 500, MARGIN_Y + 185);
        initOutput(outPutPSumNot, panePSumNot, MARGIN_X + 500, MARGIN_Y + 315);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });


        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelN.setVisible(false);
            labelPEvents.setVisible(false);
            labelPMul.setVisible(false);
            labelPSum.setVisible(false);
            labelPSumNot.setVisible(false);
            inputN.setVisible(false);
            inputPEvents.setVisible(false);
            outputPMul.setVisible(false);
            outputPSum.setVisible(false);
            outPutPSumNot.setVisible(false);
            paneN.setVisible(false);
            panePEvents.setVisible(false);
            panePMul.setVisible(false);
            panePSum.setVisible(false);
            panePSumNot.setVisible(false);
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
        labelN = new JLabel("事件总数N");
        labelPEvents = new JLabel("各事件概率PAi");
        labelPMul = new JLabel("P(A1A2...An)");
        labelPSum = new JLabel("P(A1+A2+...+An)");
        labelPSumNot = new JLabel("P(~A1+~A2+...+~An)");

        labelN.setBounds(MARGIN_X, MARGIN_Y, 200, 50);
        labelPEvents.setBounds(MARGIN_X, MARGIN_Y + 130, 500, 50);
        labelPMul.setBounds(MARGIN_X + 500, MARGIN_Y, 300, 50);
        labelPSum.setBounds(MARGIN_X + 500, MARGIN_Y + 130, 300, 50);
        labelPSumNot.setBounds(MARGIN_X + 500, MARGIN_Y + 260, 300, 50);

        labelN.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPEvents.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPMul.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        labelPSum.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        labelPSumNot.setFont(new Font(FONT_NAME, Font.PLAIN, 24));

        labelN.setVisible(true);
        labelPEvents.setVisible(true);
        labelPMul.setVisible(true);
        labelPSum.setVisible(true);
        labelPSumNot.setVisible(true);

        window.add(labelN);
        window.add(labelPEvents);
        window.add(labelPMul);
        window.add(labelPSum);
        window.add(labelPSumNot);
    }

    /**
     * @Description 求解上述三个概率值
     * @author 罗孝俊
     * @date 2023/12/3 12:11
     **/
    public void solve() {
        String[] probabilityEventsString = inputPEvents.getText().split("(\\s)+");
        int num;
        if (inputN.getText().matches(INTEGER_REGEX)) {
            num = Integer.parseInt(inputN.getText());
        } else {
            JOptionPane.showMessageDialog(null, "事件总数N不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (num != probabilityEventsString.length) {
            JOptionPane.showMessageDialog(null, "事件数：" + probabilityEventsString.length + "\n但是事件总数：" + num, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Fraction[] probabilityEvents = new Fraction[num];
        try {
            for (int i = 0; i < num; i++) {
                String str = probabilityEventsString[i];
                if (str.matches(FRACTION_REGEX)) {
                    String[] tmp = str.split("/");
                    probabilityEvents[i] = new Fraction(tmp[0], tmp[1]);
                } else if (str.matches(NUMBER_REGEX)) {
                    probabilityEvents[i] = Operation.toFraction(str);
                } else {
                    JOptionPane.showMessageDialog(null, "P(A" + i + ")输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Independence independence = new Independence(num, probabilityEvents);
            outputPMul.setText(independence.calMulA());
            outputPSum.setText(independence.calSumA());
            outPutPSumNot.setText(independence.calSumNotA());
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
