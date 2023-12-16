package ui.uiprobability;

import modules.basic.OperationExtra;

import javax.swing.*;

import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 一元线性回归UI
 * @date 2023/12/15 22:15
 */
public class LinearRegressionUI {
    public JTextArea inputX, inputY;
    public JTextField inputDivisionValue;
    public JTextField outputSlope, outputIntercept, outputCorrelationCoefficient, outputStandardDeviation;
    public JTextField outputUaSlope, outputUaIntercept, outputUbSlope, outputUbIntercept, outputUSlope, outputUIntercept;
    public JScrollPane paneX, paneY, paneDivisionValue;
    public JScrollPane paneSlope, paneIntercept, paneCorrelationCoefficient, paneStandardDeviation;
    public JScrollPane paneUaSlope, paneUaIntercept, paneUbSlope, paneUbIntercept, paneUSlope, paneUIntercept;
    public JLabel labelX, labelY, labelDivisionValue, labelSlope, labelIntercept, labelCorrelationCoefficient, labelStandardDeviation, labelUaSlope, labelUaIntercept, labelUbSlope, labelUbIntercept, labelUSlope, labelUIntercept;
    public JButton btnSolve, btnBack;
    /**
     * @Description 构造界面UI
     * @author 罗孝俊
     * @date 2023/12/15 23:11
    **/
    public LinearRegressionUI(){
        inputX = new JTextArea("1 2 3");
        inputY = new JTextArea("1 2 3");
        inputDivisionValue = new JTextField("0.1");
        outputSlope = new JTextField();
        outputIntercept = new JTextField();
        outputCorrelationCoefficient = new JTextField();
        outputStandardDeviation = new JTextField();
        outputUaSlope = new JTextField();
        outputUaIntercept = new JTextField();
        outputUbSlope = new JTextField();
        outputUbIntercept = new JTextField();
        outputUSlope = new JTextField();
        outputUIntercept = new JTextField();

        paneX = new JScrollPane(inputX, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneY = new JScrollPane(inputY, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneDivisionValue = new JScrollPane(inputDivisionValue, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneSlope = new JScrollPane(outputSlope, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneIntercept = new JScrollPane(outputIntercept, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneCorrelationCoefficient = new JScrollPane(outputCorrelationCoefficient, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneStandardDeviation = new JScrollPane(outputStandardDeviation, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUaSlope = new JScrollPane(outputUaSlope, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUaIntercept = new JScrollPane(outputUaIntercept, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUbSlope = new JScrollPane(outputUbSlope, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUbIntercept = new JScrollPane(outputUbIntercept, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUSlope = new JScrollPane(outputUSlope, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneUIntercept = new JScrollPane(outputUIntercept, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputDivisionValue, paneDivisionValue, MARGIN_X, MARGIN_Y + 55);
        initInput(inputX, paneX, MARGIN_X, MARGIN_Y + 185);
        paneX.setBounds(MARGIN_X, MARGIN_Y + 185, 300, 120);
        inputX.setLineWrap(true);
        initInput(inputY, paneY, MARGIN_X, MARGIN_Y + 365);
        paneY.setBounds(MARGIN_X, MARGIN_Y + 365, 300, 120);
        inputY.setLineWrap(true);

        initOutput(outputSlope, paneSlope, MARGIN_X, MARGIN_Y + 545);
        initOutput(outputIntercept, paneIntercept, MARGIN_X, MARGIN_Y + 675);
        initOutput(outputCorrelationCoefficient, paneCorrelationCoefficient, MARGIN_X + 450, MARGIN_Y + 705);
        initOutput(outputStandardDeviation, paneStandardDeviation, MARGIN_X + 450, MARGIN_Y + 55);
        initOutput(outputUaSlope, paneUaSlope, MARGIN_X + 450, MARGIN_Y + 185);
        paneUaSlope.setBounds(MARGIN_X + 450, MARGIN_Y + 185, 245, 60);
        initOutput(outputUbSlope, paneUbSlope, MARGIN_X + 700, MARGIN_Y + 185);
        paneUbSlope.setBounds(MARGIN_X + 700, MARGIN_Y + 185, 245, 60);
        initOutput(outputUSlope, paneUSlope, MARGIN_X + 450, MARGIN_Y + 315);
        initOutput(outputUaIntercept, paneUaIntercept, MARGIN_X + 450, MARGIN_Y + 445);
        paneUaIntercept.setBounds(MARGIN_X + 450, MARGIN_Y + 445, 245, 60);
        initOutput(outputUbIntercept, paneUbIntercept, MARGIN_X + 700, MARGIN_Y + 445);
        paneUbIntercept.setBounds(MARGIN_X + 700, MARGIN_Y + 445, 245, 60);
        initOutput(outputUIntercept, paneUIntercept, MARGIN_X + 450, MARGIN_Y + 575);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelDivisionValue.setVisible(false);
            labelX.setVisible(false);
            labelY.setVisible(false);
            labelSlope.setVisible(false);
            labelIntercept.setVisible(false);
            labelStandardDeviation.setVisible(false);
            labelCorrelationCoefficient.setVisible(false);
            labelUaIntercept.setVisible(false);
            labelUbIntercept.setVisible(false);
            labelUIntercept.setVisible(false);
            labelUaSlope.setVisible(false);
            labelUbSlope.setVisible(false);
            labelUSlope.setVisible(false);

            inputDivisionValue.setVisible(false);
            inputX.setVisible(false);
            inputY.setVisible(false);
            outputSlope.setVisible(false);
            outputIntercept.setVisible(false);
            outputStandardDeviation.setVisible(false);
            outputCorrelationCoefficient.setVisible(false);
            outputUaIntercept.setVisible(false);
            outputUbIntercept.setVisible(false);
            outputUIntercept.setVisible(false);
            outputUaSlope.setVisible(false);
            outputUbSlope.setVisible(false);
            outputUSlope.setVisible(false);

            paneDivisionValue.setVisible(false);
            paneX.setVisible(false);
            paneY.setVisible(false);
            paneSlope.setVisible(false);
            paneIntercept.setVisible(false);
            paneStandardDeviation.setVisible(false);
            paneCorrelationCoefficient.setVisible(false);
            paneUaIntercept.setVisible(false);
            paneUbIntercept.setVisible(false);
            paneUIntercept.setVisible(false);
            paneUaSlope.setVisible(false);
            paneUbSlope.setVisible(false);
            paneUSlope.setVisible(false);

            btnSolve.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/15 23:11
    **/
    private void initLabel(){
        labelX = new JLabel("自变量x输入");
        labelY = new JLabel("因变量y输入");
        labelDivisionValue = new JLabel("误差限输入");
        labelSlope = new JLabel("斜率b");
        labelIntercept = new JLabel("截距a");
        labelCorrelationCoefficient = new JLabel("相关系数r");
        labelStandardDeviation = new JLabel("标准差s");
        labelUaSlope = new JLabel("b的A类不确定度");
        labelUbSlope = new JLabel("b的B类不确定度");
        labelUSlope = new JLabel("b的不确定度");
        labelUaIntercept = new JLabel("a的A类不确定度");
        labelUbIntercept = new JLabel("a的B类不确定度");
        labelUIntercept = new JLabel("a的不确定度");

        labelDivisionValue.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelX.setBounds(MARGIN_X, MARGIN_Y + 130, 400, 50);
        labelY.setBounds(MARGIN_X, MARGIN_Y + 310, 400, 50);
        labelSlope.setBounds(MARGIN_X, MARGIN_Y + 490, 400, 50);
        labelIntercept.setBounds(MARGIN_X, MARGIN_Y + 620, 400, 50);
        labelCorrelationCoefficient.setBounds(MARGIN_X + 450, MARGIN_Y + 650, 400, 50);
        labelStandardDeviation.setBounds(MARGIN_X + 450, MARGIN_Y, 400, 50);
        labelUaSlope.setBounds(MARGIN_X + 450, MARGIN_Y + 130, 250, 50);
        labelUbSlope.setBounds(MARGIN_X + 700, MARGIN_Y + 130, 250, 50);
        labelUSlope.setBounds(MARGIN_X + 450, MARGIN_Y + 260, 400, 50);
        labelUaIntercept.setBounds(MARGIN_X + 450, MARGIN_Y + 390, 250, 50);
        labelUbIntercept.setBounds(MARGIN_X + 700, MARGIN_Y + 390, 250, 50);
        labelUIntercept.setBounds(MARGIN_X + 450, MARGIN_Y + 520, 400, 50);

        labelDivisionValue.setFont(new Font("宋体", Font.PLAIN, 24));
        labelX.setFont(new Font("宋体", Font.PLAIN, 24));
        labelY.setFont(new Font("宋体", Font.PLAIN, 24));
        labelSlope.setFont(new Font("宋体", Font.PLAIN, 24));
        labelIntercept.setFont(new Font("宋体", Font.PLAIN, 24));
        labelCorrelationCoefficient.setFont(new Font("宋体", Font.PLAIN, 24));
        labelStandardDeviation.setFont(new Font("宋体", Font.PLAIN, 24));
        labelUaSlope.setFont(new Font("宋体", Font.PLAIN, 20));
        labelUbSlope.setFont(new Font("宋体", Font.PLAIN, 20));
        labelUSlope.setFont(new Font("宋体", Font.PLAIN, 24));
        labelUaIntercept.setFont(new Font("宋体", Font.PLAIN, 20));
        labelUbIntercept.setFont(new Font("宋体", Font.PLAIN, 20));
        labelUIntercept.setFont(new Font("宋体", Font.PLAIN, 24));

        window.add(labelDivisionValue);
        window.add(labelX);
        window.add(labelY);
        window.add(labelSlope);
        window.add(labelIntercept);
        window.add(labelCorrelationCoefficient);
        window.add(labelStandardDeviation);
        window.add(labelUaSlope);
        window.add(labelUbSlope);
        window.add(labelUSlope);
        window.add(labelUaIntercept);
        window.add(labelUbIntercept);
        window.add(labelUIntercept);
    }

    /**
     * @Description 求解一元线性回归
     * @author 罗孝俊
     * @date 2023/12/15 23:35
    **/
    private void solve(){
        try{
            String dataX = inputX.getText();
            String dataY = inputY.getText();
            String dataDivisionValue = inputDivisionValue.getText();
            outputSlope.setText(OperationExtra.getSlope(dataX, dataY, dataDivisionValue));
            outputIntercept.setText(OperationExtra.getIntercept(dataX, dataY, dataDivisionValue));
            outputCorrelationCoefficient.setText(OperationExtra.getCorrelationCoefficient(dataX, dataY, dataDivisionValue));
            outputStandardDeviation.setText(OperationExtra.getStandardDeviation(dataX, dataY, dataDivisionValue));
            outputUaSlope.setText(OperationExtra.getUaSlope(dataX, dataY, dataDivisionValue));
            outputUbSlope.setText(OperationExtra.getUbSlope(dataX, dataY, dataDivisionValue));
            outputUSlope.setText(OperationExtra.getUSlope(dataX, dataY, dataDivisionValue));
            outputUaIntercept.setText(OperationExtra.getUaIntercept(dataX, dataY, dataDivisionValue));
            outputUbIntercept.setText(OperationExtra.getUbIntercept(dataX, dataY, dataDivisionValue));
            outputUIntercept.setText(OperationExtra.getUIntercept(dataX, dataY, dataDivisionValue));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}