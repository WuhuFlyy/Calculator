package ui;

import javax.swing.*;
import java.awt.*;

import modules.basic.*;
import ui.uigeneral.GeneralUI;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 计算器模块的UI
 * @date 2023/11/17 19:44
 */
public class CalculatorUI {
//    public JFrame window;

    public JTextField inputScreen, outputScreen;
    public JScrollPane inputPane, outputPane;

    public ButtonPanel buttonPanel;

    public JButton btnBack;

    /**
     * @Description 计算器模块的UI构造方法，调用后直接生成计算器模块
     * @author 罗孝俊
     * @date 2023/11/17 23:54
    **/
    public CalculatorUI(){
        initInputScreen();
        initOutScreen();
        buttonPanel = ButtonPanel.getButtonPanel(inputScreen, outputScreen, 0);
        buttonPanel.setBounds(MARGIN_X, MARGIN_Y + 160, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        buttonPanel.setVisible(true);
        window.add(buttonPanel);

        btnBack = ButtonPanel.createButton("<");
        btnBack.setBounds(MARGIN_X, 10, 70, 40);
        btnBack.addActionListener(event ->{
            buttonPanel.setVisible(false);
            inputScreen.setVisible(false);
            inputPane.setVisible(false);
            outputScreen.setVisible(false);
            outputPane.setVisible(false);
            new GeneralUI();
            btnBack.setVisible(false);
        });
        window.add(btnBack);
        btnBack.setVisible(true);
        btnBack.repaint();

        window.setVisible(true);
    }

    /**
     * @Description 初始化计算器的输入栏
     * @author 罗孝俊
     * @date 2023/11/17 20:14
    **/
    private void initInputScreen() {
        inputScreen = new JTextField("0");
        inputScreen.setBounds(MARGIN_X, MARGIN_Y, 500, 70);
        inputScreen.setEditable(false);
        inputScreen.setFocusable(true);
        inputScreen.setBackground(Color.GRAY);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        inputPane = new JScrollPane(inputScreen, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inputPane.setBounds(MARGIN_X, MARGIN_Y, 500, 70);
        inputPane.setBackground(Color.GRAY);
        window.add(inputPane);
        inputScreen.setVisible(true);
        inputPane.setVisible(true);
    }

    /**
     * @Description 初始化计算器输出栏
     * @author 罗孝俊
     * @date 2023/11/17 23:11
    **/
    private void initOutScreen() {
        outputScreen = new JTextField("0");
        outputScreen.setBounds(MARGIN_X, MARGIN_Y + 70, 500, 70);
        outputScreen.setEditable(false);
        outputScreen.setFocusable(true);
        //outputScreen.setBackground(Color.WHITE);
        outputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        outputPane = new JScrollPane(outputScreen, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPane.setBounds(MARGIN_X, MARGIN_Y + 70, 500, 70);
        window.add(outputPane);
        outputScreen.setVisible(true);
        outputPane.setVisible(true);
    }

    /**
     * @Description  计算器二元运算
     * @param firstNumber   运算符左值
     * @param secondNumber  运算符右值
     * @param operator  运算符类型
     * @return java.lang.String 结果
     * @author 罗孝俊
     * @date 2023/11/22 16:34
    **/
    public static String calculate(String firstNumber, String secondNumber, char operator){
        switch (operator){
            case '+':
                return Operation.add(firstNumber, secondNumber);
            case '-':
                return Operation.subtract(firstNumber, secondNumber);
            case '*':
                return Operation.multiply(firstNumber, secondNumber);
            case '/':
                if(secondNumber.equals("0")){
                    if(firstNumber.charAt(0) != '-'){
                        return POSITIVE_INFINITY;
                    }else{
                        return NEGATIVE_INFINITY;
                    }
                }
                return Operation.divide(firstNumber, secondNumber);
            case '%':
                if(secondNumber.equals("0")){
                    return ERROR_MATH;
                }
                return Operation.remainder(firstNumber, secondNumber);
            case '^':
                if(firstNumber.equals("0") && secondNumber.equals("0")){
                    return ERROR_MATH;
                }
                return Operation.pow(firstNumber, secondNumber);
            default:
                return secondNumber;
        }
    }
    /**
     * @Description  计算器一元运算
     * @param number    运算数
     * @param operator  运算符，注意用的是String类型
     * @return java.lang.String
     * @author 罗孝俊
     * @date 2023/11/22 20:39
    **/
    public static String calculate(String number, String operator){
        if(operator.equals("atan")){
//            Operation_2 op = new Operation_2(10);
//            return op.atan(number);
        }

        return number;
    }
}
