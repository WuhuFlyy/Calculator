package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author 罗孝俊
 * @Description: UI模块的全局变量
 * @date 2023/11/17 21:34
 */
public class UIValues {
    public static final JFrame window = new JFrame("Calculation Tool");
    public static final String FONT_NAME = "Consolas";
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 900;
    public static final int MARGIN_X = 20;
    public static final int MARGIN_Y = 55;

    public static int MARGIN_X_RIGHT = WINDOW_WIDTH - MARGIN_X;
    public static int MARGIN_Y_DOWN = WINDOW_HEIGHT - MARGIN_Y;
    public static final int BUTTON_PANEL_WIDTH = 600;
    public static final int BUTTON_PANEL_HEIGHT = 400;
    public static final String POSITIVE_INFINITY = "+Infinity";
    public static final String NEGATIVE_INFINITY = "-Infinity";
    public static final String ERROR_MATH = "Math Error";
    public static final String ZERO_REGEX = "[-]?[0]*";
    public static final String NUMBER_REGEX = "([-]?\\d+[.]\\d*)|(\\d+)|(-\\d+)";
    public static final String FRACTION_REGEX = "[-]?\\d+[/]\\d+";
    public static final String INTEGER_REGEX = "[-]?\\d+";
    public static final String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";

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
}
