package ui.uiprobability;

import modules.basic.Fraction;
import modules.basic.Operation;
import modules.probability.Bayesian;
import modules.probability.TotalProbability;
import ui.ButtonPanel;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static ui.UIValues.*;
import static ui.UIValues.window;

/**
 * @author 罗孝俊
 * @Description: 贝叶斯公式UI
 * @date 2023/12/4 19:39
 */
public class BayesianUI {
    JTextComponent inputNow;
    public JTextField inputN, inputJ, outputPBjUnderA, outputPA;
    public JTextArea inputPBi, inputPAUnderBi;
    public JScrollPane paneN, paneJ, panePBi, panePAUnderBi, panePBjUnderA, panePA;
    public JLabel labelN, labelJ, labelPBi, labelPAUnderBi, labelPBjUnderA, labelPA;
    public JButton btnSolve, btnBack;
    public ButtonPanel buttonPanel;

    /**
     * @Description UI界面的构造方法
     * @author 罗孝俊
     * @date 2023/12/4 21:41
    **/
    public BayesianUI() {
        inputN = new JTextField("3");
        inputJ = new JTextField("0");
        inputPBi = new JTextArea("0,0,0...");
        inputPAUnderBi = new JTextArea("0,0,0...");
        paneN = new JScrollPane(inputN, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneJ = new JScrollPane(inputJ, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePBi = new JScrollPane(inputPBi, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePAUnderBi = new JScrollPane(inputPAUnderBi, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPBjUnderA = new JTextField("0");
        outputPA = new JTextField("0");
        panePBjUnderA = new JScrollPane(outputPBjUnderA, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panePA = new JScrollPane(outputPA ,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputN, paneN, MARGIN_X, MARGIN_Y + 55);
        initInput(inputJ, paneJ, MARGIN_X, MARGIN_Y + 185);
        initInput(inputPBi, panePBi, MARGIN_X + 500, MARGIN_Y + 55);
        panePBi.setBounds(MARGIN_X + 500, MARGIN_Y + 55, 300, 120);
        inputPBi.setEditable(true);
        initInput(inputPAUnderBi, panePAUnderBi, MARGIN_X + 500, MARGIN_Y + 235);
        panePAUnderBi.setBounds(MARGIN_X + 500, MARGIN_Y + 235, 300, 120);
        inputPAUnderBi.setEditable(true);
        initOutput(outputPBjUnderA, panePBjUnderA, MARGIN_X + 500, MARGIN_Y + 410);
        initOutput(outputPA, panePA, MARGIN_X + 500, MARGIN_Y + 525);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        btnSolve.setBounds(MARGIN_X_RIGHT - 120, MARGIN_Y_DOWN - 40, 120, 40);
        btnSolve.addActionListener(event -> {
            solve();
        });
        window.add(btnSolve);
        btnSolve.setVisible(true);
        btnSolve.repaint();

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            if(buttonPanel != null){
                buttonPanel.setVisible(false);
            }
            labelN.setVisible(false);
            labelJ.setVisible(false);
            labelPBi.setVisible(false);
            labelPAUnderBi.setVisible(false);
            labelPBjUnderA.setVisible(false);
            labelPA.setVisible(false);
            inputN.setVisible(false);
            inputJ.setVisible(false);
            inputPBi.setVisible(false);
            inputPAUnderBi.setVisible(false);
            outputPBjUnderA.setVisible(false);
            outputPA.setVisible(false);
            paneN.setVisible(false);
            paneJ.setVisible(false);
            panePBi.setVisible(false);
            panePAUnderBi.setVisible(false);
            panePBjUnderA.setVisible(false);
            panePA.setVisible(false);
            btnSolve.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description  初始化输入栏
     * @param text  输入栏的JTextComponent
     * @param pane  输入栏的JScrollPane
     * @param positionX 界面左上角X值
     * @param positionY 界面左上角Y值
     * @author 罗孝俊
     * @date 2023/12/4 20:49
     **/
    private void initInput(JTextComponent text, JScrollPane pane, int positionX, int positionY) {
        text.setEditable(false);
        text.setFocusable(true);
        text.setBackground(Color.GRAY);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                inputNow = text;
                buttonPanel = ButtonPanel.getButtonPanel(inputNow, inputNow, 2);
                if (buttonPanel != null) {
                    buttonPanel.setBounds(MARGIN_X, MARGIN_Y_DOWN - 400, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
                    window.add(buttonPanel);
                    buttonPanel.setVisible(true);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buttonPanel != null) {
                    buttonPanel.setVisible(false);
                }
                if (inputNow.getText().isBlank()) {
                    inputNow.setText("0");
                }
            }
        });
        if (text instanceof JTextArea) {
            ((JTextArea) text).setLineWrap(true);
            ((JTextArea) text).setWrapStyleWord(true);
        }
        pane.setBounds(positionX, positionY, 200, 60);
        pane.setBackground(Color.GRAY);
        window.add(pane);
        text.setVisible(true);
        pane.setVisible(true);
    }

    /**
     * @Description 初始化输出部分的界面
     * @param text  文字载体JTextComponent
     * @param pane  对应的滚动窗体
     * @param positionX 界面左上角X值
     * @param positionY 界面左上角Y值
     * @author 罗孝俊
     * @date 2023/12/4 20:51
     **/
    private void initOutput(JTextField text, JScrollPane pane, int positionX, int positionY) {
        text.setBounds(positionX, positionY, 300, 60);
        text.setEditable(false);
        text.setFocusable(true);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        pane.setBounds(positionX, positionY, 300, 60);
        window.add(pane);
        text.setVisible(true);
        pane.setVisible(true);
    }

    /**
     * @Description  初始化两个输入提示标签
     * @author 罗孝俊
     * @date 2023/12/4 20:51
     **/
    private void initLabel() {
        labelN = new JLabel("事件总数N");
        labelJ = new JLabel("待计算的P(Bj|A)中的j(1 <= j <= n)");
        labelPBi = new JLabel("P(Bi)(用半角逗号隔开)");
        labelPAUnderBi = new JLabel("P(A|Bi)(用半角逗号隔开)");
        labelPBjUnderA = new JLabel("P(Bj|A)");
        labelPA = new JLabel("P(A)");

        labelN.setBounds(MARGIN_X, MARGIN_Y, 200, 50);
        labelJ.setBounds(MARGIN_X, MARGIN_Y + 130, 400, 50);
        labelPBi.setBounds(MARGIN_X + 500, MARGIN_Y, 400, 50);
        labelPAUnderBi.setBounds(MARGIN_X + 500, MARGIN_Y + 180, 400, 50);
        labelPBjUnderA.setBounds(MARGIN_X + 500, MARGIN_Y + 360, 300, 50);
        labelPA.setBounds(MARGIN_X + 500, MARGIN_Y + 475, 300, 50);

        labelN.setFont(new Font("宋体", Font.PLAIN, 24));
        labelJ.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPBi.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPAUnderBi.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPBjUnderA.setFont(new Font("宋体", Font.PLAIN, 24));
        labelPA.setFont(new Font("宋体", Font.PLAIN, 24));

        labelN.setVisible(true);
        labelJ.setVisible(true);
        labelPBi.setVisible(true);
        labelPAUnderBi.setVisible(true);
        labelPBjUnderA.setVisible(true);
        labelPA.setVisible(true);

        window.add(labelN);
        window.add(labelJ);
        window.add(labelPBi);
        window.add(labelPAUnderBi);
        window.add(labelPBjUnderA);
        window.add(labelPA);
    }

    /**
     * @Description  求解P(Bj|A) 和 P(A)
     * @author 罗孝俊
     * @date 2023/12/4 20:51
    **/
    public void solve(){
        int num, j;
        if(inputN.getText().matches(POSITIVE_INTEGER_REGEX)){
            num = Integer.parseInt(inputN.getText());
        }else{
            JOptionPane.showMessageDialog(null, "事件总数N不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(inputJ.getText().matches(POSITIVE_INTEGER_REGEX)){
            j = Integer.parseInt(inputJ.getText());
        }else{
            JOptionPane.showMessageDialog(null, "j的输入不合法(1 <= j <= n且j为正整数)", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(j > num){
            JOptionPane.showMessageDialog(null, "j的输入不合法(1 <= j <= n且j为正整数)", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] probabilityBiString = inputPBi.getText().split("(\\s)*,(\\s)*");
        if(num != probabilityBiString.length){
            JOptionPane.showMessageDialog(null, "Bi的总数：" + probabilityBiString.length + "\n但是事件总数：" + num, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] probabilityAUnderBiString = inputPAUnderBi.getText().split("(\\s)*,(\\s)*");
        if(num != probabilityAUnderBiString.length){
            JOptionPane.showMessageDialog(null, "P(A|Bi)的总数：" + probabilityAUnderBiString.length + "\n但是事件总数：" + num, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Fraction[] probabilityBi = new Fraction[num];
        Fraction[] probabilityAUnderBi = new Fraction[num];
        try{
            for(int i = 0; i < num; i++){
                String str = probabilityBiString[i];
                if(str.matches(FRACTION_REGEX)){
                    String[] tmp = str.split("/");
                    probabilityBi[i] = new Fraction(tmp[0], tmp[1]);
                }else if(str.matches(NUMBER_REGEX)){
                    probabilityBi[i] = Operation.toFraction(str);
                }else{
                    JOptionPane.showMessageDialog(null, "P(B" + i + ")输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            for(int i = 0; i < num; i++){
                String str = probabilityAUnderBiString[i];
                if(str.matches(FRACTION_REGEX)){
                    String[] tmp = str.split("/");
                    probabilityAUnderBi[i] = new Fraction(tmp[0], tmp[1]);
                }else if(str.matches(NUMBER_REGEX)){
                    probabilityAUnderBi[i] = Operation.toFraction(str);
                }else{
                    JOptionPane.showMessageDialog(null, "P(A|B" + i + ")输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Bayesian bayesian = new Bayesian(num, probabilityBi, probabilityAUnderBi, j);
            TotalProbability totalProbability = new TotalProbability(num, probabilityBi, probabilityAUnderBi);
            outputPBjUnderA.setText(bayesian.calBjunderA());
            outputPA.setText(totalProbability.calA());
        }catch (ArithmeticException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}