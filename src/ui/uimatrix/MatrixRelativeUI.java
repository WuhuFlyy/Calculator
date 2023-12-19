package ui.uimatrix;

import modules.matrix.Matrix;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 求逆和转置矩阵的UI
 * @date 2023/12/8 13:45
 */
public class MatrixRelativeUI {
    public JTextField inputRow, inputColumn;
    public JTextArea inputMatrix, outputInverse, outputTrans, outputRank, outputDeterminant;
    public JScrollPane paneRow, paneColumn, paneMatrix, paneInverse, paneTrans, paneRank, paneDeterminant;
    public JLabel labelRow, labelColumn, labelMatrix, labelInverse, labelTrans, labelRank, labelDeterminant;
    public JButton btnSolve, btnBack;

    /**
     * @Description 界面UI构造方法
     * @author 罗孝俊
     * @date 2023/12/8 14:42
     **/
    public MatrixRelativeUI() {
        inputRow = new JTextField("3");
        inputColumn = new JTextField("2");
        inputMatrix = new JTextArea("1 1\n2 1\n1 3");
        outputInverse = new JTextArea();
        outputTrans = new JTextArea();
        outputRank = new JTextArea();
        outputDeterminant = new JTextArea();
        paneRow = new JScrollPane(inputRow, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneColumn = new JScrollPane(inputColumn, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneMatrix = new JScrollPane(inputMatrix, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneInverse = new JScrollPane(outputInverse, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneTrans = new JScrollPane(outputTrans, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneRank = new JScrollPane(outputRank, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneDeterminant = new JScrollPane(outputDeterminant, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        initInput(inputRow, paneRow, MARGIN_X, MARGIN_Y + 55);
        initInput(inputColumn, paneColumn, MARGIN_X, MARGIN_Y + 185);
        initInput(inputMatrix, paneMatrix, MARGIN_X, MARGIN_Y + 315);
        paneMatrix.setBounds(MARGIN_X, MARGIN_Y + 365, 300, 250);
        initOutput(outputInverse, paneInverse, MARGIN_X + 500, MARGIN_Y + 55);
        paneInverse.setBounds(MARGIN_X + 500, MARGIN_Y + 55, 400, 200);
        initOutput(outputTrans, paneTrans, MARGIN_X + 500, MARGIN_Y + 270);
        paneTrans.setBounds(MARGIN_X + 500, MARGIN_Y + 310, 400, 200);
        initOutput(outputRank, paneRank, MARGIN_X + 500, MARGIN_Y + 565);
        initOutput(outputDeterminant, paneDeterminant, MARGIN_X + 500, MARGIN_Y + 670);
        initLabel();

        btnSolve = createButton("solve", FONT_NAME);
        initButtonSolve(btnSolve, event -> {
            solve();
        });

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelRow.setVisible(false);
            labelColumn.setVisible(false);
            labelMatrix.setVisible(false);
            labelInverse.setVisible(false);
            labelTrans.setVisible(false);
            labelRank.setVisible(false);
            labelDeterminant.setVisible(false);

            inputRow.setVisible(false);
            inputColumn.setVisible(false);
            inputMatrix.setVisible(false);
            outputInverse.setVisible(false);
            outputTrans.setVisible(false);
            outputRank.setVisible(false);
            outputDeterminant.setVisible(false);

            paneRow.setVisible(false);
            paneColumn.setVisible(false);
            paneMatrix.setVisible(false);
            paneInverse.setVisible(false);
            paneTrans.setVisible(false);
            paneRank.setVisible(false);
            paneDeterminant.setVisible(false);

            btnSolve.setVisible(false);
            new MatrixGeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/8 14:30
     **/
    private void initLabel() {
        labelRow = new JLabel("矩阵行数");
        labelColumn = new JLabel("矩阵列数");
        labelMatrix = new JLabel("矩阵输入(从左至右,从上至下)");
        labelInverse = new JLabel("逆矩阵");
        labelTrans = new JLabel("转置矩阵");
        labelRank = new JLabel("矩阵的秩");
        labelDeterminant = new JLabel("行列式");

        labelRow.setBounds(MARGIN_X, MARGIN_Y, 400, 50);
        labelColumn.setBounds(MARGIN_X, MARGIN_Y + 130, 400, 50);
        labelMatrix.setBounds(MARGIN_X, MARGIN_Y + 260, 500, 50);
        labelInverse.setBounds(MARGIN_X + 500, MARGIN_Y, 400, 50);
        labelTrans.setBounds(MARGIN_X + 500, MARGIN_Y + 255, 400, 50);
        labelRank.setBounds(MARGIN_X + 500, MARGIN_Y + 520, 400, 50);
        labelDeterminant.setBounds(MARGIN_X + 500, MARGIN_Y + 615, 400, 50);

        labelRow.setFont(new Font("宋体", Font.PLAIN, 24));
        labelColumn.setFont(new Font("宋体", Font.PLAIN, 24));
        labelMatrix.setFont(new Font("宋体", Font.PLAIN, 24));
        labelInverse.setFont(new Font("宋体", Font.PLAIN, 24));
        labelTrans.setFont(new Font("宋体", Font.PLAIN, 24));
        labelRank.setFont(new Font("宋体", Font.PLAIN, 24));
        labelDeterminant.setFont(new Font("宋体", Font.PLAIN, 24));

        window.add(labelRow);
        window.add(labelColumn);
        window.add(labelMatrix);
        window.add(labelInverse);
        window.add(labelTrans);
        window.add(labelRank);
        window.add(labelDeterminant);
    }

    /**
     * @Description 求矩阵逆、转置和秩
     * @author 罗孝俊
     * @date 2023/12/8 14:32
     **/
    public void solve() {
        int row, column;
        if (inputRow.getText().matches(POSITIVE_INTEGER_REGEX)) {
            row = Integer.parseInt(inputRow.getText());
        } else {
            JOptionPane.showMessageDialog(null, "矩阵行数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (inputColumn.getText().matches(POSITIVE_INTEGER_REGEX)) {
            column = Integer.parseInt(inputColumn.getText());
        } else {
            JOptionPane.showMessageDialog(null, "矩阵列数不合法", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Matrix matrix = getMatrix(row, column, inputMatrix);
            if (matrix == null) {
                return;
            }

            int rank = matrix.getRank();
            if (row != column) {
                outputInverse.setFont(new Font("宋体", Font.PLAIN, 33));
                outputInverse.setText("矩阵非方阵");
                outputDeterminant.setText("Not Exist");
            } else {
                outputDeterminant.setText(matrix.getDeterminant().toString());
                if (!matrix.invertible()) {
                    outputInverse.setFont(new Font("宋体", Font.PLAIN, 33));
                    outputInverse.setText("矩阵不可逆");
                } else {
                    outputInverse.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
                    StringBuilder inverseString = new StringBuilder();
                    Matrix inverseMatrix = matrix.inverse();
                    for (int i = 1; i <= row; i++) {
                        for (int j = 1; j <= column; j++) {
                            inverseString.append(inverseMatrix.matrix[i][j]).append(" ");
                        }
                        inverseString.append("\n");
                    }
                    outputInverse.setText(inverseString.toString());
                }
            }

            Matrix transMatrix = matrix.transposition();
            StringBuilder transString = new StringBuilder();
            for (int i = 1; i <= column; i++) {
                for (int j = 1; j <= row; j++) {
                    transString.append(transMatrix.matrix[i][j]).append(" ");
                }
                transString.append("\n");
            }
            outputTrans.setText(transString.toString());
            outputRank.setText(Integer.toString(rank));
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
