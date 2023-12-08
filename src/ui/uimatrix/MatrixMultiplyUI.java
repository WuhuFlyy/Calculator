package ui.uimatrix;

import static ui.UIValues.*;

import javax.swing.*;

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
    public MatrixMultiplyUI(){
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
        paneMatrix1.setBounds(MARGIN_X, MARGIN_Y + 365, 300, 160);
        initInput(inputRow2, paneRow2, MARGIN_X + 500, MARGIN_Y + 55);
        initInput(inputColumn2, paneColumn2, MARGIN_X + 500, MARGIN_Y + 185);
        initInput(inputMatrix2, paneMatrix2, MARGIN_X + 500, MARGIN_Y + 315);
    }
}
