package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 按钮面板类，提供一个唯一的按钮面板实例
 * @date 2023/11/17 21:33
 */
public class ButtonPanel extends JPanel{
    private static ButtonPanel buttonPanel;
    public JTextComponent inputScreen, outputScreen;
    public JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint;
    public JButton btnC, btnBack, btnMod, btnDiv, btnMul,  btnSub, btnAdd, btnSquare,  btnEqual;
    public JButton btnAtan,  btnReverse, btnSin, btnCos, btnTan, btnLog, btnFac;
    public JButton[] btnEmpty;
    public String typedValue;
    private char selectedOperator = ' ';
    // For calculate with Opt != (=)
    private boolean go = true;
    // Connect numbers in display
    private boolean addToDisplay = true;
    //Denote which mode of panel is it now
    public int nowMode;

    /**
     * @Description  构造ButtonPanel类
     * @param inputScreen 用户输入栏的JTextField
     * @param outputScreen 输出栏JTextField
     * @author 罗孝俊
     * @date 2023/11/17 23:00
    **/
    private ButtonPanel(JTextComponent inputScreen, JTextComponent outputScreen){
        setLayout(new GridLayout(5, 6, 10, 30));
        typedValue = "0";
        this.inputScreen = inputScreen;
        this.outputScreen = outputScreen;
        initButton();
        add(btnC);
        add(btnBack);
        add(btnMod);
        add(btnDiv);
        add(btnReverse);
        add(btnFac);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btnMul);
        add(btnAtan);
        add(btnLog);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btnSub);
        add(btnCos);
        add(btnEmpty[0]);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btnAdd);
        add(btnSin);
        add(btnEmpty[1]);
        add(btnPoint);
        add(btn0);
        add(btnEqual);
        add(btnSquare);
        add(btnTan);
        add(btnEmpty[2]);
    }

    /**
     * @Description
     * @param inputScreen 输入栏的JTextComponent
     * @param outputScreen  输出栏的JTextComponent
     * @return ui.ButtonPanel
     * @author 罗孝俊
     * @date 2023/11/26 9:54
    **/
    public static ButtonPanel getButtonPanel(JTextComponent inputScreen, JTextComponent outputScreen){
        if(inputScreen == null || outputScreen == null){
            return null;
        }
        if(buttonPanel == null){
            buttonPanel = new ButtonPanel(inputScreen, outputScreen);
        }else{
            buttonPanel.initParameter();
            buttonPanel.setTextComponent(inputScreen, outputScreen);
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
    public void setTextComponent(JTextComponent inputScreen, JTextComponent outputScreen){
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
    public static JButton createButton(String label) {
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
            inputScreen.setText("0");
            selectedOperator = ' ';
            typedValue = "0";
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
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
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
            if(nowMode == 2){
                if(!Pattern.matches(INTEGER_REGEX, inputScreen.getText()))
                    return;
                if(addToDisplay){
                    inputScreen.setText(inputScreen.getText() + "/");
                }
                go = true;
            }else if(nowMode == 0){
                if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                    return;
                if(go){
                    typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                    if(typedValue.matches(NUMBER_REGEX)){
                        inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                        outputScreen.setText(inputScreen.getText());
                    }else{
                        inputScreen.setText(typedValue);
                    }
                    selectedOperator = '/';
                    go = false;
                    addToDisplay = false;
                }else{
                    selectedOperator = '/';
                }
            }
        });

        btnMul = createButton("*");
        btnMul.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
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
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
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
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
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
                if (!inputScreen.getText().contains(".") && !inputScreen.getText().contains("/")) {
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
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("0");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "0");
                    }else{
                        inputScreen.setText("0");
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("1");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "1");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-1");
                        }else{
                            inputScreen.setText("1");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("2");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "2");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-2");
                        }else{
                            inputScreen.setText("2");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("3");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "3");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-3");
                        }else{
                            inputScreen.setText("3");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("4");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "4");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-4");
                        }else{
                            inputScreen.setText("4");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("5");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "5");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-5");
                        }else{
                            inputScreen.setText("5");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("6");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "6");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-6");
                        }else{
                            inputScreen.setText("6");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("7");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "7");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-7");
                        }else{
                            inputScreen.setText("7");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("8");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "8");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-8");
                        }else{
                            inputScreen.setText("8");
                        }
                    }
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
                if(inputScreen.getText().isEmpty()){
                    inputScreen.setText("9");
                }else{
                    if(!Pattern.matches(ZERO_REGEX, inputScreen.getText())){
                        inputScreen.setText(inputScreen.getText() + "9");
                    }else{

                        if(inputScreen.getText().charAt(0) == '-'){
                            inputScreen.setText("-9");
                        }else{
                            inputScreen.setText("9");
                        }
                    }
                }
            }else{
                inputScreen.setText("9");
                addToDisplay = true;
            }
            go = true;
        });

        btnEqual = createButton("=");
        btnEqual.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if (go) {
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
                }
                selectedOperator = '=';
                addToDisplay = false;
            }
        });

        btnAtan = createButton("atan");
        btnAtan.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "atan");
            if(typedValue.matches(NUMBER_REGEX)){
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                outputScreen.setText(inputScreen.getText());
            }else{
                inputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnReverse = createButton("+/-");
        btnReverse.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;
            StringBuilder sb = new StringBuilder(inputScreen.getText());
            if(sb.charAt(0) != '-'){
                sb.insert(0, '-');
            }else{
                sb.deleteCharAt(0);
            }
            inputScreen.setText(sb.toString());
        });

        btnTan = createButton("tan");
        btnTan.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "tan");
            if(typedValue.matches(NUMBER_REGEX)){
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                outputScreen.setText(inputScreen.getText());
            }else{
                inputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnCos = createButton("cos");
        btnCos.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "cos");
            if(typedValue.matches(NUMBER_REGEX)){
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                outputScreen.setText(inputScreen.getText());
            }else{
                inputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnSin = createButton("sin");
        btnSin.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "sin");
            if(typedValue.matches(NUMBER_REGEX)){
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                outputScreen.setText(inputScreen.getText());
            }else{
                inputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnFac = createButton("!");
        btnFac.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            typedValue = CalculatorUI.calculate(inputScreen.getText(), "!");
            if(typedValue.matches(NUMBER_REGEX)){
                inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                outputScreen.setText(inputScreen.getText());
            }else{
                inputScreen.setText(typedValue);
            }
            go = true;
            addToDisplay = false;
        });

        btnLog = createButton("log");
        btnLog.addActionListener(event -> {
            if (!Pattern.matches(NUMBER_REGEX, inputScreen.getText()) && !Pattern.matches(FRACTION_REGEX, inputScreen.getText()))
                return;

            if(go){
                typedValue = CalculatorUI.calculate(typedValue, inputScreen.getText(), selectedOperator);
                if(typedValue.matches(NUMBER_REGEX)){
                    inputScreen.setText(new BigDecimal(typedValue).toPlainString());
                    outputScreen.setText(inputScreen.getText());
                }else{
                    inputScreen.setText(typedValue);
                }
                selectedOperator = 'l';
                go = false;
                addToDisplay = false;
            }else{
                selectedOperator = 'l';
            }
        });

        btnEmpty = new JButton[4];
        for(int i = 0; i < 3; i++){
            btnEmpty[i] = createButton("");
            btnEmpty[i].setVisible(false);
        }
    }

    /**
     * @Description   初始化控制流的参数
     * @author 罗孝俊
     * @date 2023/11/27 10:53
    **/
    public void initParameter(){
        selectedOperator = ' ';
        go = true;
        addToDisplay = true;
    }
}