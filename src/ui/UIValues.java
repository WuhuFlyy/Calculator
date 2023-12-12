package ui;


import modules.basic.Fraction;
import modules.basic.Operation;
import modules.matrix.Matrix;
import ui.uimatrix.MatrixPowUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author 罗孝俊
 * @Description: UI模块的全局变量和工具类
 * @date 2023/11/17 21:34
 */
public class UIValues {
    public static final JFrame window = new JFrame("Calculation Tool");
    public static final String FONT_NAME = "Consolas";
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 900;
    public static final int MARGIN_X = 20;
    public static final int MARGIN_Y = 55;
    public static int PANEL_WIDTH = 300;
    public static int PANEL_HEIGHT = 780;
    public static int MARGIN_X_RIGHT = WINDOW_WIDTH - MARGIN_X;
    public static int MARGIN_Y_DOWN = WINDOW_HEIGHT - MARGIN_Y;
    public static final int BUTTON_PANEL_WIDTH = 600;
    public static final int BUTTON_PANEL_HEIGHT = 400;
    public static final String POSITIVE_INFINITY = "+Infinity";
    public static final String NEGATIVE_INFINITY = "-Infinity";
    public static final String ERROR_MATH = "Math Error";
    public static final String ZERO_REGEX = "[-]?[0]*";
    public static final String NUMBER_REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)|([-]?\\d+[E][-]?\\d+)";
    public static final String FRACTION_REGEX = "[-]?\\d+[/]\\d+";
    public static final String INTEGER_REGEX = "[-]?\\d+";
    public static final String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
    public static final String POSITIVE_FRACTION_REGEX = "[1-9]\\d*[/]\\d+";
    public static final String POSITIVE_NUMBER_REGEX = "(\\d+[.]\\d*)|([1-9]\\d*)|([1-9]\\d*[E][-]?\\d+)";


    /**
     * @Description  初始化一个JButton
     * @param label JButton显示的文字
     * @param font  JButton的字体
     * @return javax.swing.JButton  JButton对象
     * @author 罗孝俊
     * @date 2023/11/27 9:21
    **/
    public static JButton createButton(String label, String font) {
        JButton btn = new JButton(label);
        //btn.setBounds(x, y, UIValues.BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font(font, Font.BOLD, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        return btn;
    }

    /**
     * @Description  初始化每一个子界面的back按钮
     * @param btnBack   back按钮
     * @param listener  back按钮的事件监听器
     * @author 罗孝俊
     * @date 2023/11/27 9:31
    **/
    public static void initBtnBack(JButton btnBack, ActionListener listener){
        btnBack.setBounds(MARGIN_X, 10, 70, 40);
        btnBack.addActionListener(listener);
        window.add(btnBack);
        btnBack.setVisible(true);
        btnBack.repaint();
    }

    /**
     * @Description  用于初始化部分界面的输入界面（无buttonPanel介入）
     * @param text  输入界面的JTextComponent
     * @param pane  输入界面的JScrollPane
     * @param positionX 输入界面左上角X值
     * @param positionY 输入界面左上角Y值
     * @author 罗孝俊
     * @date 2023/12/8 14:01
    **/
    public static void initInput(JTextComponent text, JScrollPane pane, int positionX, int positionY) {
        text.setEditable(true);
        text.setBackground(Color.GRAY);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        if (text instanceof JTextArea) {
//            ((JTextArea) text).setLineWrap(true);
            ((JTextArea) text).setWrapStyleWord(true);
        }
        pane.setBounds(positionX, positionY, 200, 60);
        pane.setBackground(Color.GRAY);
        window.add(pane);
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
    public static void initOutput(JTextComponent text, JScrollPane pane, int positionX, int positionY) {
        text.setBounds(positionX, positionY, 300, 50);
        text.setEditable(false);
        text.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        pane.setBounds(positionX, positionY, 300, 50);
        window.add(pane);
    }

    /**
     * @Description 初始化solve按钮
     * @param btnSolve  solve按钮实例
     * @param listener  事件
     * @author 罗孝俊
     * @date 2023/12/8 14:36
    **/
    public static void initButtonSolve(JButton btnSolve, ActionListener listener){
        btnSolve.setBounds(MARGIN_X_RIGHT - 120, MARGIN_Y_DOWN - 40, 120, 40);
        btnSolve.addActionListener(listener);
        window.add(btnSolve);
        btnSolve.repaint();
    }

    /**
     * @Description 根据输入构造矩阵
     * @param row 行数
     * @param column 列数
     * @param inputMatrix 输入矩阵的JTextArea
     * @return modules.matrix.Matrix 矩阵实例
     * @author 罗孝俊
     * @date 2023/12/8 23:16
    **/
    public static Matrix getMatrix(int row, int column, JTextArea inputMatrix){
        String[] elementsString = inputMatrix.getText().split("(\\s)+");
        if(row * column != elementsString.length){
            JOptionPane.showMessageDialog(null, "元素总数：" + elementsString.length + "\n但是row*line：" + row*column, "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Fraction[][] elements = new Fraction[row + 1][column + 1];
        try{
            int k = 0;
            for(int i = 1; i <= row; i++){
                for(int j = 1; j <= column; j++){
                    String str = elementsString[k++];
                    if(str.matches(FRACTION_REGEX)){
                        String[] tmp = str.split("/");
                        elements[i][j] = new Fraction(tmp[0], tmp[1]);
                    }else if(str.matches(NUMBER_REGEX)){
                        elements[i][j] = Operation.toFraction(str);
                    }else{
                        JOptionPane.showMessageDialog(null, "M(" + i + j + ")输入不合法", "Warning", JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                }
            }


            return new Matrix(elements, row, column);
        }catch (ArithmeticException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}
