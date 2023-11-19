package ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 按钮面板类，提供一个唯一的按钮面板实例
 * @date 2023/11/17 21:33
 */
public class ButtonPanel extends JPanel{

    public static final String ZERO_REGEX = "[0]*";
    public StringBuilder expression;
    private static ButtonPanel buttonPanel;
    public JTextField inputScreen, outputScreen;
    public JButton btnC;
    public JButton btnBack;
    public JButton btnMod;
    public JButton btnDiv;
    public JButton btnMul;
    public JButton btnSub;
    public JButton btnAdd;
    public JButton btnSquare;
    public JButton btn0;
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;
    public JButton btn6;
    public JButton btn7;
    public JButton btn8;
    public JButton btn9;
    public JButton btnPoint;
    public JButton btnEqual;
    public BigDecimal typedValue;

    /***
     * @Description  构造ButtonPanel类
     * @param inputScreen 用户输入栏的JTextField
     * @param outputScreen 输出栏JTextField
     * @author 罗孝俊
     * @date 2023/11/17 23:00
    **/
    private ButtonPanel(JTextField inputScreen, JTextField outputScreen){
        //setBounds(UIValues.MARGIN_X, UIValues.MARGIN_Y, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        setLayout(new GridLayout(5, 4, 10, 30));
        expression = new StringBuilder("0");
        typedValue = new BigDecimal(0);
        this.inputScreen = inputScreen;
        this.outputScreen = outputScreen;
        initButton();
        add(btnC);
        add(btnBack);
        add(btnMod);
        add(btnDiv);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btnMul);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btnSub);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btnAdd);
        add(btnPoint);
        add(btn0);
        add(btnEqual);
        add(btnSquare);
    }

    /**
     * @Description  单实例模式构造ButtonPanel
     * @param inputScreen 用户输入栏的JTextField
     * @param outputScreen 输出栏TextField
     * @return ui.ButtonPanel       
     * @author 罗孝俊
     * @date 2023/11/17 23:01
    **/
    public static ButtonPanel getButtonPanel(JTextField inputScreen, JTextField outputScreen){
        if(buttonPanel == null){
            buttonPanel = new ButtonPanel(inputScreen, outputScreen);
        }else{
            buttonPanel.setTextField(inputScreen, outputScreen);
        }
        return buttonPanel;
    }

    /**
     * @Description  设置ButtonPanel的输入输出栏（不得在构造前使用）
     * @param inputScreen 输入栏
     * @param outputScreen  输出栏
     * @author 罗孝俊
     * @date 2023/11/17 23:12
    **/
    public void setTextField(JTextField inputScreen, JTextField outputScreen){
        buttonPanel.inputScreen = inputScreen;
        buttonPanel.outputScreen = outputScreen;
        this.inputScreen = inputScreen;
        this.outputScreen = outputScreen;
    }

    /**
     * @Description  初始化时新建一个按钮对象
     * @param label 按钮标签
     * @return javax.swing.JButton 生成的按钮对象
     * @author 罗孝俊
     * @date 2023/11/17 19:58
     **/
    public JButton createButton(String label) {
        JButton btn = new JButton(label);
        //btn.setBounds(x, y, UIValues.BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
//        calculatorPanel.add(btn);
        return btn;
    }

    /**
     * @Description 初始化所有按钮，包含点击事件
     * @author 罗孝俊
     * @date 2023/11/17 22:04
    **/
    public void initButton(){
        if(inputScreen == null || outputScreen == null){
            System.out.println("Error: Text field not set");
            return;
        }

        btnC = createButton("C");
        btnC.addActionListener(event -> {
            inputScreen.setText("0");
            expression = new StringBuilder("0");
            typedValue = BigDecimal.ZERO;
        });

        btnBack = createButton("<-");
        btnBack.addActionListener(event -> {
            StringBuilder text = new StringBuilder(inputScreen.getText());
            text.deleteCharAt(text.length() - 1);   //error maybe
            expression.deleteCharAt(expression.length() - 1);
            if(text.isEmpty()){
                inputScreen.setText("0");
                expression = new StringBuilder("0");
            }else{
                inputScreen.setText(text.toString());
            }
        });

        btnMod = createButton("%");
        btnMod.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "%");
                expression.append("%");
            }
        });

        btnDiv = createButton("/");
        btnDiv.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "/");
                expression.append("/");
            }
        });

        btnMul = createButton("*");
        btnMul.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "*");
                expression.append("*");
            }
        });

        btnAdd = createButton("+");
        btnAdd.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "+");
                expression.append("+");
            }
        });

        btnSub = createButton("-");
        btnSub.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "-");
                expression.append("-");
            }
        });

        btnPoint = createButton(".");
        btnPoint.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + ".");
                expression.append(".");
            }
        });

        btnSquare = createButton("^");
        btnSquare.addActionListener(event -> {
            if(!checkOperator(expression)){
                inputScreen.setText(inputScreen.getText() + "^");
                expression.append("^");
            }
        });
        btn0 = createButton("0");
        btn0.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "0");
                expression.append("0");
            }else{
                inputScreen.setText("0");
                expression = new StringBuilder("0");
            }
        });

        btn1 = createButton("1");
        btn1.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "1");
                expression.append("1");
            }else{
                inputScreen.setText("1");
                expression = new StringBuilder("1");
            }
        });

        btn2 = createButton("2");
        btn2.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "2");
                expression.append("2");
            }else{
                inputScreen.setText("2");
                expression = new StringBuilder("2");
            }
        });

        btn3 = createButton("3");
        btn3.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "3");
                expression.append("3");
            }else{
                inputScreen.setText("3");
                expression = new StringBuilder("3");
            }
        });

        btn4 = createButton("4");
        btn4.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "4");
                expression.append("4");
            }else{
                inputScreen.setText("4");
                expression = new StringBuilder("4");
            }
        });

        btn5 = createButton("5");
        btn5.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "5");
                expression.append("5");
            }else{
                inputScreen.setText("5");
                expression = new StringBuilder("5");
            }
        });

        btn6 = createButton("6");
        btn6.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "6");
                expression.append("6");
            }else{
                inputScreen.setText("6");
                expression = new StringBuilder("6");
            }
        });

        btn7 = createButton("7");
        btn7.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "7");
                expression.append("7");
            }else{
                inputScreen.setText("7");
                expression = new StringBuilder("7");
            }
        });

        btn8 = createButton("8");
        btn8.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "8");
                expression.append("8");
            }else{
                inputScreen.setText("8");
                expression = new StringBuilder("8");
            }
        });

        btn9 = createButton("9");
        btn9.addActionListener(event -> {
            if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                inputScreen.setText(inputScreen.getText() + "9");
                expression.append("9");
            }else{
                inputScreen.setText("9");
                expression = new StringBuilder("9");
            }
        });

        btnEqual = createButton("=");
        btnEqual.addActionListener(event -> {
            //test only
            if(checkOperator(expression)){
                outputScreen.setText("Syntax Error");
            }else{
                outputScreen.setText(expression.toString());
            }
        });
    }

    /**
     * @Description  判断StringBuilder对象的最后一个输入是不是运算符或者小数点
     * @param sb StringBuilder对象
     * @return boolean  是则返回True，否则返回false
     * @author 罗孝俊
     * @date 2023/11/18 0:07
    **/
    private boolean checkOperator(StringBuilder sb){
        return sb == null || sb.isEmpty() || sb.charAt(sb.length() - 1) == '/' || sb.charAt(sb.length() - 1) == '*' || sb.charAt(sb.length() - 1) == '+' || sb.charAt(sb.length() - 1) == '-' || sb.charAt(sb.length() - 1) == '%' || sb.charAt(sb.length() - 1) == '.' || sb.charAt(sb.length() - 1) == '^';
    }
}