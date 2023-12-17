package ui.uimatrix;

import modules.basic.Fraction;
import modules.basic.Operation;
import modules.matrix.Matrix;

import static ui.UIValues.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 矩阵幂
 * @date 2023/12/8 17:47
 */
public class MatrixPowUI {
    public JTextField inputRow, inputExponential;
    public JTextArea inputMatrix, outputAns;
    public JScrollPane paneRow, paneMatrix, paneExponential, paneAns;
    public JLabel labelRow, labelMatrix, labelExponential, labelAns;
    public JButton btnSolve, btnBack;

    public MatrixPowUI(){
        inputRow = new JTextField("3");
        inputExponential = new JTextField("3");
        inputMatrix = new JTextArea("0 7 2\n1 5 0\n7 2 1");
        outputAns = new JTextArea();
        paneRow = new JScrollPane(inputRow, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneExponential = new JScrollPane(inputExponential, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneMatrix = new JScrollPane(inputMatrix, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAns = new JScrollPane(outputAns, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputRow, paneRow, MARGIN_X, MARGIN_Y + 55);
        initInput(inputExponential, paneExponential, MARGIN_X, MARGIN_Y + 315);
        initInput(inputMatrix, paneMatrix, MARGIN_X + 500, MARGIN_Y + 55);
        paneMatrix.setBounds(MARGIN_X + 500, MARGIN_Y + 55, 300, 160);
        initOutput(outputAns, paneAns, MARGIN_X + 500, MARGIN_Y + 270);
        paneAns.setBounds(MARGIN_X + 500, MARGIN_Y + 270, 400, 320);
        initLabel();
        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelRow.setVisible(false);
            labelExponential.setVisible(false);
            labelMatrix.setVisible(false);
            labelAns.setVisible(false);

            inputRow.setVisible(false);
            inputExponential.setVisible(false);
            inputMatrix.setVisible(false);
            outputAns.setVisible(false);

            paneRow.setVisible(false);
            paneExponential.setVisible(false);
            paneMatrix.setVisible(false);
            paneAns.setVisible(false);

            btnSolve.setVisible(false);
            new MatrixGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/8 22:52
    **/
    private void initLabel() {
        labelRow = new JLabel("方阵行数");
        labelExponential = new JLabel("指数(必须为整数)");
        labelMatrix = new JLabel("矩阵输入(从左至右,从上至下)");
        labelAns = new JLabel("矩阵幂运算结果");

        labelRow.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelExponential.setBounds(MARGIN_X, MARGIN_Y + 260, 400, 50);
        labelMatrix.setBounds(MARGIN_X + 500, MARGIN_Y, 500, 50);
        labelAns.setBounds(MARGIN_X + 500, MARGIN_Y + 215, 500, 50);

        labelRow.setFont(new Font("宋体", Font.PLAIN, 24));
        labelExponential.setFont(new Font("宋体", Font.PLAIN, 24));
        labelMatrix.setFont(new Font("宋体", Font.PLAIN, 24));
        labelAns.setFont(new Font("宋体", Font.PLAIN, 24));

        window.add(labelRow);
        window.add(labelExponential);
        window.add(labelMatrix);
        window.add(labelAns);
    }

    private void solve(){
        int row, exp;
        if(inputRow.getText().matches(POSITIVE_INTEGER_REGEX)){
            row = Integer.parseInt(inputRow.getText());
        }else{
            JOptionPane.showMessageDialog(null, "方阵行数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(inputExponential.getText().matches(INTEGER_REGEX)){
            exp = Integer.parseInt(inputExponential.getText());
        }else{
            JOptionPane.showMessageDialog(null, "指数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try{
            Matrix matrix = getMatrix(row, row, inputMatrix);
            if(matrix == null){
                return;
            }
            Matrix powMatrix = matrix.pow(exp);
            StringBuilder powString = new StringBuilder();
            for(int i = 1; i <= row; i++){
                for(int j = 1; j <= row; j++){
                    powString.append(powMatrix.matrix[i][j]).append(" ");
                }
                powString.append("\n");
            }
            outputAns.setText(powString.toString());
        }catch (ArithmeticException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
