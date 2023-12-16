package ui.uiequation;

import ui.uigeneral.GeneralUI;

import static ui.UIValues.*;

import javax.swing.*;

/**
 * @author 罗孝俊
 * @Description: 解方程主菜单UI
 * @date 2023/12/8 23:40
 */
public class EquationGeneralUI {
    public JButton btnBack;
    public EquationGeneralPanel equationGeneralPanel;

    /**
     * @Description 界面构造
     * @author 罗孝俊
     * @date 2023/12/8 23:51
    **/
    public EquationGeneralUI(){
        btnBack = createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            equationGeneralPanel.setVisible(false);
            new GeneralUI();
            btnBack.setVisible(false);
        });

        equationGeneralPanel = new EquationGeneralPanel(btnBack);
        equationGeneralPanel.setBounds(MARGIN_X + 340, MARGIN_Y, PANEL_WIDTH, PANEL_HEIGHT);
        window.add(equationGeneralPanel);
        inputAccuracy.setVisible(false);
        labelAccuracy.setVisible(false);
    }
}
