package ui.uigeneral;

import javax.swing.*;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 主界面的UI
 * @date 2023/11/26 9:52
 */
public class GeneralUI {
    public GeneralButtonPanel generalButtonPanel;
    /**
     * @Description 总界面构造方法
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
        generalButtonPanel.setBounds(MARGIN_X + 340, MARGIN_Y, PANEL_WIDTH, PANEL_HEIGHT);
        window.add(generalButtonPanel);
        window.setVisible(true);
    }
}