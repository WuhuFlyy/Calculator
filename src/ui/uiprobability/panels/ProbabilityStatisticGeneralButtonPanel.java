package ui.uiprobability.panels;

import ui.UIValues;
import ui.uiprobability.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 概率统计界面主菜单按钮板
 * @date 2023/11/26 21:02
 */
public class ProbabilityStatisticGeneralButtonPanel extends JPanel{
    public JButton btnClassical, btnConditional, btnIndependence, btnBayesian, btnBinomialDistribution, btnLinearRegression, btnBack;

    /**
     * @Description   概统主界面按钮板构造
     * @param btnBack UI界面传入的返回按钮
     * @author 罗孝俊
     * @date 2023/11/27 8:59
    **/
    public ProbabilityStatisticGeneralButtonPanel(JButton btnBack){
        this.btnBack = btnBack;
        setLayout(new GridLayout(6, 1, 0, 40));
        initButton();
        add(btnClassical);
        add(btnConditional);
        add(btnIndependence);
        add(btnBayesian);
        add(btnBinomialDistribution);
        add(btnLinearRegression);
    }

    /**
     * @Description   按钮初始化
     * @author 罗孝俊
     * @date 2023/11/27 9:00
    **/
    private void initButton(){
        btnClassical = UIValues.createButton("古典概型", "宋体");
        btnClassical.addActionListener(event ->{
            new ClassicalUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnConditional = UIValues.createButton("条件概率", "宋体");
        btnConditional.addActionListener(event ->{
            new ConditionalUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnIndependence = UIValues.createButton("独立事件概率", "宋体");
        btnIndependence.addActionListener(event -> {
            new IndependenceUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnBayesian = UIValues.createButton("贝叶斯\n&全概率", "宋体");
        btnBayesian.addActionListener(event -> {
            new BayesianUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });
        btnBinomialDistribution = UIValues.createButton("二项分布", "宋体");
        btnBinomialDistribution.addActionListener(event -> {
            new BinomialUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });

        btnLinearRegression = UIValues.createButton("一元线性回归", "宋体");
        btnLinearRegression.addActionListener(event -> {
            new LinearRegressionUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });
    }
}