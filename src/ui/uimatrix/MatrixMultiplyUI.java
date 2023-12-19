package ui.uimatrix;

import modules.matrix.Matrix;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 矩阵乘法UI
 * @date 2023/12/8 16:51
 */
public class MatrixMultiplyUI {
    public JTextField inputRow1, inputColumn1, inputRow2, inputColumn2;
    public JTextArea inputMatrix1, inputMatrix2, outputAns;
    public JScrollPane paneRow1, paneRow2, paneColumn1, paneColumn2, paneMatrix1, paneMatrix2, paneAns;
    public JLabel labelRow1, labelRow2, labelColumn1, labelColumn2, labelMatrix1, labelMatrix2, labelAns;
    public JButton btnSolve, btnBack;

    /**
     * @Description 界面UI构造方法
     * @author 罗孝俊
     * @date 2023/12/8 16:56
     **/
    public MatrixMultiplyUI() {
        inputRow1 = new JTextField("3");
        inputColumn1 = new JTextField("2");
        inputRow2 = new JTextField("2");
        inputColumn2 = new JTextField("3");
        inputMatrix1 = new JTextArea("1 1\n1 1\n1 1");
        inputMatrix2 = new JTextArea("1 1 1\n1 1 1");
        outputAns = new JTextArea();

        paneRow1 = new JScrollPane(inputRow1, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneColumn1 = new JScrollPane(inputColumn1, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneMatrix1 = new JScrollPane(inputMatrix1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneRow2 = new JScrollPane(inputRow2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneColumn2 = new JScrollPane(inputColumn2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneMatrix2 = new JScrollPane(inputMatrix2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneAns = new JScrollPane(outputAns, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputRow1, paneRow1, MARGIN_X, MARGIN_Y + 55);
        initInput(inputColumn1, paneColumn1, MARGIN_X, MARGIN_Y + 185);
        initInput(inputMatrix1, paneMatrix1, MARGIN_X, MARGIN_Y + 315);
        paneMatrix1.setBounds(MARGIN_X, MARGIN_Y + 315, 300, 160);
        initInput(inputRow2, paneRow2, MARGIN_X + 500, MARGIN_Y + 55);
        initInput(inputColumn2, paneColumn2, MARGIN_X + 500, MARGIN_Y + 185);
        initInput(inputMatrix2, paneMatrix2, MARGIN_X + 500, MARGIN_Y + 315);
        paneMatrix2.setBounds(MARGIN_X + 500, MARGIN_Y + 315, 300, 160);
        initOutput(outputAns, paneAns, MARGIN_X + 250, MARGIN_Y + 585);
        paneAns.setBounds(MARGIN_X + 150, MARGIN_Y + 535, 500, 180);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelRow1.setVisible(false);
            labelColumn1.setVisible(false);
            labelMatrix1.setVisible(false);
            labelRow2.setVisible(false);
            labelColumn2.setVisible(false);
            labelMatrix2.setVisible(false);
            labelAns.setVisible(false);

            inputRow1.setVisible(false);
            inputColumn1.setVisible(false);
            inputMatrix1.setVisible(false);
            inputRow2.setVisible(false);
            inputColumn2.setVisible(false);
            inputMatrix2.setVisible(false);
            outputAns.setVisible(false);

            paneRow1.setVisible(false);
            paneColumn1.setVisible(false);
            paneMatrix1.setVisible(false);
            paneRow2.setVisible(false);
            paneColumn2.setVisible(false);
            paneMatrix2.setVisible(false);
            paneAns.setVisible(false);

            btnSolve.setVisible(false);
            new MatrixGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/8 17:08
     **/
    private void initLabel() {
        labelRow1 = new JLabel("左矩阵行数");
        labelColumn1 = new JLabel("左矩阵列数");
        labelMatrix1 = new JLabel("左矩阵输入");
        labelRow2 = new JLabel("右矩阵行数");
        labelColumn2 = new JLabel("右矩阵列数");
        labelMatrix2 = new JLabel("右矩阵输入");
        labelAns = new JLabel("相乘结果");

        labelRow1.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelColumn1.setBounds(MARGIN_X, MARGIN_Y + 130, 400, 50);
        labelMatrix1.setBounds(MARGIN_X, MARGIN_Y + 260, 500, 50);
        labelRow2.setBounds(MARGIN_X + 500, MARGIN_Y, 400, 50);
        labelColumn2.setBounds(MARGIN_X + 500, MARGIN_Y + 130, 400, 50);
        labelMatrix2.setBounds(MARGIN_X + 500, MARGIN_Y + 260, 400, 50);
        labelAns.setBounds(MARGIN_X + 250, MARGIN_Y + 480, 400, 50);

        labelRow1.setFont(new Font("宋体", Font.PLAIN, 24));
        labelColumn1.setFont(new Font("宋体", Font.PLAIN, 24));
        labelMatrix1.setFont(new Font("宋体", Font.PLAIN, 24));
        labelRow2.setFont(new Font("宋体", Font.PLAIN, 24));
        labelColumn2.setFont(new Font("宋体", Font.PLAIN, 24));
        labelMatrix2.setFont(new Font("宋体", Font.PLAIN, 24));
        labelAns.setFont(new Font("宋体", Font.PLAIN, 24));

        window.add(labelRow1);
        window.add(labelColumn1);
        window.add(labelMatrix1);
        window.add(labelRow2);
        window.add(labelColumn2);
        window.add(labelMatrix2);
        window.add(labelAns);
    }

    /**
     * @Description 相乘
     * @author 罗孝俊
     * @date 2023/12/8 17:16
     **/
    public void solve() {
        int row1, column1, row2, column2;
        if (inputRow1.getText().matches(POSITIVE_INTEGER_REGEX)) {
            row1 = Integer.parseInt(inputRow1.getText());
        } else {
            JOptionPane.showMessageDialog(null, "左矩阵行数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (inputColumn1.getText().matches(POSITIVE_INTEGER_REGEX)) {
            column1 = Integer.parseInt(inputColumn1.getText());
        } else {
            JOptionPane.showMessageDialog(null, "左矩阵列数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (inputRow2.getText().matches(POSITIVE_INTEGER_REGEX)) {
            row2 = Integer.parseInt(inputRow2.getText());
        } else {
            JOptionPane.showMessageDialog(null, "右矩阵行数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (inputColumn2.getText().matches(POSITIVE_INTEGER_REGEX)) {
            column2 = Integer.parseInt(inputColumn2.getText());
        } else {
            JOptionPane.showMessageDialog(null, "右矩阵列数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (column1 != row2) {
            JOptionPane.showMessageDialog(null, "左矩阵列数不等于右矩阵行数", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Matrix matrix1 = getMatrix(row1, column1, inputMatrix1);
            if (matrix1 == null) {
                return;
            }
            Matrix matrix2 = getMatrix(row2, column2, inputMatrix2);
            if (matrix2 == null) {
                return;
            }

            Matrix product = matrix1.multiply(matrix2);
            StringBuilder productString = new StringBuilder();
            for (int i = 1; i <= product.row; i++) {
                for (int j = 1; j <= product.column; j++) {
                    productString.append(product.matrix[i][j]).append(" ");
                }
                productString.append("\n");
            }
            outputAns.setText(productString.toString());
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}