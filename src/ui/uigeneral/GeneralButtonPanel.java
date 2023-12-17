package ui.uigeneral;

import ui.uicalculator.CalculatorUI;
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
    /**
     * @Description 按钮板构造
     * @author 罗孝俊
     * @date 2023/11/26 11:38
    **/
    public GeneralButtonPanel(){
        setLayout(new GridLayout(5, 1, 0, 20));
        initButton();
        add(btnCalculator);
        add(btnProbability);
        add(btnMatrix);
        add(btnEquation);
        add(btnGraphics);
    }
    /**
     * @Description    初始化按钮
     * @author 罗孝俊
     * @date 2023/11/26 11:39
    **/
    private void initButton(){
        btnCalculator = createButton("计算器");
        btnCalculator.addActionListener(event ->{
            labelNotice.setVisible(false);
            new CalculatorUI();
            this.setVisible(false);
        });

        btnProbability = createButton("概率统计");
        btnProbability.addActionListener(event ->{
            labelNotice.setVisible(false);
            new ProbabilityStatisticGeneralUI();
            this.setVisible(false);
        });

        btnMatrix = createButton("矩阵运算");
        btnMatrix.addActionListener(event -> {
            labelNotice.setVisible(false);
            new MatrixGeneralUI();
            this.setVisible(false);
        });

        btnEquation = createButton("解方程");
        btnEquation.addActionListener(event -> {
            labelNotice.setVisible(false);
            new EquationGeneralUI();
            this.setVisible(false);
        });

        btnGraphics = createButton("函数图像绘制");
        btnGraphics.addActionListener(event ->{
            labelNotice.setVisible(false);
            new FunctionGraphicUI();
            this.setVisible(false);
        });
    }

    /**
     * @Description 构造单个按钮对象
     * @param label 按钮标签
     * @return javax.swing.JButton 返回按钮对象
     * @author 罗孝俊
     * @date 2023/12/17 11:39
    **/
    public JButton createButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("宋体", Font.BOLD, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        return btn;
    }
}