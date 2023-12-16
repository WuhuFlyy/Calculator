package ui.uigeneral;

import modules.GlobalVariable;
import modules.basic.Operation_2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;

import static ui.UIValues.*;

/**
 * @author 罗孝俊
 * @Description: 主界面的UI
 * @date 2023/11/26 9:52
 */
public class GeneralUI {
    public GeneralButtonPanel generalButtonPanel;
    /**
     * @Description 总界面构造方法，初始化window和精度输入
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

        inputAccuracy.setEditable(true);
        inputAccuracy.setFont(new Font(FONT_NAME, Font.PLAIN, 30));
        inputAccuracy.setBackground(Color.GRAY);
        inputAccuracy.setBounds(MARGIN_X + BUTTON_PANEL_WIDTH - 70, 10, 70, 40);
        inputAccuracy.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                if(!inputAccuracy.getText().matches(POSITIVE_INTEGER_REGEX)){
                    JOptionPane.showMessageDialog(null, "请输入一个正整数精度", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    GlobalVariable.decimalScale = Integer.parseInt(inputAccuracy.getText());
                    Operation_2.accuracy = GlobalVariable.decimalScale + 2;
                    Operation_2.accuracyNum = BigDecimal.ONE.divide(BigDecimal.TEN.pow(Operation_2.accuracy));
                    if(GlobalVariable.decimalScale >= 50){
                        JOptionPane.showMessageDialog(null, "注意：精度太高可能会导致输出难以显示", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        labelAccuracy.setBounds(MARGIN_X + BUTTON_PANEL_WIDTH - 160, 10, 100, 40);
        labelAccuracy.setFont(new Font("宋体", Font.PLAIN, 28));
        window.add(inputAccuracy);
        window.add(labelAccuracy);
        window.setVisible(true);
        inputAccuracy.setVisible(true);
        labelAccuracy.setVisible(true);
    }
}