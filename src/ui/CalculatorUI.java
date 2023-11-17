package ui;

import javax.swing.*;
import java.awt.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 计算器模块的UI
 * @date 2023/11/17 19:44
 */
public class CalculatorUI {
    public JFrame window;

    public JTextField inputScreen, outputScreen;

    public ButtonPanel buttonPanel;

    /**
     * @Description 计算器模块的UI构造方法，调用后直接生成计算器模块（暂时没把JFrame窗体提出来单独建立）
     *              目前能够禁止用户「输入连续的符号」和「以运算符或小数点结尾」的情况
     * @author 罗孝俊
     * @date 2023/11/17 23:54
    **/
    public CalculatorUI(){
        //JFrame.setDefaultLookAndFeelDecorated(true);

        window = new JFrame("Calculator");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);

        initInputScreen();
        initOutScreen();
        buttonPanel = ButtonPanel.getButtonPanel(inputScreen, outputScreen);
        buttonPanel.setBounds(MARGIN_X, MARGIN_Y + 160, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        window.add(buttonPanel);

        window.setLayout(null);
        window.setResizable(false);
        inputScreen.setFocusable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * @Description 初始化计算器的输入栏
     * @author 罗孝俊
     * @date 2023/11/17 20:14
    **/
    private void initInputScreen() {
        inputScreen = new JTextField("0");
        inputScreen.setBounds(MARGIN_X, MARGIN_Y, 350, 70);
        inputScreen.setEditable(false);
        inputScreen.setFocusable(true);
        inputScreen.setBackground(Color.GRAY);
        inputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        window.add(inputScreen);
    }

    /**
     * @Description 初始化计算器输出栏
     * @author 罗孝俊
     * @date 2023/11/17 23:11
    **/
    private void initOutScreen() {
        outputScreen = new JTextField("0");
        outputScreen.setBounds(MARGIN_X, MARGIN_Y + 70, 350, 70);
        outputScreen.setEditable(false);
        outputScreen.setFocusable(true);
        //outputScreen.setBackground(Color.WHITE);
        outputScreen.setFont(new Font(FONT_NAME, Font.PLAIN, 33));
        window.add(outputScreen);
    }
}
