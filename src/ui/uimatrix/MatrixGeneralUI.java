package ui.uimatrix;

import ui.UIValues;
import ui.uigeneral.GeneralUI;

import javax.swing.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 矩阵运算主菜单UI
 * @date 2023/12/8 13:21
 */
public class MatrixGeneralUI {
    public JButton btnBack;
    public MatrixGeneralPanel matrixGeneralPanel;

    /**
     * @Description 界面构造
     * @author 罗孝俊
     * @date 2023/12/8 13:22
    **/
    public MatrixGeneralUI(){
        btnBack = UIValues.createButton("<", FONT_NAME);
        initBtnBack(btnBack, event -> {
            matrixGeneralPanel.setVisible(false);
            new GeneralUI();
            btnBack.setVisible(false);
        });

        matrixGeneralPanel = new MatrixGeneralPanel(btnBack);
        matrixGeneralPanel.setBounds(MARGIN_X + 340, MARGIN_Y, PANEL_WIDTH, PANEL_HEIGHT);
        window.add(matrixGeneralPanel);

        inputAccuracy.setVisible(false);
        labelAccuracy.setVisible(false);
    }

}
