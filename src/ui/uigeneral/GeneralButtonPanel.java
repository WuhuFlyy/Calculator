package ui.uigeneral;

import ui.CalculatorUI;
import ui.uiprobabilitystatistics.ProbabilityStatisticGeneralUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 主页面的按钮板
 * @date 2023/11/26 10:05
 */
public class GeneralButtonPanel extends JPanel{
    public JButton btnCalculator;
    public JButton btnProbability;
    public JButton btnMatrix;
    public JButton btnGraphics;
    public GeneralButtonPanel(){
        setLayout(new GridLayout(4, 1, 0, 40));
        initButton();
        add(btnCalculator);
        add(btnProbability);
        add(btnMatrix);
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

        btnMatrix = createButton("矩阵运算(开发中)");

        btnGraphics = createButton("函数图像绘制(开发中)");
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
