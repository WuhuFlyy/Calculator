package ui.uifunctiongraphic;

import ui.uigeneral.GeneralUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 函数绘制UI
 * @date 2023/12/3 18:15
 */
public class FunctionGraphicUI implements ItemListener {
    public JLabel labelA, labelB, labelC, labelChoose, labelUnitLength;
    public JTextField textA, textB, textC, textUnitLength;
    public JButton btnDraw, btnBack;
    public JComboBox functionChooseBox;
    public static String[] functions = {"ax^2+bx+c", "ae^bx+c", "a*sin(PIx+b)+c","a*b^x+c","a*x^b+c"};
    public int functionIndex;
    public GraphicPanel graphicPanel = new GraphicPanel();
    /**
     * @Description 初始化函数绘制UI
     * @author 罗孝俊
     * @date 2023/12/3 18:54
    **/
    public FunctionGraphicUI() {
        inputAccuracy.setVisible(false);
        labelAccuracy.setVisible(false);
        initLabel();
        initText();
        initButton();
        initBox();
        graphicPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        graphicPanel.setVisible(true);
        window.add(graphicPanel);
    }

    /**
     * @Description 初始化标签
     * @author 罗孝俊
     * @date 2023/12/3 18:54
    **/
    private void initLabel(){
        labelA = new JLabel("a = ");
        labelB = new JLabel("b = ");
        labelC = new JLabel("c = ");
        labelUnitLength = new JLabel("单位像素:");
        labelChoose = new JLabel("请选择函数类型：");
        labelA.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        labelB.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        labelC.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        labelUnitLength.setFont(new Font("宋体", Font.PLAIN, 20));
        labelChoose.setFont(new Font("宋体", Font.PLAIN, 24));
        labelA.setBounds(MARGIN_X_RIGHT - 150, MARGIN_Y, 75, 30);
        labelB.setBounds(MARGIN_X_RIGHT - 150, MARGIN_Y + 35, 75, 30);
        labelC.setBounds(MARGIN_X_RIGHT - 150, MARGIN_Y + 70, 75, 30);
        labelUnitLength.setBounds(MARGIN_X_RIGHT - 200, MARGIN_Y + 105, 125, 30);
        labelChoose.setBounds(MARGIN_X, MARGIN_Y, 200, 30);
        labelA.setVisible(true);
        labelB.setVisible(true);
        labelC.setVisible(true);
        labelUnitLength.setVisible(true);
        labelChoose.setVisible(true);
        window.add(labelA);
        window.add(labelB);
        window.add(labelC);
        window.add(labelUnitLength);
        window.add(labelChoose);
    }

    /**
     * @Description 初始化文本区域
     * @author 罗孝俊
     * @date 2023/12/3 18:54
    **/
    private void initText(){
        textA = new JTextField("0");
        textB = new JTextField("0");
        textC = new JTextField("0");
        textUnitLength = new JTextField("100");
        textA.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        textB.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        textC.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        textUnitLength.setFont(new Font(FONT_NAME, Font.PLAIN, 24));
        textA.setBounds(MARGIN_X_RIGHT - 75, MARGIN_Y, 75, 30);
        textB.setBounds(MARGIN_X_RIGHT - 75, MARGIN_Y + 35, 75, 30);
        textC.setBounds(MARGIN_X_RIGHT - 75, MARGIN_Y + 70, 75, 30);
        textUnitLength.setBounds(MARGIN_X_RIGHT - 75, MARGIN_Y + 105, 75, 30);
        textA.setVisible(true);
        textB.setVisible(true);
        textC.setVisible(true);
        textUnitLength.setVisible(true);
        window.add(textA);
        window.add(textB);
        window.add(textC);
        window.add(textUnitLength);
    }

    /**
     * @Description 初始化按钮
     * @author 罗孝俊
     * @date 2023/12/3 18:55
    **/
    private void initButton(){
        btnDraw = createButton("draw", FONT_NAME);
        btnDraw.addActionListener(event -> {
            this.paintFn(event);
            window.repaint();
        });
        btnDraw.setBounds(MARGIN_X_RIGHT - 100, MARGIN_Y + 140, 100, 30);
        btnDraw.setVisible(true);
        window.add(btnDraw);

        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            labelA.setVisible(false);
            labelB.setVisible(false);
            labelC.setVisible(false);
            labelUnitLength.setVisible(false);
            labelChoose.setVisible(false);
            textA.setVisible(false);
            textB.setVisible(false);
            textC.setVisible(false);
            textUnitLength.setVisible(false);
            btnDraw.setVisible(false);
            functionChooseBox.setVisible(false);
            graphicPanel.setVisible(false);
            new GeneralUI();
            btnBack.setVisible(false);
        });
    }

    /**
     * @Description 初始化复选框
     * @author 罗孝俊
     * @date 2023/12/3 18:55
     **/
    private void initBox(){
        functionChooseBox = new JComboBox<>(functions);
        functionChooseBox.setBounds(MARGIN_X, MARGIN_Y + 30, 200, 30);
        functionChooseBox.setMaximumRowCount(3);
        functionChooseBox.addItemListener(this);
        window.add(functionChooseBox);
    }

    /**
     * @Description  重载用于复选框的itemStateChanged方法
     * @param e ItemEvent
     * @author 罗孝俊
     * @date 2023/12/3 21:03
    **/
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            functionIndex = functionChooseBox.getSelectedIndex();
        }
    }


    public void paintFn(ActionEvent e){
        if(!textUnitLength.getText().matches(POSITIVE_INTEGER_REGEX)){
            JOptionPane.showMessageDialog(null, "请输入一个正整数的像素精度", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if(!textA.getText().matches(NUMBER_REGEX) || !textB.getText().matches(NUMBER_REGEX) || !textC.getText().matches(NUMBER_REGEX)){
            JOptionPane.showMessageDialog(null, "请输入一个合法的参数", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            graphicPanel.paintFn(Double.parseDouble(textA.getText()), Double.parseDouble(textB.getText()), Double.parseDouble(textC.getText()), Integer.parseInt(textUnitLength.getText()));
        }
    }

    /**
     * @author 罗孝俊
     * @Description: 函数绘制模块
     * @date 2023/12/3 18:15
     */
    class GraphicPanel extends JPanel {
        private double fa, fb, fc;
        //像素精度，越小越精细，显示范围也越小
        private int unitLength = 100;
        int width, height, x, y;

        /**
         * @Description
         * @param a 参数a
         * @param b 参数b
         * @param c 参数c
         * @author 罗孝俊
         * @date 2023/12/3 19:21
         **/
        public void paintFn(double a, double b, double c, int unitLength){
            fa = a;
            fb = b;
            fc = c;
            this.unitLength = unitLength;
        }

        /**
         * @Description 返回x对应的函数值
         * @param x 自变量的值
         * @return double 函数值
         * @author 罗孝俊
         * @date 2023/12/3 19:32
         **/
        public double functionValue(double x){
            switch (functionIndex){
                case 0:
                    return fa * x * x + fb * x + fc;
                case 1:
                    return fa * Math.pow(Math.E, fb * x) + fc;
                case 2:
                    return fa * Math.sin(Math.PI * x + fb) + fc;
                case 3:
                    return fa * Math.pow(fb, x) + fc;
                case 4:
                    return fa * Math.pow(x, fb) + fc;
                default:
                    return 0;
            }
        }

        /**
         * @Description 重载paintComponent函数
         * @param g 图像绘制用的Graphics类，不归用户调用
         * @author 罗孝俊
         * @date 2023/12/3 19:42
        **/
        @Override
        public void paintComponent(Graphics g) {
            width = WINDOW_WIDTH;
            height = WINDOW_HEIGHT;
            x = width / 2;
            y = height / 2;
            this.drawAxes(g);
            this.function(g);
            window.repaint();
        }

        /**
         * @Description  画坐标轴
         * @param g 图像绘制用的Graphics类，不归用户调用
         * @author 罗孝俊
         * @date 2023/12/3 21:02
        **/
        private void drawAxes(Graphics g) {
            g.drawLine(0, y, width, y);
            g.drawLine(x, 0, x, height);
            g.drawString("0",x + 2,y + 12);
            for(int i = 1; i * unitLength < width; i++) {
                //画X轴
                g.drawLine(x + i * unitLength,y-1,x + i * unitLength,y-6);
                g.drawLine(x - i * unitLength, y-1, x - i * unitLength, y-6);
                g.drawString(String.valueOf(i), x + i * unitLength - 3, y + 12);
                g.drawString(String.valueOf(i * -1), x - i * unitLength - 3, y + 12);
                //画Y轴
                g.drawLine(x+1,y + i * unitLength,x+6,y + i * unitLength);
                g.drawLine(x+1,y - i * unitLength,x+6,y - i * unitLength);
                g.drawString(String.valueOf(i), x - 12, y - i * unitLength - 3);
                g.drawString(String.valueOf(i * -1), x - 12, y + i * unitLength - 3);
            }
        }

        /**
         * @Description 画图象
         * @param g 图像绘制用的Graphics类，不归用户调用
         * @author 罗孝俊
         * @date 2023/12/3 19:51
        **/
        public void function(Graphics g) {
            Point2D temp1,temp2;
            //用户看到的坐标值
            double nowX, nowY;
            Graphics2D graphic2D = (Graphics2D)g;
            nowX = -1.0 * x / unitLength;
            //temp1返回面板的实际坐标值（以像素为单位）
            nowY = functionValue(nowX);
            temp1 = new Point2D.Double(this.alterX(nowX * unitLength), this.alterY(nowY * unitLength));
            for(int i = 0 ; i < width; i++){
                nowX = nowX + 1.0 / unitLength;
                nowY = functionValue(nowX);
                if (Math.abs(nowY) < y){
                    temp2 = new Point2D.Double(this.alterX(nowX * unitLength), this.alterY(nowY * unitLength));
                    graphic2D.draw(new Line2D.Double(temp1, temp2));
                    temp1 = temp2;
                }
            }
        }

        /**
         * @Description  新旧坐标转换(X)
         * @param nowX 虚拟坐标X
         * @return double 物理坐标X（以像素点为单位）
         * @author 罗孝俊
         * @date 2023/12/3 20:59
        **/
        private double alterX(double nowX){
            return x + nowX;
        }
        /**
         * @Description 新旧坐标转换(Y)
         * @param nowY 虚拟坐标Y
         * @return double 物理坐标Y
         * @author 罗孝俊
         * @date 2023/12/3 21:01
        **/
        private double alterY(double nowY){
            return -1 * (nowY - y);
        }
    }
}