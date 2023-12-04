package ui.uiprobability.panels;

import ui.UIValues;
import ui.uiprobability.BayesianUI;
import ui.uiprobability.ClassicalUI;
import ui.uiprobability.ConditionalUI;
import ui.uiprobability.IndependenceUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 概率统计界面主菜单按钮板
 * @date 2023/11/26 21:02
 */
public class ProbabilityStatisticGeneralButtonPanel extends JPanel{
    public JButton btnClassical;
    public JButton btnBack;
    public JButton btnConditional;
    public JButton btnIndependence;

    public JButton btnBayesian;

    /**
     * @Description   概统主界面按钮板构造
     * @author 罗孝俊
     * @date 2023/11/27 8:59
    **/
    public ProbabilityStatisticGeneralButtonPanel(JButton btnBack){
        this.btnBack = btnBack;
        setLayout(new GridLayout(4, 1, 0, 60));
        initButton();
        add(btnClassical);
        add(btnConditional);
        add(btnIndependence);
//        add(btnTotalProbability);
        add(btnBayesian);
        //add(btnBinomialDistribution);
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
//        btnTotalProbability = UIValues.createButton("全概率", "宋体");
        btnBayesian = UIValues.createButton("贝叶斯\n&全概率", "宋体");
        btnBayesian.addActionListener(event -> {
            new BayesianUI();
            btnBack.setVisible(false);
            this.setVisible(false);
        });
        //btnBinomialDistribution = UIValues.createButton("二项分布", "宋体");
    }
}