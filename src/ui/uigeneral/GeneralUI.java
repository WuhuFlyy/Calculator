package ui.uigeneral;

import javax.swing.*;

import static ui.UIValues.*;
import static ui.UIValues.window;

/**
 * @author 罗孝俊
 * @Description: 主界面的UI
 * @date 2023/11/26 9:52
 */
public class GeneralUI {
    private int GENERAL_PANEL_WIDTH = 300;
    private int GENERAL_PANEL_HEIGHT = 580;
    public GeneralButtonPanel generalButtonPanel;
    /**
     * @Description 界面构造方法
     * @author 罗孝俊
     * @date 2023/11/27 8:59
    **/
    public GeneralUI(){
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generalButtonPanel = new GeneralButtonPanel();
        generalButtonPanel.setBounds(MARGIN_X + 165, MARGIN_Y, GENERAL_PANEL_WIDTH, GENERAL_PANEL_HEIGHT);
        window.add(generalButtonPanel);
        window.setVisible(true);
    }
}
//1120