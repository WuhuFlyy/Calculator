package ui.uiprobabilitystatistics.panels;

import ui.UIValues;
import ui.uiprobabilitystatistics.ClassicalUI;
import ui.uiprobabilitystatistics.ProbabilityStatisticGeneralUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author 罗孝俊
 * @Description: 概率统计界面主菜单按钮板
 * @date 2023/11/26 21:02
 */
public class ProbabilityStatisticGeneralButtonPanel extends JPanel{
    public JButton btnClassical, btnBack;

    /**
     * @Description   概统主界面按钮板构造
     * @author 罗孝俊
     * @date 2023/11/27 8:59
    **/
    public ProbabilityStatisticGeneralButtonPanel(JButton btnBack){
        this.btnBack = btnBack;
        setLayout(new GridLayout(6, 1, 0, 40));
        initButton();
        add(btnClassical);
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
    }


}
