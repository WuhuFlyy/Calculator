package ui.uigeneral;

import ui.CalculatorUI;
import ui.uiequation.EquationGeneralUI;
import ui.uifunctiongraphic.FunctionGraphicUI;
import ui.uimatrix.MatrixGeneralUI;
import ui.uiprobability.ProbabilityStatisticGeneralUI;
import static ui.UIValues.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 主页面的按钮板
 * @date 2023/11/26 10:05
 */
public class GeneralButtonPanel extends JPanel{
    public JButton btnCalculator, btnProbability, btnMatrix, btnGraphics, btnEquation;
    public JLabel labelNotice;
    public GeneralButtonPanel(){
        setLayout(new GridLayout(6, 1, 0, 40));
        initButton();
        labelNotice = new JLabel("<html><body><p align=\\\"center\\\">输入多个数据时<br/>请用空白符分隔</p></body></html>");
//        labelNotice.setBounds(MARGIN_X, MARGIN_Y + 100, 500, 50);
        labelNotice.setFont(new Font("黑体", Font.BOLD, 33));
        add(labelNotice);
        add(btnCalculator);
        add(btnProbability);
        add(btnMatrix);
        add(btnEquation);
        add(btnGraphics);
    }
    private void initButton(){
        btnCalculator = createButton("计算器");
        btnCalculator.addActionListener(event ->{
            new CalculatorUI();
            this.setVisible(false);
        });

        btnProbability = createButton("概率统计");
        btnProbability.addActionListener(event ->{
            new ProbabilityStatisticGeneralUI();
            this.setVisible(false);
        });

        btnMatrix = createButton("矩阵运算");
        btnMatrix.addActionListener(event -> {
            new MatrixGeneralUI();
            this.setVisible(false);
        });

        btnEquation = createButton("解方程");
        btnEquation.addActionListener(event -> {
            new EquationGeneralUI();
            this.setVisible(false);
        });
        btnGraphics = createButton("函数图像绘制");
        btnGraphics.addActionListener(event ->{
            new FunctionGraphicUI();
            this.setVisible(false);
        });
    }

    public JButton createButton(String label) {
        JButton btn = new JButton(label);
        //btn.setBounds(x, y, UIValues.BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setFont(new Font("宋体", Font.BOLD, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        return btn;
    }
}