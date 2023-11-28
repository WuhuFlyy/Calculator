package ui.uiprobability;

import ui.UIValues;
import ui.uigeneral.GeneralUI;
import ui.uiprobability.panels.ProbabilityStatisticGeneralButtonPanel;

import javax.swing.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 概率统计界面UI
 * @date 2023/11/26 12:15
 */
public class ProbabilityStatisticGeneralUI {
    private int PANEL_WIDTH = 300;
    private int PANEL_HEIGHT = 580;
    public JButton btnBack;
    public ProbabilityStatisticGeneralButtonPanel probabilityStatisticGeneralButtonPanel;
    /**
     * @Description   界面构造方法
     * @author 罗孝俊
     * @date 2023/11/27 8:58
    **/
    public ProbabilityStatisticGeneralUI(){
        btnBack = UIValues.createButton("<", "Consolas");
        UIValues.initBtnBack(btnBack, event -> {
            probabilityStatisticGeneralButtonPanel.setVisible(false);
            new GeneralUI();
            btnBack.setVisible(false);
        });
        probabilityStatisticGeneralButtonPanel = new ProbabilityStatisticGeneralButtonPanel(btnBack);
        probabilityStatisticGeneralButtonPanel.setBounds(MARGIN_X + 165, MARGIN_Y, PANEL_WIDTH, PANEL_HEIGHT);
        probabilityStatisticGeneralButtonPanel.setVisible(true);
        window.add(probabilityStatisticGeneralButtonPanel);
    }
}
