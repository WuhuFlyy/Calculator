package ui.uiprobability;

import modules.probability.Classical;
import ui.ButtonPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 古典概型
 * @date 2023/11/26 12:13
 */
public class ClassicalUI {
    public JTextField inputK, inputN, outputPA, outputPNotA, inputNow;
    public JScrollPane paneK, paneN, panePA, panePNotA;
    public JLabel labelK, labelN;
    public JButton btnSolve, btnBack;
    public ButtonPanel buttonPanel;

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
     * @Description  初始化输入栏
     * @param text  输入栏的JTextField
     * @param pane  输入栏的JScrollPane
     * @param positionX 左上角坐标
     * @param positionY 右上角坐标
     * @author 罗孝俊
     * @date 2023/11/27 10:32
    **/

    private void initInput(JTextField text, JScrollPane pane, int positionX, int positionY){
        text.setBounds(positionX, positionY, 200, 60);
        text.setEditable(false);
        text.setFocusable(true);
        text.setBackground(Color.GRAY);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(!text.getText().matches(INTEGER_REGEX)){
                    text.setText("0");
                }
                inputNow = text;
                buttonPanel = ButtonPanel.getButtonPanel(inputNow, inputNow, 1);
                if(buttonPanel != null){
                    buttonPanel.setBounds(MARGIN_X, MARGIN_Y_DOWN - 400, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
                    window.add(buttonPanel);
                    buttonPanel.setVisible(true);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(buttonPanel != null){
                    buttonPanel.setVisible(false);
                }
            }
        });

        pane.setBounds(positionX, positionY, 200, 60);
        pane.setBackground(Color.GRAY);
        window.add(pane);
        text.setVisible(true);
        pane.setVisible(true);
    }

    /**
     * @Description 初始化输出部分的界面
     * @param text  文字载体JTextField
     * @param pane  JTextField对应的滚动窗体
     * @param positionX 界面左上角X值
     * @param positionY 界面左上角Y值
     * @author 罗孝俊
     * @date 2023/12/3 11:23
    **/
    private void initOutput(JTextField text, JScrollPane pane, int positionX, int positionY){
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
     * @Description  solve按钮的点击事件
     * @author 罗孝俊
     * @date 2023/12/3 11:22
    **/
    private void solve(){
        if(!inputK.getText().matches(INTEGER_REGEX) || !inputN.getText().matches(INTEGER_REGEX)){
            JOptionPane.showMessageDialog(null, "请输入一个整数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int n = Integer.parseInt(inputN.getText());
        int k = Integer.parseInt(inputK.getText());
        try{
            Classical classical = new Classical(n, k);
            outputPA.setText("P(A) = " + classical.calA());
            outputPNotA.setText("~P(A) = " + classical.calNotA());
        }catch(ArithmeticException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @Description  初始化两个输入提示标签
     * @author 罗孝俊
     * @date 2023/12/3 11:22
    **/
    private void initLabel(){
        labelK = new JLabel("A包含的基本事件数k");
        labelN = new JLabel("样本空间事件总数N");
        labelK.setBounds(MARGIN_X, MARGIN_Y, 300, 50);
        labelK.setFont(new Font("宋体", Font.PLAIN, 30));
        labelK.setVisible(true);
        labelN.setBounds(MARGIN_X, MARGIN_Y + 130, 300, 50);
        labelN.setFont(new Font("宋体", Font.PLAIN, 30));
        labelN.setVisible(true);
        window.add(labelK);
        window.add(labelN);
    }
}