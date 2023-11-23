package ui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import modules.basic.Operation;
import ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 按钮面板类，提供一个唯一的按钮面板实例
 * @date 2023/11/17 21:33
 */
public class ButtonPanel extends JPanel{

    public static final String ZERO_REGEX = "[0]*";
    public static final String NUMBER_REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)";
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
    public JButton btnAtan;
    public JButton btnReverse;
    public JButton[] btnEmpty;
    public String typedValue;
    private char selectedOperator = ' ';
    private boolean go = true; // For calculate with Opt != (=)
    private boolean addToDisplay = true; // Connect numbers in display

    /**
     * @Description  构造ButtonPanel类
     * @param inputScreen 用户输入栏的JTextField
     * @param outputScreen 输出栏JTextField
     * @author 罗孝俊
     * @date 2023/11/17 23:00
    **/
    private ButtonPanel(JTextField inputScreen, JTextField outputScreen){
        //setBounds(UIValues.MARGIN_X, UIValues.MARGIN_Y, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        setLayout(new GridLayout(5, 5, 10, 30));
        typedValue = "0";
        this.inputScreen = inputScreen;
        this.outputScreen = outputScreen;
        initButton();
        add(btnC);
        add(btnBack);
        add(btnMod);
        add(btnDiv);
        add(btnReverse);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btnMul);
        add(btnAtan);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btnSub);
        add(btnEmpty[1]);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btnAdd);
        add(btnEmpty[2]);
        add(btnPoint);
        add(btn0);
        add(btnEqual);
        add(btnSquare);
        add(btnEmpty[3]);
//        add(btnAtan);
    }

    /**
     * @Description  单实例模式构造ButtonPanel
     * @param inputScreen 用户输入栏的JTextField
     * @param outputScreen 输出栏TextField
     * @return ui.ButtonPanel  按钮Panel
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
        btn.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
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
            if(btnC.getLabel().equals("C")){
                inputScreen.setText("0");
                typedValue = "0";
                if(selectedOperator != ' '){
                    btnC.setLabel("CE");
                }
            }else{
                selectedOperator = ' ';
                outputScreen.setText("0");
                btnC.setLabel("C");
            }

        });

        btnBack = createButton("<-");
        btnBack.addActionListener(event -> {
            StringBuilder text = new StringBuilder(inputScreen.getText());
            text.deleteCharAt(text.length() - 1);   //error maybe
            if(text.isEmpty()){
                inputScreen.setText("0");
            }else{
                inputScreen.setText(text.toString());
            }
        });

        btnMod = createButton("%");
        btnMod.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '%';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '%';
            }
        });

        btnDiv = createButton("/");
        btnDiv.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '/';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '/';
            }
        });

        btnMul = createButton("*");
        btnMul.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '*';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '*';
            }
        });

        btnAdd = createButton("+");
        btnAdd.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '+';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '+';
            }
        });

        btnSub = createButton("-");
        btnSub.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '-';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '-';
            }
        });

        btnPoint = createButton(".");
        btnPoint.addActionListener(event -> {
            if (addToDisplay) {
                if (!inputScreen.getText().contains(".")) {
                    inputScreen.setText(inputScreen.getText() + ".");
                }
            } else {
                inputScreen.setText("0.");
                addToDisplay = true;
            }
            go = true;
        });

        btnSquare = createButton("^");
        btnSquare.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '^';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = '^';
            }
        });

        btn0 = createButton("0");
        btn0.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "0");
                }else{
                    inputScreen.setText("0");
                }
            }else{
                inputScreen.setText("0");
                addToDisplay = true;
            }
            go = true;
        });

        btn1 = createButton("1");
        btn1.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "1");
                }else{
                    inputScreen.setText("1");
                }
            }else{
                inputScreen.setText("1");
                addToDisplay = true;
            }
            go = true;
        });

        btn2 = createButton("2");
        btn2.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "2");
                }else{
                    inputScreen.setText("2");
                }
            }else{
                inputScreen.setText("2");
                addToDisplay = true;
            }
            go = true;
        });

        btn3 = createButton("3");
        btn3.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "3");
                }else{
                    inputScreen.setText("3");
                }
            }else{
                inputScreen.setText("3");
                addToDisplay = true;
            }
            go = true;
        });

        btn4 = createButton("4");
        btn4.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "4");
                }else{
                    inputScreen.setText("4");
                }
            }else{
                inputScreen.setText("4");
                addToDisplay = true;
            }
            go = true;
        });

        btn5 = createButton("5");
        btn5.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "5");
                }else{
                    inputScreen.setText("5");
                }
            }else{
                inputScreen.setText("5");
                addToDisplay = true;
            }
            go = true;
        });

        btn6 = createButton("6");
        btn6.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "6");
                }else{
                    inputScreen.setText("6");
                }
            }else{
                inputScreen.setText("6");
                addToDisplay = true;
            }
            go = true;
        });

        btn7 = createButton("7");
        btn7.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "7");
                }else{
                    inputScreen.setText("7");
                }
            }else{
                inputScreen.setText("7");
                addToDisplay = true;
            }
            go = true;
        });

        btn8 = createButton("8");
        btn8.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "8");
                }else{
                    inputScreen.setText("8");
                }
            }else{
                inputScreen.setText("8");
                addToDisplay = true;
            }
            go = true;
        });

        btn9 = createButton("9");
        btn9.addActionListener(event -> {
            if(addToDisplay){
                if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                    inputScreen.setText(inputScreen.getText() + "9");
                }else{
                    inputScreen.setText("9");
                }
            }else{
                inputScreen.setText("9");
                addToDisplay = true;
            }
            go = true;
        });

        btnEqual = createButton("=");
        btnEqual.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                if(typedValue.matches(NUMBER_REGEX)){
                    outputScreen.setText(typedValue);
                }
                selectedOperator = '=';
                addToDisplay = false;
            }
        });

        btnAtan = createButton("atan");
        btnAtan.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "atan");
            inputScreen.setText(new BigDecimal(typedValue).toPlainString());
            if(typedValue.matches(NUMBER_REGEX)){
                outputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnReverse = createButton("+/-");
        btnReverse.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()))
                return;
            StringBuilder sb = new StringBuilder(inputScreen.getText());
            if(sb.charAt(0) != '-'){
                sb.insert(0, '-');
            }else{
                sb.deleteCharAt(0);
            }
            inputScreen.setText(sb.toString());
        });
        btnEmpty = new JButton[4];
        for(int i = 0; i < 4; i++){
            btnEmpty[i] = createButton("");
        }
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