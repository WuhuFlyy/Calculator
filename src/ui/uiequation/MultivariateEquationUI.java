package ui.uiequation;

import modules.equation.MultivariateEquation;

import static ui.UIValues.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * @author 罗孝俊
 * @Description: 多元方程组UI
 * @date 2023/12/12 23:47
 */
public class MultivariateEquationUI {
    public JTextField inputCoefficient1, inputCoefficient2, inputCoefficient3;
    public JTextArea outputAns;
    public JScrollPane paneCoefficient1, paneCoefficient2, paneCoefficient3, paneAns;
    public JLabel labelTimes, labelCoefficient, labelAns;
    public JComboBox<String> boxTimes;
    public JButton btnSolve, btnBack;

    /**
     * @Description 界面UI构造
     * @author 罗孝俊
     * @date 2023/12/13 0:01
    **/
    public MultivariateEquationUI(){
        inputCoefficient1 = new JTextField("1, 2, 0");
        inputCoefficient2 = new JTextField("2, 3, 3");
        inputCoefficient3 = new JTextField("3, 4, 2, 5");
        outputAns = new JTextArea();
        paneCoefficient1 = new JScrollPane(inputCoefficient1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneCoefficient2 = new JScrollPane(inputCoefficient2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneCoefficient3 = new JScrollPane(inputCoefficient3, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAns = new JScrollPane(outputAns, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputCoefficient1, paneCoefficient1, MARGIN_X, MARGIN_Y + 185);
        paneCoefficient1.setBounds(MARGIN_X, MARGIN_Y + 185, 450, 60);
        initInput(inputCoefficient2, paneCoefficient2, MARGIN_X, MARGIN_Y + 315);
        paneCoefficient2.setBounds(MARGIN_X, MARGIN_Y + 315, 450, 60);
        initInput(inputCoefficient3, paneCoefficient3, MARGIN_X, MARGIN_Y + 445);
        paneCoefficient3.setBounds(MARGIN_X, MARGIN_Y + 445, 450, 60);
        initOutput(outputAns, paneAns, MARGIN_X + 500, MARGIN_Y + 55);
        paneAns.setBounds(MARGIN_X + 500, MARGIN_Y + 55, 400, 600);
        outputAns.setLineWrap(true);
        initLabel();
        initBox();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelTimes.setVisible(false);
            labelCoefficient.setVisible(false);
            labelAns.setVisible(false);

            inputCoefficient1.setVisible(false);
            inputCoefficient2.setVisible(false);
            inputCoefficient3.setVisible(false);
            outputAns.setVisible(false);
            boxTimes.setVisible(false);

            paneCoefficient1.setVisible(false);
            paneCoefficient2.setVisible(false);
            paneCoefficient3.setVisible(false);
            paneAns.setVisible(false);

            btnSolve.setVisible(false);
            new EquationGeneralUI();
            btnBack.setVisible(false);
        });
        inputCoefficient3.setVisible(false);
        paneCoefficient3.setVisible(false);
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/13 0:10
    **/
    private void initLabel(){
        labelTimes = new JLabel("方程个数");
        labelCoefficient = new JLabel("(ax + by = c)中的a,b,c");
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

    private void initBox(){
        String[] times = {"2", "3"};
        boxTimes = new JComboBox<>(times);
        boxTimes.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        boxTimes.setMaximumRowCount(2);
        boxTimes.setBounds(MARGIN_X, MARGIN_Y + 55, 100, 50);
        boxTimes.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                int i = boxTimes.getSelectedIndex();
                if(i == 0){
                    paneCoefficient3.setVisible(false);
                    inputCoefficient3.setVisible(false);
                    labelCoefficient.setText("(ax + by = c)中的a,b,c");
                }else{
                    paneCoefficient3.setVisible(true);
                    inputCoefficient3.setVisible(true);
                    labelCoefficient.setText("(ax + by + cz = d)中的a,b,c,d");
                }
            }
        });
        window.add(boxTimes);
    }

    /**
     * @Description 解方程
     * @author 罗孝俊
     * @date 2023/12/13 0:10
    **/
    private void solve(){
        //总的构造
        try{
            String[] coefficientString1 = inputCoefficient1.getText().split("(\\s)*,(\\s)*");
            String[] coefficientString2 = inputCoefficient2.getText().split("(\\s)*,(\\s)*");
            double[] coefficient1 = new double[3];
            double[] coefficient2 = new double[3];
            double[] constant = new double[3];
            for(int i = 0; i <= 1; i++){
                if(!transform(i, coefficient1, coefficientString1[i])){
                    JOptionPane.showMessageDialog(null, "第一个方程的第" + (i+1) + "个系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            for(int i = 0; i <= 1; i++){
                if(!transform(i, coefficient2, coefficientString2[i])){
                    JOptionPane.showMessageDialog(null, "第二个方程的系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if(boxTimes.getSelectedIndex() == 0){
                //二元
                if(!transform(0, constant, coefficientString1[2])){
                    JOptionPane.showMessageDialog(null, "第一个方程的常数项系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!transform(1, constant, coefficientString2[2])){
                    JOptionPane.showMessageDialog(null, "第二个方程的常数项系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                MultivariateEquation multivariateEquation = new MultivariateEquation(coefficient1, coefficient2, constant);
                outputAns.setText(multivariateEquation.solveBinaryEquation());
            }else{
                //三元
                String[] coefficientString3 = inputCoefficient3.getText().split("(\\s)*,(\\s)*");
                double[] coefficient3 = new double[3];

                if(!transform(0, constant, coefficientString1[3])){
                    JOptionPane.showMessageDialog(null, "第一个方程的常数项系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!transform(1, constant, coefficientString2[3])){
                    JOptionPane.showMessageDialog(null, "第二个方程的常数项系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!transform(2, constant, coefficientString3[3])){
                    JOptionPane.showMessageDialog(null, "第三个方程的常数项系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!transform(2, coefficient1, coefficientString1[2])){
                    JOptionPane.showMessageDialog(null, "第一个方程的第3个系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(!transform(2, coefficient2, coefficientString2[2])){
                    JOptionPane.showMessageDialog(null, "第二个方程的第3个系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                for(int i = 0; i <= 2; i++){
                    if(!transform(i, coefficient3, coefficientString3[i])){
                        JOptionPane.showMessageDialog(null, "第三个方程的第" + (i+1) +"个系数输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                MultivariateEquation multivariateEquation = new MultivariateEquation(coefficient1, coefficient2, coefficient3, constant);
                outputAns.setText(multivariateEquation.solveCubicEquation());
            }
        }catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "输入系数数量与元数不符", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }
}
