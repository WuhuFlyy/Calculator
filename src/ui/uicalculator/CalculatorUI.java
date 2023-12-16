package ui.uicalculator;

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
    private static String ans;
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
        window.setSize(MARGIN_X + BUTTON_PANEL_WIDTH + MARGIN_X, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        initInputScreen();
        initOutScreen();
        buttonPanel = ButtonPanel.getButtonPanel(inputScreen, outputScreen);
        buttonPanel.setBounds(MARGIN_X, MARGIN_Y_DOWN - BUTTON_PANEL_HEIGHT, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
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
        btnBack.repaint();
    }

    /**
     * @Description 初始化计算器的输入栏
     * @author 罗孝俊
     * @date 2023/11/17 20:14
    **/
    private void initInputScreen() {
        inputScreen = new JTextField("0");
        inputScreen.setBounds(MARGIN_X, MARGIN_Y, BUTTON_PANEL_WIDTH, 70);
        inputScreen.setEditable(false);
        inputScreen.setFocusable(true);
        inputScreen.setBackground(Color.GRAY);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        inputPane = new JScrollPane(inputScreen, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inputPane.setBounds(MARGIN_X, MARGIN_Y, BUTTON_PANEL_WIDTH, 70);
        inputPane.setBackground(Color.GRAY);
        window.add(inputPane);
    }

    /**
     * @Description 初始化计算器输出栏
     * @author 罗孝俊
     * @date 2023/11/17 23:11
    **/
    private void initOutScreen() {
        outputScreen = new JTextField("0");
        outputScreen.setBounds(MARGIN_X, MARGIN_Y + 70, BUTTON_PANEL_WIDTH, 70);
        outputScreen.setEditable(false);
        outputScreen.setFocusable(true);
        outputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        outputPane = new JScrollPane(outputScreen, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPane.setBounds(MARGIN_X, MARGIN_Y + 70, BUTTON_PANEL_WIDTH, 70);
        window.add(outputPane);
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
            case 'l':
                try{
                    ans = OperationExtra.getLog(firstNumber, secondNumber);
                }catch (ArithmeticException e){
                    ans = ERROR_MATH;
                }
                return ans;
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
        switch (operator) {
            case "atan" -> ans = OperationExtra.getArctan(number);
            case "tan" -> {
                try {
                    ans = OperationExtra.getTan(number);
                } catch (ArithmeticException e) {
                    ans = ERROR_MATH;
                }
            }
            case "cos" -> ans = OperationExtra.getCos(number);
            case "sin" -> ans = OperationExtra.getSin(number);
            case "!" -> {
                if (!number.matches(INTEGER_REGEX) || number.charAt(0) == '-') {
                    ans = ERROR_MATH;
                } else {
                    ans = Operation.calFactorial(number);
                }
            }
            default -> ans = number;
        }
        return ans;
    }
}